var main = {
    init : function () {
        var _this = this;
        $('#sub').on('click', function () {
                        if(cPwd.value==""){
                				alert("현재 비밀번호를 입력해주세요.");
                				cPwd.focus();
                				return false;
                			}
                			if(password.value==""){
                				alert("바꿀 비밀번호를 입력해주세요.");
                				password.focus();
                				return false;
                			}
                			if(confirmpw.value==""){
                				alert("비밀번호 확인을 입력해주세요.");
                				confirmpw.focus();
                				return false;
                			}

                			if(password.value.length<4){
                				alert("비밀번호가 너무 짧습니다.");
                				password.value="";
                				password.focus();
                				return false;
                			}

                			if(password.value!=confirmpw.value){
                				alert("비밀번호와 비밀번호 확인이 다릅니다.");
                				confirmpw.value="";
                				confirmpw.focus();
                				return false;
                				}
            _this.validate();
            _this.update();
        });
        $('#delete').on('click', function () {
            _this.validate();
             if(confirm("탈퇴 하시겠습니까?") == false){
                  return;
             }
            _this.delete();
        });

    },


        validate: function(){
            var cPwd = $('#cPwd').val();

            $.ajax({
                type: "POST",
                url: "/user/validation/"+cPwd,
                data:{cPwd},
                async:false
            }).done(function(result){
                if(result==false){
                    alert("현재 비밀번호를 다시 입력해주세요");
                    cPwd.val("");
                    cPwd.focus();
                }
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },


    update : function () {
        var uid = $("#uid").val();

        var data = {
            name: $("#name").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
                 type: 'POST',
                 url: '/user/update/'+uid,
                 contentType:'application/json; charset=utf-8',
                 data: JSON.stringify(data),
                 async:false
        }).done(function() {
            alert('정보가 수정되었습니다.');
            window.location.href = '/logout';

        }).fail(function (error) {
            alert("에러 발생");
        });
    },

    delete : function(){
        var uid = $("#uid").val();

        $.ajax({
                            type: 'DELETE',
                            url: '/user/delete/'+uid,
                            dataType: 'json',
                            contentType:'application/json; charset=utf-8'
                        }).done(function() {
                            alert('탈퇴되었습니다.');
                            window.location.href = '/logout';
                        }).fail(function (error) {
                            alert(JSON.stringify(error));
                        });
    }

};

main.init();