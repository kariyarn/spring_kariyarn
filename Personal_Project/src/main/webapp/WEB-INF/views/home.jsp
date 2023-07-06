<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.id }">
				<a href="${pageContext.request.contextPath }/member/loginform">로그인</a>
				<a href="${pageContext.request.contextPath }/member/signup_form">회원가입</a>
			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath }/member/info">${id }</a>로그인 중...
					<a href="${pageContext.request.contextPath }/member/logout">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
		
		<ul>
			<li><a href="">RANKING</a></li>
			<li><a href="${pageContext.request.contextPath }/movie/list">영화 리뷰</a></li>
			<li><a href="${pageContext.request.contextPath }/commu/list">커뮤니티</a></li>
		</ul>
	</div>
</body>
</html>