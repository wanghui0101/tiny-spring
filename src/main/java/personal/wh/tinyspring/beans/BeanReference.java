package personal.wh.tinyspring.beans;

/**
 * 依赖bean
 * 
 * @author wh
 */
public class BeanReference {

	private String name; // 依赖bean的名字
	//private Object bean; // 依赖bean的实例
	
	public BeanReference(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}*/

}
