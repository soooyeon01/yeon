<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<script>
							function next(){
								location.href="${pageContext.servletContext.contextPath}/with/withcaselect?pageNum=${pageMaker.endPage + 1}";
							}
							function pagenum(){
								location.href="${pageContext.servletContext.contextPath}/with/withcaselect?pageNum=${status.index}";
							}
							function prev(){
								location.href="${pageContext.servletContext.contextPath}/with/withcaselect?pageNum=${pageMaker.startPage -1}";
							}
							
						</script>

						<div class="datatable-bottom">
					    	<nav class="datatable-pagination">
						    	<ul class="datatable-pagination-list">
						    		
						    		<li class="datatable-pagination-list-item" >
					    			<c:if test="${pageMaker.prev }">
					    				<a href="javascript:void(0)" onclick="prev();" class="datatable-pagination-list-item-link">‹</a>
					    			</c:if>
						    		</li>
						    		
						    		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" varStatus="status">
					    			<c:choose>
					    				<c:when test="${ status.index == pageMaker.cri.pageNum }">
					    					<c:set scope="page" var="selectedBgColor" value="style='background-color:yellow;'"></c:set>
					    				</c:when>
					    				<c:otherwise>
					    					<c:set scope="page" var="selectedBgColor" value=""></c:set>
					    				</c:otherwise>
					    			</c:choose>
						    		<li class="datatable-pagination-list-item" >
						    			<a 	href= "javascript:void(0)" onclick="pagenum();" class="datatable-pagination-list-item-link"
						    				<c:if test="${ status.index == pageMaker.cri.pageNum }">${pageScope.selectedBgColor }</c:if>
						    				>${ status.index }</a>
						    		</li>
						    		</c:forEach>
						    		<li class="datatable-pagination-list-item">
						    		<c:if test="${pageMaker.next }">
						    			<a href="javascript:void(0)" onclick="next();" class="datatable-pagination-list-item-link">›</a>

						    		</c:if>
						    		</li>
					    		</ul>
			    			</nav>
						</div>
						
						
						
						