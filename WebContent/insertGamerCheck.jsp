<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="_template.jsp">
	<c:param name="title" value="登録確認"/>
	<c:param name="content">

		<h1>確認画面</h1>
		<form action="InsertGamerServlet" method="post">
			<div class="form-group">
				<label for="name">プレイヤー名：</label>
				<input type="text" name="name" value="${NAME}" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="teamName">チーム名：</label>
				<input type="text" name="teamName" value="${TEAM_NAME}" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="settings">ゲーム内設定：</label>
				<input type="text" name="settings" value="${SETTINGS}" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="profile">選手情報：</label>
				<textarea  name="profile" class="form-control" required>${PROFILE}</textarea>
			</div>
			<input type="submit" value="登録" class="btn btn-primary">
		</form>

	</c:param>
</c:import>