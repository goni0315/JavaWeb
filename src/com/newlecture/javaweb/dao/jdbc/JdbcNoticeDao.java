package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.newlecture.javaweb.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao {

	public List<NoticeView> getList(int page, String query) {

		int offset = (page - 1) * 10; // 0 10 20 30 40 �������� an=a1+(n-1)*d
		List<NoticeView> list = null;

		String sql = "SELECT * FROM NoticeView where title like ? order by regDate desc limit ?, 10";

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
			st.setString(1, "%" + query + "%");
			st.setInt(2, page);

			// 결과 �??��?���?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// 결과 ?��?��?���?
			while (rs.next()) {
				NoticeView n = new NoticeView();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setWriterId(rs.getString("writerId"));
				n.setWriterName(rs.getString("writerName"));
				n.setContent(rs.getString("content"));
				n.setRegDate(rs.getDate("regDate"));
				n.setHit(rs.getInt("hit"));
				n.setCountCmt(rs.getInt("countCmt"));

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

		return list;
	}

	public int getCount() {

		int count = 0;

		String sqlCount = "SELECT count(id) count FROM Notice;";

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

			Statement stCount = con.createStatement();
			ResultSet rsCount = stCount.executeQuery(sqlCount);
			rsCount.next();
			count = rsCount.getInt("count");

			rsCount.close();
			con.close();

			/* out.println(list.get(0).getTitle()); */

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public NoticeView get(String id) {

		String sql = "SELECT * FROM Notice where id=?";

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
			st.setString(1, id);

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

		return n;
	}

	@Override
	public int update(String id, String title, String content) {

		int result = 0;

		String sql = "UPDATE Notice SET title=?, content=? where id=?";

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
			st.setString(3, id);

			// 결과 �??��?���?
			// ResultSet rs = st.executeQuery(sql);
			result = st.executeUpdate();

			// ������Ʈ�� �ο� ���� �˷���

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
		return result;

	}

}
