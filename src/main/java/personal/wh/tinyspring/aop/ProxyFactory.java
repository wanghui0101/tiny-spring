package personal.wh.tinyspring.aop;

public class ProxyFactory extends AbstractAopProxy {
	
	private boolean proxyTargetClass; // 是否强制开启cglib代理

	public ProxyFactory(AdvisedSupport advisedSupport) {
		this(advisedSupport, false);
	}
	
	public ProxyFactory(AdvisedSupport advisedSupport, boolean proxyTargetClass) {
		super(advisedSupport);
		this.proxyTargetClass = proxyTargetClass;
	}

	@Override
	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	protected AopProxy createAopProxy() {
		if (!proxyTargetClass) {
			Class<?>[] interfaces = advisedSupport.getTargetSource().getInterfaces();
			if (interfaces != null && interfaces.length > 0) { // 被代理类如果有接口，则使用JDK代理
				return new JdkDynamicAopProxy(advisedSupport);
			}
		}
		// 强制使用cglib代理，或者被代理类无接口，则使用cglib代理
		return new CglibAopProxy(advisedSupport);
	}
	
}
