<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<div class="container">
		<ul>
			<li><a href="${pageContext.request.contextPath }/member/insertform">파라미터 추출 테스트</a></li>
			<li><a href="${pageContext.request.contextPath }/fortune">오늘의 운세보기1</a></li>
			<li><a href="${pageContext.request.contextPath }/fortune2">오늘의 운세보기2</a></li>
			<li><a href="${pageContext.request.contextPath }/fortune3">오늘의 운세보기3</a></li>
			<li><a href="${pageContext.request.contextPath }/member/delete?num=1">작업 후 리다일렉트 응답받기</a></li>
			<li><a href="${pageContext.request.contextPath }/member/delete?num=2">작업 후 리다일렉트 응답받기</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json1">JSON응답받기1</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json2">JSON응답받기2</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json3">JSON응답받기3</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json4">JSON응답받기4</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json5">JSON응답받기5</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json6">JSON응답받기6</a></li>
		</ul>
	
		<ul>
			<c:forEach var="tmp" items="${requestScope.noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>