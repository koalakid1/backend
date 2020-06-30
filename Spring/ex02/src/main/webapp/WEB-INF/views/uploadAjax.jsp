<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{padding: 0; margin: 0;}
	.bigPictureWrapper{
		position: absolute;
		display: none;
		justify-content: center;
		align-items: center;
		top: 0%;
		width: 100%;
		height: 100%;
		background-color: gray;
		z-index: 100;
	}

	.bigPicture{
		position: relative;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.bigPicture{
		width: 600px;
	}

	.uploadResult {
		width: 100%;
		background-color: gray;
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
	}
	.uploadResult ul li img {
		width: 20px;
	}
	
</style>
</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<div class ="uploadResult">
		<ul>
		
		</ul>
	</div>
	
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>
	
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	
		function showImage(fileCallPath){
			$(".bigPictureWrapper").css("display","flex").show();
			
			$(".bigPicture").html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>")
							.animate({width:'100%', height:'100%'}, 1000);
		}
		
		$(document).ready(function(){
			
			
			
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; // 5MB
			
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
			
			
			var cloneObj = $(".uploadDiv").clone();
			var uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr){
				var str = "";
				$(uploadResultArr).each(
					function(i,obj){
						if (!obj.image) {
							var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
							
							var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
							
							
							str += "<li><div><a href='/download?fileName="+fileCallPath+"'><img src='/resources/img/attach.jfif'>"
									+ obj.fileName + "</a><span style='cursor:pointer' data-file=\'" + fileCallPath + "\' data-type='file'> &times; </span></div></li>";
						}else {
// 							str += "<li>" + obj.fileName + "</li>";
							var fileCallPath = encodeURIComponent( obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
							
							var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
							
							// '\\'를 '/'로 변경. '\\'는 윈도우, '/'는 웹에서 사용
							originPath = originPath.replace(new RegExp(/\\/g),"/");
							
							str += "<li>";
							str += "<a href=\"javascript:showImage(\'"+ originPath +"\')\">"
							str += "<img src='/display?fileName="+ fileCallPath +"'></a>";
							str += "<span style='cursor:pointer' data-file=\'"+ fileCallPath + "\' data-type='image'> &times; </span></li>";
							
							console.log(originPath);
							
							console.log(str);
						}
					});
				
				uploadResult.append(str);
			}
			
			$(".bigPictureWrapper").on("click", function(e){
				$(".bigPicture").animate({width:'0%', hieght: '0%'}, 1000);
				setTimeout(function(){
					$(".bigPictureWrapper").hide();
				}, 1000);
			});
			
			$("#uploadBtn").on("click", function(e){
				
				var formData = new FormData();
				
				var inputFile = $("input[name='uploadFile']");
				
				var files = inputFile[0].files;
				
				console.log(files);
				
				for(var i = 0; i < files.length; i++){
					
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					
					formData.append("uploadFile", files[i]);
				}
				
				$.ajax({
					url : '/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'POST',
					dataType: 'json',
					success: function(result){
						console.log(result);
						showUploadedFile(result);
						$(".uploadDiv").html(cloneObj.html());
					}
				});
			});
			
			$(".uploadResult").on("click", "span", function(e){
				var targetFile = $(this).data("file");
				var type = $(this).data("type");
				console.log(targetFile);
				
				$.ajax({
					url: '/deleteFile',
					data: {fileName: targetFile, type:type},
					dataType: 'text',
					type: 'POST',
					success: function(result){
						alert(result);
					}
				}); //$.ajax
				
				$(this).closest("li").remove();
			});
			
		});
		
	</script>
</body>
</html>