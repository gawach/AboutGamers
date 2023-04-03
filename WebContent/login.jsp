<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="ログイン"/>
	<c:param name="content">

		<form action="LoginServlet" method="post">
			<table>
				<thead>
					<tr>
						<td><h1>ユーザー情報入力</h1></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<label for="email">メールアドレス</label>
							<input type="email" name="email">
						</td>
					</tr>
					<tr>
						<td>
							<label for="pass">パスワード</label>
							<input type="password" name="pass">
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="ログイン"></td>
					</tr>
				</tbody>
			</table>
		</form>

	</c:param>
</c:import>