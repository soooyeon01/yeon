<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.spring.util.PageMaker"%>
<div class="datatable-bottom">
	<nav class="datatable-pagination">
		<ul class="datatable-pagination-list">

			<li class="datatable-pagination-list-item"><c:if
					test="${pageMaker.prev }">
					<a href="javascript:void(0);"
						onclick="prev('${pageMaker.startPage -1}');"
						class="datatable-pagination-list-item-link">‹</a>
				</c:if></li>

			<c:forEach begin="${pageMaker.startPage }"
				end="${pageMaker.endPage }" varStatus="status">
				<c:choose>
					<c:when test="${ status.index == pageMaker.cri.pageNum }">
						<c:set scope="page" var="selectedBgColor"
							value="style='background-color:#feeaa5;'"></c:set>
					</c:when>
					<c:otherwise>
						<c:set scope="page" var="selectedBgColor" value=""></c:set>
					</c:otherwise>
				</c:choose>
				<li class="datatable-pagination-list-item"><a
					href="javascript:void(0);" onclick=" pageNum('${status.index}');"
					class="datatable-pagination-list-item-link"
					<c:if test="${ status.index == pageMaker.cri.pageNum }">${pageScope.selectedBgColor }</c:if>>${ status.index }</a>
				</li>
			</c:forEach>
			<li class="datatable-pagination-list-item"><c:if
					test="${pageMaker.next }">
					<a href="javascript:void(0);"
						onclick="next('${pageMaker.endPage + 1}');"
						class="datatable-pagination-list-item-link">›</a>
				</c:if></li>
		</ul>
	</nav>
</div>
<script>
	function prev(prevPage) {
		var selectElement = document.getElementById("region-select");
		var region = selectElement.options[selectElement.selectedIndex].value;
		location.href = "${pageContext.servletContext.contextPath}/shel/shelall?region="
				+ region + "&pageNum=" + prevPage;
	}

	function pageNum(page) {
		var selectElement = document.getElementById("region-select");
		var region = selectElement.options[selectElement.selectedIndex].value;
		location.href = "${pageContext.servletContext.contextPath}/shel/shelall?region="
				+ region + "&pageNum=" + page;
	}

	function next(nextPage) {
		var selectElement = document.getElementById("region-select");
		var region = selectElement.options[selectElement.selectedIndex].value;
		location.href = "${pageContext.servletContext.contextPath}/shel/shelall?region="
				+ region + "&pageNum=" + nextPage;
	}
</script>


