package personal.wh.tinyspring;

public class HelloWorldService {
	
	private String text;
	
	private int num;
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public void helloWorld() {
		System.out.println("hello " + text + num + "!");
	}
	
}
