package personal.wh.tinyspring;

import org.junit.Test;

import personal.wh.tinyspring.aop.AdvisedSupport;
import personal.wh.tinyspring.aop.AopProxy;
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
		
		// 4. 通过JDK实现动态代理
		AopProxy aopProxy = new JdkDynamicAopProxy(advisedSupport);
		
		// 5. 得到被代理对象
		HelloWorldService proxiedHelloWorldService = (HelloWorldService) aopProxy.getProxy();
		
		// 6. 调用被代理对象的方法，此时则会在方法前后执行advice
		proxiedHelloWorldService.helloWorld();
	}
}
