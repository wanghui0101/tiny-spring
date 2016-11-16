package personal.wh.tinyspring.factory;

import personal.wh.tinyspring.BeanDefinition;

/**
 * 具备自动装配能力的bean工厂
 * 
 * @author Wh
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) {
		try {
			Object bean = beanDefinition.getBeanClass().newInstance();
			return bean;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
