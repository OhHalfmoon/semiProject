<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="includes/head.jsp"/>
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<main class="site-main">
    <section class="blog_area single-post-area py-80px section-margin--small">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="blog_right_sidebar small" style="width: 250px; height: 240px">
                        <div class="single_sidebar_widget author_widget">
                            <%--                            <img class="author_img rounded-circle" src="img/salad.png" alt="">--%>
                            <h4>${shopVO.shopName}</h4>
                            <c:if test="${memberVO.credit >= 20}">
                                <p>등급 : 양상추</p>
                            </c:if>
                            <c:if test="${memberVO.credit >= 10 and memberVO.credit < 20}">
                                <p>등급 : 청상추</p>
                            </c:if>
                            <c:if test="${memberVO.credit < 10}">
                                <p>등급 : 상추</p>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9" style="padding-left: 0px">
                    <div class="d-flex" style="justify-content: space-between;">
                        <div class="blog_right_sidebar"
                             style="width: auto;height: auto;padding: 5px 10px; text-align: center;">
                            <div class="single_sidebar_widget author_widget" style="padding-top: 8px;">
                                <p>${memberVO.nickname}</p>
                            </div>
                        </div>
                        <div class="blog_right_sidebar"
                             style="width: auto;height: auto;padding: 5px 10px; text-align: center;">
                            <div class="single_sidebar_widget author_widget" style="padding-top: 8px;"
                                 style="float :right;">
                                <p>${addressVO.addr}</p>
                            </div>
                        </div>
                    </div>
                    <div class="blog_right_sidebar"
                         style="width: auto;padding-left: 30px;padding-bottom: 5px;padding-top: 5px; margin-top: 10px">
                        <ul class="d-flex">
                            <li style="padding-right: 15px">○상점오픈일 : ${openDays}일</li>
                            <li style="padding-right: 15px">○상점방문수 : ${shopVO.visits}명</li>
                            <li style="padding-right: 15px">○상품판매 : ${pgCount}회</li>
                        </ul>
                    </div>
                    <div class="blog_right_sidebar" style="width: auto; margin-top: 10px; height: 137px;">
                        <div class="d-flex single_sidebar_widget author_widget" style="height: max-content">
                            <p>${shopVO.intro}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="product_description_area" style="margin-top: 10px">
            <div class="container">
                <ul class="nav nav-tabs" id="myTab" role="tablist" style="background-color: white">
                    <li class="nav-item" style="float: left">
                        <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
                           aria-selected="true" style="border-radius: 0; margin-right: 8px">상품목록</a>
                    </li>
                    <style>
                        .product_description_area .nav.nav-tabs li a {
                            background: #218838;
                        }
                    </style>
                    <li class="nav-item" style="float: left;">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                           aria-controls="profile"
                           aria-selected="false" style="border-radius: 0">후기목록</a>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent" style="margin-top: 30px">
                    <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">
                            <c:if test="${empty list}">
                                <div class="col-md-12 col-lg-12 col-xl-12">
                                    <p class="h4 text-center" style="padding-top: 0px;">작성한 글이 없습니다!</p>
                                </div>
                            </c:if>
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

                                                    <li><a href="">
                                                        <button>상점 보러가기</button>
                                                    </a></li>
                                                </ul>
                                            </div>
                                            <div class="card-body row p-3" style="text-align: left;">
                                                <p class="col-6">${board.cateName}</p>
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
                            </c:forEach>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="table-responsive">
                            <div class="card-body">
                                <ul class="list-group chat">
                                    <div id="review" role="tabpanel" aria-labelledby="review-tab">
                                        <div class="card-body">
                                            <ul class="list-group chat">
                                                <c:forEach items="${replyVOList}" var="replyVO">
                                                    <c:forEach items="${replyBoardList}" var="replyBoard">
                                                        <c:forEach items="${replyBoard.attachs}" var="attach">
                                                            <%--                                                            <c:if test="${empty replyBoard.bno == replyVO.bno}">--%>
                                                            <%--                                                                <div class="col-md-12 col-lg-12 col-xl-12">--%>
                                                            <%--                                                                    <p class="h4 text-center" style="padding-top: 0px;">후기가 없습니다!</p>--%>
                                                            <%--                                                                </div>--%>
                                                            <%--                                                            </c:if>--%>
                                                            <c:if test="${replyBoard.bno == replyVO.bno}">
                                                                <li class="list-group-item" data-rno="\${obj.rno}">
                                                                    <div style="border-bottom: 1px solid #eee">
                                                                        <h6 class="mt-3 ml-3 mb-0"
                                                                            style="color: #4cd10f; ">작성후기</h6>
                                                                        <a href="${pageContext.request.contextPath}/board/${replyBoard.bno}"
                                                                           class="container">
                                                                            <div class="d-flex row  mt-0 mb-3">
                                                                                <div class="col-2">

                                                                                    <div class="ml-5 m-4"
                                                                                         style="box-shadow: 3px 3px 10px #ddd; width: 100px; height: 100px">

                                                                                        <c:if test="${attach.attNo == null}">
                                                                                            <img class="card-img"
                                                                                                 src="${pageContext.request.contextPath}/resources/img/no-image-small.png"
                                                                                                 alt="">
                                                                                        </c:if>
                                                                                        <c:if test="${attach.attNo != null}">
                                                                                            <img class="card-img"
                                                                                                 src="${pageContext.request.contextPath}/displaySmall${attach.url}"
                                                                                                 alt="">
                                                                                        </c:if>

                                                                                    </div>
                                                                                </div>
                                                                                <div class="mt-5 col-9">
                                                                                    <div class="d-flex">
                                                                                        <h6>[${replyVO.dong}]</h6>
                                                                                        <h6 class="ml-3">${replyBoard.title}</h6>
                                                                                    </div>
                                                                                    <div class="d-flex mt-3">q
                                                                                        <h4 style="font-size: 18px;">${replyVO.comment}</h4>
                                                                                        <h4 class="ml-auto"
                                                                                            style=" font-size: 18px;">${replyBoard.price}원</h4>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </a>
                                                                    </div>
                                                                </li>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
    <section style="background-color: #f2ffec;" id="parallax-1" data-anchor-target="#parallax-1"></section>
    <jsp:include page="includes/footer.jsp"/>
</body>
<script>
    $(function () {
        $("#myTab li:first a").click();
    })
</script>
</html>