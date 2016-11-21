package personal.wh.tinyspring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 定义AOP的增强(Advice)
 * 
 * @author wh
 */
public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.nanoTime();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start1!");
		Object result = invocation.proceed();
		long end = System.nanoTime();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end1! takes " + (end - start)
				+ " nanoseconds.");
		return result;
	}

}
