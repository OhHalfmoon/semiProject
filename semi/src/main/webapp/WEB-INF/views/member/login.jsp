<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <jsp:include page="/WEB-INF/views/includes/head.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>


<!-- ================ start banner area ================= -->
<section class="blog-banner-area" id="category">
  <div class="container h-100">
    <div class="blog-banner">
      <div class="text-center">
        <%-- <h1>Login</h1> --%>
        <h1>환영합니다! 오늘도 우리동네 친구들과 <span class="text-success">상추!</span></h1>
        <nav aria-label="breadcrumb" class="banner-breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">로그인</li>
          </ol>
        </nav>
      </div>
    </div>
  </div>
</section>
<!-- ================ end banner area ================= -->

<!--================Login Box Area =================-->
<section class="login_box_area section-margin">
  <div class="container">
    <div class="row">
      <div class="col-lg-6">
        <div class="login_box_img">
          <div class="hover">
            <h4>아직 계정이 없으신가요?</h4>
            <p>회원가입 하셔서 주변의 다양한 물품을 살펴보세요!</p>
            <div>
              <a class="button button--active" href="terms">회원가입으로 이동</a>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="login_form_inner">
          <h3>로그인</h3>
          <form class="row login_form" method="post" action="login" id="loginForm" >
            <div class="col-md-12 form-group">
              <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디를 입력해주세요'" value="${cookie.memberId.value}">
            </div>
            <div class="col-md-12 form-group">
              <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'">
            </div>
            <div class="col-md-12 form-group">
              <div class="creat_account">
                <input type="checkbox" id="rememberId" name="rememberId" <c:if test="${cookie.memberId ne null}">checked</c:if>>
                <input type="hidden" name="_rememberId" value="on">
                <label for="rememberId">아이디 기억하기</label>
              </div>
            </div>
            <c:if test="${errorMsg ne null}">
              <div class="col-md-12 form-group">
                <p class="text-danger">${errorMsg}</p>
              </div>
            </c:if>
            <div class="col-md-12 form-group">
              <button type="submit" value="submit" class="button button-login w-100">로그인</button>
              <a class="button button--active p-2" href="">패스워드 찾기</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
<!--================End Login Box Area =================-->


<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</body>
</html>