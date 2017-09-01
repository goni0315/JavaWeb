package com.newlecture.javaweb.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/notice-reg")
public class NoticeRegController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");		
		String content = request.getParameter("content");
		
		String sql = "INSERT INTO Notice(id, title, content, writerId) VALUES((select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice n), ?, ?, ?)";
		
		
				

		// ì¿¼ë¦¬ê°? ë³µì¡?•´ì§?ë©? ?„£ê¸°ê? ?˜?“œ?‹ˆ ?¼?‹¨ ?ë¡? ?„£ê³? ?•„?˜?—?„œ ì²˜ë¦¬?•¨

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?“œ?¼?´ë²? ë¡œë“œ
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?—°ê²? / ?¸ì¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?‹¤?–‰
			// Statement st = con.createStatement();
			// PreparedStatement?Š” ë¯¸ë¦¬ sql?„ ?„£?Š”ê²ƒì´ë¯?ë¡? ?•„?˜?—?„œ sql?„ ë¹¼ì¤˜?•¼?•¨
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
			

			// ê²°ê³¼ ê°?? ¸?˜¤ê¸?
			// ResultSet rs = st.executeQuery(sql);
			int result = st.executeUpdate();
			
			//¾÷µ¥ÀÌÆ®µÈ ·Î¿ì °¹¼ö ¾Ë·ÁÁÜ

			// ê²°ê³¼ ?‚¬?š©?•˜ê¸?

			
			
			st.close();
			con.close();
			
			

			/* out.println(list.get(0).getTitle()); */

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*response.sendRedirect("notice-detail?id="+id);*/
		response.sendRedirect("notice-list");
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	

		// response.sendRedirect("notice.jsp"); //?ƒˆë¡œì¶œë°œí•˜?Š”ë°”ì—…
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/reg.jsp").forward(request, response); // ?´?–´?„œ
																											// ì¶œë°œ?•˜?Š”
																											// ë°©ë²•
		// redirect
		// forward
	}

}
