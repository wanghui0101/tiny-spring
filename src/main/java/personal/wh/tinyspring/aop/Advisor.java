package personal.wh.tinyspring.aop;

import org.aopalliance.aop.Advice;

/**
 * 增强者(大概是一个功能包装的意思，不是AOP中的标准概念)
 * 
 * @author wh
 */
public interface Advisor {

	Advice getAdvice();
	
}
