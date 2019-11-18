package com.mframe.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化HandlerMapping
 * @author wwf
 *
 */
@SuppressWarnings("serial")
public class HttpServletBean extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// 初始化SpringMVC的对象以及相关的URL映射关系
		initServletBean();

    }
	
	protected void initServletBean() throws ServletException {
	}
	
}
