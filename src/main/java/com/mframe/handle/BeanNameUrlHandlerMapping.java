package com.mframe.handle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.mframe.annotation.ComponentScan;
import com.mframe.config.SpringMvcConfig;
import com.mframe.util.ReflexUtils;

public class BeanNameUrlHandlerMapping implements HandlerMapping {

	private Map<String, Controller> registry = new HashMap<String, Controller>();
	
	public void initHandlerMapping() {
		ComponentScan componentScan = SpringMvcConfig.class.getDeclaredAnnotation(ComponentScan.class);
		String value = componentScan.value();
		if(StringUtils.isEmpty(value)) {
			return;
		}
		// 扫描获取到ComponentScan配置的包路径下所有的类
		Set<Class<?>> classInfos = ReflexUtils.getClasses(value);
		for(Class<?> classInfo : classInfos) {
			Class<?>[] interfacesClassInfos = classInfo.getInterfaces();
			if(interfacesClassInfos == null || interfacesClassInfos.length == 0) {
				continue;
			}
			for(Class<?> interfacesClassInfo : interfacesClassInfos) {
				if(Controller.class.isAssignableFrom(interfacesClassInfo)) { // 是否实现了Controller接口
					Controller controller = null;
					try {
						controller = (Controller) classInfo.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
					String simpleName = controller.getClass().getSimpleName();
					String beanNameUrl = com.mframe.util.StringUtils.lowerFirstCapse(simpleName);
					registry.put("/" + beanNameUrl, controller);
				}
			}
		}
	}

	public HandlerExecutionChain getHandler(String url) {
		Controller controller = registry.get(url);
		if(controller == null) {
			return null;
		}
		return new HandlerExecutionChain(controller);
	}

}
