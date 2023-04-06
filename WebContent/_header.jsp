<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="LOGOUT_SERVLET" value="/LogoutServlet"/>
<c:url var="LOGIN_PAGE" value="/login.jsp" />
<c:url var="SIGNUP_PAGE" value="/signup.jsp"/>
<c:set var="USER_NAME" value="${sessionScope.LOGIN_USER.name_user}" />
<c:set var="USER_LEVEL" value="${sessionScope.LOGIN_USER.level_user}"/>

<nav class="navbar navbar-expand navbar-light bg-dark fixed-top">
	<a href="<c:out value="GamerList.jsp"/>" class="navbar-brand"><i class="fas fa-gamepad" style="color: fuchsia;"></i></a>
	<ul class="navbar-nav float-right">
		<c:choose>
			<c:when test="${USER_LEVEL > 0}">
				<li class="nav-item"><a href="#" class="nav-link text-info h4 text-right"><c:out value="${USER_NAME}" /></a></li>
				<li class="nav-item"><a href="<c:out value="${LOGOUT_SERVLET}"/>" class="nav-link text-white">ログアウト</a></li>
			</c:when>
			<c:otherwise>
				<li class="nav-item"><a href="<c:out value="${SIGNUP_PAGE}"/>" class="nav-link text-white">サインアップ</a></li>
				<li class="nav-item"><a href="<c:out value="${LOGIN_PAGE}"/>" class="nav-link text-white">ログイン</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
<div style="padding-bottom: 80px"></div>
<!-- 完了メッセージ -->
<c:if test="${COMPLETION_MESSAGE != null}">
	<h3 class="alert alert-success"><c:out value="${COMPLETION_MESSAGE}" /></h3>
</c:if>
<!-- エラーメッセージ -->
<c:if test="${ERROR_MESSAGE != null}">
	<h3 class="alert alert-danger"><c:out value="${ERROR_MESSAGE}" /></h3>
</c:if>