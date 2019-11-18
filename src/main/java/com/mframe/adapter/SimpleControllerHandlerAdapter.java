package com.mframe.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mframe.handle.Controller;
import com.mframe.view.ModelAndView;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {

	public boolean supports(Object handler) {
		return (handler instanceof Controller);
	}

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return ((Controller) handler).handleRequest(request, response);
	}

}
