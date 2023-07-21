<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.spring.util.PageMaker" %>
						<div class="datatable-bottom">
					    	<nav class="datatable-pagination">
						    	<ul class="datatable-pagination-list">
						    		
						    		<li class="datatable-pagination-list-item" >
					    			<c:if test="${pageMaker.prev }">
					    				<a href="javascript:void(0);" onclick="prev('${pageMaker.startPage -1}');" class="datatable-pagination-list-item-link">‹</a>
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
						    			<a 	href="javascript:void(0);" onclick=" pageNum('${status.index}');"
						    				class="datatable-pagination-list-item-link"
						    				<c:if test="${ status.index == pageMaker.cri.pageNum }">${pageScope.selectedBgColor }</c:if>
						    				>${ status.index }</a>
						    		</li>
						    		</c:forEach>
						    		<li class="datatable-pagination-list-item">
						    		<c:if test="${pageMaker.next }">
						    			<a href="javascript:void(0);" onclick="next('${pageMaker.endPage + 1}');"  class="datatable-pagination-list-item-link">›</a>
						    		</c:if>
						    		</li>
					    		</ul>
			    			</nav>
						</div>
	<script>
	function updatePageNumberStyles(selectedPageNumber) {
	    $('li.datatable-pagination-list-item a').each(function() {
	        const pageNumber = parseInt($(this).text(), 10);
	        if (pageNumber === selectedPageNumber) {
	            $(this).css('background-color', 'tomato');
	        } else {
	            $(this).css('background-color', '');
	        }
	    });
	}
	function prev(prevPage){
		var addr = "${pageContext.servletContext.contextPath}/pet/region"
		var selectElement = document.getElementById("region-select");
		//cri.setPageNum(page);
		
		var region = selectElement.options[selectElement.selectedIndex].value;
		$.ajax({
            url: addr, 
            type: "POST",
            data: {
                region: region,
            	pageNum:page
            },
            dataType:"json",
            success:function (data, textStatus) {
                console.log("succedss");
                window.scrollTo(0, 0);
                updatePageNumberStyles(parseInt(prevPage, 10));
                if (data.petList && data.petList.length > 0) {
                    const animals = data.petList;
                 
                    let animalsHtml = ``;
                    for (let i = 0; i < 10; i++) {
                        let animal = animals[i];
                         animalsHtml += `
                       
                        <div class="data">
                          <a href="${pageContext.servletContext.contextPath}/pet/petdetail?method=get&pet_notice_no=`+animal.pet_notice_no+`">
                            <img src=`+animal.popfile+` alt="펫이미지" style="height: 300px" />
                          </a>
                          <div>
                            <br>
                            <p style="display: block;">품종 : `+animal.kindCd+`</p>
                            <br>
                            <p style="display: block;">나이 : `+animal.age+`</p>
                            <br>
                            <p style="display: block;">무게 : `+animal.weight+`</p>
                            <br>
                            <p style="display: block;">성별 : `+animal.sexCd+`</p>
                            <br>
                            <p style="display: block;">특징 : `+animal.specialMark+`</p>
                            <br>
                          </div>
                        </div>`;
                        
                    }
                   
                  
                    $("#animals-container .container").html(animalsHtml);
                 
                } else {
     
                    location.href="${pageContext.servletContext.contextPath}/pet/petall";
                   
                } 
                
             
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
	}
	
	
	
	function pageNum(page){
	
		var addr = "${pageContext.servletContext.contextPath}/pet/region"
		var selectElement = document.getElementById("region-select");
		//cri.setPageNum(page);
		var region = selectElement.options[selectElement.selectedIndex].value;
		//console.log("page"+page);
                $.ajax({
                    url: addr, 
                    type: "POST",
                    data: {
                        region: region,
                    	pageNum:page
                    },
                    dataType:"json",
                    success:function (data, textStatus) {
                        console.log("succedss");
                        window.scrollTo(0, 0);
                        updatePageNumberStyles(parseInt(page, 10));
                        if (data.petList && data.petList.length > 0) {
                            const animals = data.petList;
                         
                            let animalsHtml = ``;
                            for (let i = 0; i < 10; i++) {
                                let animal = animals[i];
                                 animalsHtml += `
                               
                                <div class="data">
                                  <a href="${pageContext.servletContext.contextPath}/pet/petdetail?method=get&pet_notice_no=`+animal.pet_notice_no+`">
                                    <img src=`+animal.popfile+` alt="펫이미지" style="height: 300px" />
                                  </a>
                                  <div>
                                    <br>
                                    <p style="display: block;">품종 : `+animal.kindCd+`</p>
                                    <br>
                                    <p style="display: block;">나이 : `+animal.age+`</p>
                                    <br>
                                    <p style="display: block;">무게 : `+animal.weight+`</p>
                                    <br>
                                    <p style="display: block;">성별 : `+animal.sexCd+`</p>
                                    <br>
                                    <p style="display: block;">특징 : `+animal.specialMark+`</p>
                                    <br>
                                  </div>
                                </div>`;
                                
                            }
                           
                          
                            $("#animals-container .container").html(animalsHtml);
                         	
                        } else {
             
                            location.href="${pageContext.servletContext.contextPath}/pet/petall";
                           
                        } 
                         
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                        console.log(textStatus);
                        console.log(errorThrown);
                    }
                });
          
	}
	
	function next(nextPage){
		var addr = "${pageContext.servletContext.contextPath}/pet/region"
		var selectElement = document.getElementById("region-select");
		//cri.setPageNum(page);
		var region = selectElement.options[selectElement.selectedIndex].value;
		//location.href='${pageContext.servletContext.contextPath}/pet/petall?pageNum=${pageMaker.endPage + 1}&region=${param.region}'
		$.ajax({
            url: addr, 
            type: "POST",
            data: {
                region: region,
            	pageNum:nextPage
            },
            dataType:"json",
            success:function (data, textStatus) {
                console.log("succedss");
                window.scrollTo(0, 0);
                updatePageNumberStyles(parseInt(nextPage, 10));
                if (data.petList && data.petList.length > 0) {
                    const animals = data.petList;
                 
                    let animalsHtml = ``;
                    for (let i = 0; i < 10; i++) {
                        let animal = animals[i];
                         animalsHtml += `
                       
                        <div class="data">
                          <a href="${pageContext.servletContext.contextPath}/pet/petdetail?method=get&pet_notice_no=`+animal.pet_notice_no+`">
                            <img src=`+animal.popfile+` alt="펫이미지" style="height: 300px" />
                          </a>
                          <div>
                            <br>
                            <p style="display: block;">품종 : `+animal.kindCd+`</p>
                            <br>
                            <p style="display: block;">나이 : `+animal.age+`</p>
                            <br>
                            <p style="display: block;">무게 : `+animal.weight+`</p>
                            <br>
                            <p style="display: block;">성별 : `+animal.sexCd+`</p>
                            <br>
                            <p style="display: block;">특징 : `+animal.specialMark+`</p>
                            <br>
                          </div>
                        </div>`;
                        
                    }
                  
                  
                    $("#animals-container .container").html(animalsHtml);
                 
                } else {
                    
                    location.href="${pageContext.servletContext.contextPath}/pet/petall";
                   
                } 
                 
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });	
				
				
				
				
	}
	
</script>
						
						
						