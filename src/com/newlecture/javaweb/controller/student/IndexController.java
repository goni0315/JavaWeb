package com.newlecture.javaweb.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/student/index")
public class IndexController extends HttpServlet{	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				
		
//		response.sendRedirect("notice.jsp"); //?ƒˆë¡œì¶œë°œí•˜?Š”ë°”ì—…
		request.getRequestDispatcher("/WEB-INF/views/student/index.jsp").forward(request, response); //?´?–´?„œ ì¶œë°œ?•˜?Š” ë°©ë²•
		//redirect
		//forward
	}
}