package com.happyclub.controller;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.happyclub.beans.User;
import com.happyclub.beans.Login;

@Controller
public class LoginController {
	@Autowired
	UserService userService;



	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView showlogin(HttpRequest req,HttpResponse res){

		ModelAndView mav=new ModelAndView("login");
		mav.addObject("login", new Login());
		return mav;

	}
	@RequestMapping(value="/loginprocess",method=RequestMethod.POST)
	public ModelAndView loginProcess(HttpRequest res,HttpResponse req
			,@ModelAttribute("login")Login login){
		ModelAndView mav=null;

		User user = userService.validateUser(login);
		if(user != null){
			mav=new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		}else{
			mav=new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}




		return mav;


	}

}
