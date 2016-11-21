package personal.wh.tinyspring;

import personal.wh.tinyspring.beans.factory.BeanNameAware;
import personal.wh.tinyspring.beans.factory.InitializingBean;
import personal.wh.tinyspring.context.ApplicationContext;
import personal.wh.tinyspring.context.ApplicationContextAware;

public class HelloWorldServiceImpl implements HelloWorldService, 
	BeanNameAware, ApplicationContextAware, InitializingBean {
	
	private String text;
	private int num;
	
	private ApplicationContext applicationContext;
	private String beanName;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public void helloWorld() {
		System.out.println("applicationContext: " + applicationContext);
		System.out.println("hello " + text + num + "!");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("beanName: " + beanName + " initialized.");
	}
	
}
