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
		 * String id=""; //Í∏∞Î≥∏Í∞?
		 * 
		 * if(_id != null && !_id.equals("")) id = _id;
		 */

		// Model
		/* list = new ArrayList<>(); */
		Notice n = null;

		/* List<Notice> list = null; */
		String sql = "SELECT * FROM Notice where id=?";

		// ÏøºÎ¶¨Í∞? Î≥µÏû°?ï¥Ïß?Î©? ?Ñ£Í∏∞Í? ?ûò?ìú?ãà ?ùº?ã® ?Î°? ?Ñ£Í≥? ?ïÑ?ûò?óê?Ñú Ï≤òÎ¶¨?ï®

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?ìú?ùº?ù¥Î≤? Î°úÎìú
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?ó∞Í≤? / ?ù∏Ï¶?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?ã§?ñâ
			// Statement st = con.createStatement();
			// PreparedStatement?äî ÎØ∏Î¶¨ sql?ùÑ ?Ñ£?äîÍ≤ÉÏù¥ÎØ?Î°? ?ïÑ?ûò?óê?Ñú sql?ùÑ ÎπºÏ§ò?ïº?ï®
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			// Í≤∞Í≥º Í∞??†∏?ò§Í∏?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Í≤∞Í≥º ?Ç¨?ö©?ïòÍ∏?

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

		// response.sendRedirect("notice.jsp"); //?ÉàÎ°úÏ∂úÎ∞úÌïò?äîÎ∞îÏóÖ
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/edit.jsp").forward(request, response); // ?ù¥?ñ¥?Ñú
																											// Ï∂úÎ∞ú?ïò?äî
																											// Î∞©Î≤ï
		// redirect
		// forward
	}

}
