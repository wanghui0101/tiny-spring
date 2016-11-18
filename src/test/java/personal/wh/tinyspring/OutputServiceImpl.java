package personal.wh.tinyspring;

public class OutputServiceImpl implements OutputService {

	private HelloWorldService helloWorldService;

	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@Override
	public void output() {
		System.out.print("output: ");
		helloWorldService.helloWorld();
	}
	
}
