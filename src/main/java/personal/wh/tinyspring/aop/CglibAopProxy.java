package personal.wh.tinyspring.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibAopProxy extends AbstractAopProxy implements MethodInterceptor {

	public CglibAopProxy(AdvisedSupport advisedSupport) {
		super(advisedSupport);
	}

	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
		enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) 
			throws Throwable {
		org.aopalliance.intercept.MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
		MethodMatcher methodMatcher = advisedSupport.getMethodMatcher();
		if (methodMatcher != null && 
				methodMatcher.matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
			return methodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),
					method, args, proxy));
		} else {
			return proxy.invoke(advisedSupport.getTargetSource().getTarget(), args);
		}
	}
	
	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

		private MethodProxy proxy;
		
		public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy proxy) {
			super(target, method, args);
			this.proxy = proxy;
		}
		
		@Override
		public Object proceed() throws Throwable {
			return proxy.invoke(getThis(), getArguments());
		}
		
	}

}
