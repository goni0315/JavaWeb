package com.newlecture.javaweb.controller.admin.notice;

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

@WebServlet("/admin/notice/edit")
public class NoticeEditController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String title = request.getParameter("title");		
		String content = request.getParameter("content");
		
		NoticeDao noticeDao=new JdbcNoticeDao();
		int result = noticeDao.update(id, title, content);
		
		
		
		response.sendRedirect("detail?id="+id);
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		/*
		 * String id=""; //κΈ°λ³Έκ°?
		 * 
		 * if(_id != null && !_id.equals("")) id = _id;
		 */

		// Model
		/* list = new ArrayList<>(); */
		Notice n = null;

		/* List<Notice> list = null; */
		String sql = "SELECT * FROM Notice where id=?";

		// μΏΌλ¦¬κ°? λ³΅μ‘?΄μ§?λ©? ?£κΈ°κ? ??? ?Ό?¨ ?λ‘? ?£κ³? ???? μ²λ¦¬?¨

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ??Ό?΄λ²? λ‘λ
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?°κ²? / ?Έμ¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?€?
			// Statement st = con.createStatement();
			// PreparedStatement? λ―Έλ¦¬ sql? ?£?κ²μ΄λ―?λ‘? ???? sql? λΉΌμ€?Ό?¨
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			// κ²°κ³Ό κ°?? Έ?€κΈ?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// κ²°κ³Ό ?¬?©?κΈ?

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

		// response.sendRedirect("notice.jsp"); //?λ‘μΆλ°ν?λ°μ
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/edit.jsp").forward(request, response); // ?΄?΄?
																											// μΆλ°??
																											// λ°©λ²
		// redirect
		// forward
	}

}
