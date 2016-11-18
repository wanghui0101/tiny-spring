package personal.wh.tinyspring.aop;

import java.lang.reflect.Method;

/**
 * 判断类的方法是否能否与切点表达式匹配
 * 
 * @author wh
 */
public interface MethodMatcher {

	boolean matches(Method method, Class<?> targetClass);
	
}
