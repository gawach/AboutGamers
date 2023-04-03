<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="ゲーマー登録"/>
	<c:param name="content">

		<form action="InsertGamerCheckServlet" method="post">
			<table>
				<tr>
					<td>プレイヤー名：</td>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<td>チーム名：</td>
					<td><input type="text" name="teamName" required></td>
				</tr>
				<tr>
					<td>ゲーム内設定：</td>
					<td><input type="text" name="settings" required></td>
				</tr>
				<tr>
					<td>選手情報</td>
					<td><input type="text" name="profile" required></td>
				</tr>
				<tr>
					<td><input type="submit" value="登録確認"></td>
				</tr>
			</table>
		</form>

	</c:param>
</c:import>