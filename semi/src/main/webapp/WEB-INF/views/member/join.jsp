<%--
  User: banghansol
  Date: 2023/04/07
  Time: 3:35 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <h1>회원가입</h1>
                    <nav aria-label="breadcrumb" class="banner-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">회원가입</li>
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
                    <div class="login_box_img h-100">
                        <div class="hover">
                            <h4>이미 계정을 가지고 있으신가요?</h4>
                            <p>로그인하셔서 근처의 여러 물품들을 확인해보세요!</p>
                            <a class="button button-account" href="login">로그인하기</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="login_form_inner register_form_inner">
                        <h3>회원가입</h3>
                        <form class="row login_form" method="post" id="joinForm" >
                            <div class="col-md-8 form-group">
                                <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디를 입력해주세요'">
                            </div>
                            <div class="col-md-4 form-group">
                                <button type="button" id="idCheck" class="button button--active px-3 py-2">중복확인</button>
                            </div>
                            <div class="col-md-8 form-group">
                                <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임을 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '닉네임을 입력해주세요'">
                            </div>
                            <div class="col-md-4 form-group">
                                <button type="button" id="nickCheck" class="button button--active px-3 py-2">중복확인</button>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호를 입력해주세요'">
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 다시 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호를 다시 입력해주세요'">
                            </div>
                            <div class="col-md-8 form-group">
                                <input type="email" class="form-control" id="email" name="email" placeholder="이메일 주소를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '이메일 주소를 입력해주세요'">
                            </div>
                            <div class="col-md-4 form-group">
                                <button type="button" id="emailCheck" class="button button--active px-2 py-2">이메일인증</button>
                            </div>
                            <div class="col-md-8 form-group">
                                <input type="text" class="form-control text-truncate" id="addr" name="addr" placeholder="주소 검색을 해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '주소 검색을 해주세요'"  readonly>
                            </div>
                            <div class="col-md-4 form-group">
                                <button type="button" class="button button--active px-3 py-2" id="addressSearch">주소검색</button>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="addrDetail" name="addrDetail" placeholder="상세주소를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '상세주소를 입력해주세요'">
                            </div>
                            <%--
                            <div class="col-md-12 form-group">
                                <div class="creat_account">
                                    <input type="checkbox" id="f-option2" name="selector">
                                    <label for="f-option2">Keep me logged in</label>
                                </div>
                            </div>
                            --%>
                            <input type="hidden" name="x">
                            <input type="hidden" name="y">
                            <input type="hidden" name="dong">
                            <div class="col-md-12 form-group">
                                <button type="submit" value="submit" class="button button--active btn-block px-3 py-2">회원가입</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Login Box Area =================-->



    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        var cp = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/resources/js/join.js"></script>
</body>
</html>