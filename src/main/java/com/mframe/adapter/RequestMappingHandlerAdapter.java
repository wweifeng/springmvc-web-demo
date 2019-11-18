package com.mframe.adapter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mframe.handle.HandlerMethod;
import com.mframe.view.ModelAndView;

public class RequestMappingHandlerAdapter implements HandlerAdapter {

	public boolean supports(Object handler) {
		return (handler instanceof HandlerMethod);
	}

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return handler((HandlerMethod) handler);
	}

	protected ModelAndView handler(HandlerMethod handler) {
		Method method = handler.getMethod();
		Object object = handler.getBean();
		Object viewName = null;
		try {
			viewName = method.invoke(object, null); // 反射执行目标方法
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new ModelAndView((String) viewName);
	}
	
}
