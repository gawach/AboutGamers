<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="_template.jsp">
	<c:param name="title" value="検索結果"/>
	<c:param name="content">

		<!-- プレイヤー検索欄 -->
		<div class="wrapper">
			<div class="wrapper-top">
				<form action="SearchServlet" method="post" class="form-inline">
					<div class="form-row">
						<div class="form-group col-auto">
							<input type="text" name="name" placeholder="プレイヤー名" class="form-control">
						</div>
						<div class="form-group col-auto">
							<input type="text" name="teamName" placeholder="チーム名" class="form-control">
						</div>
						<div class="form-group col-auto">
							<button type="submit" class="form-control btn btn-info"><i class="fas fa-search"></i></button>
						</div>

						<!-- 選手登録表示判定 -->
							<c:set var="userLevel" value="${sessionScope.LOGIN_USER.level_user}"/>
							<c:if test="${userLevel == 2}">
								<input type="submit" class="form-control btn btn-warning" formaction="insertGamer.jsp" value="選手登録">
							</c:if>
					</div>
				</form>
			</div>


			<!-- 一覧表示  -->
			<div class="wrapper-middle">
				<c:forEach var="gamer" items="${GAMER_LIST}">
					<div class="card shadow-lg rounded border wrapper-card">
					  <img class="card-img-top img-fluid" src="img/${gamer.name_gamer}.jpg" alt="Card image cap">
					  <div class="card-body">
					    <a href="GamerServlet?${gamer.id_gamer}" class="card-link h3">${gamer.name_gamer}</a>
					    <p class="card-text over-flow">${gamer.profile_gamer}</p>
					  </div>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- ページネーション -->

		<!-- 挿入 -->


			<!-- 注目選手表示 -->
			<c:if test="${userLevel > 0}">
				<div class="aside">
					<h3>注目選手！</h3>
					<c:forEach var="sortGamer" items="${SORT_GAMER_LIST}">
						<div class="card shadow-lg rounded border aside-card">
						  <img class="card-img-top" src="img/${sortGamer.name_gamer}.jpg" alt="Card image cap" >
						  <div class="card-body">
						    <a href="GamerServlet?${sortGamer.id_gamer}" class="card-link h5">${sortGamer.name_gamer}</a>
						  </div>
						</div>
					</c:forEach>
				</div>
			</c:if>
	</c:param>
</c:import>