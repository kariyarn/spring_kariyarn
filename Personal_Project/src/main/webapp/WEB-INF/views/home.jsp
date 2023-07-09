<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
<link  href="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
</head>
<style>
  .img {
    width: 100px; 
    height: auto;
  }
</style>
<style type="text/css">
    .fotorama__html{position:relative !important;}
    .fotorama__html div{height:100%;}
    .fotorama__html a{z-index:1; display:block; height:100%;}
</style>
<body>
	<%-- webapp/include/navbar.jsp 페이지 포함시키기 --%>
	<jsp:include page="${pageContext.request.contextPath}/include/navbar">
		<jsp:param value="movie" name="current"/>
	</jsp:include>
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
			<li><a href="${pageContext.request.contextPath }/ranking/list">RANKING</a></li>
			<li><a href="${pageContext.request.contextPath }/movie/list">영화 리뷰</a></li>
			<li><a href="${pageContext.request.contextPath }/commu/list">커뮤니티</a></li>
		</ul>
		<!-- 영화 이미지 추가하기 -->
		
			<div class="fotorama"
				data-allowfullscreen="true"
				data-width="100%"
    		 	data-ratio="800/600"
     			data-minwidth="400"
     			data-maxwidth="600"
     			data-minheight="300"
     			data-maxheight="100%"
     			data-click='false'
     			data-swipe='false'
     			data-shuffle="true"
     			data-nav="thumbs"
     			data-autoplay="1500">
					<c:forEach var="tmp" items="${list }">
                      		<div data-img="${pageContext.request.contextPath }${tmp.imagePath}" />
                      		<a href="${pageContext.request.contextPath}/movie/detail?num=${tmp.num}"></a>
                      		</div>
					</c:forEach>	
			</div>
	<!-- jquery로딩 -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
	<!-- 플러그인 js 로딩 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
	<script>
 
	<script type="text/javascript">
	    $(document).ready(function(e) {
	            $(".fotorama").on("click",function(){
	                var href = $(this).find("a").attr('href');
	                location.href=href;
	            });
	    });
    </script>

	</script>
</body>
</html>