package personal.wh.tinyspring;

import java.util.Map;

import org.junit.Test;

import personal.wh.tinyspring.factory.AutowireCapableBeanFactory;
import personal.wh.tinyspring.factory.BeanFactory;
import personal.wh.tinyspring.io.DefaultResourceLoader;
import personal.wh.tinyspring.io.ResourceLoader;
import personal.wh.tinyspring.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {

	@Test
	public void test() throws Exception {
		
		// 1. 使用bean定义读取器从xml文件中加载bean定义
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resourceLoader);
		beanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		
		// 2. 初始化bean工厂
		BeanFactory beanFactory = new AutowireCapableBeanFactory();
		
		// 3. 将bean定义读取器读取的bean定义，依次向bean工厂中注册
		for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
		}

		// 4. 从bean工厂中获取bean
		HelloWorldService helloWorldServiceBean = (HelloWorldService) beanFactory.getBean("helloWorldService");

		// 5. 调用bean中的方法
		helloWorldServiceBean.helloWorld();
	}

}
