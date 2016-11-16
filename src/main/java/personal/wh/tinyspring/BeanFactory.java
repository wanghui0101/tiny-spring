package personal.wh.tinyspring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean工厂，持有所有bean定义
 * 
 * @author Wh
 */
public class BeanFactory {

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
	public Object getBean(String name) {
		return this.beanDefinitionMap.get(name).getBean();
	}
	
	/**
	 * 注册bean定义
	 * @param name
	 * @param beanDefinition
	 */
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		this.beanDefinitionMap.put(name, beanDefinition);
	}
	
}
