package personal.wh.tinyspring.beans.factory;

import java.lang.reflect.Method;

import personal.wh.tinyspring.beans.BeanDefinition;
import personal.wh.tinyspring.beans.BeanReference;
import personal.wh.tinyspring.beans.PropertyValue;
import personal.wh.tinyspring.util.StringUtils;

/**
 * 具备自动装配能力的bean工厂
 * 
 * @author Wh
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	/**
	 * 通过setter方法注入属性
	 * @param bean
	 * @param beanDefinition
	 * @throws Exception 
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
		for (PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()) {
			String setter = "set" + StringUtils.capitalize(pv.getName()); // setXxx
			Object value = pv.getValue(); // 可能是简单类型，也可能是BeanReference
			
			Method setterMethod = null; 
			if (value instanceof BeanReference) { // 依赖bean注入
				BeanReference beanRef = (BeanReference) value;
				value = getBean(beanRef.getName()); // 获取bean的同时，进行了实例化
				
				try {
					setterMethod = bean.getClass().getDeclaredMethod(setter, value.getClass());
				} catch (NoSuchMethodException e) {
					Method[] methods = bean.getClass().getDeclaredMethods();
					for (Method targetSetter : methods) {
						if (targetSetter.getName().equals(setter)) {
							Class<?>[] parameterTypes = targetSetter.getParameterTypes();
							if (parameterTypes != null && parameterTypes.length == 1) { // 只有1个参数
								Class<?> parameterType = parameterTypes[0]; // setter方法参数类型
								if (parameterType.isAssignableFrom(value.getClass())) {
									setterMethod = targetSetter;
								}
							}
						}
					}
				}
			} else { // 基本类型参数注入
				
				// 因注入的值都是以字符串表示的，但注入的字段不一定是字符串类型，所以还需要类型转换
				// 但又不知道目标setter方法的参数类型，所以只能循环所有的方法
				// 先找到方法，进行类型转换，再反射注入
				Method[] methods = bean.getClass().getDeclaredMethods();
				for (Method targetSetter : methods) {
					if (targetSetter.getName().equals(setter)) {
						Class<?>[] parameterTypes = targetSetter.getParameterTypes();
						if (parameterTypes != null && parameterTypes.length == 1) { // 只有1个参数
							Class<?> parameterType = parameterTypes[0]; // setter方法参数类型
							// 进行参数类型转换，将value类型转换为parameterType
							value = parseParameterType(value.toString(), parameterType);
							setterMethod = targetSetter; 
						}
					}
				}
			}
			
			if (setterMethod != null) {
				setterMethod.invoke(bean, value);
			}
		}
	}

	private Object parseParameterType(String parameter, Class<?> parameterType) {
		if (parameterType.equals(int.class) || parameterType.equals(Integer.class)) {
			return Integer.valueOf(parameter);
		}
		if (parameterType.equals(long.class) || parameterType.equals(Long.class)) {
			return Long.valueOf(parameter);
		}
		// TODO：其它类型转换
		return parameter;
	}

}
