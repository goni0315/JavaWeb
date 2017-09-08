package com.newlecture.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet{	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		session.invalidate();
		
	response.sendRedirect("../index"); 
	//request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response); //?΄?΄? μΆλ°?? λ°©λ²
	//redirect
	//forward
	}
	
}