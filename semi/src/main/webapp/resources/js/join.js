
$("#addressSearch").click(function(){ //주소입력칸을 클릭하면
    //카카오 지도 발생
    new daum.Postcode({
        oncomplete: function(data) { //선택시 입력값 세팅
            document.getElementById("addr").value = data.address; // 주소 넣기
            document.querySelector("input[name=addrDetail]").focus(); //상세입력 포커싱
            console.log(data.address);
            $.ajax({
                url : 'https://dapi.kakao.com/v2/local/search/address.json?query=' + data.address,
                headers: { 'Authorization': 'KakaoAK 1cbbb5e35159be304d08cd94adc6fa18'},
                type: 'GET'
            }).done(function(data) {
                console.log(data);
                console.log(data.documents[0].road_address.x);
                console.log(data.documents[0].road_address.y);
                console.log(data.documents[0].road_address.region_3depth_name);

                $('input[name=x]').attr('value',data.documents[0].road_address.x);
                $('input[name=y]').attr('value',data.documents[0].road_address.y);
                $('input[name=dong]').attr('value',data.documents[0].road_address.region_3depth_name);


                // document.getElementById("x").value = data.documents[0].road_address.x;
                // document.getElementById("y").value = data.documents[0].road_address.y;
            })
        }
    }).open();
});



