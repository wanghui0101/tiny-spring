package personal.wh.tinyspring.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import personal.wh.tinyspring.AbstractBeanDefinitionReader;
import personal.wh.tinyspring.BeanDefinition;
import personal.wh.tinyspring.PropertyValue;
import personal.wh.tinyspring.io.Resource;
import personal.wh.tinyspring.io.ResourceLoader;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(String location) throws Exception {
		Resource resource = getResourceLoader().getResource(location);
		InputStream inputStream = resource.getInputStream(); // xml文件输入流
		doLoadBeanDefintion(inputStream);
	}

	protected void doLoadBeanDefintion(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream); // 将xml文件转换为Document对象
		registerBeanDefinitions(doc);
		inputStream.close();
	}

	protected void registerBeanDefinitions(Document doc) {
		Element beansTagEle = doc.getDocumentElement(); // beans节点
		parseBeanDefinitions(beansTagEle);
	}

	protected void parseBeanDefinitions(Element beansTagEle) {
		NodeList beanTagList = beansTagEle.getChildNodes(); // bean节点列表
		for (int i = 0; i < beanTagList.getLength(); i++) {
			Node beanTag = beanTagList.item(i);
			if (beanTag instanceof Element) {
				Element beanTagEle = (Element) beanTag;
				processBeanDefinition(beanTagEle);
			}
		}
	}

	protected void processBeanDefinition(Element beanTagEle) {
		String id = beanTagEle.getAttribute("id");
		String beanClassName = beanTagEle.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName(beanClassName);
		processProperty(beanTagEle, beanDefinition);
		getRegistry().put(id, beanDefinition);
	}

	protected void processProperty(Element beanTagEle, BeanDefinition beanDefinition) {
		NodeList propertyTagList = beanTagEle.getChildNodes();
		for (int i = 0; i < propertyTagList.getLength(); i++) {
			Node propertyTag = propertyTagList.item(i);
			if (propertyTag instanceof Element) {
				Element propertyTagEle = (Element) propertyTag;
				String name = propertyTagEle.getAttribute("name");
				String value = propertyTagEle.getAttribute("value");
				beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
			}
		}
	}

}
