package personal.wh.tinyspring;

public class OutputServiceImpl implements OutputService {

	private HelloWorldServiceImpl helloWorldService;

	public void setHelloWorldService(HelloWorldServiceImpl helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@Override
	public void output() {
		System.out.print("output: ");
		helloWorldService.helloWorld();
	}
	
}
