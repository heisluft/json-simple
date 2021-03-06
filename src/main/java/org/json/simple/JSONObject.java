/*
 * $Id: JSONObject.java,v 1.1 2006/04/15 14:10:48 platform Exp $
 * Created on 2006-4-10
 */
package org.json.simple;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
 *
 * @author FangYidong&lt;fangyidong@yahoo.com.cn&gt;
 */
public class JSONObject extends HashMap<String, Object> implements JSONAware, JSONStreamAware {

	private static final long serialVersionUID = -503443796854799292L;


	public JSONObject() {
		super();
	}

	/**
	 * Allows creation of a JSONObject from a Map. After that, both the generated JSONObject and the Map can be
	 * modified
	 * independently.
	 *
	 * @param map
	 * 		the initial values
	 */
	public JSONObject(Map<String, Object> map) {
		super(map);
	}


	/**
	 * Encode a map into JSON text and write it to out. If this map is also a JSONAware or JSONStreamAware,
	 * JSONAware or
	 * JSONStreamAware specific behaviours will be ignored at this top level.
	 *
	 * @param map
	 * 		the map to write
	 * @param out
	 * 		the writer to write to
	 *
	 * @throws IOException
	 * 		if there is a problem within the writer
	 * @see org.json.simple.JSONValue#writeJSONString(Object, Writer)
	 */
	public static void writeJSONString(Map<String, Object> map, Writer out) throws IOException {
		if(map == null) {
			out.write("null");
			return;
		}
		boolean first = true;
		out.write('{');
		for (Entry<String, Object> entry : map.entrySet()) {
			if (first) first = false;
			else out.write(',');
			out.write('\"');
			out.write(escape(String.valueOf(entry.getKey())));
			out.write("\":");
			JSONValue.writeJSONString(entry.getValue(), out);
		}
		out.write('}');
	}

	/**
	 * Convert a map to JSON text. The result is a JSON object. If this map is also a JSONAware, JSONAware specific
	 * behaviours will be omitted at this top level.
	 *
	 * @param map
	 * 		the map to write
	 *
	 * @return JSON text, or "null" if map is null.
	 *
	 * @see org.json.simple.JSONValue#toJSONString(Object)
	 */
	public static String toJSONString(Map<String, Object> map) {
		final StringWriter writer = new StringWriter();

		try {
			writeJSONString(map, writer);
			return writer.toString();
		} catch(IOException e) {
			// This should never happen with a StringWriter
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convenience Method to get a value as an instance of type JSONObject
	 * @param key the key
	 * @return the casted value
	 */
	public JSONObject getObject(String key) {
		if(!containsKey(key)) return null;
		return (JSONObject) get(key);
	}
	/**
	 * Convenience Method to get a value as an instance of type String
	 * @param key the key
	 * @return the casted value
	 */
	public String getString(String key) {
		if(!containsKey(key)) return null;
		return (String) get(key);
	}
	/**
	 * Convenience Method to get a value as an instance of type JSONArray
	 * @param key the key
	 * @return the casted value
	 */
	public JSONArray getArray(String key) {
		if(!containsKey(key)) return null;
		return (JSONArray) get(key);
	}

	/**
	 * Convenience Method to get a value as an int
	 * @param key the key
	 * @return the casted value
	 * @throws RuntimeException if the map does not contain the specified key
	 */
	public int getInt(String key) {
		if(!containsKey(key)) throw new RuntimeException("Map does not contain key '" + key + "'");
		return (Integer) get(key);
	}

	public static String toString(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		sb.append('\"');
		if(key == null) sb.append("null");
		else JSONValue.escape(key, sb);
		sb.append('\"').append(':');

		sb.append(JSONValue.toJSONString(value));

		return sb.toString();
	}

	/**
	 * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000 through U+001F). It's the same as
	 * JSONValue.escape() only for compatibility here.
	 *
	 * @param s
	 * 		the string to escape
	 *
	 * @return the escaped string
	 *
	 * @see org.json.simple.JSONValue#escape(String)
	 */
	public static String escape(String s) {
		return JSONValue.escape(s);
	}

	public void writeJSONString(Writer out) throws IOException {
		writeJSONString(this, out);
	}

	public String toJSONString() {
		return toJSONString(this);
	}

	public String toString() {
		return toJSONString();
	}
}
