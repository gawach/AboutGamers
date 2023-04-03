<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="_template.jsp">
	<c:param name="title" value="登録確認"/>
	<c:param name="content">

		<h1>確認画面</h1>
		<form action="InsertGamerServlet" method="post">
			<table>
				<tr>
					<td>プレイヤー名：</td>
					<td><input type="text" name="name" value="${NAME}" readonly></td>
				</tr>
				<tr>
					<td>チーム名：</td>
					<td><input type="text" name="teamName" value="${TEAM_NAME}" readonly></td>
				</tr>
				<tr>
					<td>ゲーム内設定：</td>
					<td><input type="text" name="settings" value="${SETTINGS}" readonly></td>
				</tr>
				<tr>
					<td>選手情報</td>
					<td><input type="text" name="profile" value="${PROFILE}" readonly></td>
				</tr>
				<tr>
					<td><input type="submit" value="登録確認"></td>
				</tr>
			</table>
		</form>

	</c:param>
</c:import>