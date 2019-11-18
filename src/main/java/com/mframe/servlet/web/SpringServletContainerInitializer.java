package com.mframe.servlet.web;

import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

/**
 * Tomcat容器启动Servlet时会加载该类,必须在META-INF下面services目录下创建
 * 调用onStartup方法
 * Set<Class<?>> c 会获取到所有实现了WebApplicationInitializer的子类
 * @author wwf
 *
 */
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		
		// 循环执行WebApplicationInitializer子类的onStartup方法
		for(Class<?> classInfo : c) {
			try {
				Method method = classInfo.getMethod("onStartup", ServletContext.class);
				Object object = classInfo.newInstance();
				method.invoke(object, ctx);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}

}
