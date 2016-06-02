package com.yunrong.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器 - 编码格式转换
 * 
 * @param <E>
 */

public class EncodingConvertFilter<E> implements Filter {

	private static final String FROM_ENCODING_PARAMETER_NAME = "fromEncoding";
	private static final String TO_ENCODING_PARAMETER_NAME = "toEncoding";

	private String fromEncoding = "ISO-8859-1";
	private String toEncoding = "UTF-8";

	public void init(FilterConfig filterConfig) {
		String fromEncodingParameter = filterConfig
				.getInitParameter(FROM_ENCODING_PARAMETER_NAME);
		String toEncodingParameter = filterConfig
				.getInitParameter(TO_ENCODING_PARAMETER_NAME);
		if (fromEncodingParameter != null) {
			fromEncoding = fromEncodingParameter;
		}
		if (toEncodingParameter != null) {
			toEncoding = toEncodingParameter;
		}
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (request.getMethod().equalsIgnoreCase("GET")) {
			Iterator it = request.getParameterMap().entrySet().iterator();

			while(it.hasNext()){
				
				String[] parames = ((Entry<String, String[]>)it.next()).getValue();
			
			
				for (int i = 0; i < parames.length; i++) {
					try {
						parames[i] = new String(
								parames[i].getBytes(fromEncoding), toEncoding);
						System.out.println(parames[0]);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {
	}

}