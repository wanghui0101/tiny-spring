package personal.wh.tinyspring.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 对MethodInvocation接口的简单实现
 * 
 * @author wh
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

	private Object target;
	private Method method;
	private Object[] args;
	
	public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
		this.target = target;
		this.method = method;
		this.args = args;
	}
	
	@Override
	public Object[] getArguments() {
		return args;
	}

	@Override
	public Object proceed() throws Throwable {
		// 核心方法还是使用反射调用方法
		return method.invoke(target, args);
	}

	@Override
	public Object getThis() {
		return target;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return method;
	}

	@Override
	public Method getMethod() {
		return method;
	}

}
