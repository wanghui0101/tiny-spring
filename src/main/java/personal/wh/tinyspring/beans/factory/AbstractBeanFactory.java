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
	
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
	
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
			bean = doCreateBean(beanDefinition, name);
			bean = initializeBean(bean, name);
			beanDefinition.setBean(bean);
		}
		return bean;
	}

	@Override
	public <T> T getBean(Class<T> beanClass) throws Exception {
		List<T> beans = getBeansForType(beanClass);
		if (beans == null || beans.size() != 1) {
			throw new RuntimeException();
		}
		return beans.get(0);
	}

	protected Object doCreateBean(BeanDefinition beanDefinition, String beanName) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		applyPropertyValues(bean, beanDefinition); // 属性注入
		invokeAwareMethods(bean, beanName);
		return bean;
	}
	
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}
	
	protected Object initializeBean(Object bean, String name) throws Exception {
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}
		if (bean instanceof InitializingBean) {
			((InitializingBean) bean).afterPropertiesSet();
		}
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
		
		return bean;
	}
	
	private void invokeAwareMethods(Object bean, String name) {
		if (bean instanceof Aware) {
			if (bean instanceof BeanFactoryAware) {
				((BeanFactoryAware) bean).setBeanFactory(this);
			}
			if (bean instanceof BeanNameAware) {
				((BeanNameAware) bean).setBeanName(name);
			}
		}
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
	
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		beanPostProcessors.add(beanPostProcessor);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getBeansForType(Class<T> type) throws Exception {
		List<T> beans = new ArrayList<T>();
		for (String beanDefinitionName : this.beanDefinitionNames) {
			if (type.isAssignableFrom(this.beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
				beans.add((T) getBean(beanDefinitionName));
			}
		}
		return beans;
	}
	
	/**
	 * 如何创建bean的示例，交给子类
	 * @param beanDefinition
	 * @return
	 */
	protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;
	
}
