package personal.wh.tinyspring;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装bean的所有属性键值
 * 
 * @author Wh
 */
public class PropertyValues {

	private final List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	
	public void addPropertyValue(PropertyValue propertyValue) {
		this.propertyValues.add(propertyValue);
	}

	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}
	
}
