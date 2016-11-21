package personal.wh.tinyspring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 通过JDK实现动态代理
 * 
 * @author wh
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

	public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
		super(advisedSupport);
	}

	@Override
	public Object getProxy() {
		// JDK动态代理的标准写法
		return Proxy.newProxyInstance(getClass().getClassLoader(), 
				advisedSupport.getTargetSource().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
		MethodMatcher methodMatcher = advisedSupport.getMethodMatcher();
		if (methodMatcher != null && 
				methodMatcher.matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(
					advisedSupport.getTargetSource().getTarget(), method, args));
		} else {
			return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
		}
	}

}
