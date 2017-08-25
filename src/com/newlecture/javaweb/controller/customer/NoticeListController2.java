package com.newlecture.javaweb.controller.customer;

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

@WebServlet("/customer/notice-detail")
public class NoticeListController2 extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String _title = request.getParameter("title");
		String title=""; //κΈ°λ³Έκ°?

		if(_title != null && !_title.equals(""))
			title = _title;

		List<Notice> list = null;
		String sql = "SELECT * FROM Notice where title like ?";
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
			st.setString(1, "%" + title + "%");

			// κ²°κ³Ό κ°?? Έ?€κΈ?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// κ²°κ³Ό ?¬?©?κΈ?
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
		
//	response.sendRedirect("notice.jsp"); //?λ‘μΆλ°ν?λ°μ
	request.getRequestDispatcher("/WEB-INF/views/customer/notice/list1.jsp").forward(request, response); //?΄?΄? μΆλ°?? λ°©λ²
	//redirect
	//forward
	}
	

}