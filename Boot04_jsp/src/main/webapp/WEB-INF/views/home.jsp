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
			<li><a href="fortune">오늘의 운세 보기</a></li>
			<li><a href="fortune2">오늘의 운세 보기2</a></li>
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>