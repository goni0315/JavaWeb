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
  <jsp:include page="../inc/header.jsp"></jsp:include>
    
 
  <!-- visual -->
<jsp:include page="inc/visual.jsp"></jsp:include>
   

   
   <div id="body" class="clearfix">
            <div class="content-container">   
      <!-- aside -->
<jsp:include page="inc/aside.jsp"></jsp:include>
        
        <main id="main">
         <h2>가입동의</h2>
         
         <div>
            <h3>경로</h3>
            <ol>
               <li>home</li>
               <li>회원정보</li>
               <li>가입동의</li>
            </ol>
         </div>
         
         <c:if test="${not empty param.error}">                  
         <div style="color: red;">
         모든 내용에 동의를 하셔야 합니다
         </div>
         </c:if>
   <form method="post">
   <div>
   <h3>개인정보 활용 동의</h3>
   <div style="width: 100%; height: 100px; overflow: scroll;">
가입된 정보는 ㅇㅇ합니다<br>
가입된 정보는 ㅇㅇ합니다<br>
가입된 정보는 ㅇㅇ합니다<br>
가입된 정보는 ㅇㅇ합니다<br>
가입된 정보는 ㅇㅇ합니다<br>
가입된 정보는 ㅇㅇ합니다<br>
가입된 정보는 ㅇㅇ합니다<br>
   </div>
   <div>
   <input type="checkbox"  name="agree"  value="ok">
   </div>
   <div>
   <input type="submit" value="가입동의">
   </div>
   </div>
     </form>    
         
      
      
      </main>   
      </div>
   </div>
   
  <!-- footer -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
   
</body>
</html>








