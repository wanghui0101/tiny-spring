package personal.wh.tinyspring.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import personal.wh.tinyspring.BeanDefinition;

/**
 * bean工厂，持有所有bean定义
 * 
 * @author Wh
 */
public abstract class AbstractBeanFactory implements BeanFactory {

	/**
	 * 所有的bean定义
	 * key为beanName，value为bean定义
	 */
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	/**
	 * 通过beanName获取bean定义
	 * @param name
	 * @return
	 */
	@Override
	public Object getBean(String name) {
		return this.beanDefinitionMap.get(name).getBean();
	}
	
	/**
	 * 注册bean定义
	 * @param name
	 * @param beanDefinition
	 */
	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		Object bean = doCreateBean(beanDefinition);
		beanDefinition.setBean(bean);
		this.beanDefinitionMap.put(name, beanDefinition);
	}
	
	/**
	 * 如何创建bean的示例，交给子类
	 * @param beanDefinition
	 * @return
	 */
	protected abstract Object doCreateBean(BeanDefinition beanDefinition);
	
}
