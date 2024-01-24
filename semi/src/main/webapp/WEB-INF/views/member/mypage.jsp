<%--
  Created by IntelliJ IDEA.
  User: banghansol
  Date: 2023/04/17
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <jsp:include page="/WEB-INF/views/includes/head.jsp"/>
    <style>
        textarea { resize: none}
        p.h4 { padding-bottom: 150px;}
    </style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/includes/header.jsp"/>
  <section class="section-margin">
    <div class="container">
      <%--
      <div class="card my-4">
        <div class="card-body row">
          <div class="col-md-3 d-flex flex-column align-items-center border">
            <i class="fas fa-camera h1"></i>
            <div>함상점</div>
            <div>청상추</div>
            <button class="btn btn-primary">내 상점 관리</button>
          </div>
          <div class="col-md-9 d-flex flex-row">
            <div class="col-md-12 row">
              <div class="col-sm-6">
                <div class="row">
                  <div class="form-group col-md-9">
                    <input class="form-control" type="text" id="shopName" name="shopName" placeholder="상점 이름을 입력해주세요!">
                  </div>
                  <div class="form-group col-md-3">
                    <button class="btn btn-primary">수정</button>
                  </div>
                </div>
              </div>
              <div class="col-sm-6 flex-column">
                  <button class="btn btn-warning mx-2">내 정보 수정</button>
                  <button class="btn btn-primary mx-2">내 주소록 관리</button>
              </div>
              <div class="col-md-12 card my-2 p-3">
                test1
              </div>
              <div class="col-md-12 card my-2 h-400">
                test2
              </div>

            </div>
          </div>

        </div>
      </div>
      --%>

          <c:if test="${result eq true}">
              <div class="alert alert-success" role="alert">
                      ${updateMsg}
              </div>
          </c:if>
          <c:if test="${result eq false}">
              <div class="alert alert-danger" role="alert">
                      ${updateMsg}
              </div>
          </c:if>

        <div class="card">
          <div class="card-body row pb-0">

            <div class="col-lg-3">
              <div class="blog_right_sidebar small h-100 rounded">
                <div class="single_sidebar_widget author_widget">
                  <img class="author_img rounded-circle" src="img/salad.png" alt="">
                  <p class="m-0 p-2 h4">${myInfo.shopVO.shopName}</p>
                    <c:if test="${myInfo.credit >= 20}">
                        <p class="mb-3 h5">등급 : 양상추</p>
                    </c:if>
                    <c:if test="${myInfo.credit >= 10 and myInfo.credit < 20}">
                        <p class="mb-3 h5">청상추</p>
                    </c:if>
                    <c:if test="${myInfo.credit < 10}">
                        <p class="mb-3 h5">등급 : 상추</p>
                    </c:if>
                  <%--<button type="button" class="btn btn-success">구독</button>--%>
                </div>
              </div>
            </div>
            <div class="col-lg-9 pl-0">
              <div class="d-flex justify-content-between">
                  <div>
                      <form method="post" class="d-flex flex-row" action="shopName" name="shopNameForm">
                        <div class="blog_right_sidebar d-inline card p-0">
                            <input class="form-control" type="text" name="shopName" value="${myInfo.shopVO.shopName}">
                        </div>
                          <input type="hidden" name="shopNo" value="${myInfo.memberNo}" />
                        <button class="button button--active px-3 py-1 mx-3 align-self-center">수정</button>
                      </form>
                  </div>
                  <div class="d-flex flex-row">
                      <a class="button button--active px-3 py-1 mx-3 align-self-center" href="${pageContext.request.contextPath}/member/modify">내 정보 수정</a>
                      <a class="button button--active px-3 py-1 align-self-center" href="${pageContext.request.contextPath}/member/address">내 주소록 관리</a>

                  </div>
                  <%--<div class="blog_right_sidebar d-inline card p-0">
                    <div class="single_sidebar_widget author_widget card-body px-3 py-2">
                      <p>서울시 양천구 신월동 월정로 262</p>
                    </div>
                  </div>--%>
              </div>
              <div class="blog_right_sidebar px-3 py-1 my-2 card">
                <ul class="d-flex card-body p-0">
                  <li class="p-2">○ 상점오픈일 : ${myInfo.openDate}일</li>
                  <li class="p-2">○ 상점방문수 : ${myInfo.shopVO.visits}회</li>
                  <li class="p-2">○ 상품판매 : ${pgCount}회</li>
                </ul>
              </div>

                  <%--
                <div class="d-flex single_sidebar_widget author_widget">
                  <p>${myInfo.shopVO.intro}</p>
                </div>
                  --%>
                <form method="post" action="shopIntro" id="shopIntroForm" name="shopIntroForm">
                  <textarea class="blog_right_sidebar form-control different-control w-100" id="intro" name="intro" placeholder="상점 소개글을 작성해주세요" rows="3">${myInfo.shopVO.intro}</textarea>
                    <input type="hidden" name="shopNo" value="${myInfo.memberNo}" />
                </form>
            </div>
          </div>
            <div class="px-3">

                  <button id="shopIntroSubmit" class="button button--active px-3 py-1 mx-1 my-2 align-self-center float-right">수정</button>
            </div>
        </div>

      <div class="card  my-4">
        <div class="card-body">
          <nav class="tab-area">
            <div class="nav nav-tabs" id="nav-tab" role="tablist">

              <button class="nav-link active" id="nav-boardlist-tab" data-toggle="tab" data-target="#nav-boardlist" type="button" role="tab" aria-controls="nav-home" aria-selected="true">상품목록</button>
              <button class="nav-link" id="nav-profile-tab" data-toggle="tab" data-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false">후기목록</button>
              <%--<button class="nav-link" id="nav-myreply-tab" data-toggle="tab" data-target="#nav-myreply" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">내가 쓴 후기</button>--%>
              <button class="nav-link" id="nav-mytrade-tab" data-toggle="tab" data-target="#nav-mytrade" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">내 요청</button>
              <button class="nav-link" id="nav-applytrade-tab" data-toggle="tab" data-target="#nav-applytrade" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">받은 요청</button>
              <button class="nav-link" id="nav-applyreserve-tab" data-toggle="tab" data-target="#nav-applyreserve" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">받은 예약 요청</button>
              <button class="nav-link" id="nav-applyfinish-tab" data-toggle="tab" data-target="#nav-applyfinish" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">거래완료 목록</button>
            </div>
          </nav>
          <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-boardlist" role="tabpanel" aria-labelledby="nav-home-tab">
              <%-- 상품목록 --%>
              <div class="row">
                  <c:if test="${empty myBoardList}">

                      <div class="col-md-12 col-lg-12 col-xl-12">
                          <p class="h4 text-center">작성한 게시글이 없습니다!</p>
                      </div>
                  </c:if>
                <c:forEach items="${myBoardList}" var="board">
                <%--
                <div class="col-md-6 col-lg-4 col-xl-3">
                  <div class="card text-center card-product">
                    <div class="card-product__img">
                      <c:if test="${attach.attNo == null}">
                        <img class="card-img"
                             src="${pageContext.request.contextPath}/resources/img/no-image.png"
                             alt="">
                      </c:if>

                      <ul class="card-product__imgOverlay p-2">
                        <li>
                          <a class="btn" href="#">
                            상점 보러가기
                          </a>
                        </li>
                        <li>
                          <i class="ti-heart"></i>
                        </li>
                      </ul>
                    </div>
                    <div class="card-body">
                      <p>test</p>
                      <h4 class="card-product__title">
                        <a href="${pageContext.request.contextPath}/board/${board.bno}">${board.title}</a>
                      </h4>
                      <p class="card-product__price">${board.price}</p>
                    </div>

                  </div>
                </div>
                --%>

                    <div class="col-md-6 col-lg-4 col-xl-3">
                        <div class="card text-center card-product">
                            <div class="card-product__img" style="box-shadow: 3px 3px 20px #ddd;">

                                <a href="${pageContext.request.contextPath}/board/${board.bno}">
                                    <c:if test="${board.attachs[0] == null}">
                                        <img class="card-img"
                                             src="${pageContext.request.contextPath}/resources/img/no-image.png"
                                             alt="">
                                    </c:if>
                                    <c:if test="${board.attachs[0] != null}">
                                        <img class="card-img"
                                             src="${pageContext.request.contextPath}/display${board.attachs[0].url}"
                                             alt="">
                                    </c:if>
                                </a>
                                <ul class="card-product__imgOverlay">

                                    <li><a href="${pageContext.request.contextPath}/shop/${board.memberNo}">
                                        <button>상점 보러가기</button>
                                    </a></li>
                                </ul>
                            </div>
                            <div class="card-body row p-3" style="text-align: left;">
                                <p class="col-6">${board.cateName}</p>
                                <p class="col-6 " font-size: 15px; style="text-align: right;">
                                    <i class=" fas fa-heart mt-1"></i> 50
                                </p>
                                <h4 class=" col-12 card-product__title mb-0">
                                    <a href="${pageContext.request.contextPath}/board/${board.bno}">${board.title}</a>
                                </h4>
                                <p class="col-12" style="font-size: 13px;">${board.addrName}</p>
                                <p class="col-6 mt-4">${board.memberName}</p>
                                <h5 class="card-product__price col-6 mt-4"
                                    style="text-align: right;">${board.price}원</h5>
                            </div>
                        </div>
                    </div>

                </c:forEach>

              </div>

            </div>
            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
