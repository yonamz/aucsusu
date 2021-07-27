var main = {
        init : function () {
                var _this = this;

                $('#update').on('click', function () {
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
                            if(_this.validate()!=false){
                                _this.update();
                            }
                });

                $('#deleteUser').on('click',function(){
                    _this.validate();
                    if(confirm("정말로 탈퇴할까요?") == false){
                        return;
                    }else{
                        _this.deleteUser();
                    }
                });
        },


        validate: function(){   //현재 로그인한 사용자의 비밀번호와 입력한 비밀번호가 일치하는지 확인
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
                var id = $('#uid').val();
                var data = {
                    name: $('#name').val(),
                    password: $('#password').val(),
                    confirmpw: $('#confirmpw').val(),
                    email: $('#email').val()
                };
                $.ajax({
                        type: 'POST',
                        url: '/user/update/'+id,
                        dataType: 'text',
                        contentType:'application/json; charset=utf-8',
                        data: JSON.stringify(data)
                      }).done(function() {
                           alert('업데이트 완료!');
                           window.location.href = '/logout';
                      }).fail(function (error) {
                           alert(JSON.stringify(error));
                      });


            },
            deleteUser : function(){
                    var id = $("#uid").val();

                    $.ajax({
                                        type: 'DELETE',
                                        url: '/user/delete/'+id,
                                        dataType: 'text',
                                        contentType:'application/json; charset=utf-8',
                                        data: JSON.stringify(id)
                                    }).done(function() {
                                        alert('탈퇴되었습니다.');
                                        window.location.href = '/logout';
                                    }).fail(function (error) {
                                        alert(JSON.stringify(error));
                                    });
            }

 };

main.init();