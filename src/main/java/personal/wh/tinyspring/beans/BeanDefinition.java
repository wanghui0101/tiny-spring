package personal.wh.tinyspring.beans;

/**
 * bean定义
 * 
 * 通过beanClassName可以得到 beanClass
 * 通过beanClass可以得到 bean
 * 
 * @author Wh
 */
public class BeanDefinition {
	
	private String beanClassName; // 类名(包括包名)
	private Class<?> beanClass; // 类
	private Object bean; // 类的实例
	private PropertyValues propertyValues = new PropertyValues(); // 所有属性键值对
	
	public BeanDefinition() {
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}
	
}
