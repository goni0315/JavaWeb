import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/notice")
public class Nana extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// utf8 읽어야 하니 보낼때도 utf8로 보내야함
		response.setCharacterEncoding("utf-8");
		// 브라우저에게 html파일이라는걸 알려줌 서블릿 바로 아래
		response.setContentType("text/html; chatset=utf-8");

		PrintWriter out = response.getWriter();
		// OutputStream os = response.getOutputStream();
		// PrintStream out = new PrintStream(os);

		String title = request.getParameter("title");
		System.out.println(title);
		List<Notice> list = null;
		String sql = "SELECT * FROM Notice where title like ?";
		// 쿼리가 복잡해지면 넣기가 힘드니 일단 ?로 넣고 아래에서 처리함

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC 드라이버 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			// Statement st = con.createStatement();
			// PreparedStatement는 미리 sql을 넣는것이므로 아래에서 sql을 빼줘야함
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");

			// 결과 가져오기
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// 결과 사용하기
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
		// --------------view---------------

		
		//검색을하고 뒤로 가기 없이 또 검색을 하기 위해
		//html을 자바에서 표현을 했는데 넘나 불편한것
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("	<title>Insert title here</title>");
		out.println("</head>");
		out.println("	<body>");
		out.println("	<form action=\"notice\" method=\"get\">");
		out.println("	<label>검색어</label>");
		out.println("	<input type=\"text\" name=\"title\">");
		out.println("	<input type=\"submit\">");
		out.println("</form>");			
	
		

		// 콘솔에 출력 System.out.println(list.get(0).getTitle());
		for (Notice n : list)
			out.println(n.getTitle() + "<br/>");
		out.println("	</body>");
		out.println("	</html>");

	}
}
