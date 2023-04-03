<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="ゲーマー一覧"/>
	<c:param name="content">

		<form action="SearchServlet" method="post">
			<table class="table">
				<tr>
					<td><input type="text" name="name" placeholder="プレイヤー名"></td>
				</tr>
				<tr>
					<td><input type="text" name="teamName" placeholder="チーム名"></td>
				</tr>
				<tr>
					<td><input type="submit" value="検索"></td>
				</tr>
			</table>
		</form>

		<form action="GamerServlet" method="post">
			<c:set var="userLevel" value="${sessionScope.LOGIN_USER.level_user}"/>

			<c:if test="${userLevel > 0}">
				<c:forEach var="sortGamer" items="${SORT_GAMER_LIST}">
					<c:out value="${sortGamer.name_gamer}" />
					<button type="submit"  name="gamerId" value="${sortGamer.id_gamer}" >詳しく</button>
				</c:forEach>
			</c:if>

			<c:if test="${userLevel == 2}">
				<c:url var="insertGamer" value="/insertGamer.jsp" />
				<a href="${insertGamer}">登録</a>
			</c:if>

			<table>
				<c:forEach var="gamer" items="${GAMER_LIST}">
					<tr>
						<td><c:out value="${gamer.name_gamer}" /></td>
						<td><button type="submit" name="gamerId" value="${gamer.id_gamer}">詳しく</button>
					</tr>
				</c:forEach>
			</table>
		</form>

	</c:param>
</c:import>