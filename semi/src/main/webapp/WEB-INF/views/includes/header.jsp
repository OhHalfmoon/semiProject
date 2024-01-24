<%--
  User: banghansol
  Date: 2023/04/05
  Time: 3:07 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!--================ Start Header Menu Area =================-->
  <header class="header_area">

    <div class="top-menu " style="min-height: 30px; border-bottom: 1px solid #eee;">
      <div class="container">
         <c:if test="${loginMember.admin}">
            <a href="${pageContext.request.contextPath}/cheat/cheatList" class="small mr-2">신고접수 목록</a>
            <a href="${pageContext.request.contextPath}/cheat/penaltyList" class="small mr-2">신고처리 목록</a>
          </c:if>
        <div class="float-right">
          <c:if test="${loginMember eq null}">
            <a href="${pageContext.request.contextPath}/member/login" class="small mr-2">로그인</a>
            <a href="${pageContext.request.contextPath}/member/terms" class="small mr-3">회원가입</a>
          </c:if>
       
          <c:if test="${loginMember ne null}">
           <p class="d-inline mx-3 small">${loginMember.nickname}님 환영합니다.</p>
            <a href="${pageContext.request.contextPath}/member/mypage" class="small mr-2">마이페이지</a>
            <a href="${pageContext.request.contextPath}/member/logout" class="small mr-3">로그아웃</a>
          </c:if>

        </div>
      </div>
    </div>

    <div class="main_menu">
      <nav class="navbar navbar-expand-lg navbar-light header-bar flex-column" id="header-bar">
        <div class="container pt-4">
          <a class="navbar-brand logo_h" href="${pageContext.request.contextPath}/"><img class="pb-2" src="${pageContext.request.contextPath}/resources/img/salad_small.png" alt=""><img
              src="${pageContext.request.contextPath}/resources/img/logo_text.png" alt=""></a>

          <div class="navbar navbar-expand-lg" id="navbarSupportedContent">
            <form class="form-inline " method="get" action="${pageContext.request.contextPath}/board/search">
              <div class="nav-item m-3 mr-5 search-header">
                <input class="form-control" type="text" style="width: 480px; height: 30px;" placeholder="상상하던 추천물품 검색!" name="keyword" value="${page.cri.keyword}">
                <button style="border-style: none; background-color: unset; width: 40px; height: 30px;" type="submit"><i
                    style="font-size: 20px; color: #4cd10f;" class="icofont-search-1"></i></button>

              </div>
            </form>

            <ul class="nav-shop nav-btn ">
              <li class="nav-item m-0"><a class="button button-header p-2 " href="${pageContext.request.contextPath}/board/write"><i
                    class="icofont-pencil-alt-2 mr-1" style="color: #4cd10f;"></i>판매하기</a></li>
              <li class="nav-item  m-0"><a class="button button-header p-2" href="${pageContext.request.contextPath}/shop/${loginMember.memberNo}"><i class="icofont-ui-home mr-1"
                    style="color: #4cd10f;"></i>내상점</a></li>
              <li class="nav-item  ml-0"><a class="button button-header p-2" href="${pageContext.request.contextPath}/room/chat"><i style="color: #4cd10f;"
                    class="icofont-ui-message mr-1"></i>상추쪽지</a></li>
            </ul>
          </div>
        </div>
        <div class="container pl-2" style="margin-top: -20px;">

          <ul class="btn btn-category">
            <i class="float-left icofont-navigation-menu pt-1" style=" font-size: 18px;"></i>
            <p class="float-leftml-2 header-ment ml-4" style="padding-top: 2px; ">상상하던 물품을 추천합니다!</p>
          </ul>
        </div>
      </nav>
       <div class="container">
        <div class="">
          <div class="header_area category-box">
          <!-- 카테고리 정리 필요함(지금 너무 많음) -->
            <ul class="ml-2">
       <li><a href="${pageContext.request.contextPath}/board/listaddr"><i class=" icofont-ui-rating"></i> 우리동네마켓</a></li>
              
              <li><a href="${pageContext.request.contextPath}/board/category/1"><i class=" icofont-ui-rating"></i> 디지털 기기</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/2"><i class="icofont-ui-rating"></i> 생활가전</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/3"><i class=" icofont-ui-rating"></i> 가구/인테리어</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/4"><i class="icofont-ui-rating"></i> 생활/주방</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/5"><i class=" icofont-ui-rating"></i> 유아동</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/6"><i class=" icofont-ui-rating"></i> 여성의류/잡화</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/7"><i class=" icofont-ui-rating"></i> 남성패션/잡화</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/8"><i class="icofont-ui-rating"></i> 뷰티/미용</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/9"><i class=" icofont-ui-rating"></i> 스포츠/레저</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/10"><i class="icofont-ui-rating"></i> 취미/게임/음반</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/11"><i class="icofont-ui-rating"></i> 반려동물용품</a></li>
              <li><a href="${pageContext.request.contextPath}/board/category/12"><i class="icofont-ui-rating"></i> 삽니다</a></li>
		       <li><a href="${pageContext.request.contextPath}/board/list"><i class=" icofont-ui-rating"></i> 전체상품보기</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </header>
  <!--================ End Header Menu Area =================-->