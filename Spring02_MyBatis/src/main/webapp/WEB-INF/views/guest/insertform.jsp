<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath }/guest/insert" method="post">
			<div>
				<label class="form-label" for="writer">작성자</label>
				<input class="form-control" type="text"	name="writer" id="writer" placeholder="작성자를 등록하세요" />
			</div>
			<div>
				<label class="form-label" for="content">내용</label>
				<textarea class="form-control" name="content" id="content" cols="30" rows="10"></textarea>
			</div>
			<div>
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password"	name="pwd" id="pwd" placeholder="비밀번호 입력" />
			</div>
			<button class="btn btn-outline-primary" type="submit">등록</button>
		</form>
	</div>
</body>
</html>