package personal.wh.tinyspring.beans.factory;

public interface InitializingBean {

	void afterPropertiesSet() throws Exception;
	
}
