<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="プレイヤー詳細" />
	<c:param name="content">

		<table class="table">
			<tr>
				<td class="col-2 bg-dark text-white text-center">プレイヤー名</td>
				<td class="col-auto">${GAMER.name_gamer}</td>
			</tr>
			<tr>
				<td class="col-2 bg-dark text-white text-center">チーム名</td>
				<td class="col-auto">${GAMER.team_name_gamer}</td>
			</tr>
			<tr>
				<td class="col-2 bg-dark text-white text-center">設定情報</td>
				<td class="col-auto">${GAMER.settings_gamer}</td>
			</tr>
			<tr>
				<td class="col-2 bg-dark text-white text-center">プレイヤー情報</td>
				<td class="auto">${GAMER.profile_gamer}</td>
			</tr>
		</table>
		<img class="card-img-top img-fluide" src="img/${GAMER.name_gamer}.jpg" alt="Card image cap" style="height:500px;">


		<!-- 選手情報送信 -->
		<c:set var="user" value="${sessionScope.LOGIN_USER.name_user}" />
		<c:if test="${user == '管理者'}">
			<form method="post">
				<input type="hidden" name="id" value="${GAMER.id_gamer}">
				<input type="hidden" name="name" value="${GAMER.name_gamer}">
				<input type="hidden" name="teamName" value="${GAMER.team_name_gamer}">
				<input type="hidden" name="settings" value="${GAMER.settings_gamer}">
				<input type="hidden" name="profile" value="${GAMER.profile_gamer}">

				<button type="submit"  name="gamerId" value="${GAMER.id_gamer}"  formaction="UpdateEditGamerServlet"  class="btn btn-info">情報変更</button>
				<button type="submit" name="gamerId" value="${GAMER.id_gamer}" formaction="DeleteGamerServlet" class="btn btn-danger">削除</button>
			</form>
		</c:if>

	</c:param>
</c:import>