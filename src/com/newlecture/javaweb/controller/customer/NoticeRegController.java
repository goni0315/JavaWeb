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
		
		
				

		// 쿼리�? 복잡?���?�? ?��기�? ?��?��?�� ?��?�� ?�? ?���? ?��?��?��?�� 처리?��

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?��?��
			// Statement st = con.createStatement();
			// PreparedStatement?�� 미리 sql?�� ?��?��것이�?�? ?��?��?��?�� sql?�� 빼줘?��?��
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
			

			// 결과 �??��?���?
			// ResultSet rs = st.executeQuery(sql);
			int result = st.executeUpdate();
			
			//������Ʈ�� �ο� ���� �˷���

			// 결과 ?��?��?���?

			
			
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

	

		// response.sendRedirect("notice.jsp"); //?��로출발하?��바업
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/reg.jsp").forward(request, response); // ?��?��?��
																											// 출발?��?��
																											// 방법
		// redirect
		// forward
	}

}
