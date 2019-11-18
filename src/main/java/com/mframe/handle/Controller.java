package com.mframe.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mframe.view.ModelAndView;

public interface Controller {
	
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
