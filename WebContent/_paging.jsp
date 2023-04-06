<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="GamerListServlet?">
	<nav aria-label="Page navigation example">
	  <ul class="pagination">
	    <li class="page-item">
	      <a class="page-link" href="GamerListServlet?" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	        <span class="sr-only">Previous</span>
	      </a>
	    </li>

			<c:forEach var="BUTTON" begin="${BUTTON}" end="${BUTTON + 4}" step="1" varStatus="st" >
	    	<c:choose>
	    		<c:when test="${CURRENT_PAGE == st.current}" >
				    <li class="page-item active">
				    	<span class="page-link">${BUTTON}</span>
				    	<span class="sr-only">(CURRENT)</span>
		    		</li>
	    		</c:when>
	    		<c:otherwise>
	    		 <li class="page-item">
		    			<a class="page-link" href="GamerListServlet?${BUTTON}">${BUTTON}</a>
				    </li>
	    		</c:otherwise>
	    	</c:choose>
	    </c:forEach>

	    <li class="page-item">
		    <a class="page-link" href="GamerListServlet?${TOTAL_PAGE_NUM}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		        <span class="sr-only">Next</span>
		    </a>
	    </li>
	  </ul>
	</nav>
</form>