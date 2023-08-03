<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload-ajax</title>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">
   function uploadFileAjax(){
      var formData = new FormData();
      var inputFile = document.getElementsByName("uploadFile");
      var files = inputFile[0].files;
      console.log(files);
      for(var i=0; i < files.length; i++){
         formData.append("uploadFile",files[i]);
      }
      
      $.ajax({
         url:'${pageContext.servletContext.contextPath}/upload/uploadAjaxAction'
         ,processData : false
         ,contentType: false
         ,data: formData
         ,dataType:"json"
         ,type: 'POST'
         ,success : function(jsonArray){
            alert("uploaded");
            let div = document.getElementById("img-insight");
            
            //remove children image elements in Div
            if(div.children.length > 0){
               let childrenList = div.children;
               let length = childrenList.length;
               for(var i=0 ; i < length; i++){
                  childrenList[0].remove();
               }
            }
            
            // new image elements added to Div
            for(var i=0 ; i < jsonArray.length ; i++){
               let imgObj = document.createElement("img");
               console.log("jsonArray["+i+"] - " + jsonArray[i]);   
               console.log("img src - " + jsonArray[i].src);
               imgObj.src = jsonArray[i].src;
               
               div.appendChild(imgObj);
            }
            
         }
      });
   }
</script>
</head>
<body>

   <div>
      <input type="file" name="uploadFile" multiple="multiple"/>
      <button onclick="uploadFileAjax();">전송</button>
      
      <hr>
      <br>
      <div id="img-insight">
      
      </div>
      
   </div>
</body>
</html>