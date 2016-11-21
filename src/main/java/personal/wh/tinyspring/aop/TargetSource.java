package personal.wh.tinyspring.aop;

/**
 * 包装被执行增强(advice)的类
 * 
 * @author wh
 */
public class TargetSource {

	private Object target; // 被增强的类实例
	private Class<?> targetClass; // 被增强的类
	private Class<?>[] interfaces; // 被增强的类接口

	public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
		this.target = target;
		this.targetClass = targetClass;
		this.interfaces = interfaces;
	}

	public Object getTarget() {
		return target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}
	
	public Class<?>[] getInterfaces() {
		return interfaces;
	}
	
}
