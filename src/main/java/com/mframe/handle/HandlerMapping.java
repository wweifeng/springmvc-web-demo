package com.mframe.handle;

public interface HandlerMapping {
	
	public void initHandlerMapping();
	
	public HandlerExecutionChain getHandler(String url);

}
