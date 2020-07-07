<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>
<style>
*{padding:0;margin:0}
.uploadResult{
	width:100%;
	height:150px;
	background-color:#EEE;
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
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: #EEE; 
  z-index: 100;
}

.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}

.bigPicture img{
	width:600px;
}

</style>
<script>
$(document).ready(function(){
	
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
							
				str+="<li style='cursor:pointer' data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
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
				str+="<li style='cursor:pointer' data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
				str+="<span>"+obj.fileName+"</span>";
				str+="<button style='cursor:pointer' data-file='"+fileCallPath+"' data-type='file' type='button' class='btn btn-warning btn-circle'>";
				str+=" <i class='fa fa-times'></i></button><br>";
				str+="<img src='/resources/img/attach.jfif'>";			
				str+="</div></li>";
			}
			
		});
		uploadResult.append(str);
		
	}
	
	
	
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
	

	
	/* 삭제처리 ***************************************************************/
	$(".uploadResult").on("click","button",function(e){
		var targetFile=$(this).data("file"); // data-file의 값을 추출
		var type=$(this).data("type"); // data-type의 값을 추출
		var targetLi=$(this).closest("li");// 클릭한 x버튼의 부모 <li>태그 선택
		
		$.ajax({
			url: '/deleteFile',
			data: {fileName: targetFile, type: type},
			beforeSend:function(xhr){xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)},
			dataType: 'text',
			type: 'POST',
			success: function(result){
				alert(result);
				targetLi.remove(); // <li>태그 삭제
			}
		});
	})	
	/* 삭제처리.끝. */	
	
	/* 등록처리 ****************************************************************/
	var formObj=$("form[role='form']");
	$("button[type='submit']").on("click",function(e){
		//전송을 막는다
		e.preventDefault;
		var str="";
		$(".uploadResult ul li").each(function(i,obj){
			var jobj=$(obj);
			str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>"
			str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>"
		});
		
		formObj.append(str).submit();
		
	});	
	/* 등록처리.끝. */
	
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	
	//파일찾기버튼을 이용해서 파일업로드	
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
			beforeSend:function(xhr){xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)},
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
                            	글등록	
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form role="form" action="/board/register" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            	<div class="form-group">
                            		<label>Title</label>
                            		<input class="form-control" name="title">
                            	</div>
                            	<div class="form-group">
                            		<label>Content</label>
                            		<textarea class="form-control" rows="3" name="content"></textarea>                          		
                            	</div>
                            	<div class="form-group">
                            		<label>Writer</label>
                            		<input class="form-control" name="writer" value='<sec:authentication property="principal.username"/>' readonly="readonly">                            		
                            	</div>
                            	<button type="submit" class="btn btn-default">등록</button>
                            	<button type="reset" class="btn btn-default">취소</button>
                            </form>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
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
                            		<input type="file" class="form-control" name="uploadFile" multiple>
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
       <!-- 컨텐츠 -->
       

<%@ include file="../includes/footer.jsp" %>    