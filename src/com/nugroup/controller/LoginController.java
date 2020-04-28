package com.nugroup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nugroup.entity.User;
import com.nugroup.service.LoginService;




@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/processlogin")
	public String authUser(HttpServletRequest request,Model theModel,@RequestParam("fEmail") String email,@RequestParam("fPassword") String password)
	{
		
		HttpSession session = request.getSession();
		User userDB = loginService.getUser(email);
	
		email = email.trim();
		password =password.trim();
		if(email.equals(null)|| email.equals("") || password.equals(null)|| password.equals("") ){
			theModel.addAttribute("errormsg", "Enter all required");
			return "login";
			}
		if(userDB==null) {
			theModel.addAttribute("errormsg", "Email not found");
			return "login";
			
		}
		
		if(userDB.getEnabled()==0) {
			theModel.addAttribute("errormsg", "Account not yet activated please contact admin");
			return "login";
		}
if(userDB.getEmail().equals(email) && userDB.getPassword().equals(password)) {
	session.setAttribute("loggedUser", userDB);
	
	if(userDB.getUserDetail().getRole().equals("Student")) {
		return "studenthome";
	}
	if(userDB.getUserDetail().getRole().equals("Admin")) {
		return "adminhome";
	
	
}



}
	theModel.addAttribute("errormsg","Incorrect email or password");


	//theModel.addAttribute("errormsg2", "Incorrect user name or password");
	return "login";




	}
	
	
	}




