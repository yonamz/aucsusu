var main = {
        init : function () {
                var _this = this;

                $('#btnSignup').on('click', function () {
                    if(_this.save1()!=false){
                        _this.saveChk();
                        _this.save2();
                    }


                });

                $('#btnCancel').on('click', function () {
                     _this.cancel();
                });

                $('#chkID').on('click', function (){
                    _this.idChk();
                });
        },
        check: function(re, what, message){
               if(re.test(what.value)){
                    return true;
               }
               alert(message);
               what.value="";
               what.focus();
        },
        save1 : function(){
                                            var re = /^[a-zA-Z0-9]{4,12}$/;
                                            var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

                                            var data = {
                                                uid: $('#uid').val(),
                                                name: $('#name').val(),
                                                password: $('#password').val(),
                                                confirmpw: $('#confirmpw').val(),
                                                phoneNumber: $('#phoneNumber').val(),
                                                email: $('#email').val()
                                            };

                                            if(!this.check(re,uid,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")){
                                                                  return false;
                                            }

                                                        if(password.value==""||password.value==" "){
                                                                  alert("비밀번호를 입력해주세요");
                                                                  password.value="";
                                                                  password.focus();
                                                                  return false;
                                                        }
                                                        if(password.value.length<4){
                                                            alert("비밀번호가 너무 짧습니다.");
                                                            password.value="";
                                                            password.focus();
                                                            return false;
                                                        }

                                                        if(name.value==""||name.value==" "){
                                                            alert("이름을 입력해주세요");
                                                            name.value="";
                                                            name.focus();
                                                            return false;
                                                        }

                                                        if(email.value==""||email.value==" "){
                                                              alert("이메일을 입력해주세요");
                                                              email.value="";
                                                              email.focus();
                                                              return false;
                                                        }

                                                        if(password.value!=confirmpw.value){
                                                            alert("비밀번호가 다릅니다.");
                                                            confirmpw.value="";
                                                            confirmpw.focus();
                                                            return false;
                                                        }

                                                        if(phoneNumber.value.length!=11){
                                                            alert("폰번호는 - 제외 입력해주세요");
                                                            phoneNumber.focus();
                                                            return false;
                                                        }
                                                        if(!this.check(re2,email,"이메일 형식이 올바르지 않습니다.")){
                                                            email.focus();
                                                            return false;
                                                        }
        },

        save2 : function () {
                var data = {
                    uid: $('#uid').val(),
                    name: $('#name').val(),
                    password: $('#password').val(),
                    confirmpw: $('#confirmpw').val(),
                    phoneNumber: $('#phoneNumber').val(),
                    email: $('#email').val()
                };
                $.ajax({
                        type: 'POST',
                        url: '/user/signup',
                        dataType: 'json',
                        contentType:'application/json; charset=utf-8',
                        data: JSON.stringify(data)
                      }).done(function() {
                           alert('회원가입 완료!');
                           window.location.href = '/login/';
                      }).fail(function (error) {
                           alert(JSON.stringify(error));
                      });


            },

            idChk : function(){     //https://1-7171771.tistory.com/78 참고
                      var id = $('#uid').val();

                       $.ajax({
                                type : "GET",
                            	url : "/user/"+id+"/exists",
                            	data:{id}
                            }).done(function(result){
                                if(result==false){
                                    alert('사용 가능한 아이디 입니다');
                                }else{
                                    alert('중복된 아이디 입니다');
                                }
                            }).fail(function(error) {
                            	alert(JSON.stringify(error));
                            });
             },
             saveChk : function(){     //https://1-7171771.tistory.com/78 참고
                                   var id = $('#uid').val();

                                    $.ajax({
                                             type : "GET",
                                         	url : "/user/"+id+"/exists",
                                         	data:{id},
                                         	async:false
                                         }).done(function(result){
                                             if(result==false){
                                                 return true;
                                             }else{
                                                 alert('중복된 아이디 입니다');
                                                 $('#uid').focus();
                                                 return fail;
                                             }
                                         }).fail(function(error) {
                                         	alert(JSON.stringify(error));
                                         });
                          },

            cancel : function(){
                $('#uid').val('');
                $('#password').val('');
                $('#confirmpw').val('');
                $('#name').val('');
                $('#email').val('');
                $('#phoneNumber').val('');
            }

 };

main.init();