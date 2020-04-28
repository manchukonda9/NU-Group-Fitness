package com.nugroup.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nugroup.service.LoginService;

public class ScriptHandlerInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String interEmail = request.getParameter("fEmail");
		System.out.println(interEmail);
		if(interEmail.contains("<") || interEmail.contains("/>") || interEmail.contains("*") || interEmail.contains(">")) {
			response.sendRedirect("/nugroup-backend/blacksheep");
			
		}
		else {
			System.out.println("its a legit login");
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("Inside after completion");
	}
	

}
