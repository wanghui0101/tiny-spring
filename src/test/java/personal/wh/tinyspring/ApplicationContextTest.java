package personal.wh.tinyspring;

import org.junit.Test;

import personal.wh.tinyspring.context.ApplicationContext;
import personal.wh.tinyspring.context.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

	@Test
	public void test() throws Exception {
		
		// 1. 初始化bean工厂
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");

		// 2. 从bean工厂中获取bean，并调用方法
		HelloWorldService helloWorldServiceBean = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldServiceBean.helloWorld();

		OutputService outputService = (OutputService) applicationContext.getBean("outputService");
		outputService.output();
	}

}
