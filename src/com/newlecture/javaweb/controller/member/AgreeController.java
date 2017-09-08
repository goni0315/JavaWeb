package com.newlecture.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.Messaging.SyncScopeHelper;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/member/agree")
public class AgreeController extends HttpServlet{	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		
//	response.sendRedirect("notice.jsp"); //?ÉàÎ°úÏ∂úÎ∞úÌïò?äîÎ∞îÏóÖ
	request.getRequestDispatcher("/WEB-INF/views/member/agree.jsp").forward(request, response); //?ù¥?ñ¥?Ñú Ï∂úÎ∞ú?ïò?äî Î∞©Î≤ï
	//redirect
	//forward
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String _agree = request.getParameter("agree");
		String agree = "no";
		
		if(_agree != null && !_agree.equals(""))
			agree = _agree;
				
						
				if(!agree.equals("ok"))
				response.sendRedirect("agree?error=1");
				else
				response.sendRedirect("join"); 
				
	//redirect
	//forward
	}
}