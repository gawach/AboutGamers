<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="ゲーマー登録"/>
	<c:param name="content">

		<form action="InsertGamerCheckServlet" method="post">
			<div class="form-group">
				<label for="name">プレイヤー名</label>
				<input type="text" name="name" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="teamName">チーム名</label>
				<input type="text" name="teamName" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="settings">ゲーム内設定</label>
				<input type="text" name="settings" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="profile">選手情報</label>
				<textarea  name="profile" class="form-control" required>${PROFILE}</textarea>
			</div>
			<input type="submit" value="登録確認" class="btn btn-dark">
		</form>

	</c:param>
</c:import>