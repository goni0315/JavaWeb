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

		int offset = (page - 1) * 10; // 0 10 20 30 40 µîÂ÷¼ö¿­ an=a1+(n-1)*d
		List<NoticeView> list = null;

		String sql = "SELECT * FROM NoticeView where title like ? order by regDate desc limit ?, 10";

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
			st.setString(1, "%" + query + "%");
			st.setInt(2, page);

			// ê²°ê³¼ ê°?? ¸?˜¤ê¸?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// ê²°ê³¼ ?‚¬?š©?•˜ê¸?
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

		// ì¿¼ë¦¬ê°? ë³µì¡?•´ì§?ë©? ?„£ê¸°ê? ?˜?“œ?‹ˆ ?¼?‹¨ ?ë¡? ?„£ê³? ?•„?˜?—?„œ ì²˜ë¦¬?•¨

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		NoticeView n = null;
		// JDBC ?“œ?¼?´ë²? ë¡œë“œ
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?—°ê²? / ?¸ì¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?‹¤?–‰
			// Statement st = con.createStatement();
			// PreparedStatement?Š” ë¯¸ë¦¬ sql?„ ?„£?Š”ê²ƒì´ë¯?ë¡? ?•„?˜?—?„œ sql?„ ë¹¼ì¤˜?•¼?•¨
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			// ê²°ê³¼ ê°?? ¸?˜¤ê¸?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// ê²°ê³¼ ?‚¬?š©?•˜ê¸?

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
			st.setString(3, id);

			// ê²°ê³¼ ê°?? ¸?˜¤ê¸?
			// ResultSet rs = st.executeQuery(sql);
			result = st.executeUpdate();

			// ¾÷µ¥ÀÌÆ®µÈ ·Î¿ì °¹¼ö ¾Ë·ÁÁÜ

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
		return result;

	}

}
