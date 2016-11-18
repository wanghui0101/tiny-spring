package personal.wh.tinyspring.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import personal.wh.tinyspring.beans.factory.AbstractBeanFactory;
import personal.wh.tinyspring.beans.factory.BeanFactory;
import personal.wh.tinyspring.beans.factory.BeanFactoryAware;
import personal.wh.tinyspring.beans.factory.BeanPostProcessor;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private AbstractBeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = (AbstractBeanFactory) beanFactory;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws Exception {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws Exception {
		if (bean instanceof AspectJExpressionPointcutAdvisor) {
			return bean;
		}
		if (bean instanceof MethodInterceptor) {
			return bean;
		}
		
		List<PointcutAdvisor> pointcutAdvisors = beanFactory.getBeansForType(PointcutAdvisor.class);
		
		// TODO: 排序
		// @Order, Orderer接口
		
		for (PointcutAdvisor pointcutAdvisor : pointcutAdvisors) {
			if (pointcutAdvisor.getPointcut().getClassFilter().matches(bean.getClass())) {
				AdvisedSupport advisedSupport = new AdvisedSupport();
				advisedSupport.setTargetSource(new TargetSource(bean.getClass().getInterfaces(), bean));
				advisedSupport.setMethodInterceptor((MethodInterceptor) pointcutAdvisor.getAdvice());
				advisedSupport.setMethodMatcher(pointcutAdvisor.getPointcut().getMethodMatcher());
				
				bean = new JdkDynamicAopProxy(advisedSupport).getProxy();
			}
		}
		
		return bean;
	}

}
