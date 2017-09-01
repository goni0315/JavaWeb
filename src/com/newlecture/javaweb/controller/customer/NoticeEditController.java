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

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/customer/notice-edit")
public class NoticeEditController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String title = request.getParameter("title");		
		String content = request.getParameter("content");
		
		NoticeDao noticeDao=new JdbcNoticeDao();
		int result = noticeDao.update(id, title, content);
		
		
		
		response.sendRedirect("notice-detail?id="+id);
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		/*
		 * String id=""; //기본�?
		 * 
		 * if(_id != null && !_id.equals("")) id = _id;
		 */

		// Model
		/* list = new ArrayList<>(); */
		Notice n = null;

		/* List<Notice> list = null; */
		String sql = "SELECT * FROM Notice where id=?";

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
			st.setString(1, id);

			// 결과 �??��?���?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// 결과 ?��?��?���?

			while (rs.next()) {
				n = new Notice();

				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setWriterId(rs.getString("writerId"));
				n.setContent(rs.getString("content"));
				n.setHit(rs.getInt("hit"));

				// ..

			}
			rs.close();
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

		request.setAttribute("notice", n);

		// response.sendRedirect("notice.jsp"); //?��로출발하?��바업
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/edit.jsp").forward(request, response); // ?��?��?��
																											// 출발?��?��
																											// 방법
		// redirect
		// forward
	}

}