<%--              ${replyList}--%>
              <ul class="list-group chat">
                  <c:if test="${empty replyList}">
                      <div class="col-md-12 col-lg-12 col-xl-12">
                          <p class="h4 text-center">작성한 후기가 없습니다!</p>
                      </div>
                  </c:if>
                <c:forEach items="${replyList}" var="reply">
                  <li class="list-group-item" data-rno="\${obj.rno}">
                      <%--
                    <div class="header">
                      <strong class="primary-font">${reply.nickname}</strong>
                      <small class="">${reply.dong}</small>
                      <small class="float-right">${reply.regdate}</small><br>®
                      <small class="">평점 :
                        ${reply.rating}점</small>
                    </div>
                    <div class="form-group col-10">
                      <p>${reply.comment}</p>
                    </div>
                      --%>

                          <div style="border-bottom: 1px solid #eee">
                              <h6 class="mt-3 ml-3 mb-0"  style="color: #4cd10f; ">작성후기</h6>
                              <a href="${pageContext.request.contextPath}/board/${reply.bno}" class="container">
                                  <div class="d-flex row  mt-0 mb-3" >
                                      <div class="col-2">

                                          <div class="ml-5 m-4" style="box-shadow: 3px 3px 10px #ddd; width: 100px; height: 100px">

                                              <c:if test="${reply.attachDTO eq null}">
                                                  <img class="card-img" src="${pageContext.request.contextPath}/resources/img/no-image-small.png" alt="">
                                              </c:if>
                                              <c:if test="${reply.attachDTO ne null}">
                                                  <img class="card-img" src="${pageContext.request.contextPath}/displaySmall${reply.attachDTO.url}" alt="">
                                              </c:if>

                                          </div>
                                      </div>
                                      <div class="mt-5 col-9">
                                          <div class="d-flex">
                                              <h6>[${reply.dong}]</h6>
                                              <h6 class="ml-3">${reply.title}</h6>
                                          </div>
                                          <div class="d-flex mt-3">
                                              <h4 style="font-size: 18px;">${reply.comment}</h4>
                                              <h4 class="ml-auto"style=" font-size: 18px;">${reply.price}원</h4>
                                          </div>
                                      </div>
                                  </div>
                              </a>
                          </div>
                  </li>

                </c:forEach>
              </ul>
            </div>
            <%--<div class="tab-pane fade" id="nav-myreply" role="tabpanel" aria-labelledby="nav-contact-tab">내가 쓴 후기</div>--%>
            <div class="tab-pane fade" id="nav-mytrade" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <c:if test="${empty mytr}">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <p class="h4 text-center">신청한 요청사항이 없습니다!</p>
                        </div>
                    </c:if>
                <%--
                    <div class="d-flex">
                    	<h6 class="mr-4">판매자</h6> <h6 class="mr-4">물품번호</h6> <h6 class="mr-5">물품</h6>
                    </div>
                --%>
                    <div class="card-body">
                        <div class="col-lg-12 d-flex flex-row py-2">
                            <div class="col-md-2">판매자</div>
                            <div class="col-md-2">글번호</div>
                            <div class="col-md-4">판매물품명</div>
                            <div class="col-md-2">판매상태</div>
                            <div class="col-md-2">요청삭제</div>
                        </div>                    
                    	<ul class="list-group chat" id="myTrade"></ul>
                 	 </div>
            </div>
            <div class="tab-pane fade" id="nav-applytrade" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <c:if test="${empty pg}">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <p class="h4 text-center">신청받은 요청사항이 없습니다!</p>
                        </div>
                    </c:if>
                <%--
                    <div class="d-flex">
                    	<h6 class="mr-4">구매자</h6> <h6 class="mr-4">물품번호</h6> <h6 class="mr-5">물품</h6>
                    </div>
                --%>
                    <div class="card-body">
                        <div class="col-lg-12 d-flex flex-row py-2">
                            <div class="col-md-1">구매자</div>
                            <div class="col-md-1">글번호</div>
                            <div class="col-md-4">판매물품명</div>
                            <div class="col-md-2">판매상태</div>
                            <div class="col-md-2">예약전환</div>
                            <div class="col-md-2">요청삭제</div>
                        </div>
                    	<ul class="list-group chat" id="prog"></ul>
                 	 </div>
            </div>
            <div class="tab-pane fade" id="nav-applyreserve" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <c:if test="${empty rs}">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <p class="h4 text-center">신청받은 예약요청이 없습니다!</p>
                        </div>
                    </c:if>
                <%--
                    <div class="d-flex">
                    	<h6 class="mr-4">구매자</h6> <h6 class="mr-4">물품번호</h6> <h6 class="mr-5">물품</h6>
                    </div>
                --%>
                    <div class="card-body">
                        <div class="col-lg-12 d-flex flex-row py-2">
                            <div class="col-md-1">구매자</div>
                            <div class="col-md-1">글번호</div>
                            <div class="col-md-4">판매물품명</div>
                            <div class="col-md-2">판매상태</div>
                            <div class="col-md-2">거래완료</div>
                            <div class="col-md-2">요청삭제</div>
                        </div>
                    	<ul class="list-group chat" id="reserve"></ul>
                 	</div>
            </div>
            <div class="tab-pane fade" id="nav-applyfinish" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <c:if test="${empty fi}">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <p class="h4 text-center">거래완료 내역이 없습니다!</p>
                        </div>
                    </c:if>
                <%--
                    <div class="d-flex">
                    	<h6 class="mr-4">구매자</h6> <h6 class="mr-4">물품번호</h6> <h6 class="mr-5">물품</h6>
                    </div>
                --%>
                    <div class="card-body">
                        <div class="col-lg-12 d-flex flex-row py-2">
                            <div class="col-md-2">구매자</div>
                            <div class="col-md-2">글번호</div>
                            <div class="col-md-6">판매물품명</div>
                            <div class="col-md-2">판매상태</div>
                        </div>
                    	<ul class="list-group chat" id="finish"></ul>
                 	</div>
            </div>			
          </div>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
 <script src="${pageContext.request.contextPath}/resources/js/reply.js"></script>
 <script>
 var cp = '${pageContext.request.contextPath}'
 $(function() {

     $("#shopIntroSubmit").click(function () {
         $("#shopIntroForm").submit();
     })
	
	 var memberNo = '${loginMember.memberNo}';
	 tradeService.getList({memberNo:memberNo}, function(result) {
		 for(var i in result) {
			 console.log(result[i].tradeNo)
		 }
		 var str = "";
		 for (var i in result) {
			 str += getTradeListr(result[i]);
			 $("#prog").html(str);
		 }		 		 
	 })
	 
	 function getTradeListr(obj) {
		return `<li class="list-group-item" data-tradeNo="\${obj.tradeNo}">
			<div class="col-lg-12 d-flex" id="state">
		    	<div class="col-sm-1">\${obj.nickname}</div>
		    	<div class="col-sm-1">\${obj.bno}</div>
		    	<div class="col-sm-4">\${obj.title}</div>
		    	<div class="col-sm-2">\${obj.pgName}</div>
                <div class="col-md-2 flex-row">
		    		<button class="button button--active p-2" id="change"><i style="color: #004211;" class="icofont-verification-check "></i>예약전환</button>
                </div>
                <div class="col-md-2">
		    		<button class="button button--active p-2"  id="remove"><i style="color: #004211;" class="icofont-verification-check "></i>요청삭제</button>
                </div>
		    </div>
			</li>
		</ul>`;	
	}
	 
	 $("#prog").on("click","#change", function() {				 
		 var str = "";
		 var obj = {progress:2, tradeNo:$(this).parent().parent().parent().attr("data-tradeno")}	
 		 tradeService.modify(obj, function(result) {
 			 tradeService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {
 					 str += getTradeListr(result[i]);					 
 				 }
 				$("#prog").html(str); 				 
 			 })
 		})
 		console.log(this);
			$("#prog").html(str);
	 		alert("수정완료");
	 })

	 $("#prog").on("click","#remove", function() {				 
		 var str = "";
		 var obj = {tradeNo:$(this).parent().parent().parent().attr("data-tradeno")}	
 		 tradeService.remove(obj, function(result) {
 			 tradeService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {
 					 str += getTradeListr(result[i]);					 
 				 }
 				$("#prog").html(str); 				 
 			 })
 		})
 		console.log(this);
			$("#prog").html(str);
	 		alert("삭제완료");
	 })	 	 	 
 })

