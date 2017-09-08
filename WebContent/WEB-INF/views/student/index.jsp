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
<jsp:include page="../student/inc/visual.jsp"></jsp:include>
   

   
   <div id="body" class="clearfix">
      <div class="content-container">      
      
      <!-- <aside></aside> -->
<jsp:include page="../student/inc/aside.jsp"></jsp:include>
        
        <main id="main">
         <h2>student index</h2>
         
     
      </main>   
      </div>
   </div>
   
  <!-- footer -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
   
</body>
</html>








