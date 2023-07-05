<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/update_form</title>
<style>
	/* 이미지 업로드 폼을 숨긴다 */
	#imageForm{
		display: none;
	}
	#profileImage{
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
</style>
</head>
<body>
		<a id="galleryLink" href="javascript:">
			<c:choose>
				<c:when test="${ empty dto.imagePath }">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
					  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
					  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
					</svg>
				</c:when>
				<c:otherwise>
					<img id="galleryImage" src="${pageContext.request.contextPath }${ dto.imagePath}">
				</c:otherwise>
			</c:choose>
		</a>
		<form action="${pageContext.request.contextPath}/gallery/update?num=${dto.num }" method="post">		
			<input type="hidden" name="imagePath" 
				value="${ empty dto.imagePath ? 'empty' : dto.imagePath }"/>		
			<div>
				<label for="writer">아이디</label>
				<input type="text" id="writer" value="${dto.writer }" disabled/>
			</div>
			<div>
				<label for="caption">이미지 설명</label>
				<input type="text" id="caption" name="caption" value="${dto.caption }"/>
			</div>
			<button type="submit">수정</button>
			<button type="reset">취소</button>
		</form>	
		
		<form id="imageForm" action="${pageContext.request.contextPath}/gallery/upload_image" method="post" enctype="multipart/form-data">
			프로필 사진
			<input type="file" id="image" name="image" accept=".jpg, .png, .gif"/>
			<button type="submit">업로드</button>
		</form>
					
	</div>
	<!-- gura_util.js 로딩 -->
	<script src="${pageContext.request.contextPath }/resources/js/gura_util.js"></script>
	<script>

		//프로필 이미지 링크를 클릭하면 
		document.querySelector("#galleryLink").addEventListener("click", function(){
			// input type="file" 을 강제 클릭 시킨다. 
			document.querySelector("#image").click();
		});	
		
		//프로필 이미지를 선택하면(바뀌면) 실행할 함수 등록
		document.querySelector("#image").addEventListener("change", function(){
			//ajax 전송할 폼의 참조값 얻어오기
			const form=document.querySelector("#imageForm");
			//gura_util.js 에 있는 함수를 이용해서 ajax 전송하기 
			ajaxFormPromise(form)
			.then(function(response){
				return response.json();
			})
			.then(function(data){
				console.log(data);
				// input name="profile" 요소의 value 값으로 이미지 경로 넣어주기
				document.querySelector("input[name=imagePath]").value=data.imagePath;
				
				// img 요소를 문자열로 작성한 다음 
				let img=`<img id="galleryImage" 
					src="${pageContext.request.contextPath }\${data.imagePath}">`;
				//id 가 profileLink 인 요소의 내부(자식요소)에 덮어쓰기 하면서 html 형식으로 해석해 주세요 라는 의미 
				document.querySelector("#galleryLink").innerHTML=img;
			});
		});		
		
	</script>
</body>
</html>