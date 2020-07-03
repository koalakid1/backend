<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%@ include file="../includes/header.jsp" %>

<style>
*{padding:0;margin:0}
.uploadResult{
	width:100%;
	height:150px;
	background-color:gray;
}
.uploadResult ul{
	display:flex;
	flex-flow:row;
	justify-content: center;
	align-items: center;
}
.uploadResult ul li{
	list-style:none;
	padding:10px;
}
.uploadResult ul li img{
	width: 100px;
}
.bigPictureWrapper {
  position: fixed;
  display: none;
  justify-content: center;
  align-items: center;
  top:0% !important;
  left:0% !important;
  width:100%;
  height:100%;
  background-color: black; 
  z-index: 1000;
 
}

.bigPicture {
  position: relative  !important;
  display:flex  !important;
  justify-content: center  !important;
  align-items: center  !important;
}

.bigPicture img{
	width:600px;
}

</style>


	<script>
		$(document).ready(function(){
			(function(){
				var bno='<c:out value="${board.bno}"/>';
				$.getJSON("/board/getAttachList",{bno:bno},function(arr){
					var str="";
					$(arr).each(function(i,attach){							
						
						if(attach.fileType){
							//썸네일경로
							var fileCallPath=encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
										
							str+="<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
							str+="<span>"+attach.fileName+"</span>";
							str+="<button style='cursor:pointer' type='button' data-file='"+fileCallPath+"' data-type='"+attach.fileType+"' class='btn btn-warning btn-circle'>";
							str+=" <i class='fa fa-times'></i></button><br>";
							str+="<img src='/display?fileName="+fileCallPath+"'>";
							str+="</div></li>";
							console.log(fileCallPath);	
						}else{
							var fileCallPath=encodeURIComponent(attach.uploadPath+"/"+attach.uuid+"_"+attach.fileName);
									
							// '\\'를 '/'로 변경, '\\'는 윈도우, '/'는 웹에서 사용
							var fileLink=fileCallPath.replace(new RegExp(/\\/g),"/");
							console.log(fileLink);
							str+="<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
							str+="<span>"+attach.fileName+"</span>";
							str+="<button style='cursor:pointer' data-file='"+fileCallPath+"' data-type='"+attach.fileType+"' type='button' class='btn btn-warning btn-circle'>";
							str+=" <i class='fa fa-times'></i></button><br>";
							str+="<img src='/resources/img/attach.jfif'>";			
							str+="</div></li>";
						}
						
					});
					$(".uploadResult ul").html(str);
					
				});
			})();
			
			//첨부파일삭제
			$(".uploadResult").on("click","button",function(e){
				if(confirm("파일을 삭제하시겠습니까?")){
					var targetLi=$(this).closest("li");
					targetLi.remove();
				}
			});
			
			//파일확장자.파일사이즈체크
			var regex=new RegExp("()\.(exe|sh|zip|alz)$");
			var maxSize=5*1024*1024;//5MB
			function checkExtension(fileName,fileSize){
				if(fileSize>=maxSize){
					alert("파일사이즈 초과");
					return false;
				}
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				
				return true;
			}
			
			
			//드래그앤드롭을 이용해서  파일업로드
			$(".uploadResult").on("dragenter dragover",function(e){
				e.preventDefault();//파일이 새롭게 열리는 것을 막는다.
			});
			$(".uploadResult").on("drop",function(e){
				e.preventDefault();//파일이 새롭게 열리는 것을 막는다.
				
				var files=e.originalEvent.dataTransfer.files;
				//formData객체에 업로드할 파일들을 추가
				var formData=new FormData();
				for(i=0;i<files.length;i++){
					formData.append("uploadFile",files[i]);
				}
				
				
				$.ajax({
					url: '/uploadAjaxAction',
					processData:false,
					contentType:false,
					data:formData,
					type:'POST',
					dataType:'json',
					success:function(result){
						console.log(result);
						
						//파일목록
						showUploadResult(result);
						
						//<input type='file'>초기화
						//$(".uploadDiv").html(cloneObj.html());
					}
				});
				
			});
			
			
			
			//파일업로드
			$("input[type='file']").change(function(e){
				//formData객체에 업로드할 파일들을 추가
				var formData=new FormData();
				var inputFile=$("input[name='uploadFile']");
				var files=inputFile[0].files;
				for(var i=0;i<files.length;i++){
					if(!checkExtension(files[i].name,files[i].size)){
						return false;
					}
					formData.append("uploadFile",files[i]);
				}
				$.ajax({
					url: '/uploadAjaxAction',
					processData:false,
					contentType:false,
					data:formData,
					type:'POST',
					dataType:'json',
					success:function(result){
						console.log(result);
						
						//파일목록
						showUploadResult(result);
						
						//<input type='file'>초기화
						//$(".uploadDiv").html(cloneObj.html());
					}
				});
			});
			
			//첨부파일목록출력
			function showUploadResult(uploadResultArr){
				//첨부파일목록이 없으면 중지
				if(!uploadResultArr || uploadResultArr.length==0){
					return;
				}
				
				console.log("@@@@@@@@@@@@@@@@@@@@@@@@@");
				var uploadResult=$(".uploadResult ul");
				
				//목록초기화
				//uploadResult.empty();
				
				var str="";
				$(uploadResultArr).each(function(i,obj){
					
					//이미지파일이 아니면 attach.png출력
					if(obj.image){
						//썸네일경로
						var fileCallPath=encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
									
						str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
						str+="<span>"+obj.fileName+"</span>";
						str+="<button style='cursor:pointer' type='button' data-file='"+fileCallPath+"' data-type='image' class='btn btn-warning btn-circle'>";
						str+=" <i class='fa fa-times'></i></button><br>";
						str+="<img src='/display?fileName="+fileCallPath+"'>";
						str+="</div></li>";
						console.log(fileCallPath);	
					}else{
						var fileCallPath=encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
								
						// '\\'를 '/'로 변경, '\\'는 윈도우, '/'는 웹에서 사용
						var fileLink=fileCallPath.replace(new RegExp(/\\/g),"/");
						console.log(fileLink);
						str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
						str+="<span>"+obj.fileName+"</span>";
						str+="<button style='cursor:pointer' data-file='"+fileCallPath+"' data-type='file' type='button' class='btn btn-warning btn-circle'>";
						str+=" <i class='fa fa-times'></i></button><br>";
						str+="<img src='/resources/img/attach.jfif'>";			
						str+="</div></li>";
					}
					
				});
				uploadResult.append(str);
				
			}
			
			
			
			//수정하기
			var formObj=$("form");//form태그를 찾음
			$("button").on("click",function(e){
				e.preventDefault();//전송을 막음
				
				var operation=$(this).data("oper"); // data-oper 속성값을 구함
				console.log(operation);
				if(operation==="remove"){
					formObj.attr("action","/board/remove");//form의 action값을 변경
				}else if(operation==="list"){
					//self.location="/board/list";//목록으로 이동
					//return;
					formObj.attr("action","/board/list").attr("method","get");
					
					//일단 필요한 것 먼저 백업
					var PageNumTag=$("input[name='pageNum']").clone();
					var amountTag=$("input[name='amount']").clone();
					var keywordTag=$("input[name='keyword']").clone();
					var typeTag=$("input[name='type']").clone();
					//입력항목전부삭제					
					formObj.empty;
					//필요한 4개 다시 추가
					formObj.append(PageNumTag);
					formObj.append(amountTag);
					formObj.append(keywordTag);
					formObj.append(typeTag);
				}else if(operation==='modify'){
					var str="";
					$(".uploadResult ul li").each(function(i,obj){
						var jobj=$(obj);
						str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>"
						str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>"
						str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>"
						str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>"
					});
				}
				formObj.append(str).submit();
			});
		});
	
	</script>

	   <!--  컨텐츠 ---------------------------------->	
       <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	글내용	
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form" action="/board/modify" method="post">
                        		<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
                        		<input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
                           		<input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                            	<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
                           		<div class="form-group">
                           			<label>글번호</label>
                           			<input class="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly>
                           		</div> 
                            	<div class="form-group">
                            		<label>Title</label>
                            		<input class="form-control" name="title" value='<c:out value="${board.title}"/>'>
                            	</div>
                            	<div class="form-group">
                            		<label>Content</label>
                            		<textarea class="form-control" rows="3" name="content"><c:out value="${board.content}"/></textarea>                          		
                            	</div>
                            	<div class="form-group">
                            		<label>Writer</label>
                            		<input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly>                            		
                            	</div>
                            	<button type="submit" data-oper="modify" class="btn btn-default">
                            		수정
                            	</button>
                            	<button type="submit" data-oper="remove" class="btn btn-default">
                            		삭제
                            	</button>
                            	<button type="submit" data-oper="list" class="btn btn-default">
                            		목록
                            	</button>
                          	</form>                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    
                    <div class="bigPictureWrapper">
						<div class="bigPicture"></div>
					</div>
                    
                    <!-- 첨부파일 ----------------------------------------------------->
		            <div class="row">
		            	<div class="col-lg-12">
		            		<div class="panel panel-default">
		                        <div class="panel-heading">
		                            	File Attach	
		                        </div>
		                        <!-- /.panel-heading -->
		                        <div class="panel-body">  
		                        	<div class="form-group uploadDiv">
		                        		<input type="file" name="uploadFile" multiple>
		                        	</div>                         
		                            <div class="uploadResult">
		                            	<ul></ul>      		
		                            </div>                          
		                        </div>
		                        <!-- /.panel-body -->
		                    </div>
		                    <!-- /.panel -->
		            	</div>
		            </div>
		            <!-- 첨부파일 -->
                    
                    
                    
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
        </div>
       <!-- 컨텐츠 -->
       

<%@ include file="../includes/footer.jsp" %>    