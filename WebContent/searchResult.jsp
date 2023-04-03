<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:import url="_template.jsp">
	<c:param name="title" value="検索結果"/>
	<c:param name="content">

		<form action="GamerServlet" method="post">
			<table>
				<c:forEach var="gamer" items="${GAMER_LIST}">
					<tr>
						<td><c:out value="${gamer.name_gamer}"/></td>
						<td><input type="hidden" name="gamer" value="${gamer.id_gamer}"></td>
						<td>
							<input type="submit" value="詳細を見る">
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>

	</c:param>
</c:import>