package personal.wh.tinyspring.beans.factory;

/**
 * bean工厂，定义基础功能	
 * 
 * @author Wh
 */
public interface BeanFactory {

	Object getBean(String name) throws Exception;
	
	<T> T getBean(Class<T> beanClass) throws Exception; 
}
