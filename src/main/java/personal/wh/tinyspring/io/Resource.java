package personal.wh.tinyspring.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 对资源的抽象
 * 
 * @author Wh
 */
public interface Resource {

	InputStream getInputStream() throws IOException;
	
}
