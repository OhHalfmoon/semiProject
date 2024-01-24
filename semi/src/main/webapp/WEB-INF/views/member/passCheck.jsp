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
            <div class="card">
                <div class="card-body">
                    <div class="col-md-8 border d-block m-auto p-5 d-flex flex-column align-items-center justify-content-center">
                        <h2 class="text-lg-center">비밀번호 확인</h2>
                        <form class="col-lg-6 m-3 p-3" method="post">
                            <label for="passCheck"></label>
                            <input class="form-control my-3" type="password" name="passCheck" id="passCheck" placeholder="비밀번호를 입력해주세요">
                            <button class="btn btn-primary btn-block my-3">확인</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</body>
</html>