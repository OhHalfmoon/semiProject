<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <jsp:include page="includes/head.jsp"/>
</head>
<body>
  <jsp:include page="includes/header.jsp"/>

 <!--================ End Header Menu Area =================-->
  <main class="site-main">

    <!--================ Hero banner start =================-->
    <section class="container-fluid p-0 pb-5  mb-5 mt-5">

      <div id="demo" class="carousel slide" data-ride="carousel">
        <ul class="carousel-indicators">
          <li data-target="#demo" data-slide-to="0" class="active"></li>
          <li data-target="#demo" data-slide-to="1"></li>
          <li data-target="#demo" data-slide-to="2"></li>
        </ul>
        <div class="carousel-inner ">
          <div class="carousel-item active ">
            <img src="${pageContext.request.contextPath}/resources/img/main_image.png" alt="Los Angeles" width="1100" height="500">
            <div class="carousel-caption">
            </div>
          </div>
          <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/resources/img/main_image2.png" alt="Chicago" width="1100" height="500">
            <div class="carousel-caption">
             
            </div>
          </div>
          <div class="carousel-item ">
            <img src="${pageContext.request.contextPath}/resources/img/main_image3.png" alt="New York" width="1100" height="500">
            <div class="carousel-caption">
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
          <i class="icofont-arrow-left" style="color: #4cd10f; opacity: 70%; font-size: 40px;"></i>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
          <i class="icofont-arrow-right" style="color: #4cd10f; opacity: 70%; font-size: 40px;"></i>
        </a>
      </div>


    </section>
    <!--================ Hero banner start =================-->


    <!-- ================ Blog section start ================= -->
  <%--   <section class="blog">
      <div class="container">
        <div class="section-intro pb-60px">
          <h2>우리동네 <span style="color: #4cd10f;">소식</span>
          </h2>
          <p>싱싱한 우리동네 소식을 들어봐요</p>
        </div>

        <div class="row">


          <div class="col-sm-12 col-lg-6 mb-4 mb-lg-0">
            <div class="card card-blog">
              <div class="card-blog__img">
                <img class="card-img rounded-0" src="${pageContext.request.contextPath}/resources/img/blog/blog2.png" alt="">
              </div>
              <div class="card-body">
                <ul class="card-blog__info">
                  <li><a href="#">By Admin</a></li>
                  <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                </ul>
                <h4 class="card-blog__title"><a href="single-blog.html">The Shopping News also offers top-quality
                    printing services</a></h4>
                <p>Let one fifth i bring fly to divided face for bearing divide unto seed. Winged divided light Forth.
                </p>
                <a class="card-blog__link" href="#">Read More <i class="ti-arrow-right"></i></a>
              </div>
            </div>
          </div>

          <div class="col-sm-12 col-lg-6 mb-4 mb-lg-0">
            <div class="card card-blog">
              <div class="card-blog__img">
                <img class="card-img rounded-0" src="${pageContext.request.contextPath}/resources/img/blog/blog3.png" alt="">
              </div>
              <div class="card-body">
                <ul class="card-blog__info">
                  <li><a href="#">By Admin</a></li>
                  <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                </ul>
                <h4 class="card-blog__title"><a href="single-blog.html">Professional design staff and efficient
                    equipment you’ll find we offer</a></h4>
                <p>Let one fifth i bring fly to divided face for bearing divide unto seed. Winged divided light Forth.
                </p>
                <a class="card-blog__link" href="#">Read More <i class="ti-arrow-right"></i></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section> --%>
 
    <!-- ================ trending product section start ================= -->
    <section class="section-margin calc-60px">
      <div class="container">
        <div class="section-intro pb-60px">
          <h3><span style="color: #4cd10f;">상</span>상하던 오늘의 상품 <span style="color: #4cd10f;">추</span>천</h3>
          <p>우리동네 물품을 보고싶으면 로그인 해주세요</p>
        </div>
        <div class="row">
        <c:forEach items="${list}" var="board">
				<c:forEach items="${board.attachs}" var="attach"> 
								
								<div class="col-md-6 col-lg-4 col-xl-3">
									<div class="card text-center card-product">
										<div class="card-product__img" style="box-shadow: 3px 3px 20px #ddd;">
										
										<a href="${pageContext.request.contextPath}/board/${board.bno}">
											<c:if test="${attach.attNo == null}">
												<img class="card-img"
													 src="${pageContext.request.contextPath}/resources/img/no-image.png"
													alt="">
											</c:if>
											<c:if test="${attach.attNo != null}">
											<img class="card-img"
												 src="${pageContext.request.contextPath}/display${attach.url}"
												alt="">
											</c:if>
											</a>
											<ul class="card-product__imgOverlay">

												<li><a href="${pageContext.request.contextPath}/shop/${board.memberNo}"><button>상점 보러가기</button></a></li>
												
											</ul>
										</div>
										<div class="card-body row p-3" style="text-align: left;">
											<p class="col-6">${board.cateName}</p>
											<p class="col-6 " font-size: 15px; style="text-align: right;">
										
											</p>
											<h4 class=" col-12 card-product__title mb-0">
												<a href="${pageContext.request.contextPath}/board/${board.bno}">${board.title}</a>
											</h4>
											<p class="col-12" style="font-size: 13px;">${board.addrName}</p>
											<c:if test="${board.memberName == null}">
												<p class="col-6 mt-4">탈퇴한 회원</p>
											</c:if>
											<c:if test="${board.memberName != null}">
												<p class="col-6 mt-4">${board.memberName}</p>
											</c:if>
											
											<h5 class="card-product__price col-6 mt-4"
												style="text-align: right;">${board.price}원</h5>
										</div>
									</div>
								</div>

					</c:forEach>  
				</c:forEach>

         



          <div class="container" style="text-align: center;">
            <a class="button button--active mt-3 mt-xl-4" href="${pageContext.request.contextPath}/board/list">상추 더보기</a>

          </div>
        </div>
      </div>
    </section>
    <!-- ================ trending product section end ================= -->


    <!-- ================ offer section start ================= -->
     <!-- 뺄까 다른 콘텐츠 넣을까 고민중 -->
     <c:if test="${loginMember eq null}">
     
    <section style="background-color: #f2ffec;" id="parallax-1" data-anchor-target="#parallax-1">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <div class="offer__content" style="padding-right: 100px;">
              <h3>싱싱한 상추 만나러가기</h3>
              <h4>동네 상추</h4>
              <p>로그인해서 동네 싱싱한 상추를 만나러가보세요</p>
              <div class="float-right mb-5">
	              <a class="button button--active my-5" href="${pageContext.request.contextPath}/member/login">로그인 하러가기</a>
	              <a class="button button--active my-5 ml-3" href="${pageContext.request.contextPath}/member/terms">회원가입 하러가기</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    </c:if>
     <c:if test="${loginMember ne null}">
     
    <section style="background-color: #f2ffec;" id="parallax-1" data-anchor-target="#parallax-1">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <div class="offer__content " style="padding-right: 100px;">
              <h3>환영합니다. <span style="color: #4cd10f;">${loginMember.nickname}</span>님!</h3>
              <h4>오늘도 우리동네 친구들과 상추!</h4>
               <div class="float-right mb-5">
	              <a class="button button--active my-5" href="${pageContext.request.contextPath}/board/write">판매하러가기</a>
	              <a class="button button--active my-5 ml-3" href="${pageContext.request.contextPath}/board/listaddr">우리동네마켓 보러가기</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    </c:if>
    <!-- ================ offer section end ================= -->





  </main>


  <jsp:include page="includes/footer.jsp"/>
</body>
</html>