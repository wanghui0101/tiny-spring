package personal.wh.tinyspring.context;

import personal.wh.tinyspring.beans.factory.Aware;
import personal.wh.tinyspring.beans.factory.BeanPostProcessor;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

	private ApplicationContext applicationContext;
	
	public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		invokeAwareInterfaces(bean);
		return bean;
	}

	private void invokeAwareInterfaces(Object bean) {
		if (bean instanceof Aware) {
			if (bean instanceof ApplicationContextAware) {
				((ApplicationContextAware) bean).setApplicationContext(applicationContext);
			}
			// TODO: 可扩展其它Aware接口
		}
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}

}
