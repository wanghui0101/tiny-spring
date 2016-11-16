package personal.wh.tinyspring;

public class HelloWorldService {
	
	private String text;
	
	public void setText(String text) {
		this.text = text;
	}

	public void helloWorld() {
		System.out.println("hello " + text + "!");
	}
	
}
