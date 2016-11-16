package personal.wh.tinyspring;

/**
 * bean定义
 * 
 * @author Wh
 */
public class BeanDefinition {

	private Object bean; // 持有的bean实例
	
	public BeanDefinition(Object bean) {
		this.bean = bean;
	}
	
	public Object getBean() {
		return this.bean;
	}
	
}
