package com.mframe.handle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.mframe.annotation.ComponentScan;
import com.mframe.annotation.Controller;
import com.mframe.annotation.RequestMapping;
import com.mframe.config.SpringMvcConfig;
import com.mframe.util.ReflexUtils;

/**
 * 初始化URL和Controller映射关系
 * @author wwf
 *
 */
public class RequestMappingHandlerMapping implements HandlerMapping {
	
	private Map<String, HandlerMethod> registry = new HashMap<String, HandlerMethod>();
	
	public void initHandlerMapping() {
		
		ComponentScan componentScan = SpringMvcConfig.class.getDeclaredAnnotation(ComponentScan.class);
		String value = componentScan.value();
		if(StringUtils.isEmpty(value)) {
			return;
		}
		// 扫描获取到ComponentScan配置的包路径下所有的类
		Set<Class<?>> classInfos = ReflexUtils.getClasses(value);
		for(Class<?> classInfo : classInfos) {
			// 判断类是否有Controller注解
			Controller controller = classInfo.getDeclaredAnnotation(Controller.class);
			if(controller == null) {
				continue;
			}
			// 获取到Controller的所有方法
			Method[] methods = classInfo.getDeclaredMethods();
			for(Method method : methods) {
				// 判断方法是否有RequestMapping注解
				RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
				if(requestMapping == null) {
					continue;
				}
				String requestUrl = requestMapping.value();
				registry.put(requestUrl, new HandlerMethod(classInfo, method));
			}
		}
	}

	public HandlerExecutionChain getHandler(String url) {
		HandlerMethod handlerMethod = registry.get(url);
		if(handlerMethod == null) {
			return null;
		}
		return new HandlerExecutionChain(handlerMethod);
	}
	
	/*public HandlerMethod getHanderMetod(String url) {
		return registry.get(url);
	}*/

}
