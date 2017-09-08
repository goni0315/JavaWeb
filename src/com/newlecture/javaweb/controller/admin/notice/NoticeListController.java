package com.newlecture.javaweb.controller.admin.notice;
		
import java.io.IOException;
import java.util.List;
		
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
		
import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;
		
@WebServlet("/admin/notice/list")
public class NoticeListController extends HttpServlet{
		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String _query = request.getParameter("title");
		String query=""; //ê¸°ë³¸ê°?
		String _page = request.getParameter("p");		
		int page = 1; // Àü´Ş‰çÀ»¶§ ±âº»°ª		
		if(_page != null && !_page.equals(""))             //Àü´ŞÀÌ µÇ¾ù´Ù¸é
		page = Integer.parseInt(_page);		
		
		if(_query != null && !_query.equals(""))
			query = _query;			
		
		NoticeDao noticeDao = new JdbcNoticeDao();		
		
	request.setAttribute("list", noticeDao.getList(page, query));
	request.setAttribute("count", noticeDao.getCount());
		
//	response.sendRedirect("notice.jsp"); //?ƒˆë¡œì¶œë°œí•˜?Š”ë°”ì—…
	request.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(request, response); //?´?–´?„œ ì¶œë°œ?•˜?Š” ë°©ë²•
	//redirect
	//forward
	}
	

}