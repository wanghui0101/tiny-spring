package personal.wh.tinyspring.context;

import java.util.Map;

import personal.wh.tinyspring.beans.AbstractBeanDefinitionReader;
import personal.wh.tinyspring.beans.BeanDefinition;
import personal.wh.tinyspring.beans.factory.AbstractBeanFactory;
import personal.wh.tinyspring.beans.factory.AutowireCapableBeanFactory;
import personal.wh.tinyspring.beans.io.DefaultResourceLoader;
import personal.wh.tinyspring.beans.io.ResourceLoader;
import personal.wh.tinyspring.beans.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	private String configLocation;
	
	public ClassPathXmlApplicationContext(String configLocation) throws Exception {
		this(configLocation, new AutowireCapableBeanFactory());
	}

	public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}

	@Override
	public void refresh() throws Exception {
		// 1. 使用bean定义读取器从xml文件中加载bean定义
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resourceLoader);
		beanDefinitionReader.loadBeanDefinitions(configLocation);

		// 2. 将bean定义读取器读取的bean定义，依次向bean工厂中注册(只注册，并不实例化bean)
		for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
		}

		if (!lazyInit) {
			// 3. 实例化所有bean，并进行属性注入
			beanFactory.preInstantiateSingletons();
		}
		
	}

}
