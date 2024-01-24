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
            <div class="card border-0">
                <div class="card-body">
                    <div class="col-md-8 border d-block m-auto p-5 d-flex flex-column align-items-center justify-content-center">
                        <h2 class="text-lg-center">회원정보 수정</h2>
                        <form class="col-lg-8" method="post" id="memModifyForm">
                            <label for="memberId">아이디</label>
                            <input class="form-control my-3" type="text" name="memberId" id="memberId" value="${memberInfo.memberId}" readonly>
                            <label for="nickname">닉네임</label>
                            <input class="form-control my-3" type="text" name="nickname" id="nickname" value="${memberInfo.nickname}" placeholder="닉네임을 입력해주세요">
                            <c:if test="${errors.nickname ne null}">
                                <div class="err-msg">
                                    <p>${errors.nickname}</p>
                                </div>
                            </c:if>
                            <label for="newEmail">이메일</label>
                            <div>현재 이메일 : ${memberInfo.email}</div>

                            <div class="col-md-12 form-group flex-row px-0 d-flex justify-content-between">
                                <input class="form-control my-3 col-md-8 d-inline" type="text" name="newEmail" id="newEmail" placeholder="이메일을 입력해주세요" value="${memberInfo.newEmail}"
                                       <c:if test="${memberInfo.code ne null}">readonly</c:if>
                                >
                                <button class="button button--active p-0 form-control align-middle my-auto" id="emailCheck" type="button" <c:if test="${memberInfo.code ne null}">disabled</c:if>>이메일 인증</button>
                            </div>
                            <c:if test="${errors.email ne null}">
                                <div class="err-msg">
                                    <p>${errors.email}</p>
                                </div>
                            </c:if>
                            <c:if test="${errors.code ne null}">
                                <label for="emailAuth">인증코드</label>
                                <div class="col-md-12 form-group flex-row px-0 d-flex justify-content-between">
                                    <input class="form-control my-3 col-md-8 d-inline" type="text" name="code" id="emailAuth" placeholder="인증코드를 입력해주세요">
                                    <button class="button button--active p-0 form-control align-middle my-auto" id="emailAuthCheck" type="button">인증하기</button>
                                </div>
                            </c:if>

                            <c:if test="${errors.code ne null}">
                                <div class="err-msg">
                                    <p>${errors.code}</p>
                                </div>
                            </c:if>
                            <%--
                            <label for="phone">핸드폰번호</label>
                            <input class="form-control my-3" type="text" name="phone" id="phone" placeholder="핸드폰 번호를 입력해주세요">
                            --%>
                            <input type="hidden" id="memberNo" name="memberNo" value="${memberInfo.memberNo}">
                            <input type="hidden" id="email" name="email" value="${memberInfo.email}">
                            <button class="button button--active btn-block my-1 p-2">확인</button>
                            <a class="button button--active text-center btn-block my-1 p-2" href="${pageContext.request.contextPath}/member/passChange">비밀번호 변경</a>
                            <a class="button button-header text-center btn-block my-1 p-2" href="${pageContext.request.contextPath}/member/withdrawal">회원탈퇴</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
    <script>
        $(function () {
            $("#emailCheck").click(function () {
                console.log("test")
                var newEmail = $("#newEmail").val();
               if(newEmail.length <4){
                   return false;
               }

                var email = $('#email').val()

                var data = {
                    "email": newEmail
                }


                var jsonStr = JSON.stringify(data);

                $.ajax({
                    url: 'emailSend',
                    type: 'post',
                    contentType: 'application/json; charset=utf-8',
                    data: jsonStr,
                    dataType: 'json',
                    success : function (data) {
                        console.log("email ok", data)

                        let str = "";
                        str += `<label for="emailAuth">인증코드</label>
                            <div class="col-md-12 form-group flex-row px-0 d-flex justify-content-between">
                                <input class="form-control my-3 col-md-8 d-inline" type="text" name="code" id="emailAuth" placeholder="인증코드를 입력해주세요">
                                <button class="button button--active p-0 form-control align-middle my-auto" id="emailAuthCheck" type="button">인증하기</button>
                            </div>
                            <div id="emailAuthResult"></div>
                        `;
                        $("#newEmail").parent().after(str);
                        $("#newEmail").attr("readonly", true)
                        $("#emailCheck").attr("disabled", true);

                        $(document).on("click", "#emailAuthCheck", function (){
                            emailAuthCheck()
                        })
                    }, error: function (){

                    }
                });


            });

            function emailAuthCheck(){

                var newEmail = $('#newEmail').val()
                var code = $('#emailAuth').val();

                var data = {
                    email: newEmail,
                    code : code
                }

                var jsonStr = JSON.stringify(data);

                $.ajax({
                    url:'emailAuthCheck',
                    type: 'post',
                    contentType: 'application/json; charset=utf-8',
                    data: jsonStr,
                    dataType: 'json',
                    async: false,
                    success: function (chk) {
                        console.log(chk);
                        isValid = chk;


                        var str = "";
                        if(chk === true){
                            console.log("true", chk, str);
                            str = '<p class="text-success">이메일 인증이 완료되었습니다!</p>';
                            $("#emailAuthCheck").attr("disabled", true);
                            $("#emailAuth").attr("readonly", true)
                        }else {
                            console.log("false", chk, str);
                            str = '<p class="err-msg">이메일 인증을 실패하였습니다!</p>';
                        }
                        $("#emailAuthResult").html(str);
                    },
                    error: function (textStatus, errorThrown) {
                        console.log(textStatus + ": " + errorThrown);
                        var str = "";
                        str += '<p  class="err-msg">이메일 인증을 실패하였습니다!</p>';
                        $("#emailAuthResult").html(str);
                    }
                })
            }
        })
    </script>
</body>
</html>