package com.newlecture.javaweb.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberRoleDao;

@WebServlet("/member/home")
public class HomeController extends HttpServlet{	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		
		
		HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();		 
		 
		 Object _memberId = session.getAttribute("id");
		 
		 
		 //로그인한적이 없으면 로그인을 하러감
		  if(_memberId==null)
			  response.sendRedirect("login?returnURL=home");
			  else { 
				  String memberId = _memberId.toString();
				  
				  MemberRoleDao memberRoleDao = new JdbcMemberRoleDao();
				  String defaultRoleId = memberRoleDao.getDefaultRole(memberId);				  
				  
				  //2로그인하고오면
				  
				  if(defaultRoleId.equals("ROLE_ADMIN")) {
				  response.sendRedirect("../admin/index"); 
				  }else if(defaultRoleId.equals("ROLE_STUDENT")) {
				  response.sendRedirect("../student/index");
				  }
				  else
				  response.sendRedirect("../index"); 
			  }
		
		

		  
		  
	}
	

}