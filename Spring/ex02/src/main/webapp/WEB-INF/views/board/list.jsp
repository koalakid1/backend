<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

	<script type="text/javascript">
   
   
   $(document).ready(function(){
   
      // 모달창 자바스크립트 (시작) ------------------------------>
      var result='<c:out value="${result}"/>';
      
      checkModal(result);
      
      history.replaceState({},null,null); //history.state를 null로 변경
      
      function checkModal(result){
         if(result==='' || history.state){
            return;
         }
         if(parseInt(result)>0){
            $(".modal-body").html("게시글 "+parseInt(result)+"번이 등록되었습니다.");
         }
         $("#myModal").modal("show");
      }
      // 모달창 자바스크립트 (끝) --------->
   
      // 등록 버튼 클릭 (시작) ------------------------>
      $("#regBtn").on("click", function(){
         self.location="/board/register";
      });
      // 등록 버튼 클릭 (끝) ------->
      
      var actionForm = $("#actionForm");
      $(".paginate_button a").on("click",function(e){
    	  e.preventDefault();
    	  console.log("click");
    	  actionForm.find("input[name='pageNum']").val($(this).attr("href"));
    	  actionForm.submit();
      });
      
      $(".move").on("click", function(e){
    	  e.preventDefault(); //다른페이지로 이동을 막음
    	  actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
    	  actionForm.attr("action","/board/get");
    	  actionForm.submit();
      });
      
      var searchForm = $("#searchForm");
      
      $("#searchForm button").on("click", function(e){
    	
    	  if(!searchForm.find("option:selected").val()){
    		  alert("검색종류를 선택하세요");
    		  return false;
    	  }
    	  
    	  if(!searchForm.find("input[name='keyword']").val()){
    		  alert("키워드를 입력하세요");
    		  return false;
    	  }
    	  
    	  searchForm.find("input[name='pageNum']").val("1");
    	  e.preventDefault();
    	  
    	  
    	  searchForm.submit();
    	  
      });
      
      
   });
   
   function selChange(){
  	  $("#amount").submit();
    }
    
   
</script>
<!-- 컨텐츠 ---------------------------------------->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tables</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					글 목록
					<button id="regBtn" type="button" class="btn btn-primary btn-xs pull-right">글쓰기</button>
				</div>
				
				
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%"
						class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<form action="/board/list" id="amount" method="get" >
								<input type="hidden" name="type" value="${pageMaker.cri.type}">
								<input type="hidden" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>" />
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
								<select name="amount" onchange="selChange()" style="float:right;">
									<option value="5"
<%-- 										<c:if test="${pageMaker.cri.amount == 5}">selected</c:if> --%>
										>5줄 보기</option>
									<option value="10"
										<c:if test="${pageMaker.cri.amount == 10}">selected</c:if>>10줄 보기</option>
									<option value="15"
										<c:if test="${pageMaker.cri.amount == 15}">selected</c:if>>15줄 보기</option>
									<option value="20"
										<c:if test="${pageMaker.cri.amount == 20}">selected</c:if>>20줄 보기</option>
								</select>
							</form>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>수정일</th>
							</tr>
						</thead>
						<c:forEach items="${list}" var="board">
							<tr>
								<td width="50"><c:out value="${board.bno}" /></td>
								<td width="330">
<%-- 									<a href="/board/get?bno=<c:out value='${board.bno}'/>"> --%>
<%-- 										<c:out value="${board.title}" /> --%>
<!-- 									</a> -->
										<a class="move" href="<c:out value='${board.bno}'/>">
											<c:out value="${board.title}"/>
										</a>
								</td>
								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.updateDate}" /></td>
							</tr>
						</c:forEach>
					</table>
					<!-- /.table-responsive -->
						
					<!-- 검색창 ---------------------------->
					
					<div class="row">
						<div class="col-lg-12">
							<form action="/board/list" id="searchForm" method="get">
								<select name="type">
									<option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>								
									<option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
									<option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
									<option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
									<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option>
									<option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목 or 작성자</option>
									<option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목 or 내용 or 작성자</option>
								</select>
								<input type="text" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>" />
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
								<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
								<button class="btn btn-default">Search</button>
							</form>
						</div>
					</div>
					
					<!-- 검색창 -->
					
					<!-- paging ---------------------------------->
					<div class="pull-right">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li class="paginate_button previous">
									<a href="${pageMaker.startPage-1}">Previous</a>
								</li>
							</c:if>
							
							<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<li class="paginate_button ${pageMaker.cri.pageNum==num?"active":"" }">
									<a href="${num}">${num}</a>
								</li>
							</c:forEach>
							
							<c:if test="${pageMaker.next}">
								<li class="paginate_button next">
									<a href="${pageMaker.endPage+1}">Next</a>
								</li>
							</c:if>
						</ul>
					</div>
					
					<form action="/board/list" method="get" id="actionForm">
						<input type="hidden" name="pageNum" value = '${pageMaker.cri.pageNum}'>
						<input type="hidden" name="amount" value = '${pageMaker.cri.amount}'>
						<input type="hidden" name="type" value="<c:out value='${pageMaker.cri.type}'/>">
						<input type="hidden" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>">
					</form>
					
					<!-- paging -->
					<!-- Modal ------------------------------------>
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content -->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">알림</h4>
								</div>
								<div class="modal-body">
									<p>처리가 완료되었습니다.</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">확인</button>
								</div>
							</div>

						</div>
					</div>
					<!-- Modal -->

				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>
<!-- 컨텐츠 -->
<%@ include file="../includes/footer.jsp"%>
</body>

</html>
