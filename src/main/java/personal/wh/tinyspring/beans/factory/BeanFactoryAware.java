package personal.wh.tinyspring.beans.factory;

public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory beanFactory);
	
}
