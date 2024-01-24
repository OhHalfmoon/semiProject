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
            <div class="card">
                <div class="card-body">
                    <div class="col-md-8 border d-block m-auto p-5 d-flex flex-column align-items-center justify-content-center">
                        <h2 class="text-lg-center">회원탈퇴</h2>
                        <form class="col-lg-8 m-3 p-3" method="post" id="withdrawalForm">
                            <label for="reason"></label>
                            <input class="form-control my-3" type="text" name="reason" id="reason" placeholder="탈퇴사유를 입력해주세요">
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
    $(function () {
        $("#withdrawalForm").submit(function () {
            event.preventDefault();

            if(confirm("정말 탈퇴하시겠습니까?")){
                this.submit();
            } else {
                return false;
            }
        })

        $("#backbtn").click(function () {
            history.back();
        })
    })
</script>
</body>
</html>