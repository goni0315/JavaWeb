package com.newlecture.javaweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/index")
public class IndexController extends HttpServlet{	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				
		
//	response.sendRedirect("notice.jsp"); //?λ‘μΆλ°ν?λ°μ
	request.getRequestDispatcher("/WEB-INF/views/admin/index.jsp").forward(request, response); //?΄?΄? μΆλ°?? λ°©λ²
	//redirect
	//forward
	}
}