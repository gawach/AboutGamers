<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="ユーザー登録"/>
	<c:param name="content">

		<h1>ユーザー情報入力</h1>
		<form action="SignupServlet" method="post" class="form">
			<div class="form-group">
				<label for="name">名前</label>
				<input type="text" name="name" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="email">メールアドレス</label>
				<input type="email" name="email" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="pass">パスワード</label>
				<input type="password" name="pass" class="form-control">
			</div>
			<div class="form-group">
				<label for="pass">パスワード確認</label>
				<input type="password" name="passConfirm" class="form-control" required>
			</div>
			<input type="submit" value="サインアップ" class="btn btn-success">
		</form>

	</c:param>
</c:import>