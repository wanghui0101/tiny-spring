package personal.wh.tinyspring;

import java.util.Map;

import org.junit.Test;

import personal.wh.tinyspring.beans.AbstractBeanDefinitionReader;
import personal.wh.tinyspring.beans.BeanDefinition;
import personal.wh.tinyspring.beans.factory.AbstractBeanFactory;
import personal.wh.tinyspring.beans.factory.AutowireCapableBeanFactory;
import personal.wh.tinyspring.beans.io.DefaultResourceLoader;
import personal.wh.tinyspring.beans.io.ResourceLoader;
import personal.wh.tinyspring.beans.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {

	@Test
	public void test() throws Exception {
		
		// 1. 使用bean定义读取器从xml文件中加载bean定义
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resourceLoader);
		beanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		
		// 2. 初始化bean工厂
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		
		// 3. 将bean定义读取器读取的bean定义，依次向bean工厂中注册(只注册，并不实例化bean)
		for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
		}
		
		// 4. 实例化所有bean，并进行属性注入
		beanFactory.preInstantiateSingletons();

		// 5. 从bean工厂中获取bean，并调用方法
		HelloWorldServiceImpl helloWorldServiceBean = (HelloWorldServiceImpl) beanFactory.getBean("helloWorldService");
		helloWorldServiceBean.helloWorld();
		
		OutputService outputService = (OutputService) beanFactory.getBean("outputService");
		outputService.output();
	}

}
