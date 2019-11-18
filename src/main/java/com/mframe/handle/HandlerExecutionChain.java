package com.mframe.handle;

import java.util.List;

import com.mframe.interceptor.HandlerInterceptor;

public class HandlerExecutionChain {

	private Object handler;
	
	// 拦截器等
	private List<HandlerInterceptor> interceptorList;
	
	public HandlerExecutionChain(Object handler) {
		this.handler = handler;
	}

	public Object getHandler() {
		return handler;
	}

	public void setHandler(Object handler) {
		this.handler = handler;
	}

	public List<HandlerInterceptor> getInterceptorList() {
		return interceptorList;
	}

	public void setInterceptorList(List<HandlerInterceptor> interceptorList) {
		this.interceptorList = interceptorList;
	}

}
