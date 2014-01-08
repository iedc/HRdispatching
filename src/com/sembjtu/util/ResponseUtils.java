/**
 * 
 */
package com.sembjtu.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author edc
 *
 */
public class ResponseUtils {

	private static void print(HttpServletResponse response, String contentType, String text)
			throws IOException {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter pw = response.getWriter();
		pw.write(text);
		pw.close();
	}
	
	public static void printMapToJson(HttpServletResponse response, Map<String, Object> map) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			print(response, "application/json;charset=UTF-8", "[" + objectMapper.writeValueAsString(map)
					+ "]");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
