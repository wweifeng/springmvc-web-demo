package com.mframe.handle;

public class HandlerExecutionChain {

	private Object handler;

	// 拦截器等
	
	public HandlerExecutionChain(Object handler) {
		this.handler = handler;
	}

	public Object getHandler() {
		return handler;
	}

	public void setHandler(Object handler) {
		this.handler = handler;
	}

}
