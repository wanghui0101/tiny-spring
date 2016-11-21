package personal.wh.tinyspring.context;

import java.util.List;

import personal.wh.tinyspring.beans.factory.AbstractBeanFactory;
import personal.wh.tinyspring.beans.factory.BeanPostProcessor;
import personal.wh.tinyspring.core.OrderComparator;

/**
 * 在BeanFactory的基础上扩展
 * 
 * @author wh
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
	
	protected AbstractBeanFactory beanFactory;
	
	// 装饰器模式
	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public Object getBean(String name) throws Exception {
		return beanFactory.getBean(name);
	}
	
	@Override
	public <T> T getBean(Class<T> beanClass) throws Exception {
		return beanFactory.getBean(beanClass);
	}
	
	public void refresh() throws Exception {
		loadBeanDefinitions(beanFactory);
		preparaBeanFactory(beanFactory);
		registerBeanPostProcessors(beanFactory);
		onRefresh();
	}
	
	protected void preparaBeanFactory(AbstractBeanFactory beanFactory) {
		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
	}

	protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
		List<BeanPostProcessor> beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			beanFactory.addBeanPostProcessor(beanPostProcessor);
		}
	}

	protected void onRefresh() throws Exception {
		// 实例化所有bean，并进行属性注入
		beanFactory.preInstantiateSingletons();
	}

	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

}
