package com.apexprsolutions.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apexprsolutions.domain.User;
import com.apexprsolutions.repository.UserRepository;
import com.apexprsolutions.type.EncodingType;
import com.apexprsolutions.util.HmacSignerUtil;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HmacSignerUtil hmacUtil;

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public ModelAndView getAddUser(@RequestParam("email") String email,
			@RequestParam("signature") String signature) {
		ModelAndView mv = new ModelAndView("adduser");
		//mv.addObject("message", "WELCOME");
		mv.addObject("email", email);
		mv.addObject("signature", signature);
		return mv;
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ModelAndView addUser(@RequestParam("newname") String newname, @RequestParam("newemail") String newemail,
			@RequestParam("email") String email, @RequestParam("signature") String signature) throws IOException {
		ModelAndView mv = new ModelAndView("adduser");
		mv.addObject("email", email);
		mv.addObject("signature", signature);
		User user = userRepo.findByEmailAndPassword(email, signature);
		String msg = "FAILURE";
		if (user != null && newemail != null && !newname.isEmpty() && newemail != null && !newemail.isEmpty()) {
			User newuser = new User(newname, newemail,
					hmacUtil.signWithSecretKey(EncodingType.PASSWORD.name(), "password"), "ADMIN");
			userRepo.save(newuser);
			msg = "SUCCESS";
		}
		//mv.addObject("message", msg);
		return mv;
	}

}
