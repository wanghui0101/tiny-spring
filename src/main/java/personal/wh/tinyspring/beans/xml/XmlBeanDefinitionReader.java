package personal.wh.tinyspring.beans.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import personal.wh.tinyspring.beans.AbstractBeanDefinitionReader;
import personal.wh.tinyspring.beans.BeanDefinition;
import personal.wh.tinyspring.beans.BeanReference;
import personal.wh.tinyspring.beans.PropertyValue;
import personal.wh.tinyspring.beans.io.Resource;
import personal.wh.tinyspring.beans.io.ResourceLoader;
import personal.wh.tinyspring.util.Assert;
import personal.wh.tinyspring.util.StringUtils;

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
		String name = beanTagEle.getAttribute("name");
		String beanClassName = beanTagEle.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName(beanClassName);
		processProperty(beanTagEle, beanDefinition);
		getRegistry().put(getBeanName(id, name, beanClassName), beanDefinition);
	}
	
	protected String getBeanName(String id, String name, String beanClassName) {
		String beanName = StringUtils.isNotEmpty(id) ? id : (StringUtils.isNotEmpty(name) ? name : beanClassName);
		Assert.notEmpty(beanName);
		return beanName;
	}

	protected void processProperty(Element beanTagEle, BeanDefinition beanDefinition) {
		NodeList propertyTagList = beanTagEle.getChildNodes();
		for (int i = 0; i < propertyTagList.getLength(); i++) {
			Node propertyTag = propertyTagList.item(i);
			if (propertyTag instanceof Element) {
				Element propertyTagEle = (Element) propertyTag;
				String name = propertyTagEle.getAttribute("name");
				String value = propertyTagEle.getAttribute("value");
				String ref = propertyTagEle.getAttribute("ref");
				if (StringUtils.isNotEmpty(value)) {
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				} else if (StringUtils.isNotEmpty(ref)) {
					BeanReference beanReference = new BeanReference(name);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
				}
			}
		}
	}

}
