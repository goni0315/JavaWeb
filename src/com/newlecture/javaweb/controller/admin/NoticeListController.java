package com.newlecture.javaweb.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/notice-List")
public class NoticeListController extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String _title = request.getParameter("title");
		String title=""; //ê¸°ë³¸ê°?

		if(_title != null && !_title.equals(""))
			title = _title;

		List<Notice> list = null;
		String sql = "SELECT * FROM Notice where title like ?";
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
			st.setString(1, "%" + title + "%");

			// ê²°ê³¼ ê°?? ¸?˜¤ê¸?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// ê²°ê³¼ ?‚¬?š©?•˜ê¸?
			while (rs.next()) {
				Notice n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				// ..

				list.add(n);
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
		
	request.setAttribute("list", list);
		
//	response.sendRedirect("notice.jsp"); //?ƒˆë¡œì¶œë°œí•˜?Š”ë°”ì—…
	request.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(request, response); //?´?–´?„œ ì¶œë°œ?•˜?Š” ë°©ë²•
	//redirect
	//forward
	}
	

}