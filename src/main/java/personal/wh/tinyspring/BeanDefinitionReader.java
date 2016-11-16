package personal.wh.tinyspring;

/**
 * 从外部读取bean定义
 * 
 * @author Wh
 */
public interface BeanDefinitionReader {

	/**
	 * 从location加载bean定义
	 * @param location
	 * @throws Exception
	 */
	void loadBeanDefinitions(String location) throws Exception;
}
