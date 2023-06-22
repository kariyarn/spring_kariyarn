<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/update.jsp</title>
</head>
<body>
	<script>
		alert("${param.num} 번 회원의 정보를 수정했습니다.")
		//javascript를 이용해서 페이지 이동시키기
		location.href="${pageContext.request.contextPath }/member/list"
	</script>
</body>
</html>