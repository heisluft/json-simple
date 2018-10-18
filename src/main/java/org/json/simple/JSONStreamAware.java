package org.json.simple;

import java.io.IOException;
import java.io.Writer;

/**
 * Beans that support customized output of JSON text to a writer shall implement this interface.
 *
 * @author FangYidong&lt;fangyidong@yahoo.com.cn&gt;
 */
public interface JSONStreamAware {
	/**
	 * write JSON string to out.
	 *
	 * @param out
	 * 		the writer to write to
	 *
	 * @throws IOException
	 * 		if there is a problem within the writer
	 */
	void writeJSONString(Writer out) throws IOException;
}
