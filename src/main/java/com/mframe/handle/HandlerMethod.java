package com.mframe.handle;

import java.lang.reflect.Method;

/**
 * 封装requestmapping
 * @author wwf
 *
 */
public class HandlerMethod {
	
	private Object bean;

	private Method method;

	public HandlerMethod(Class<?> classInfo, Method method) {
		this.method = method;
		try {
			this.bean = classInfo.newInstance();
		} catch (Exception e) {
		}
	}
	
	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
