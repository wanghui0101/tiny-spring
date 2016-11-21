package personal.wh.tinyspring.aop;

import org.aopalliance.aop.Advice;

import personal.wh.tinyspring.core.Ordered;

/**
 * AspectJ实现的增强者
 * 
 * @author wh
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor, Ordered {

	private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	
	private Advice advice;
	
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
	
	public void setExpression(String expression) {
		this.pointcut.setExpression(expression);
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}
	
	private int order;
	
	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}

}
