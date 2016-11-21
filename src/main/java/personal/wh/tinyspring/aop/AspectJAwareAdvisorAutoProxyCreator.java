package personal.wh.tinyspring.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import personal.wh.tinyspring.beans.factory.AbstractBeanFactory;
import personal.wh.tinyspring.beans.factory.BeanFactory;
import personal.wh.tinyspring.beans.factory.BeanFactoryAware;
import personal.wh.tinyspring.beans.factory.BeanPostProcessor;
import personal.wh.tinyspring.core.OrderComparator;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private AbstractBeanFactory beanFactory;
	
	private boolean proxyTargetClass = false; // 是否强制开启cglib代理
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = (AbstractBeanFactory) beanFactory;
	}
	
	public void setProxyTargetClass(boolean proxyTargetClass) {
		this.proxyTargetClass = proxyTargetClass;
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
		OrderComparator.sort(pointcutAdvisors);
		for (PointcutAdvisor pointcutAdvisor : pointcutAdvisors) {
			if (pointcutAdvisor.getPointcut().getClassFilter().matches(bean.getClass())) {
				AdvisedSupport advisedSupport = new AdvisedSupport();
				advisedSupport.setTargetSource(new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces()));
				advisedSupport.setMethodInterceptor((MethodInterceptor) pointcutAdvisor.getAdvice());
				advisedSupport.setMethodMatcher(pointcutAdvisor.getPointcut().getMethodMatcher());
				
				bean = new ProxyFactory(advisedSupport, proxyTargetClass).getProxy();
			}
		}
		
		return bean;
	}

}
