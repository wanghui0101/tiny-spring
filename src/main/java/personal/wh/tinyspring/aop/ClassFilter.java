package personal.wh.tinyspring.aop;

/**
 * 判断类是否能否与切点表达式匹配
 * 
 * @author wh
 */
public interface ClassFilter {

	boolean matches(Class<?> targetClass);
	
}