$(function() {	 

	var memberNo = '${loginMember.memberNo}';
	reserveService.getList({memberNo:memberNo}, function(result) {
		for(var i in result) {
			console.log(result[i].tradeNo)
	 	}
	 	var str = "";
	 	for (var i in result) {
			str += getReserveListr(result[i]);
		 	$("#reserve").html(str);
	 	}
 	})
 
 function getReserveListr(obj) {

	return `<li class="list-group-item" data-tradeNo="\${obj.tradeNo}">
		<div class="col-lg-12 d-flex" id="state">
	    	<div class="col-sm-1">\${obj.nickname}</div>
	    	<div class="col-sm-1">\${obj.bno}</div>
	    	<div class="col-sm-4">\${obj.title}</div>
	    	<div class="col-sm-2">\${obj.pgName}</div>
            <div class="col-sm-2">
                <button class="button button--active p-2" id="change"><i style="color: #004211;" class="icofont-verification-check "></i>거래완료</button>
            </div>
            <div class="col-sm-2">
                <button class="button button--active p-2" id="remove"><i style="color: #004211;" class="icofont-verification-check "></i>요청삭제</button>
            </div>
    	</div>
		</li>
	</ul>`;


}
 
	$("#reserve").on("click","#change", function() {				 
	var str = "";
	var obj = {progress:3, tradeNo:$(this).parent().parent().parent().attr("data-tradeno")}	
	reserveService.modify(obj, function(result) {
		reserveService.getList({memberNo:memberNo}, function(result) {
				 for(var i in result) {
					 str += getReserveListr(result[i]);					 
				 }
				$("#reserve").html(str); 				 
			 })
		})
		console.log(this);
		$("#reserve").html(str);
 		alert("수정완료");
	})

	 $("#reserve").on("click","#remove", function() {				 
		 var str = "";
		 var obj = {tradeNo:$(this).parent().parent().parent().attr("data-tradeno")}	
		 reserveService.remove(obj, function(result) {
			 reserveService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {
 					 str += getReserveListr(result[i]);					 
 				 }
 				$("#reserve").html(str); 				 
 			 })
 		})
 		console.log(this);
			$("#prog").html(str);
	 		alert("삭제완료");
	 })		
})

