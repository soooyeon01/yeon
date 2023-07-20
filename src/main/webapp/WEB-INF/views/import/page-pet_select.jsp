<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

						<div class="datatable-bottom">
					    	<nav class="datatable-pagination">
						    	<ul class="datatable-pagination-list">
						    		
						    		<li class="datatable-pagination-list-item" >
					    			<c:if test="${pageMaker.prev }">
					    				<a href="javascript:void(0);" onclick="location.href='${pageContext.servletContext.contextPath}/pet/petall?pageNum=${pageMaker.startPage -1}&region=${param.region}';" class="datatable-pagination-list-item-link">‹</a>
					    			</c:if>
						    		</li>
						    		
						    		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" varStatus="status">
					    			<c:choose>
					    				<c:when test="${ status.index == pageMaker.cri.pageNum }">
					    					<c:set scope="page" var="selectedBgColor" value="style='background-color:tomato;'"></c:set>
					    				</c:when>
					    				<c:otherwise>
					    					<c:set scope="page" var="selectedBgColor" value=""></c:set>
					    				</c:otherwise>
					    			</c:choose>
						    		<li class="datatable-pagination-list-item" >
						    			<a 	href="javascript:void(0);" onclick="pageNum('${pageContext.servletContext.contextPath}/pet/petall?pageNum=${status.index}');"
						    				class="datatable-pagination-list-item-link"
						    				<c:if test="${ status.index == pageMaker.cri.pageNum }">${pageScope.selectedBgColor }</c:if>
						    				>${ status.index }</a>
						    		</li>
						    		</c:forEach>
						    		<li class="datatable-pagination-list-item">
						    		<c:if test="${pageMaker.next }">
						    			<a href="javascript:void(0);" onclick="location.href='${pageContext.servletContext.contextPath}/pet/petall?pageNum=${pageMaker.endPage + 1}&region=${param.region}'"  class="datatable-pagination-list-item-link">›</a>
						    		</c:if>
						    		</li>
					    		</ul>
			    			</nav>
						</div>
	<script>
	function pageNum(addr){
		
		var selectElement = document.getElementById("region-select");
		
		
		var region = selectElement.options[selectElement.selectedIndex].value;
		
		window.location.href=addr+'&region='+region;	
	}
	
	function setPageNumbersHTML(startPage, endPage, currentPage, prev, next, servletContextPath, region) {
		  var pageNumbersHTML = '<div class="datatable-bottom"><nav class="datatable-pagination"><ul class="datatable-pagination-list">'
		  if (prev) {
		    pageNumbersHTML += '<li class="datatable-pagination-list-item"><a href="javascript:void(0);" onclick="changePage(' + (startPage - 1) + ');" class="datatable-pagination-list-item-link">‹</a></li>';
		  }
		  for (let i = startPage; i <= endPage; i++) {
		    var isSelected = i === currentPage;
		    pageNumbersHTML += '<li class="datatable-pagination-list-item"><a href="javascript:void(0);" onclick="changePage(' + i + ');" class="datatable-pagination-list-item-link"' + (isSelected ? 'style="background-color:tomato;"' : '') + '>' + i + '</a></li>';
		  }
		  if (next) {
		    pageNumbersHTML += '<li class="datatable-pagination-list-item"><a href="javascript:void(0);" onclick="changePage(' + (endPage + 1) + ');" class="datatable-pagination-list-item-link">›</a></li>';
		  }
		  pageNumbersHTML += '</ul></nav></div>';

		  return pageNumbersHTML;
		}
</script>
						
						
						