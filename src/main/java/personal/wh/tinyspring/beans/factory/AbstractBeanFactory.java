package personal.wh.tinyspring.beans.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import personal.wh.tinyspring.beans.BeanDefinition;

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
	 * 所有的beanName
	 */
	private final List<String> beanDefinitionNames = new ArrayList<String>();
	
	/**
	 * 通过beanName获取bean定义
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
		if (beanDefinition == null) {
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		}
		Object bean = beanDefinition.getBean();
		if (bean == null) {
			bean = doCreateBean(beanDefinition);
		}
		return bean;
	}
	
	/**
	 * 注册bean定义
	 * @param name
	 * @param beanDefinition
	 * @throws Exception 
	 */
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
		this.beanDefinitionNames.add(name);
		this.beanDefinitionMap.put(name, beanDefinition);
	}
	
	/**
	 * 实例化所有bean，并完成依赖bean的组装
	 * @throws Exception
	 */
	public void preInstantiateSingletons() throws Exception {
		for (Iterator<String> it = this.beanDefinitionNames.iterator(); it.hasNext();) {
			String beanName = it.next();
			getBean(beanName);
		}
	}
	
	/**
	 * 如何创建bean的示例，交给子类
	 * @param beanDefinition
	 * @return
	 */
	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
	
}
