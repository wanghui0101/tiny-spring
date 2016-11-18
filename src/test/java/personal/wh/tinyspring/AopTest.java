package personal.wh.tinyspring;

import org.junit.Test;

import personal.wh.tinyspring.aop.AdvisedSupport;
import personal.wh.tinyspring.aop.AopProxy;
import personal.wh.tinyspring.aop.AspectJExpressionPointcutAdvisor;
import personal.wh.tinyspring.aop.JdkDynamicAopProxy;
import personal.wh.tinyspring.aop.TargetSource;
import personal.wh.tinyspring.context.ApplicationContext;
import personal.wh.tinyspring.context.ClassPathXmlApplicationContext;

public class AopTest {

	@Test
	public void test() throws Exception {
		// 1. 创建ApplicationContext工厂
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		
		// 2. 获取工厂中的bean
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		
		// 3. 在AdvisedSupport中包装被代理的bean和advice
		AdvisedSupport advisedSupport = new AdvisedSupport();
		advisedSupport.setTargetSource(new TargetSource(HelloWorldService.class, helloWorldService));
		advisedSupport.setMethodInterceptor(new TimerInterceptor());
		
		// 4. 定义切面和切点表达式
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression("execution(* personal.wh.tinyspring.*.*(..))");
		advisor.setAdvice(advisedSupport.getMethodInterceptor());
		
		// 5. 判断类是否匹配，方法是否匹配
		boolean classMatched = advisor.getPointcut().getClassFilter()
				.matches(advisedSupport.getTargetSource().getTargetClass());
		boolean methodMatched = advisor.getPointcut().getMethodMatcher()
				.matches(advisedSupport.getTargetSource().getTargetClass().getDeclaredMethod("helloWorld"), 
						advisedSupport.getTargetSource().getTargetClass());
		
		// 6. 匹配则执行代理
		if (classMatched && methodMatched) {
			// 7. 通过JDK实现动态代理
			AopProxy aopProxy = new JdkDynamicAopProxy(advisedSupport);
			
			// 8. 得到被代理对象
			HelloWorldService proxiedHelloWorldService = (HelloWorldService) aopProxy.getProxy();
			
			// 9. 调用被代理对象的方法，此时则会在方法前后执行advice
			proxiedHelloWorldService.helloWorld();
		}
		
	}
}
