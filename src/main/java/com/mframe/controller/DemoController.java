package com.mframe.controller;

import com.mframe.annotation.Controller;
import com.mframe.annotation.RequestMapping;

@Controller
public class DemoController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

}
