package personal.wh.tinyspring;

public class OutputService {

	private HelloWorldService helloWorldService;
	
	

	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	

	public void output() {
		System.out.print("output: ");
		helloWorldService.helloWorld();
	}
	
}
