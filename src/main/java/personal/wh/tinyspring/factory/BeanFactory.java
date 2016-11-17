package personal.wh.tinyspring.factory;

import personal.wh.tinyspring.BeanDefinition;

/**
 * bean工厂，定义基础功能	
 * 
 * @author Wh
 */
public interface BeanFactory {

	Object getBean(String name) throws Exception;

	void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
	
}
