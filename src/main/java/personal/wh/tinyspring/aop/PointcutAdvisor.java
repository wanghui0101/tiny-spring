package personal.wh.tinyspring.aop;

public interface PointcutAdvisor extends Advisor {

	Pointcut getPointcut();
	
}
