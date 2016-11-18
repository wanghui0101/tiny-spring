package personal.wh.tinyspring.context;

import personal.wh.tinyspring.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

	void setApplicationContext(ApplicationContext applicationContext);
	
}
