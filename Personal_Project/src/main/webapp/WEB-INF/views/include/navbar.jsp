<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
   어두운색 계열의 navbar 배경색이면 data-bs-theme="dark" 속성을 추가한다 
   navbar-expand-md 는 md 영역 이상에서 navbar-collapse 가 펼쳐 지도록 한다.
-->
<nav class="navbar bg-primary navbar-expand-md" data-bs-theme="dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath }/">
      <img src="https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
      MovieNet
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
          data-bs-target="#navbarText">
           <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
         <ul class="navbar-nav me-auto">
           <li class="nav-item">
           		<a class="nav-link ${param.current eq 'ranking' ? 'active' : ''}" href="${pageContext.request.contextPath }/ranking/list">랭킹</a>
           </li>
           <li class="nav-item">
           		<a class="nav-link ${param.current eq 'movie' ? 'active' : ''}" href="${pageContext.request.contextPath }/movie/list">영화</a>
           </li>
           <li class="nav-item">
           		<a class="nav-link ${param.current eq 'commu' ? 'active' : ''}" href="${pageContext.request.contextPath}/commu/list">커뮤니티</a>
           </li>
         </ul>
         
         <div class="navbar-nav">
            <c:choose>
               <c:when test="${not empty id }">
                  <strong><a class="nav-link" href="${pageContext.request.contextPath }/member/info">${id }</a></strong>
                  <a class="nav-link" href="${pageContext.request.contextPath }/member/logout">로그아웃</a>
               </c:when>
               <c:otherwise>
                  <a class="nav-link" href="${pageContext.request.contextPath }/member/loginform">로그인</a>
                  <a class="nav-link" href="${pageContext.request.contextPath }/member/signup_form">회원가입</a>
               </c:otherwise>
            </c:choose>
         </div>
    </div>
  </div>
</nav>