package com.mframe.servlet.web.impl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.mframe.servlet.DispatcherServlet;
import com.mframe.servlet.web.WebApplicationInitializer;

public class AbstractDispatcherServletInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		// 注册DispatcherServlet类
		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet());
		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1); // 设置最先启动
	}

}
