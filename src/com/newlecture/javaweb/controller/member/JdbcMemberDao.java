package com.newlecture.javaweb.controller.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.entity.Member;
import com.newlecture.javaweb.entity.NoticeView;

public class JdbcMemberDao implements MemberDao {


	@Override
	public int insert(String id, String pwd, String name, String gender, String birthday, String phone, String email) {
		// TODO Auto-generated method stub
		return insert(new Member(id, pwd, name, null, null, phone, email));
	}


	@Override
	public int insert(Member member) {
		
		int result = 0;
		String sql = "INSERT INTO Member(id, pwd, name, gender, birthday, phone, email) VALUES(?,?,?,?,?,?,?) ";

		// 쿼리�? 복잡?���?�? ?��기�? ?��?��?�� ?��?�� ?�? ?���? ?��?��?��?�� 처리?��

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		NoticeView n = null;
		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?��?��
			// Statement st = con.createStatement();
			// PreparedStatement?�� 미리 sql?�� ?��?��것이�?�? ?��?��?��?�� sql?�� 빼줘?��?��
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, member.getId());
			st.setString(2, member.getPwd());
			st.setString(3, member.getName());
			st.setString(4, member.getGender());
			st.setString(5, member.getBirthday());
			st.setString(6, member.getPhone());
			st.setString(7, member.getEmail());

			// 결과 �??��?���?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// 결과 ?��?��?���?

			while (rs.next()) {
				n = new NoticeView();

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

		return result;	
		

}


	@Override
	public Member get(String id) {
		String sql = "SELECT * FROM Member where id=?";

		// 쿼리�? 복잡?���?�? ?��기�? ?��?��?�� ?��?�� ?�? ?���? ?��?��?��?�� 처리?��

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		Member m = null;
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
	/*		this.id = id;
			this.pwd = pwd;
			this.name = name;
			this.gender = gender;
			this.birthday = birthday;
			this.phone = phone;
			this.email = email;
			this.regDate = regDate;*/

			while (rs.next()){
				m = new Member(

				rs.getString("id"),
				rs.getString("pwd"),
				rs.getString("name"),
				rs.getString("gender"),
				rs.getString("birthday"),
				rs.getString("phone"),
				rs.getString("email")
						);

			

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

		return m;
	}
}