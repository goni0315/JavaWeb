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
      
      <!-- <aside></aside> -->
<jsp:include page="inc/aside.jsp"></jsp:include>
        
        <main id="main">
         <h2>회원가입</h2>
         
         <div>
            <h3>경로</h3>
            <ol>
               <li>home</li>
               <li>회원정보</li>
               <li>회원가입</li>
            </ol>
         </div>
   <form method="post">
         <fieldset>
            <legend>회원정보</legend>
            <table>
               <tbody>
                  <tr>
                     <th><label>아이디</label></th>
                     <td>
                        <input name="id" value=""/>
                     </td>
                  </tr>
                  <tr>
                     <th><label>비밀번호</label></th>
                     <td><input name="pwd" /></td>
                  </tr>
                  
                  <tr>                     
                     <td><input type="submit" name="btn" value="로그인" /></td>
                  </tr>
               </tbody>
            </table>
         </fieldset>
      </form>
         
         
      
         
       
     
      </main>   
      </div>
   </div>
   
  <!-- footer -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
   
</body>
</html>








