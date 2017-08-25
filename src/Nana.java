import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.newlecture.javaweb.entity.Notice;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/hello")

public class Nana extends HttpServlet{
	
   public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
   
   {
	  response.setCharacterEncoding("UTF-8");				//웹에서 읽을때 utf-8로 읽음
	  response.setContentType("text/html; chatset=UTF-8"); 	//보낼때 utf-8로 보냄
      
	  PrintWriter out = response.getWriter();		//문자 단위에 특화되 있는 것
	  /*OutputStream os = response.getOutputStream(); //바이트단위에 특화된 것

      PrintStream out= new PrintStream(os);*/
	  
	  String title = request.getParameter("title");
	  System.out.println(title);
 
	  //----------------------------------------------------------------
	  List<Notice> list = null;
	  
      String sql = "SELECT *FROM Notice where title like ?";

      String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

      // JDBC 드라이버 로드
      try {
			Class.forName("com.mysql.jdbc.Driver");
	
			// 연결 / 인증
		    Connection con = DriverManager.getConnection(url, "sist", "cclass");
	
		    // 실행
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setString(1, "%" + title + "%");
		    
		    // 결과 가져오기
		    ResultSet rs = st.executeQuery();
		    
	
		    // Model 
		    list = new ArrayList<>();
		      
		    // 결과 사용하기
		    while (rs.next()) {
		       Notice n = new Notice();
		       n.setId(rs.getString("ID"));
		       n.setTitle(rs.getString("TITLE"));
		       //..
		         
		       list.add(n);
		    }
		      rs.close();
		      st.close();
		      con.close();
		      
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

/*----------------------view---------------------------*/
	  out.println("<html>");
	  out.println("<head>");
	  out.println("<meta charset=\"UTF-8\">");
	  out.println("<title>Insert title here</title>");
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<form action=\"hello\">");
	  out.println("<label>검색어</label>");
	  out.println("<input type=\"text\" name=\"title\"/>");
	  out.println("<input type=\"submit\"/>");
	  out.println("</form>");
	  	for(Notice n : list)
	  	  out.println(n.getTitle() + "<br/>"); //텍스트가 아니기 때문에 더이상 println이 아니라 web문서에서 줄바꿀 수 있는 태그를 이용해야 한다.
      	
	  	out.println("</body>");
	  	out.println("</html>");
      
      
      

      
   }
}