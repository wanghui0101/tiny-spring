package personal.wh.tinyspring.aop;

/**
 * 包装被执行增强(advice)的类
 * 
 * @author wh
 */
public class TargetSource {

	private Class<?> targetClass; // 被增强的类(若使用jdk代理则必须为这个类的接口类)
	
	private Object target; // 被增强的类实例

	public TargetSource(Class<?> targetClass, Object target) {
		this.targetClass = targetClass;
		this.target = target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTarget() {
		return target;
	}
	
}