$(function() {	 

	var memberNo = '${loginMember.memberNo}';

  console.log("finishList", finishService.getList({memberNo:memberNo}, function(result) {
    for(var i in result) {
      console.log("finish tradeNo", i, result[i].tradeNo)
    }
    var str = "";
    for (var i in result) {
      str += getFinishListr(result[i]);
      $("#finish").html(str);
    }
  })
  );
 
 function getFinishListr(obj) {

	return `<li class="list-group-item" data-tradeNo="\${obj.tradeNo}">
		<div class="col-lg-12 d-flex" id="state">
	    	<div class="col-sm-2">\${obj.nickname}</div>
	    	<div class="col-sm-2">\${obj.bno}</div>
	    	<div class="col-sm-6">\${obj.title}</div>
	    	<div class="col-sm-2">\${obj.pgName}</div>
    	</div>
		</li>
	</ul>`;


}
 
	
})

 $(function() {

	 var memberNo = '${loginMember.memberNo}';
	 myService.getList({memberNo:memberNo}, function(result) {
		 for(var i in result) {
			 console.log(result[i].tradeNo)
		 }
		 var str = "";
		 for (var i in result) {
			 str += getMyListr(result[i]);
			 $("#myTrade").html(str);
		 }
	 })

	 function getMyListr(obj) {
		 str = `<li class="list-group-item" data-tradeNo="\${obj.tradeNo}" data-progress="\${obj.progress}">
			<div class="col-lg-12 d-flex" id="state">
		    	<div class="col-sm-2">\${obj.nickname}</div>
		    	<div class="col-sm-2">\${obj.bno}</div>
		    	<div class="col-sm-4">\${obj.title}</div>
		    	<div class="col-sm-2">\${obj.pgName}</div>
                <div class="col-sm-2">
		    	    <button class="button button--active p-2" id="myRemove">
                    <i style="color: #004211;" class="icofont-verification-check "></i>요청삭제</button>
                </div>
		    </div>
			</li>
		</ul>`;
		return str;
	}
	 


	 $("#myTrade").on("click","#myRemove", function() {
		console.log("오비제 : ", obj)
		 var str = "";
		 var obj = {tradeNo:$(this).parent().parent().parent().attr("data-tradeno")}
		 var progress = $(this).parent().parent().parent().attr("data-progress")
		 console.log(progress)
		 if (progress < 3) {
			 myService.remove(obj, function(result) {
				 myService.getList({memberNo:memberNo}, function(result) {
	 				 for(var i in result) {
	 					 str += getMyListr(result[i]);
	 				 }
	 				$("#myTrade").html(str);
	 			 })
	 		})
	 		console.log(this);
				$("#myTrade").html(str);
	 		alert("삭제완료");
		 }
		 else {
			 alert("이미 완료되어 취소할 수 없습니다")
		 }
	 })
 })

 </script>
</body>
</html>