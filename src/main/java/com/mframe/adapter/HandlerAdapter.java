package com.mframe.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mframe.view.ModelAndView;

public interface HandlerAdapter {
	
	boolean supports(Object handler);
	
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

}
