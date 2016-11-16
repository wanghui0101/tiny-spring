package personal.wh.tinyspring.factory;

import java.lang.reflect.Method;

import personal.wh.tinyspring.BeanDefinition;
import personal.wh.tinyspring.PropertyValue;
import personal.wh.tinyspring.util.StringUtils;

/**
 * 具备自动装配能力的bean工厂
 * 
 * @author Wh
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		applyPropertyValues(bean, beanDefinition); // 属性注入
		return bean;
	}
	
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}
	
	/**
	 * 通过setter方法注入属性
	 * @param bean
	 * @param beanDefinition
	 * @throws Exception 
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
		for (PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()) {
			String setter = "set" + StringUtils.capitalize(pv.getName());
			Method setterMethod = bean.getClass().getDeclaredMethod(setter, pv.getValue().getClass());
			setterMethod.invoke(bean, pv.getValue());
		}
	}

}
