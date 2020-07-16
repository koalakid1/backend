<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700,800,900&display=swap" rel="stylesheet">

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


    <link href='/resources/packages/core/main.css' rel='stylesheet' />
    <link href='/resources/packages/daygrid/main.css' rel='stylesheet' />
    <link href='/resources/packages/timegrid/main.css' rel='stylesheet' />
    <link href='/resources/packages/list/main.css' rel='stylesheet' />
    
	
	<script src="/resources/js/jquery.min.js"></script>
  <script src="/resources/js/jquery-migrate-3.0.1.min.js"></script>
	<script>

		$(document).ready(function(){
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="dd" var="today" />
			var dayCheck = '<c:out value="${today}"/>';

			
			$.ajax({
					type : 'GET',
					url : '/attend/category/'+${param.m_num}+".json",
					dataType:'json',
					success:function(data){
						var str = "";
						$.each(data,function(index, value){
							
							
							str += "<div class='container' style='margin-bottom : 20px;'>";
							str += "	<button class='btn btn-primary py-2 px-3' style='background-color: rgba(0,0,0,0.7) !important; border-color: rgba(0,0,0,0.8) !important; font-size: 20px; margin-bottom:20px; position: relative; left:0px; top:0px;'>";
							str += "<a href='#' style='color: white;'>"+value['c_name']+"</a></button>";
							str += "	<div class='progress' style='text-align : center !important;'><div class='progress-bar' role='progressbar' aria-valuenow='" + Math.floor((value['categoryCnt']/dayCheck) * 100) + "' aria-valuemin='0' aria-valuemax='100' style='width:"+ Math.floor((value['categoryCnt']/dayCheck) * 100) +"%; background-color: #00af1d;'>"
							str += Math.floor((value['categoryCnt']/dayCheck) * 100)+"%</div></div></div>";	
							
						});
						$(".achiv").html(str);
					}
				});
			});
/* <div class="container">
            <button class="btn btn-primary py-2 px-3" style="background-color: rgba(0,0,0,0.7) !important; border-color: rgba(0,0,0,0.8) !important; font-size: 20px; margin-bottom: 20px; position: relative; left:0px; top:0px;"><a href="#" style="color: white;">식습관</a></button>
          <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:65%; background-color: #00af1d;">
              65%
            </div>
          </div>
         </div>  */
	       
	</script>
	
    <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
          plugins: ['interaction','dayGrid'],
          header: {
            left: 'prev',
            center: 'title',
            right: 'next'
          },
          locale: 'ko',
          editable: false,
          navLinks: false, // can click day/week names to navigate views
          eventLimit: true, // allow "more" link when too many events
          events: function(param,callback){
        	  $.ajax({
  				type : 'GET',
  				url : '/attend/'+${param.m_num}+".json",
  				dataType:'json',
  				success:function(data){
  					var events = [];
  					$.each(data,function(index, value){
  						
  						/* events.push({
  							title : value['c_num'],
  							start : value['b_writeDate']
  						}); */
  						
  						$.each(value['categoryList'], function(index, value2){
  							events.push({
  								title:value2['c_name'],
  								start:value['b_writeDate']
  							});
  							
  							console.log(value2);
  						});
  						
  						console.log(value);
  						
  					});
  					callback(events);
  				}
  			})

          }
        });
        calendar.render();
      });

    </script>
    <style>

      body {
        margin: 0;
        padding: 0;
        font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
        font-size: 14px;
      }
      #loading {
        display: none;
        position: absolute;
        top: 10px;
        right: 10px;
      }

      #calendar {
        max-width: 900px;
        margin: 40px auto;
        padding: 0 10px;
      }

    </style>
