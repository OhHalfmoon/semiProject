<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pro_g
  Date: 2023-04-12
  Time: 오후 12:10
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- jQuery --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
            integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <%-- moment.js --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment-with-locales.min.js"
            integrity="sha512-42PE0rd+wZ2hNXftlM78BSehIGzezNeQuzihiBCvUEB3CVxHvsShF86wBWwQORNxNINlBPuq7rG4WWhNiTVHFg=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <jsp:include page="/WEB-INF/views/includes/head.jsp"/>
    <style>
        .flagged {
            font-weight: bold; /* 굵은 글씨체 */
            color: red; /* 글자색을 빨간색으로 변경 */
        }

        .sent {
            margin: 10px 0;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border-radius: 10px;
            float: right;
        }

        .received {
            margin: 10px 0;
            padding: 10px;
            background-color: #f1f0f0;
            color: black;
            border-radius: 10px;
            float: left;
        }

        .clearfloat {
            clear: both;
        }

        body {
            background-color: #f4f7f6;
            margin-top: 20px;
        }

        .card {
            background: #fff;
            transition: .5s;
            border: 0;
            margin-bottom: 30px;
            border-radius: .55rem;
            position: relative;
            width: 100%;
            box-shadow: 0 1px 2px 0 rgb(0 0 0 / 10%);
        }

        .chat-app .people-list {
            width: 280px;
            position: absolute;
            left: 0;
            top: 0;
            padding: 20px;
            z-index: 7
        }

        .chat-app .chat {
            margin-left: 280px;
            border-left: 1px solid #eaeaea
        }

        .people-list {
            -moz-transition: .5s;
            -o-transition: .5s;
            -webkit-transition: .5s;
            transition: .5s
        }

        .people-list .chat-list li {
            padding: 10px 15px;
            list-style: none;
            border-radius: 3px
        }

        .people-list .chat-list li:hover {
            background: #efefef;
            cursor: pointer
        }

        .people-list .chat-list li.active {
            background: #efefef
        }

        .people-list .chat-list li .name {
            font-size: 15px
        }

        .people-list .chat-list img {
            width: 45px;
            border-radius: 50%
        }

        .people-list img {
            float: left;
            border-radius: 50%
        }

        .people-list .about {
            float: left;
            padding-left: 8px
        }

        .people-list .status {
            color: #999;
            font-size: 13px
        }

        .chat .chat-header {
            padding: 15px 20px;
            border-bottom: 2px solid #f4f7f6
        }

        .chat .chat-header img {
            float: left;
            border-radius: 40px;
            width: 40px
        }

        .chat .chat-header .chat-about {
            float: left;
            padding-left: 10px
        }

        .chat .chat-history {
            padding: 20px;
            border-bottom: 2px solid #fff
        }

        .chat .chat-history ul {
            padding: 0
        }

        .chat .chat-history ul li {
            list-style: none;
            margin-bottom: 30px
        }

        .chat .chat-history ul li:last-child {
            margin-bottom: 0px
        }

        .chat .chat-history .message-data {
            margin-bottom: 15px
        }

        .chat .chat-history .message-data img {
            border-radius: 40px;
            width: 40px
        }

        .chat .chat-history .message-data-time {
            color: #434651;
            padding-left: 6px
        }

        .chat .chat-history .message {
            color: #444;
            padding: 18px 20px;
            line-height: 26px;
            font-size: 16px;
            border-radius: 7px;
            display: inline-block;
            position: relative
        }

        .chat .chat-history .message:after {
            bottom: 100%;
            left: 7%;
            border: solid transparent;
            content: " ";
            height: 0;
            width: 0;
            position: absolute;
            pointer-events: none;
            border-bottom-color: #fff;
            border-width: 10px;
            margin-left: -10px
        }

        .chat .chat-history .my-message {
            background: #efefef
        }

        .chat .chat-history .my-message:after {
            bottom: 100%;
            left: 30px;
            border: solid transparent;
            content: " ";
            height: 0;
            width: 0;
            position: absolute;
            pointer-events: none;
            border-bottom-color: #efefef;
            border-width: 10px;
            margin-left: -10px
        }

        .chat .chat-history .other-message {
            background: #e8f1f3;
            text-align: right
        }

        .chat .chat-history .other-message:after {
            border-bottom-color: #e8f1f3;
            left: 93%
        }

        .chat .chat-message {
            padding: 20px
        }

        .online,
        .offline,
        .me {
            margin-right: 2px;
            font-size: 8px;
            vertical-align: middle
        }

        .online {
            color: #86c541
        }

        .offline {
            color: #e47297
        }

        .me {
            color: #1d8ecd
        }

        .float-right {
            float: right
        }

        .clearfix:after {
            visibility: hidden;
            display: block;
            font-size: 0;
            content: " ";
            clear: both;
            height: 0
        }

        @media only screen and (max-width: 767px) {
            .chat-app .people-list {
                height: 465px;
                width: 100%;
                overflow-x: auto;
                background: #fff;
                left: -400px;
                display: none
            }

            .chat-app .people-list.open {
                left: 0
            }

            .chat-app .chat {
                margin: 0
            }

            .chat-app .chat .chat-header {
                border-radius: 0.55rem 0.55rem 0 0
            }

            .chat-app .chat-history {
                height: 300px;
                overflow-x: auto
            }
        }

        @media only screen and (min-width: 768px) and (max-width: 992px) {
            .chat-app .chat-list {
                height: 650px;
                overflow-x: auto
            }

            .chat-app .chat-history {
                height: 600px;
                overflow-x: auto
            }
        }

        @media only screen and (min-device-width: 768px) and (max-device-width: 1024px) and (orientation: landscape) and (-webkit-min-device-pixel-ratio: 1) {
            .chat-app .chat-list {
                height: 480px;
                overflow-x: auto
            }

            .chat-app .chat-history {
                height: calc(100vh - 350px);
                overflow-x: auto
            }
        }

        .chat-history {
            height: 600px;
            max-height: 600px;
            overflow-y: auto;
        }

        /* 스크롤바 스타일 지정 */
        .chat-history::-webkit-scrollbar {
            width: 6px;
        }

        .chat-history::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
        }

        .chat-history::-webkit-scrollbar-track {
            background-color: rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-lg-12">
            <div class="card chat-app">
                <div id="plist" class="people-list">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fa fa-search"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="검색..">
                    </div>
                    <ul class="list-unstyled chat-list mt-2 mb-0">
                        <c:forEach items="${chatRooms}" var="chatRoom">
                            <li class="clearfix chat-room-list">
                                <div class="about">
                                    <div class="chat-room" data-chat-room-no="${chatRoom.chatRoomNo}"
                                         data-chat-bno="${chatRoom.bno}">
                                            ${chatRoom.name}</div>
                                    <div class="status"><i
                                            class="${chatRoom.flag == 'F' ? 'fa fa-circle offline' : 'fa fa-circle online'}"></i>${chatRoom.flag == 'F' ? '아직안읽음' : '읽음'}
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="chat">
                    <div class="chat-header clearfix" style="background-color: #008a00">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="chat-about">
                                    <h6 class="m-b-0"></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--이전채팅목록-->
                    <div class="chat-history">
                        <%
                            String[] randomMessages = new String[]{
                                    "중고물품 거래는 새로운 제품을 만들기 위해 필요한 자원을 절약할 수 있습니다.<br><br>새로운 제품을 생산하는 과정에서는 많은 에너지와 원료가 사용되기 때문입니다.",
                                    "중고물품 거래를 통해 폐기물 발생량을 줄일 수 있습니다.<br><br>기존 제품을 사용하면서 새로운 제품을 살 필요가 없기 때문입니다.<br><br>새로운 제품을 구매하는 과정에서는 포장재 및 기타 부가적인 자원이 추가로 사용되기 때문에, 중고물품 거래를 통해 폐기물 발생량을 줄이는 것이 중요합니다.",
                                    "중고물품 거래를 통해 제품을 구입하는 경우,<br><br>새 제품을 구입하는 것보다 훨씬 저렴합니다.<br><br>중고 제품은 일반적으로 새 제품보다 가격이 낮기 때문입니다.",
                                    "중고물품 거래는 지역 경제를 활성화시킬 수 있습니다.<br><br>지역 주민들이 더 많이 중고 제품을 구매하면,<br><br>지역 내 중고 제품 상인들과 판매자들에게 수익이 들어가게 되므로 지역 경제가 활성화될 수 있습니다.",
                                    "중고물품 거래는 새로운 제품을 생산하는 과정에서 발생하는 온실가스 배출을 줄일 수 있습니다.<br><br>생산 과정에서 발생하는 온실가스는 대기 중 이산화탄소 농도를 증가시켜 지구 온난화를 가속화시킵니다.",
                            };
                        %>

                        <% int randomIndex = (int) (Math.random() * randomMessages.length); %>

                        <ul class="m-b-0" id="chatMessagesList">
                            <%-- 랜덤 인덱스 선택 --%>
                            <c:if test="${empty chatRoom}">
                                <li style="display:flex; flex-direction:column; align-items:center; justify-content:center; padding: 20% 10%">
                                    <h1>그 사실을 아시나요?</h1>
                                    <br>
                                    <br>
                                    <h4 style="display:flex; flex-direction:column; align-items:center; justify-content:center;"><%= randomMessages[randomIndex] %>
                                    </h4>
                                </li>
                            </c:if>
                            <%--채팅메시지가들어갈공간--%>
                        </ul>
                        <ul class="m-b-0" id="chatMessagesList2">
                            <%--웹소켓채팅메시지가들어갈공간--%>
                        </ul>
                    </div>
                    <!--채팅전송-->
                    <div class="chat-message clearfix">
                        <div class="input-group mb-0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-send"></i></span>
                            </div>
                            <input type="text" class="form-control" id="message" placeholder="채팅을 입력해주세요..">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    //변수 초기화
    var ws = new WebSocket("ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/chat");
    let chatRoomNo = null;
    let bno = null;
    let senderNo = ${loginMember.memberNo};
    let nickname = '${loginMember.nickname}';
    let receiverNo = 0;
    let obj = null;
    let msg = null;

    // 웹소켓 연결 시
    ws.onopen = function (ev) {
        console.log("연결 완료", ev);
    }
    // 웹소켓 연결 종료 시
    ws.onclose = function (ev) {
        console.log("연결 종료", ev);
    }

    // 메시지 입력 창에서 Enter시 작동
    $("#message").on("keydown", function (event) {
        if (event.keyCode == 13) {
            sendMessage();
            event.preventDefault();
            $("#message").val('');
        }
    })

    // 새로운 채팅 메시지가 추가될 때마다 스크롤바를 아래쪽으로 이동시킴
    $('.chat-history').on('DOMNodeInserted', function () {
        $(this).scrollTop($(this)[0].scrollHeight);
    });

    // 메시지 전송
    function sendMessage() {
        msg = $("#message").val();
        if (msg == "") {
            alert("메시지를 입력하세요")
        } else {
            if (chatRoomNo == null) {
                alert("채팅방을 선택해 주십시오")
            } else {
                obj = {
                    chatRoomNo: chatRoomNo
                    , nickname: nickname
                    , senderNo: senderNo
                    , content: msg
                    , receiverNo: receiverNo
                    , bno: bno
                    , sendtime: new Date().getTime()
                }
                // 웹소켓으로 메시지 송신
                ws.send(JSON.stringify(obj));
            }
        }
    }

    // 채팅창 클릭 시 작동
    $(document).on("click", ".chat-room-list", function () {
        let chatMessagesHtml2 = "";
        if (!$("#chatMessagesList").empty()) {
            $("#chatMessagesList").empty();
        }
        if (!$("#chatMessagesList2").empty()) {
            $("#chatMessagesList2").empty();
        }
        if (!$("#message").empty()) {
            $("#message").empty();
        }

        $(".chat-list").find(".chat-room-list.active").removeClass("active");
        $(this).closest("li").addClass("active");

        if (chatRoomNo != $(this).find(".chat-room").data("chat-room-no")) {
            chatRoomNo = $(this).find(".chat-room").data("chat-room-no"); //채팅방 번호
        }
        if (bno != $(this).find(".chat-room").data("chat-bno")) {
            bno = $(this).find(".chat-room").data("chat-bno");
        }

        if($(this).find('.status').text('아직안읽음')){
            $(this).find('.status i').removeClass('offline');
            $(this).find('.status i').addClass('online');
            $(this).find('.status').html('<i class="fa fa-circle online"></i>읽음');
        }
        // }
        // 이전 메시지 목록 조회
        $.ajax({
            url: "chat/messages",
            type: "GET",
            data: {
                senderNo: senderNo,
                chatRoomNo: chatRoomNo
            },
            success: function (data) {
                let chatMessagesHtml = "";
                for (var i = data.length - 1; i >= 0; i--) {
                    let sendTime = moment(data[i].sendTime);
                    let diff = moment.duration(moment().diff(sendTime));
                    let timeDiffString = "";

                    if (diff.asDays() > 1) {
                        timeDiffString = Math.floor(diff.asDays()) + "일 전";
                    } else if (diff.asDays() == 1) {
                        timeDiffString = "어제";
                    } else if (diff.asHours() >= 1) {
                        timeDiffString = Math.floor(diff.asHours()) + "시간 전";
                    } else if (diff.asMinutes() >= 1) {
                        timeDiffString = Math.floor(diff.asMinutes()) + "분 전";
                    } else {
                        timeDiffString = Math.floor(diff.asSeconds()) + "초 전";
                    }
                    if (data[i].flag == "R") {
                        chatMessagesHtml += "<li class='clearfix'><div class='message-data text-right'>" + "<span class='message-data-time'>" + timeDiffString + "</span></div><div class='message other-message float-right'>" + data[i].content + "</div></li>"
                    } else {
                        chatMessagesHtml += "<li class='clearfix'><div class='message-data'><span class='message-data-time'>" + timeDiffString + "</span></div><div class='message my-message'>" + data[i].content + "</div></li>"
                    }
                    if (senderNo != data[i].receiverNo) {
                        receiverNo = data[i].receiverNo;
                    }
                }
                $("#chatMessagesList").html(chatMessagesHtml);
            },
            error: function () {
                alert("채팅 메시지 목록을 가져오는데 실패하였습니다.");
            }
        });

        ws.onmessage = function (ev) {
            let message = ev.data;
            let messageParts = message.split("방금");
            let result = messageParts[messageParts.length - 1];
            let numbers = result.split(",").map(Number);

            let chatRoomNo2 = numbers[0];
            let senderNo2 = numbers[1];

            let trimmedMessage = message.substring(0, message.lastIndexOf("방") - 1);
            console.log(result)
            console.log(chatRoomNo2)
            console.log(senderNo2)
            if (chatRoomNo2 == chatRoomNo) {
                if(senderNo2 == senderNo) {
                    chatMessagesHtml2 += "<li class='clearfix'><div class='message-data text-right'>" + "<span class='message-data-time'>" + "방금" + "</span></div><div class='message other-message float-right'>" + trimmedMessage + "</div></li>"
                }else{
                    chatMessagesHtml2 += "<li class='clearfix'><div class='message-data'><span class='message-data-time'>" + "방금" + "</span></div><div class='message my-message'>" + trimmedMessage + "</div></li>"
                }
                $("#chatMessagesList2").html(chatMessagesHtml2);
            }
        }
    });
    // <ul class="list-unstyled chat-list mt-2 mb-0">

    $(function(){
        if ("${start}" !== null) {
            $(".chat-list .chat-room-list:last").click();
        }
    })
</script>
</body>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</html>
