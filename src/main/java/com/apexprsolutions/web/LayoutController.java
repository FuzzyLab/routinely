package com.apexprsolutions.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LayoutController {

	private static String APP_NAME = "Dietinely";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("name", APP_NAME);
		return mv;
	}

}
