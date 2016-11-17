package personal.wh.tinyspring.context;

import personal.wh.tinyspring.beans.factory.AbstractBeanFactory;

/**
 * 在BeanFactory的基础上扩展
 * 
 * @author wh
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
	
	protected AbstractBeanFactory beanFactory;
	
	protected boolean lazyInit = false;
	
	// 装饰器模式
	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public Object getBean(String name) throws Exception {
		return beanFactory.getBean(name);
	}
	
	public void refresh() throws Exception {
	}

}
