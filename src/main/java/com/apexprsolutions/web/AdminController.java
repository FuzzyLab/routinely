package com.apexprsolutions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apexprsolutions.domain.User;
import com.apexprsolutions.repository.ArticleRepository;
import com.apexprsolutions.repository.UserRepository;
import com.apexprsolutions.type.EncodingType;
import com.apexprsolutions.util.HmacSignerUtil;

@RestController
public class AdminController {

	private static String APP_NAME = "Apex PR Solutions";

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ArticleRepository artRepo;
	
	@Autowired
	private HmacSignerUtil hmacUtil;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView mv = new ModelAndView("adminlogin");
		mv.addObject("name", APP_NAME);
		return mv;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ModelAndView adminLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView("adminlogin");
		mv.addObject("name", APP_NAME);
		User user = userRepo.findByEmailAndPassword(email,
				hmacUtil.signWithSecretKey(EncodingType.PASSWORD.name(), password));
		if (user != null) {
			mv.addObject("user", user);
			mv.addObject("articles", artRepo.findAll());
			mv.addObject("users", userRepo.findAll());
			mv.setViewName("adminhome");
		}
		return mv;
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String db() {
		userRepo.deleteAll();
		if (userRepo.findByEmail("admin@apexprsolutions.com") == null) {
			User user = new User("Admin", "admin@apexprsolutions.com",
					hmacUtil.signWithSecretKey(EncodingType.PASSWORD.name(), "password"), "ADMIN");
			userRepo.save(user);
		}
		return "DONE";
	}

}
