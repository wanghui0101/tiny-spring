package personal.wh.tinyspring;

import org.junit.Test;

import personal.wh.tinyspring.factory.AutowireCapableBeanFactory;
import personal.wh.tinyspring.factory.BeanFactory;

public class BeanFactoryTest {

	@Test
	public void test() throws Exception {
		// 1. 初始化bean工厂
		BeanFactory beanFactory = new AutowireCapableBeanFactory();

		// 2. pojo的className
		String helloWorldServiceClassName = "personal.wh.tinyspring.HelloWorldService";

		// 3. 包装bean定义
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName(helloWorldServiceClassName);
		
		// 4. 设置属性
		PropertyValues pvs = new PropertyValues();
		pvs.addPropertyValue(new PropertyValue("text", "wh"));
		beanDefinition.setPropertyValues(pvs);

		// 5. 向bean工厂中注册，同时为bean取一个名字
		beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

		// 6. 从bean工厂中获取bean
		HelloWorldService helloWorldServiceBean = (HelloWorldService) beanFactory.getBean("helloWorldService");

		// 7. 调用bean中的方法
		helloWorldServiceBean.helloWorld();
	}

}
