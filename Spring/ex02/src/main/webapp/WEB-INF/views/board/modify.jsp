<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ include file="../includes/header.jsp" %>
	<style>
		*{padding: 0; margin: 0;}
		.bigPictureWrapper{
			position: fixed;
			display: none;
			justify-content: center;
			align-items: center;
			top: 0%;
			left: 0%;
			width: 100%;
			height: 100%;
			background-color: #EEE;
			z-index: 1000;
			background: rgba(255,255,255,0.5);
		}
	
		.bigPicture{
			position: relative;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		
		.bigPicture img{
			width: 600px;
		}
	
		.uploadResult {
			width: 100%;
			background-color: #EEE;
		}
		
		.uploadResult ul {
			display: flex;
			flex-flow: row;
			justify-content: center;
			align-items: center;
		}
		
		.uploadResult ul li {
			list-style: none;
			padding: 10px;
			align-content: center;
			text-align: center;
		}
		
		.uploadResult ul li img {
			width: 100px;
		}
		
		.uploadResult ul li span{
			color:black;
		}
	</style>
	<script>
		$(document).ready(function(){
			var formObj = $("form"); // form 태그를 찾음
			$("button").on("click",function(e){
				e.preventDefault(); //전송을 막음
				
				var operation = $(this).data("oper"); // data-oper 속성값을 구함
				console.log(operation);
				if(operation === "remove"){
					if(confirm("정말 삭제하시겠습니까?") == true){
						formObj.attr("action","/board/remove"); // form의 action 값을 변경
					}
					else{
						return false;
					}
				}else if (operation === "list"){
// 					self.location = "/board/list"; // 목록으로 이동
// 					return;
					formObj.attr("action","/board/list").attr("method","get");
					var pageNumTag = $("input[name='pageNum']").clone();
					var amountTag = $("input[name='amount']").clone();
					var keywordTag = $("input[name='keyword']").clone();
					var typeTag = $("input[name='type']").clone();
					
					formObj.empty();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(keywordTag);
					formObj.append(typeTag);
				}else if (operation === 'modify'){
					
					console.log("submit clicked");
					
					var str = "";
					
					$(".uploadResult ul li").each(function(i, obj){
						var jobj = $(obj);
						console.dir(jobj);
						
						str += "<input type='hidden' name = 'attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
						str += "<input type='hidden' name = 'attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
						str += "<input type='hidden' name = 'attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
						str += "<input type='hidden' name = 'attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
					});
					formObj.append(str).submit();
					
				}
				formObj.submit();
			});
		})
	</script>
	
	<!-- 컨텐츠 ---------------------------------------->
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
                            	글 내용
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form method="post" action="/board/modify" role="form">
                        		<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
                        		<input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
                        		<input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>">
                        		<input type="hidden" name="type" value="<c:out value='${cri.type}'/>">
	                        	<div class="form-group">
	                       			<label>글번호</label>		
	                       			<input class="form-control" name="bno" value ='<c:out value="${board.bno}" />' >
	                        	</div>
	                        	<div class="form-group">
	                       			<label>제목</label>		
	                       			<input class="form-control" name="title" value ='<c:out value="${board.title}"/>'>
	                        	</div>
	                        	<div class="form-group">
	                       			<label>내용</label>		
	                       			<textarea class="form-control" rows="3" name="content" ><c:out value="${board.content}" /></textarea>
	                        	</div>
	                        	<div class="form-group">
	                       			<label>작성자</label>		
	                       			<input class="form-control" name="writer" value ='<c:out value="${board.writer}" />' readonly>
                        		</div>
	                        	<button type="submit" data-oper="modify" class="btn btn-default">
	                        		수정
	                        	</button>
	                        	<button type="submit" data-oper="remove" class="btn btn-default">
	                        		삭제
	                        	</button>
	                        	<button type="reset" data-oper="list" class="btn btn-default">
	                        		목록
	                        	</button>
	                        </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <script>
            	$(document).ready(function(){
            		(function(){
        				var bno = "<c:out value='${board.bno}'/>";
        				
        				$.getJSON("/board/getAttachList", {bno: bno}, function(arr){
        					console.log(arr);
        					
        					var str = "";
        					
        					$(arr).each(function(i,attach){
        						//image type
        						if(attach.fileType){
        							var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);
        							str += "<li data-path = '"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
        							str += "<span>" + attach.fileName + "</span>";
        							str += "<button type='button' data-file=\'"+ fileCallPath +"\' data-type='file' class='btn btn-warning btn-circle'>";
        							str += "<i class='fa fa-times'></i></button><br>";
        							str += "<img src='/display?fileName="+fileCallPath+"'>";
        							str += "</div></li>";
        						}else{
        							str += "<li data-path = '"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
        							str += "<span>" + attach.fileName + "</span>";
        							str += "<button type='button' data-file=\'"+ fileCallPath +"\' data-type='file' class='btn btn-warning btn-circle'>";
        							str += "<i class='fa fa-times'></i></button><br>";
        							str += "<img src='/resources/img/attach.jfif'>";
        							str += "</div></li>";
        						}
        					});
        					$(".uploadResult ul").html(str);
        				}); // end getjson
        			})(); // end function
        			
            		$(".uploadResult").on("click", "button", function(e){
        				console.log("delete file");
        				
        				var targetFile = $(this).data("file");
        				
        				if(confirm("이 파일을 삭제하시겠습니까?")){
        					
        					var targetLi = $(this).closest("li");
        					targetLi.remove();
        					
//         					$.ajax({
//         						url: '/deleteFile',
//         						data: {fileName: targetFile, type: type},
//         						dataType: 'text',
//         						type: 'POST',
//         						success: function(result){
//         							alert(result);
//         							targetLi.remove();
//         						}
//         					});
        				}

        			});
        			
            		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
        			var maxSize = 5242880; //5mb
        			
        			function checkExtension(fileName, fileSize){
        				
        				if(fileSize >= maxSize){
        					alert("파일 사이즈 초과");
        					return false;
        				}
        				
        				if(regex.test(fileName)){
        					alert("해당 종류의 파일은 업로드 할 수 없습니다.");
        					return false;
        				}
        				return true;
        			}
        			
        			$("input[type='file']").change(function(e){
        				
        				var formData = new FormData();
        				
        				var inputFile = $("input[name='uploadFile']");
        				
        				var files = inputFile[0].files;
        				
        				for(var i=0; i<files.length; i++){
        					
        					if(!checkExtension(files[i].name, files[i].size)){
        						return false;
        					}
        					
        					formData.append("uploadFile", files[i]);
        					console.log("pick");
        				}
        				
        				$.ajax({
        					url: '/uploadAjaxAction',
        					processData: false,
        					contentType: false,
        					data: formData,
        					type: 'POST',
        					dataType: 'json',
        					success: function(result){
        						console.log(result);
        						showUploadResult(result);
        					}
        				}); //ajax
        				
        			});
        			
        			function showUploadResult(uploadResultArr){
        				if(!uploadResultArr || uploadResultArr.lenth == 0){return;}
        				var uploadResult = $(".uploadResult ul");
        				var str = "";
        				$(uploadResultArr).each(
        					function(i,obj){
        						if (!obj.image) {
        							var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);

        							var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
        							str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
        							str += "<span>" + obj.fileName + "</span>";
        							str += "<button type='button' data-file=\'"+ fileCallPath +"\' data-type='file' class='btn btn-warning btn-circle'>";
        							str += "<i class='fa fa-times'></i></button><br>";
        							str += "<img src='/resources/img/attach.jfif'>";
        							str += "</div></li>";
        							console.log(fileCallPath);
        							
        						}else {
//         							str += "<li>" + obj.fileName + "</li>";
        							var fileCallPath = encodeURIComponent( obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
        							
//         							var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
        							
//         							// '\\'를 '/'로 변경. '\\'는 윈도우, '/'는 웹에서 사용
//         							originPath = originPath.replace(new RegExp(/\\/g),"/");
        							
        							str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
        								str += "<span>" + obj.fileName + "</span>";
        							str += "<button type='button' data-file=\'"+ fileCallPath +"\' data-type='image' class='btn btn-warning btn-circle'>";
        							str += "<i class='fa fa-times'></i></button><br>";
        							str += "<img src='/display?fileName="+fileCallPath+"'>";
        							str += "</div></li>";
        							console.log(str);
        						}
        					});
        				
        				uploadResult.append(str);
        			}
        			
            	});
            </script>
            
            <!-- 첨부파일 -------------------->
           	<div class="bigPictureWrapper">
           		<div class="bigPicture">
           		</div>
           	</div>
           	<div class="row">
           		<div class="col-lg-12">
           			<div class="panel panel-default">
           				
           				<div class="panel-heading">Files</div>
           				
           				<div class="panel-body">
           				
           					<div class="form-group uploadDiv">
           						<input type="file" name="uploadFile" multiple="multiple">
           					</div>
           					
           					<div class="uploadResult">
           						<ul>
           						</ul>
           					</div>
           				</div>

           			</div>
           		</div>
           	</div>
           	<!-- 첨부파일 -->
            
        </div>
        <!-- 컨텐츠 -->
	<%@ include file="../includes/footer.jsp" %>
</body>

</html>
