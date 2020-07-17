<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <title>Habit, HAVE IT! 함께하고 싶은 습관</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700,800,900&display=swap"
    rel="stylesheet">

  <link rel="stylesheet" href="/resources/css/open-iconic-bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/animate.css">

  <link rel="stylesheet" href="/resources/css/owl.carousel.min.css">
  <link rel="stylesheet" href="/resources/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="/resources/css/magnific-popup.css">

  <link rel="stylesheet" href="/resources/css/aos.css">

  <link rel="stylesheet" href="/resources/css/ionicons.min.css">

  <link rel="stylesheet" href="/resources/css/bootstrap-datepicker.css">
  <link rel="stylesheet" href="/resources/css/jquery.timepicker.css">


  <link rel="stylesheet" href="/resources/css/flaticon.css">
  <link rel="stylesheet" href="/resources/css/icomoon.css">
  <link rel="stylesheet" href="/resources/css/style.css">
  
    <!-- fontawsome cdn-->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">

  <style>
    /* chat 아이콘*/
    .icon-chat {
      color: #589bf3c5;
    }

    /*좋아요 아이콘*/
    .fa-thumbs-up {
      color: #f358d1cb;
    }
    
    .top {
      background-color: #E64B4B !important; color: white !important;
    }
  </style>
</head>