</head>
<body>

    <%@include file="./includes/header.jsp" %>

	<section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <!-- 프로필 수정 -->
          <div class="col-md-4 sidebar ftco-animate">
            <div class="sidebar-box ftco-animate" style="border: 1px; background-color:rgba(0, 0, 0, 0.1); ">
              <img src="/resources/images/person_1.jpg" alt="Image placeholder" style="width: 100%;">
              <h2 style="margin-top: 30px;"><c:out value="${member.m_name}"/></h2>
              <h3 style="margin-bottom: 30px;"><c:out value="${member.m_email}"/></h3>
              <table style="border-collapse: collapse; width: 100%;">
                <tr style="border-top: 1px solid grey;">
                  <td style="padding-left: 10px; padding-right:20px; font-weight: bold;">좌우명</td>
                  <td style="width: 220px; height: 50px; line-height: 20px;"><c:out value="${member.m_intro}"/></td>
                </tr>
                <tr style="border-top: 1px solid grey;">
                  <td style="padding-left: 10px; padding-right:20px; font-weight: bold">작성글 수</td>
                  <td style="width: 220px; height: 50px;"><c:out value="${member.m_count}"/></td>
                </tr>
                <tr style="border-top: 1px solid grey; border-bottom: 1px solid grey;">
                  <td style="padding-left: 10px; padding-right:20px; font-weight: bold">레벨</td>
                  <td style="width: 220px; height: 50px;"><c:out value="${member.m_rank}"/></td> 
                </tr>
              </table>
            </div>
          </div>
          <div class="col-md-8 order-md-last ftco-animate">
            <!-- 달력 + 달성률 ---------------------------->
            <div class="sidebar-box ftco-animate">
              <!-- 달력 ----------------------------->
              <div class="col-md-12 heading-section text-center ftco-animate mb-5" >
                <span class="subheading">Habit, HAVE IT!</span>
                <h2 class="mb-2">출석체크</h2>
              </div>
              <div id='calendar'></div>
              <!-- 달성률 ------------------------>
              <div class="col-md-12 heading-section text-center ftco-animate mb-5">
                <span class="subheading" style="margin-top: 60px">Habit, HAVE IT!</span>
                <h2 class="mb-2">달성률</h2>
              </div>
			<!-- 달성률 프로그레스 바 --------------------->
              <div class="achiv">       
              </div>
            <!-- 달력 + 달성률 -->




            <!-- 내가 작성한 글 -->
            <div class="sidebar-box ftco-animate">
              <div class="col-md-12 heading-section text-center ftco-animate mb-5" style="margin-top: 90px;">
                <span class="subheading">Habit, HAVE IT!</span>
                <h2 class="mb-2">내가 쓴 글</h2>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(/resources/images/image_1.jpg);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">제가 매일 운동을 결심했던 이유</a></h3>
                  <div class="meta mb-3">
                    <div><a href="#">2020.07.20</a></div>
                    <div><a href="#">해빗이</a></div>
                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                    <div><a href="#" ><span class="fas fa-user-clock"></span> 3</a></div>
                    <div><a href="#" ><span class="far fa-thumbs-up"></span> 3</a></div>
                  </div>
                </div>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(/resources/images/image_2.jpg);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">일기쓰기가 습관이 되었습니다</a></h3>
                  <div class="meta mb-3">
                    <div><a href="#">2020.07.23</a></div>
                    <div><a href="#">습관지키미</a></div>
                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                    <div><a href="#" ><span class="fas fa-user-clock"></span> 3</a></div>
                    <div><a href="#" ><span class="far fa-thumbs-up"></span> 3</a></div>
                  </div>
                </div>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(/resources/images/image_3.jpg);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">습관 30일차! 한달 성공기</a></h3>
                  <div class="meta mb-3">
                    <div><a href="#">2020.07.24</a></div>
                    <div><a href="#">내일로</a></div>
                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                    <div><a href="#" ><span class="fas fa-user-clock"></span> 3</a></div>
                    <div><a href="#" ><span class="far fa-thumbs-up"></span> 3</a></div>
                  </div>
                </div>
              </div>

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

            <!-- 내가 좋아요 한 글 -->
            <div class="sidebar-box ftco-animate">
              <div class="col-md-12 heading-section text-center ftco-animate mb-5" style="margin-top: 60px;">
                <span class="subheading">Habit, HAVE IT!</span>
                <h2 class="mb-2">좋아요한 글</h2>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(/resources/images/image_1.jpg);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">습관 7계명. 오늘도 망설이시나요</a></h3>
                  <div class="meta mb-3">
                    <div><a href="#">2020.07.26</a></div>
                    <div><a href="#">하루</a></div>
                     <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                     <div><a href="#" ><span class="fas fa-user-clock"></span> 3</a></div>
                     <div><a href="#" ><span class="far fa-thumbs-up"></span> 3</a></div>
                   </div>
                </div>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(/resources/images/image_2.jpg);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">제가 매일 운동을 결심했던 이유</a></h3>
                  <div class="meta mb-3">
                    <div><a href="#">2020.07.20</a></div>
                    <div><a href="#">해빗이</a></div>
                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                    <div><a href="#" ><span class="fas fa-user-clock"></span> 3</a></div>
                    <div><a href="#" ><span class="far fa-thumbs-up"></span> 3</a></div>
                  </div>
                </div>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(/resources/images/image_3.jpg);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">일기쓰기가 습관이 되었습니다</a></h3>
                  <div class="meta mb-3">
                    <div><a href="#">2020.07.23</a></div>
                    <div><a href="#">습관지키미</a></div>
                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                    <div><a href="#" ><span class="fas fa-user-clock"></span> 3</a></div>
                    <div><a href="#" ><span class="far fa-thumbs-up"></span> 3</a></div>
                  </div>
                </div>
              </div>

              <div class="block-27" style="text-align: center;">
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


          </div> <!-- .col-md-8 -->
          

        </div>
      </div>
    </section> <!-- .section -->

    <%@include file="./includes/footer.jsp" %>





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
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="/resources/js/google-map.js"></script>
  <script src="/resources/js/main.js"></script>
  
  
  <script src='/resources/packages/core/main.js'></script>
    <script src='/resources/packages/interaction/main.js'></script>
    <script src='/resources/packages/daygrid/main.js'></script>
    <script src='/resources/packages/timegrid/main.js'></script>
    <script src='/resources/packages/list/main.js'></script>
 
  


  
</body>
</html>