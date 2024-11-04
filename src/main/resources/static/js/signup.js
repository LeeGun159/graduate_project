$(function () {
    // 로그인 입력 양식 모두 채웠는지 여부 확인
    $("#signupForm").submit(function() {
        let regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if($("#loginId").val() == "") {
            alert("아이디가 입력되지 않았습니다.");
            $("#loginId").focus();
            return false;
        }
        if($("#password").val() == "") {
            alert("비밀번호가 입력되지 않았습니다.");
            $("#password").focus();
            return false;
        }
        if($("#name").val() == "") {
            alert("이름이 입력되지 않았습니다.");
            $("#name").focus();
            return false;
        }
        if($("#eMail").val() == "") {
            alert("이메일이 입력되지 않았습니다.");
            $("#eMail").focus();
            return false;
        }
        if(regEmail.test($("#eMail").val()) == false) {
            alert("이메일 주소 형식을 확인해주세요.");
            $("#eMail").focus();
            return false;
        }
        if($("#phoneNumber").val() == "") {
            alert("전화번호가 입력되지 않았습니다.");
            $("#phoneNumber").focus();
            return false;
        }

        return true;
    });
});