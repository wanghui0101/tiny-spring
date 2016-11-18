package personal.wh.tinyspring.aop;

/**
 * 定义切点
 * 
 * @author wh
 */
public interface Pointcut {

	ClassFilter getClassFilter();
	
	MethodMatcher getMethodMatcher();
	
}
