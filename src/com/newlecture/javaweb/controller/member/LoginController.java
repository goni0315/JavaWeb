package com.newlecture.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.Messaging.SyncScopeHelper;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Member;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/member/login")
public class LoginController extends HttpServlet{	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		
//	response.sendRedirect("notice.jsp"); //?��로출발하?��바업
	request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response); //?��?��?�� 출발?��?�� 방법
	//redirect
	//forward
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String id = request.getParameter("id"); 
		String pwd = request.getParameter("pwd"); 

		MemberDao memberDao = new JdbcMemberDao();
		Member member = memberDao.get(id);
		
		if(member == null)
			response.sendRedirect("login?error");
		else if(!member.getPwd().equals(pwd))
			response.sendRedirect("login?error");
		else {//��������
			
			//���� ������� ���������� �����ϴ� �����
			//��������� �����ڿ����
			//��Ű����� ��Ʈ��ũ�ڿ����
			
			//��� ������� �������� �����ϴ� �����
			//application
			
			request.getSession().setAttribute("id", id);
			
			String returnURL =request.getParameter("returnURL");
			if(returnURL!=null)
				response.sendRedirect(returnURL);
			else				
			response.sendRedirect("../index"); 
		}
				
	}
}