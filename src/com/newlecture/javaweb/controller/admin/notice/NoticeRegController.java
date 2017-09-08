package com.newlecture.javaweb.controller.admin.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/notice/reg")
public class NoticeRegController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");		
		String content = request.getParameter("content");
		
		String sql = "INSERT INTO Notice(id, title, content, writerId) VALUES((select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice n), ?, ?, ?)";
		
		
				

		// 荑쇰━媛? 蹂듭옟?빐吏?硫? ?꽔湲곌? ?옒?뱶?땲 ?씪?떒 ?濡? ?꽔怨? ?븘?옒?뿉?꽌 泥섎━?븿

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?뱶?씪?씠踰? 濡쒕뱶
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?뿰寃? / ?씤利?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?떎?뻾
			// Statement st = con.createStatement();
			// PreparedStatement?뒗 誘몃━ sql?쓣 ?꽔?뒗寃껋씠誘?濡? ?븘?옒?뿉?꽌 sql?쓣 鍮쇱쨾?빞?븿
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
			

			// 寃곌낵 媛??졇?삤湲?
			// ResultSet rs = st.executeQuery(sql);
			int result = st.executeUpdate();
			
			//업데이트된 로우 갯수 알려줌

			// 寃곌낵 ?궗?슜?븯湲?

			
			
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
		response.sendRedirect("list");
		
	}
	
	@Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	      
	      response.setContentType("text/html; charset=UTF-8");
	      response.setCharacterEncoding("UTF-8");
	      PrintWriter out = response.getWriter();
	      HttpSession session = request.getSession();
	      if(session.getAttribute("id")==null)
	         out.println("<script>alert('로그인이 필요한 페이지입니다.');location.href='../../member/login?returnURL=../admin/notice/reg';</script>");
	      else 
	         request.getRequestDispatcher("/WEB-INF/views/admin/notice/reg.jsp").forward(request, response);

	      

	      // request.setAttribute("list", list); response.sendRedirect("notice.jsp");
	      
	   }
	

}