$(function(){
    var memberId = 'test';

    $("#memberId").change(function () {
        console.log('memberId Change', getMemberId());
    })

    function getMemberId() {
        memberId = $('#memberId').val();

        console.log('obj', memberId);

        return JSON.stringify({
            memberId: $("#memberId").val().trim()
        });
    }


    $("#idCheck").click(function () {
        console.log($("#memberId").val());
        $("#memberId").valid();
    });

    $("#nickCheck").click(function () {
        console.log($("#nickname").val());
        result = $("#nickname").valid();
    });

    $.validator.addMethod("idAjaxCheck", function (value, element) {

        var data = {
            memberId: $('#memberId').val()
        }

        console.log(data);
        var jsonStr = JSON.stringify(data);

        var isValid = true;

        $.ajax({
            url:'idCheck',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            data: jsonStr,
            dataType: 'json',
            async : false,
            success: function (chk) {
                console.log(chk);
                isValid = chk;
            },
            error: function (textStatus, errorThrown) {
                console.log(textStatus + ": " + errorThrown);

                isValid = false;
            }
        })

        return isValid;
    }, "이미 존재하는 아이디입니다!")

    $.validator.addMethod("nickAjaxCheck", function (value, element) {

        var data = {
            nickname: $('#nickname').val()
        }

        var jsonStr = JSON.stringify(data);

        var isValid = true;

        $.ajax({
            url:'nickCheck',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            data: jsonStr,
            dataType: 'json',
            async : false,
            success: function (chk) {
                console.log(chk);
                isValid = chk;
            },
            error: function (textStatus, errorThrown) {
                console.log(textStatus + ": " + errorThrown);

                isValid = false;
            }
        })

        return isValid;
    }, "이미 존재하는 닉네임입니다!");

    $("#emailCheck").click(function () {
        $("#email").valid();
    })

    $.validator.addMethod("emailSend", function (value, element) {
        var email = $('#email').val()

        var data = {
            "email": email
        }

        var jsonStr = JSON.stringify(data);

        var isValid = true;

        console.log('jsonStr', jsonStr);

        if($("#email").attr("readonly")){
            return true;
        }

        $.ajax({
            url:'emailSend',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            data: jsonStr,
            dataType: 'json',
            success: function (chk) {
                console.log(chk);

                console.log($("#email").val());

                var newDiv =
                    `
                            <div class="col-md-8 form-group">
                                <input type="text" class="form-control" id="emailAuth" name="emailAuth" placeholder="이메일 인증번호를 입력해주세요" onfocus="this.placeholder = ''" onblur="this.placeholder = '이메일 인증번호 입력해주세요'">
                            </div>
                            <div class="col-md-4 form-group">
                                        <button type="button" id="emailAuthCheck" class="button button--active p-2">인증코드확인</button> 
                            </div>
                            `;

                if(!$("#emailAuth").length){
                    $("#emailCheck").parent().after(newDiv);
                    $("#emailCheck").prop("disabled", true);
                    $("#email").prop("readonly", true);


                    $("#emailAuthCheck").on("click", function () {

                        $('#emailAuth').valid();


                    })

                    isValid = chk;
                }
            },
            error: function (textStatus, errorThrown) {
                console.log(textStatus + ": " + errorThrown);

                isValid = false;
            }
        })

        return isValid;
    }, "이메일 인증를 해주세요");

    $("#emailAuthCheck").click(function () {
        var result = $.validator.methods.emailAuthCheck.call($.validator, $("#emailAuthCheck").val(), null, $("#emailAuthCheck").parent());

        $("#joinForm").validate();

        console.log(result);
    })


    $.validator.addMethod("emailAuthCheck", function (value, element) {
        var email = $('#email').val()
        var code = $('#emailAuth').val();

        var data = {
            email: email,
            code : code
        }

        var jsonStr = JSON.stringify(data);

        var isValid = false;

        console.log('jsonStr', jsonStr);

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

                if(isValid){
                    $(element).removeClass("error").addClass("valid");
                    $("#emailAuthCheck").attr("disabled", true);
                }
            },
            error: function (textStatus, errorThrown) {
                console.log(textStatus + ": " + errorThrown);

                isValid = false;
            }
        })

        console.log("emailAuthCheck isValid", isValid)

        return isValid;
    }, "이메일 인증번호 확인을 해주세요");

    $('#joinForm').validate({
        rules: {
            memberId: {
                required: true,
                minlength: 4,
                maxlength: 12,
                idAjaxCheck: true
            },
            nickname: {
                required: true,
                minlength: 4,
                maxlength: 12,
                nickAjaxCheck: true
            },
            password: {
                required: true,
                minlength: 4
            },
            confirmPassword: {
                required: true,
                minlength: 4,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true,
                emailSend: true
            },
            emailAuth: {
                required: true,
                emailAuthCheck: true
            },
            addr: {
                required: true
            }
        },
        messages: {
            memberId: {
                required: "회원 ID는 필수 항목입니다!",
                minlength: "최소 4글자 이상 입력해주세요",
                maxlength: "12자 이내로 입력해주세요",
                idAjaxCheck: "이미 존재하는 아이디입니다!",
            },
            nickname: {
                required: "닉네임은 필수 항목입니다!",
                minlength: "최소 4글자 이상 입력해주세요",
                maxlength: "12자 이내로 입력해주세요"
            },
            password: {
                required: "비밀번호는 필수 항목입니다!",
                minlength: "최소 4글자 이상 입력해주세요"
            },
            confirmPassword: {
                required: "비밀번호는 필수 항목입니다!",
                minlength: "최소 4글자 이상 입력해주세요",
                equalTo: "비밀번호와 일치하지 않습니다!"
            },
            email: {
                required: "이메일은 필수 항목입니다!",
                email: "이메일 양식이 아닙니다!"
            },
            emailAuth: {
                required: "이메일 인증을 해주세요!",
                emailAuthCheck: "이메일 인증번호를 확인해주세요!"
            },
            addr: {
                required: "주소는 필수항목입니다!"
            }
        },
        highlight: function(element) {
            $(element).removeClass('is-valid').addClass('is-invalid'); // 에러가 있는 필드에 'error' 클래스 추가
        },
        unhighlight: function(element) {
            $(element).removeClass('is-invalid').addClass('is-valid'); // 에러가 없는 필드에서 'error' 클래스 제거
        },
        onfocusout: false,
        onkeyup :false,
        errorClass: "invalid-feedback",
        submitHandler: function(form) {
            //event.preventDefault();

            $(form).ajaxSubmit({
                // type:"POST",
                // data: JSON.stringify(userJson),
                // contentType: 'application/json; charset=utf-8',

                dataType: 'json',
                url:"join",
                success: function(data) {
                    console.log(data);
                    if(data == 1){
                        alert("회원가입에 성공하였습니다!");
                        $(location).attr('href', cp + '/');
                    } else {

                    }
                },
                error: function() {
                    alert("회원가입에 문제가 발생하였습니다!");
                }
            })
        }
    })
})