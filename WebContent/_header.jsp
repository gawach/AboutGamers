<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="LOGIN_PAGE" value="/login.jsp" />
<c:url var="SIGNUP_PAGE" value="/signup.jsp"/>
<c:set var="USER_NAME" value="${sessionScope.LOGIN_USER.name_user}" />
<c:set var="USER_LEVEL" value="${sessionScope.LOGIN_USER.level_user}"/>


<nav class="navbar navbar-expand navbar-light bg-light">
	<a href="<c:out value="GamerList.jsp"/>" class="navbar-brand" >一覧へ</a>
	<ul class="navbar-nav">
		<c:choose>
			<c:when test="${USER_LEVEL > 0}">
				<li class="nav-item"><c:out value="${USER_NAME}" /></li>
				<li class="nav-item">
					<form action="LogoutServlet" method="post">
						<button type="submit">ログアウト</button>
					</form>
				</li>
			</c:when>
			<c:otherwise>
				<li class="nav-item"><a href="<c:out value="${SIGNUP_PAGE}"/>" class="nav-link">サインアップ</a></li>
				<li class="nav-item"><a href="<c:out value="${LOGIN_PAGE}"/>" class="nav-link">ログイン</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
	<h1>
		<c:out value="${ACTION_MESSAGE}" />
	</h1>
