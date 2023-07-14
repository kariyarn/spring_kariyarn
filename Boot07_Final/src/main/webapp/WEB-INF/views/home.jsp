<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다.</h1>
		<c:choose>
			<c:when test="${empty sessionScope.id }">
				<a href="${pageContext.request.contextPath }/users/loginform">로그인</a>
				<a href="${pageContext.request.contextPath }/users/signup_form">회원가입</a>
			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath }/users/info">${id }</a>로그인 중...
					<a href="${pageContext.request.contextPath }/users/logout">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
		<ul>
			<li><a href="${pageContext.request.contextPath }/cafe/list">카페 리스트</a></li>
		</ul>
		
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>