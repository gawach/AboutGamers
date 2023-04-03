<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="更新画面"/>
	<c:param name="content">
		<h1>更新画面</h1>
		<form action="UpdateGamerServlet" method="post">
			<table>
				<tr>
					<td>No.</td>
					<td><input type="text" name="id" value="${ID}" readonly></td>
				</tr>
				<tr>
					<td>プレイヤー名：</td>
					<td><input type="text" name="name" value="${NAME}" required></td>
				</tr>
				<tr>
					<td>チーム名：</td>
					<td><input type="text" name="teamName" value="${TEAM_NAME}" required></td>
				</tr>
				<tr>
					<td>ゲーム内設定：</td>
					<td><input type="text" name="settings" value="${SETTINGS}" required></td>
				</tr>
				<tr>
					<td>選手情報</td>
					<td><input type="text" name="profile" value="${PROFILE}" required></td>
				</tr>
				<tr>
					<td><input type="submit" value="登録確認"></td>
				</tr>
			</table>
		</form>
	</c:param>
</c:import>