<body>

  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
      <a class="navbar-brand" href="/"><img src="/resources/images/logo.png" style="width:200px"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
        aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="oi oi-menu"></span> Menu
      </button>

      <div class="collapse navbar-collapse" id="ftco-nav">
        <ul class="col-lg-12 navbar-nav ml-auto">
          <li class=" nav-item"><a href="introduce.html" class="nav-link">사이트소개</a></li>
          <li class=" nav-item"><a href="list.html" class="nav-link">전체글보기</a></li>
          <li class="col-lg-6 nav-item"></li>
          <li class=" nav-item"><a href="signIn.html" class="nav-link">로그인</a></li>
          <li class=" nav-item"><a href="signUp.html" class="nav-link">회원가입</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- END nav -->

  <div class="hero-wrap ftco-degree-bg" style="background-image: url('/resources/images/bg_3_1.jpg');"
    data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
      <div class="row no-gutters slider-text justify-content-center align-items-center">
        <div class="ftco-animate d-flex align-items-end">
          <div class="text text-center">
            <img src="/resources/images/logo2.png"
              style="margin-left: auto; margin-right: auto; margin-top:20px; margin-bottom: 30px; display: block; width:60%">
            <h1 class="mb-4" style="line-height:80px; font-weight: bold; color: white;">함께하고 싶은 습관</h1>
            <p style="font-size: 25px;">오늘도 실패? 노노! 오늘도 성공! 습관을 함께 공유하세요!<br> 매일 올리는 글이 내 습관이 됩니다!</p>

          </div>
        </div>
      </div>
    </div>
    <div class="mouse">
      <a href="#" class="mouse-icon">
        <div class="mouse-wheel"><span class="ion-ios-arrow-round-down"></span></div>
      </a>
    </div>
  </div>

  <section class="ftco-section ftco-no-pb goto-here">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-12 heading-section text-center ftco-animate mb-5">
          <span class="subheading">Habit, HAVE IT!</span>
          <h2 class="mb-2">주간 베스트</h2>
        </div>
      </div>


	  <div class="row d-flex">
		<table class="table" style="margin-bottom:p">
		<tr>
		<c:forEach var="hboard" items="${list}" varStatus="status">
		    <c:if test="${status.count < 5}">
		    	<td class="col-md-3 d-flex ftco-animate" style="float:left; border: none !important;">

			    <div class="blog-entry justify-content-end">
			             <div class="text">
			              <a href="boardView.html" class="block-20 img" style="margin-bottom: 12px; background-image: url('/resources/images/image_1.jpg');"></a>
			              <div style="float:left"><a href='<c:out value="${hboard.b_num}"/>'><button type="button" class="top btn">Top${status.count} </button></a></div>
			              <div style="margin-bottom: 0px; margin-left:70px; height: 40px; display:flex; align-items:center"><h6 style="margin-bottom: 0px; overflow:hidden;"><a href='<c:out value="${hboard.b_num}"/>'><c:out value="[${hboard.c_name}] ${hboard.b_title}"/></a></h6></div>
			              <div class="meta mb-3">
			                <div><a href='<c:out value="${hboard.b_num}"/>'><fmt:formatDate pattern="yy.MM.dd" value="${hboard.b_writeDate}"/></a></div>
			                <div><a href='<c:out value="${hboard.b_num}"/>'><c:out value="${hboard.m_name}"/></a></div>
			                <div><span class="icon-chat"></span><a href='board/<c:out value="${hboard.b_num}"/>'>3</a></div>
			                <div><span class="fas fa-user-clock"></span><a href='<c:out value="${hboard.b_num}"/>'><c:out value="${hboard.b_readCount}"/></a></div>
			                <div><span class="far fa-thumbs-up"></span><a href='<c:out value="${hboard.b_num}"/>'><c:out value="${hboard.b_likeCount}"/></a></div>
			              </div>
						 </div>
				  </div>
			    </td>
		    </c:if>
		</c:forEach>
		</tr>
		</table>

		</div>
	 <!-- paging ---------------------------------------------------->
      <div class="row">
        <div class="col text-center">
          <div class="block-27">
            <ul>
              <li><a href="#">&lt;</a></li>
              <li class="active"><span>1</span></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">&gt;</a></li>
            </ul>
          </div>
        </div>
      </div>
      <!-- paging -->
  
	 </div>	
  </section>

  <section class="ftco-section">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-12 heading-section text-center ftco-animate mb-5">
          <span class="subheading">Habit, HAVE IT!</span>
          <h2 class="mb-2">습관 카테고리</h2>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=1" class="img" style="background-image: url(/resources/images/work-1.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=1">식습관</a></h1>
              <span class="location">Eating habits</span>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=2" class="img" style="background-image: url(/resources/images/work-2.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=2">운동</a></h1>
              <span class="location">Exercise</span>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=3" class="img" style="background-image: url(/resources/images/work-3.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=3">수면</a></h1>
              <span class="location">Sleep</span>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=4" class="img" style="background-image: url(/resources/images/work-4.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=4">독서</a></h1>
              <span class="location">Reading</span>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=5" class="img" style="background-image: url(/resources/images/work-5.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=5">집안일</a></h1>
              <span class="location">Housework</span>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=6" class="img" style="background-image: url(/resources/images/work-6.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=6">반려동물</a></h1>
              <span class="location">Pets</span>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=7" class="img" style="background-image: url(/resources/images/work-7.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=7">일기</a></h1>
              <span class="location">Diary</span>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="property-wrap ftco-animate">
            <a href="/board/category?c_num=8" class="img" style="background-image: url(/resources/images/work-8.jpg);"></a>
            <div class="text">
              <h1><a href="/board/category?c_num=8">명상</a></h1>
              <span class="location">Meditation</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>


  <%@include file="./includes/footer.jsp" %>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="/resources/js/jquery.min.js"></script>
  <script src="/resources/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="/resources/js/popper.min.js"></script>
  <script src="/resources/js/bootstrap.min.js"></script>
  <script src="/resources/js/jquery.easing.1.3.js"></script>
  <script src="/resources/js/jquery.waypoints.min.js"></script>
  <script src="/resources/js/jquery.stellar.min.js"></script>
  <script src="/resources/js/owl.carousel.min.js"></script>
  <script src="/resources/js/jquery.magnific-popup.min.js"></script>
  <script src="/resources/js/aos.js"></script>
  <script src="/resources/js/jquery.animateNumber.min.js"></script>
  <script src="/resources/js/bootstrap-datepicker.js"></script>
  <script src="/resources/js/jquery.timepicker.min.js"></script>
  <script src="/resources/js/scrollax.min.js"></script>
  <script src="/resources/js/main.js"></script>
    
  </body>
</html>