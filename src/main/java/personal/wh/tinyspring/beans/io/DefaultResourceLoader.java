package personal.wh.tinyspring.beans.io;

import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {

	@Override
	public Resource getResource(String location) {
		URL resource = this.getClass().getClassLoader().getResource(location);
		return new UrlResource(resource);
	}

}
