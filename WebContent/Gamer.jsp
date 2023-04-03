<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="ゲーマー詳細" />
	<c:param name="content">

		<table>
			<tr>
				<td>${GAMER.name_gamer}</td>
			</tr>
			<tr>
				<td>${GAMER.team_name_gamer}</td>
			</tr>
			<tr>
				<td>${GAMER.settings_gamer}</td>
			</tr>
			<tr>
				<td>${GAMER.profile_gamer}</td>
			</tr>
		</table>

		<c:set var="user" value="${sessionScope.LOGIN_USER.name_user}" />
		<c:if test="${user == '管理者'}">
			<form method="post" >
				<c:set var="user" value="${sessionScope.LOGIN_USER.name_user}" />
					<input type="hidden" name="id" value="${GAMER.id_gamer}">
					<input type="hidden" name="name" value="${GAMER.name_gamer}">
					<input type="hidden" name="teamName" value="${GAMER.team_name_gamer}">
					<input type="hidden" name="settings" value="${GAMER.settings_gamer}">
					<input type="hidden" name="profile" value="${GAMER.profile_gamer}">

					<button type="submit"  name="gamerId" value="${GAMER.id_gamer}"  formaction="UpdateEditGamerServlet">更新</button>
					<button type="submit" name="gamerId" value="${GAMER.id_gamer}" formaction="DeleteGamerServlet">削除</button>
			</form>
		</c:if>

	</c:param>
</c:import>