package personal.wh.tinyspring.beans.factory;

public interface BeanPostProcessor {

	Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;
	
	Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
	
}
