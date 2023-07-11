<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>회원 목록입니다</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath }/member/insert_form">멤버 추가</a></li>
		</ul>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>수정</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list}">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.name }</td>
						<td>${tmp.addr }</td>
						<td><a href="${pageContext.request.contextPath }/member/update_form?num=${tmp.num }">수정</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>