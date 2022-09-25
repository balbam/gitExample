<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<div style="text-align: center;">
	<a href="${path}/">Home</a> |
	<a href="${path}/member/address.do">도로명주소</a> |
	<a href="${path}/memo/list.do">메모장</a> |
	<a href="${path}/board/list.do">게시판</a> |
	<a href="${path}/upload/uploadForm">업로드 테스트</a> |
	<a href="${path}/upload/uploadAjax">업로드(Ajax)</a> |
	<a href="${path}/shop/product/list.do">상품 목록</a> |
	<c:if test="${sessionScope.userid != null}">
	 <a href="${path}/shop/cart/list.do">장바구니</a> |
	</c:if>
	
	<div style="text-align: right;">
	 <c:choose>
	   <c:when test="${sessionScope.userid == null }">
	     <!-- 로그인하지 않은 상태 -->
	     <a href="${path}/member/write.do">회원가입</a> |
	     <a href="${path}/member/login.do">로그인</a> |
	     <a href="${path}/admin/login.do">관리자 로그인</a> |
	   </c:when>
	   <c:otherwise>
	     <!-- 로그인한 상태 -->
	     ${sessionScope.name}님이 로그인 중입니다.
	     <a href="${path}/member/view.do?userid=${sessionScope.userid}">회원정보</a> |
	     <a href="${path}/member/logout.do">로그아웃</a> |
	   </c:otherwise>
	 </c:choose>
  </div>
</div>
<hr>
