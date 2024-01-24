<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: banghansol
  Date: 2023/04/17
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="/WEB-INF/views/includes/head.jsp"/>
</head>
<body>
    <jsp:include page="/WEB-INF/views/includes/header.jsp"/>
    <section class="section-margin">
        <div class="container">
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
                <div class="card-body">
                    <div class="col-md-8 border d-block m-auto p-5 d-flex flex-column align-items-center justify-content-center">
                        <h2 class="text-lg-center">비밀번호 변경</h2>
                        <form class="col-lg-8 m-3 p-3" method="post" id="passChangeForm">
                            <input class="form-control my-3" type="password" name="password" id="password" placeholder="이전 비밀번호를 입력해주세요">
                            <c:if test="${errors.password ne null}">
                                <div class="err-msg">
                                    <p>${errors.password}</p>
                                </div>
                            </c:if>
                            <input class="form-control my-3" type="password" name="newPassword" id="newPassword" placeholder="새 비밀번호을 입력해주세요">
                            <c:if test="${errors.newPassword ne null}">
                                <div class="err-msg">
                                    <p>${errors.newPassword}</p>
                                </div>
                            </c:if>
                            <input class="form-control my-3" type="password" name="newPassConfirm" id="newPassConfirm" placeholder="새 비밀번호를 다시 입력해주세요">
                            <c:if test="${errors.newPasswordConfirm ne null}">
                                <div class="err-msg">
                                    <p>${errors.newPasswordConfirm}</p>
                                </div>
                            </c:if>
                            <input type="hidden" id="memberNo" name="memberNo" value="${memberNo}">
                            <button class="button button--active btn-block my-1 p-2">확인</button>
                            <button class="button button-header btn-block my-1 p-2" id="backbtn" type="button">취소</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
    <script>
        $("#backbtn").click(function () {
            history.back();
        })
    </script>
</body>
</html>