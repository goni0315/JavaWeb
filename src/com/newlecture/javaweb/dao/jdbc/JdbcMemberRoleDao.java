package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.newlecture.javaweb.entity.NoticeView;

public class JdbcMemberRoleDao implements MemberRoleDao {	

	@Override
	public String getDefaultRole(String memberId) {
		
		String defaultRoleId = null;

		String sql = "select roleId from MemberRole where memberId=? and defaultRole=1";

		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

	
		try {
			Class.forName("com.mysql.jdbc.Driver");

		
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

		
			// Statement st = con.createStatement();
			// PreparedStatement?
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, memberId);

			// ê²°ê³¼ ê°?? ¸?˜¤ê¸?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();



			// ê²°ê³¼ ?‚¬?š©?•˜ê¸?
			if (rs.next()) {
				
				defaultRoleId =rs.getString("roleId");
			
				
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

		return defaultRoleId;	
		
	}

}
