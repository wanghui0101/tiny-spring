package personal.wh.tinyspring;

import java.util.HashMap;
import java.util.Map;

import personal.wh.tinyspring.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

	/**
	 * 将读取到的bean定义先放到这里
	 * 待全部读取完毕之后，再遍历，向bean工厂中依次注册
	 * 这里的bean定义只有结构，并没有被初始化
	 */
	private Map<String, BeanDefinition> registry;
	
	private ResourceLoader resourceLoader;
	
	protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
		this.registry = new HashMap<String, BeanDefinition>();
		this.resourceLoader = resourceLoader;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Map<String, BeanDefinition> getRegistry() {
		return registry;
	}
	
}
