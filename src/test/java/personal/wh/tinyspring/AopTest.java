package personal.wh.tinyspring;

import org.junit.Test;

import personal.wh.tinyspring.context.ApplicationContext;
import personal.wh.tinyspring.context.ClassPathXmlApplicationContext;

public class AopTest {

	@Test
	public void test() throws Exception {
		// 1. 创建ApplicationContext工厂
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		
		// 2. 获取工厂中的bean
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		
		// 3. 调用方法
		helloWorldService.helloWorld();
		
	}
}
