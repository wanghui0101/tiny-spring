package personal.wh.tinyspring.beans;

/**
 * 封装bean的一对属性键值
 * 
 * @author Wh
 */
public class PropertyValue {

	private final String name; // 属性名
	private final Object value; // 属性值
	
	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Object getValue() {
		return this.value;
	}
	
}
