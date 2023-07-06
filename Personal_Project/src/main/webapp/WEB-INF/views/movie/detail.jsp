<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="container">
   <nav>
      <ul class="breadcrumb">
         <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath }/">Home</a>
         </li>
         <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath }/movie/list">갤러리 목록</a>
         </li>
         <li class="breadcrumb-item active">상세보기</li>
      </ul>
   </nav>
   <div class="card mb-3">
      <img class="card-img-top" src="${pageContext.request.contextPath}${dto.imagePath}"/>
      <div class="card-body">
         <p class="card-text">${dto.caption}</p>
         <p class="card-text">by <strong>${dto.writer}</strong></p>
         <p><small>${dto.regdate}</small></p>
      </div>
   </div>
   <div>
      <%-- 로그인된 아이디와 글의 작성자가 같으면 수정, 삭제 링크를 제공 --%>
      <c:if test="${sessionScope.id eq dto.writer }">
         <a href="update_form?num=${dto.num }">수정</a>
         <a href="javascript:" onclick="deleteConfirm()">삭제</a>
         <script>
            function deleteConfirm(){
               const isDelete=confirm("이 글을 삭제 하겠습니까?");
               if(isDelete){
                  location.href="delete?num=${dto.num}";
               }
            }
         </script>
      </c:if>
   </div>
   <nav>
      <ul class="pagination justify-content-center">
         <c:choose>
            <c:when test="${dto.prevNum ne 0 }">
               <li class="page-item mr-3">
                     <a class="page-link" href="${pageContext.request.contextPath}/movie/detail?num=${dto.prevNum}">&larr; Prev</a>
               </li>
            </c:when>
            <c:otherwise>
               <li class="page-item disabled mr-3">
                     <a class="page-link" href="javascript:">Prev</a>
               </li>
            </c:otherwise>
         </c:choose>
         <c:choose>
            <c:when test="${dto.nextNum ne 0 }">
               <li class="page-item">
                     <a class="page-link" href="${pageContext.request.contextPath}/movie/detail?num=${dto.nextNum}">Next &rarr;</a>
               </li>
            </c:when>
            <c:otherwise>
               <li class="page-item disabled">
                     <a class="page-link" href="javascript:">Next</a>
               </li>
            </c:otherwise>
         </c:choose>         
      </ul>
   </nav>      
</div>
</body>
</html>