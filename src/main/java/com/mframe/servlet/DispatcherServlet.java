package com.mframe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mframe.adapter.HandlerAdapter;
import com.mframe.adapter.RequestMappingHandlerAdapter;
import com.mframe.adapter.SimpleControllerHandlerAdapter;
import com.mframe.handle.BeanNameUrlHandlerMapping;
import com.mframe.handle.HandlerExecutionChain;
import com.mframe.handle.HandlerMapping;
import com.mframe.handle.RequestMappingHandlerMapping;
import com.mframe.view.ModelAndView;

@SuppressWarnings("serial")
public class DispatcherServlet extends FrameworkServlet {
	
	private List<HandlerAdapter> handlerAdapters;
	
	private List<HandlerMapping> handlerMappings;
	
	public DispatcherServlet () {
		handlerAdapters = new ArrayList<HandlerAdapter>();
		handlerMappings = new ArrayList<HandlerMapping>();
	}
	
	@Override
	protected void onRefresh() {
		initStrategies();
	}
	
	protected void initStrategies() {
		// 初始化HandlerMapping
		initHandlerMapping();
		// 初始化HandlerAdapters
		initHandlerAdapters();
	}
	
	protected void initHandlerMapping() {
		handlerMappings.add(new RequestMappingHandlerMapping());
		handlerMappings.add(new BeanNameUrlHandlerMapping());
		for(HandlerMapping handlerMapping : handlerMappings) {
			handlerMapping.initHandlerMapping();
		}
	}
	
	protected void initHandlerAdapters() {
		handlerAdapters.add(new RequestMappingHandlerAdapter());
		handlerAdapters.add(new SimpleControllerHandlerAdapter());
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDispatch(request, response);
	}
	
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {// 获取请求的URL
			String url = request.getRequestURI(); 
			String contextPath = request.getContextPath();
			// 获取Handler
			HandlerExecutionChain mappedHandler = getHandler(url.replace(contextPath, ""));
			if(mappedHandler == null) {
				noHandlerFound(request, response);
				return;
			}
			// 根据 Handler获取适配器
			HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
			if(ha == null) {
				noHandlerFound(request, response);
				return;
			}
			// 获取适配器使用反射执行方法,返回视图
			ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());
			
			// 转发请求
			render(mv, request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected HandlerAdapter getHandlerAdapter(Object handler) {
		for (HandlerAdapter ha : this.handlerAdapters) {
			if (ha.supports(handler)) {
				return ha;
			}
		}
		return null;
	}

	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewName = mv.getViewName();
		request.getRequestDispatcher("/WEB-INF/view/" + viewName + ".jsp").forward(request, response);
	}
	
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	private HandlerExecutionChain getHandler(String url) {
		for(HandlerMapping hm : this.handlerMappings) {
			HandlerExecutionChain handler = hm.getHandler(url);
			if(handler != null) {
				return handler;
			}
		}
		return null;
	}
	
}
