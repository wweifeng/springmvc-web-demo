package com.mframe.view;

/**
 * 视图对象
 * @author wwf
 *
 */
public class ModelAndView {
	
	private String viewName;

	public ModelAndView(String viewName) {
		super();
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
