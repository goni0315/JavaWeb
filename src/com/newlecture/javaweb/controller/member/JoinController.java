package com.newlecture.javaweb.controller.member;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Member;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/member/join")
public class JoinController extends HttpServlet{	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		
//	response.sendRedirect("notice.jsp"); //?ƒˆë¡œì¶œë°œí•˜?Š”ë°”ì—…
	request.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(request, response); //?´?–´?„œ ì¶œë°œ?•˜?Š” ë°©ë²•
	//redirect
	//forward
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String id = request.getParameter("id");
		String pwds = request.getParameter("pwd");
		String islunar = request.getParameter("is-lunar");
		String name = null;
		String gender = null;
		String birthday = null;
		String phone = null;
		String email = null;
		
		
		/*System.out.printf("id:%s, pwd:%s, islunar:%s", id,pwds[0],islunar);		*/
		
	/*	MemberDao memberDao = new JdbcMemberDao();
		int result = memberDao.insert(id, pwds[0], name, gender, birthday, phone, email);*/
	
		Member member = new Member(id, pwds, name, gender, birthday, phone, email);
		
		int result = member.insert(member);
		
		
		if(result>0)				
	response.sendRedirect("confirm.jsp"); //?ƒˆë¡œì¶œë°œí•˜?Š”ë°”ì—…
		else
			response.sendRedirect("../error?code=1234");
	//redirect
	//forward
	}
}