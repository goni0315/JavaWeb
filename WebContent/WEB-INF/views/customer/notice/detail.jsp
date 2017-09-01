<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>

   
  <!-- header -->
  <jsp:include page="../../inc/header.jsp"></jsp:include>
    
 
  <!-- visual -->
<jsp:include page="../../customer/inc/visual.jsp"></jsp:include>
   

   
   <div id="body" class="clearfix">
      <div class="content-container">      
      
      <!-- <aside></aside> -->
<jsp:include page="../../customer/inc/aside.jsp"></jsp:include>
        
        <main id="main">
         <h2>공지사항</h2>
         
         <div>
            <h3>경로</h3>
            <ol>
               <li>home</li>
               <li>고객센터</li>
               <li>공지사항</li>
            </ol>
         </div>
   
         <div>
            <h3>공지사항 검색 폼</h3>
            <form action="notice" method="get">
               <label>검색어</label>
               <input type="text" name="title" />
               <input type="submit" />
            </form>
         </div>
         
         
         <table class="table">
         <tr>
         <th>제목</th>
         <td colspan="3">${notice.title}</td>
         </tr>   
         <tr>
         <th>작성일</th>
         <td colspan="3">${notice.regDate}</td>
         </tr>  
         <tr>
         <th>작성자</th>
         <td>${notice.writerId}</td>
         <th>조회수</th>
         <td>${notice.hit}</td> 
         </tr>   
         <tr>
         <th>첨부파일</th>
         <td colspan="3"></td>
         </tr>
         <tr>
         <td colspan="4">${notice.content}</td>
         </tr>
         </table>
         <div>
         <a href="notice-list" class="btn btn-default">목록</a>
         <a href="notice-edit?id=${notice.id}" class="btn btn-default">수정</a>
         <a href="notice-del" class="btn btn-default">삭제</a>
         
         </div>
       
         <span class="btn btn-default" href="">글쓰기</span>
         <a class="btn-img btn-cancel" href="">취소</a>
      </main>   
      </div>
   </div>
   
  <!-- footer -->
  <jsp:include page="../../inc/footer.jsp"></jsp:include>
   
</body>
</html>








