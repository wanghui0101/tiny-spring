package personal.wh.tinyspring;

import org.junit.Test;

public class BeanFactoryTest {

	@Test
	public void test() {
		// 1. 初始化bean工厂
		BeanFactory beanFactory = new BeanFactory();

		// 2. 实例化pojo
		HelloWorldService helloWorldService = new HelloWorldService();

		// 3. 将pojo包装为bean定义
		BeanDefinition helloWorldServiceBeanDefinition = new BeanDefinition(helloWorldService);

		// 4. 向bean工厂中注册，同时为bean取一个名字
		beanFactory.registerBeanDefinition("helloWorldService", helloWorldServiceBeanDefinition);

		// 5. 从bean工厂中获取bean
		HelloWorldService helloWorldServiceBean = (HelloWorldService) beanFactory.getBean("helloWorldService");

		// 6. 调用bean中的方法
		helloWorldServiceBean.helloWorld();
	}

}
