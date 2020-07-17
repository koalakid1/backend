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

<link href="/resources/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- NProgress -->
  <link href="/resources/vendors/nprogress/nprogress.css" rel="stylesheet">
<!--   iCheck -->
  <link href="/resources/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
 <!--  Custom Theme Style -->
  <link href="/resources/css/custom.min.css" rel="stylesheet">
<!--   Dropzone.js -->
  <link href="/resources/vendors/dropzone/dist/min/dropzone.min.css" rel="stylesheet">
  <!-- bootstrap-wysiwyg -->
  <link href="/resources/vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
 

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
  </style>

</head>

<body>


  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
      <a class="navbar-brand" href="index.html"><img src="/resources/images/logo.png" style="width:200px"></a>
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

  <div class="hero-wrap ftco-degree-bg" style="background-image: url('/resources//images/bg_2.jpg');height: 400px;"
    data-stellar-background-ratio="0.5">
    <div class="overlay" style="height: 400px;"></div>
    <div class="container" style="height: 400px;">
      <div class="row no-gutters slider-text justify-content-center align-items-center"
        style="height: 200px; padding-top: 150px; padding-bottom: 100px;">
        <div class="ftco-animate d-flex align-items-end">
          <div class="text text-center">
            <h1 class="mb-4" style="font-size: 48px; line-height:70px; font-weight: bold;">Habit, HAVE IT!<br>함께하고 싶은 습관
            </h1>
          </div>
        </div>
      </div>
    </div>
  </div>




  <!-- 전체글보기(게시글) --------------------------------------------------->
  <section class="ftco-section2">
    <div class="container">

      <!-- 카테고리 ---------------------------------------------->
      <div class="selectCategory">
        <ul class="pagination  pagination-lg justify-content-center">
          <li class="page-item" style="width: 130px; text-align: center;"><a class="page-link" href="#">식습관</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">운동</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">수면</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">독서</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">집안일</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">반려동물</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">일기</a></li>
          <li class="page-item" style="width: 130px; text-align: center"><a class="page-link" href="#">명상</a></li>
        </ul>
      </div>
      <!-- 카테고리 끝 -->
    </div>
  </section>
  <!-- 전체글보기(게시글) 끝-->

  <section class="ftco-section3">
    <div class="container body">
      <div>
        <div class="main_container">
          <!-- page content -->
          <div role="main">
            <div class="clearfix"></div>
            <!--UploadBoard-->
            <div class="row">
              <div class="col-md-10" style="margin:0 auto;">
                <div class="x_panel">
                  <div class="x_title">
                    <h3>게시물 등록</h3>
                    <div class="clearfix"></div>
                  </div>

                  <!--formContent-->
                  <div class="x_content">
                    <br />
                    <form class="form-horizontal form-label-left" action="/board/register" method="post">
                      <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2">제목</label>
                        <div class="col-md-9 col-sm-9 ">
                          <input type="text" name="b_title" id="autocomplete-custom-append"
                            class="form-control col-md-10" placeholder="제목을 입력 하세요" />
                        </div>
                      </div>

                      <!--카테고리 고르기 -->
                      <div class="form-group row" style="margin-top: 30px;">
                        <label class="control-label col-md-2 col-sm-2">카테고리</label>
                        <div class="col-md-9 col-sm-9">
                          <p style="word-spacing: 11px;">
	                          	<c:forEach items="${categoryList}" var="category">
	                          		<input class="flat" type="radio" name="c_num" value="<c:out value='${category.c_num}'/>" /> <c:out value='${category.c_name}'/>
	                          	</c:forEach>
                          </p>
                        </div>
                      </div>
                      <!--/카테고리 고르기-->

                      <!--태그-->
                      <div class="form-group row" style="margin-top: 10px;">
                        <label class="control-label col-md-2 col-sm-2">태그</label>
                        <div class="col-md-6 col-sm-6">
                          <input id="tags_1" type="text" class="form-control" value="식습관, 운동, 수면" />
                        </div>
                      </div>
                       <!--/태그-->
                             <!--TextContent-->
                  <div class="x_content">
                    <div id="alerts"></div>
                    <div class="btn-toolbar editor" data-role="editor-toolbar" data-target="#editor-one">
               

                      <div class="btn-group">
                        <!-- <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size">
                            <i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a> -->
                        <ul class="dropdown-menu">
                          <li>
                            <a data-edit="fontSize 5">
                              <p style="font-size:17px">Huge</p>
                            </a>
                          </li>
                          <li>
                            <a data-edit="fontSize 3">
                              <p style="font-size:14px">Normal</p>
                            </a>
                          </li>
                          <li>
                            <a data-edit="fontSize 1">
                              <p style="font-size:11px">Small</p>
                            </a>
                          </li>
                        </ul>
                      </div>

                      <div class="btn-group">
                        <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
                        <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
                        <a class="btn" data-edit="strikethrough" title="Strikethrough"><i
                            class="fa fa-strikethrough"></i></a>
                        <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i
                            class="fa fa-underline"></i></a>
                      </div>

                      <div class="btn-group">
                        <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i
                            class="fa fa-list-ul"></i></a>
                        <a class="btn" data-edit="insertorderedlist" title="Number list"><i
                            class="fa fa-list-ol"></i></a>
                        <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i
                            class="fa fa-dedent"></i></a>
                        <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
                      </div>

                      <div class="btn-group">
                        <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i
                            class="fa fa-align-left"></i></a>
                        <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i
                            class="fa fa-align-center"></i></a>
                        <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i
                            class="fa fa-align-right"></i></a>
                        <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
                            class="fa fa-align-justify"></i></a>
                      </div>

                      <!--파일첨부 -->
                      <!-- <div class="btn-group">
                          <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="fa fa-picture-o"></i></a>
                          <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
                        </div> -->

                      <div class="btn-group">
                        <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
                        <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
                      </div>
                    </div>
                    <div id="editor-one" class="editor-wrapper"></div>
                    <textarea name="b_content" id="descr" style="display:none;"></textarea>
                    <br />
                  </div>
                  <!--/TextContent-->
                     <!-- button -->
                        <button type="reset"
                        style="background-color: rgba(0, 0, 0, 0.5); color: white; width: 80px;">취소</button>
                      <button type="submit" 
                          style="background-color: #F49A24; color: white; width: 200px;">등록</button>
                          <!-- /button -->
                    	<input type="hidden" name="m_num" value="${param.m_num}">
                    </form>

                  </div>
                  <!--/formContent-->
                  
             
                
                  <div class="x_content">
                    <p>File첨부 하기</p>
                    <form action="form_upload.html" class="dropzone"></form>
                    <br />
                    <br />
                    <br />
                    <br />
                  </div>
             
                  <!--Button-->
               
                </div>
              </div>
            </div>
            <!--/UploadBoard-->
          </div>
        </div>
      </div>
    </div>
  </section>










  <!-- footer ---------------------------------------------->
  <footer class="ftco-footer ftco-section">
    <div class="container">
      <div class="row mb-5">
        <div class="col-md">
          <div class="ftco-footer-widget mb-4">
            <h2 class="ftco-heading-2">Habit, HAVE IT!</h2>
            <h2>습관 공유, 더 나은 삶, 더 나은 세상을 만들어 갑니다!</h2>
            <p>해빗해빗 유한회사 통신판매업신고번호: 제2018-서울종로-0426호 전화번호: 02-303-0812</p>
            <p>대표: Habit Haveit</p>
            <p>이메일 주소: habit@haveit.com</p>
            <p>주소: 대한민국 서울특별시 종로구 우정국로 26, 센트로폴리스 A동 20층 우편번호 03161</p>
            <p>공정거래위원회 웹사이트 링크</p>
            <ul class="ftco-footer-social list-unstyled mt-5">
              <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
              <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
              <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 text-center">

          <p>Copyright &copy;
            <script>document.write(new Date().getFullYear());</script> All rights reserved | Habit, HAVE IT! 해빗해빗
            유한회사</a></p>
        </div>
      </div>
    </div>
  </footer>





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


  <!-- jQuery -->
  <script src="/resources/vendors/jquery/dist/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="/resources/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!-- FastClick -->
  <script src="/resources/vendors/fastclick/lib/fastclick.js"></script>
  <!-- NProgress -->
  <script src="/resources/vendors/nprogress/nprogress.js"></script>
  <!-- bootstrap-progressbar -->
  <script src="/resources/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
  <!-- iCheck(checkBox) -->
  <script src="/resources/vendors/iCheck/icheck.min.js"></script>
  <!-- bootstrap-wysiwyg(태그) -->
  <script src="/resources/vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
  <script src="/resources/vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
  <script src="/resources/vendors/google-code-prettify/src/prettify.js"></script>
  <!-- jQuery Tags Input -->
  <script src="/resources/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>

  <script src="/resources/vendors/parsleyjs/dist/parsley.min.js"></script>
  <!-- Autosize -->
  <script src="/resources/vendors/autosize/dist/autosize.min.js"></script>
  <!-- jQuery autocomplete -->
  <script src="/resources/vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
  <!-- Dropzone.js -->
  <script src="/resources/vendors/dropzone/dist/min/dropzone.min.js"></script>
  <!-- Custom Theme Scripts -->
  <script src="/resources/js/custom.min.js"></script>


</body>

</html>