package com.newlecture.javaweb.controller.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/customer/notice-detail")
public class NoticeDetailController extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String id = request.getParameter("id");
		/*String id=""; //κΈ°λ³Έκ°?

		if(_id != null && !_id.equals(""))
			id = _id;*/

		// Model
					/*list = new ArrayList<>();*/
		NoticeDao dao= new JdbcNoticeDao();
		Notice n = dao.get(id);
				
		/*List<Notice> list = null;*/
				
	request.setAttribute("notice", n);
		
//	response.sendRedirect("notice.jsp"); //?λ‘μΆλ°ν?λ°μ
	request.getRequestDispatcher("/WEB-INF/views/customer/notice/detail.jsp").forward(request, response); //?΄?΄? μΆλ°?? λ°©λ²
	//redirect
	//forward
	}
}