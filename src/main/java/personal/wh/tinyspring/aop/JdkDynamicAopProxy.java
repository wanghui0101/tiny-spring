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
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

	private AdvisedSupport advisedSupport;
	
	public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}
	
	@Override
	public Object getProxy() {
		// JDK动态代理的标准写法
		return Proxy.newProxyInstance(getClass().getClassLoader(), 
				new Class<?>[] { advisedSupport.getTargetSource().getTargetClass() }, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 未直接通过 method.invoke(advisedSupport.getTargetSource(), args) 调用
		// 而是通过aopalliance的两个核心接口 MethodInterceptor 和 MethodInvocation 实现方法的反射调用
		// 但本质还是调用 method.invoke(advisedSupport.getTargetSource(), args)
		// 这样做的目的是引入aopalliance的API，为下一步定义及解析切点表达式做准备
		MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
		return methodInterceptor.invoke(new ReflectiveMethodInvocation(
				advisedSupport.getTargetSource().getTarget(), method, args));
	}

}
