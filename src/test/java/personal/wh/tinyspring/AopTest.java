package personal.wh.tinyspring;

import org.junit.Test;

import personal.wh.tinyspring.context.ApplicationContext;
import personal.wh.tinyspring.context.ClassPathXmlApplicationContext;

public class AopTest {

	@Test
	public void test() throws Exception {
		// 1. 创建ApplicationContext工厂
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		
		// 2. 获取工厂中的bean，有接口，默认通过jdk代理
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		
		// 3. 调用方法
		helloWorldService.helloWorld();
		
		// 4. 无接口，只能通过cglib代理
		SampleService sampleService = (SampleService) applicationContext.getBean("sampleService");
		sampleService.print();
		
	}
}
