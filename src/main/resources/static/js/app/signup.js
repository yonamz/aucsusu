var main = {
        init : function () {
                var _this = this;

                $('#btnSignup').on('click', function () {
                    if(_this.save1()!=false){
                        _this.saveChk();
                        _this.emailChk();
                        _this.save();
                    }

                });

                $('#btnCancel').on('click', function () {
                     _this.cancel();
                });

                $('#chkID').on('click', function (){
                    var uid = $('#uid').val();
                    var korean = uid.search(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi);

                    if(uid.search(/\s/) != -1||uid==''){
                       alert("아이디를 입력해주세요");
                       return false;
                    }

                    if (korean > 0) {
                       alert("아이디는 영문,숫자만 입력해주세요.");
                       return false;
                    }
                    _this.idChk();
                });

                $('#chkEMAIL').on('click', function (){
                        _this.emailChk();
                });

                $('#phoneNumber').bind("keyup",function(event){
                    var regNumber=/^[0-9]*$/;
                    var temp = $("#phoneNumber").val();
                    if(!regNumber.test(temp)){
                        alert("숫자만 입력하세요");
                        $("#phoneNumber").val(temp.replace(/[^0-9]/g,""));
                    }
                });


        },
        save1 : function(){
                  var idTest = /^[a-zA-Z0-9]{4,12}$/;


                  var uid = $('#uid').val();
                  var name = $('#name').val();
                  var password = $('#password').val();
                  var confirmpw = $('#confirmpw').val();
                  var phoneNumber = $('#phoneNumber').val();
                  var email = $('#email').val();

                  if(uid.search(/\s/) != -1||uid==''){
                      alert("아이디를 입력해주세요");
                      $('#uid').focus();
                      return false;
                  }else if(name.search(/\s/) != -1||name==''){
                      alert("이름을 입력해주세요");
                      $('#name').focus();
                      return false;
                  }else if(password.search(/\s/) != -1||password==''){
                         alert("비밀번호를 입력해주세요");
                         $('#password').focus();
                         return false;
                  }else if(email.search(/\s/) != -1||email==''){
                         alert("이메일을 입력해주세요");
                         $('#email').focus();
                         return false;
                  }
                  if(!this.check(idTest,uid,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")){
                      $('#uid').focus();
                      return false;
                  }


                                                                if(password.length<4){
                                                                    alert("비밀번호는 4자 이상으로 설정해주세요");
                                                                    $('#password').value="";
                                                                    $('#password').focus();
                                                                    return false;
                                                                }



                                                                if(password!=confirmpw){
                                                                    alert("비밀번호가 다릅니다.");
                                                                    $('#confirmpw').value="";
                                                                    $('#confirmpw').focus();
                                                                    return false;
                                                                }


                                                                if(phoneNumber.length!=11){
                                                                    alert("폰번호는 - 제외 입력해주세요");
                                                                    $('#phoneNumber').focus();
                                                                    return false;
                                                                }

                },

        emailChk : function(){
            var email = $('#email').val();
            var emailTest = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            if(!this.check(emailTest,email,"이메일 형식이 올바르지 않습니다.")){
               $('#email').focus();
               return false;
            }

                                   $.ajax({
                                            type : "GET",
                                        	url : "/user/exists/"+email,
                                        	data:{email}
                                        }).done(function(result){
                                            if(result==false){
                                                return true;
                                            }else{
                                                alert('중복된 이메일입니다');
                                                $('#email').focus();
                                                return false;
                                            }
                                        }).fail(function(error) {
                                        	alert(JSON.stringify(error));
                                        });
        },
        check: function(re, what, message){
               if(re.test(what.value)){
                    return true;
               }
               alert(message);
               what.value="";
        },


        save : function () {
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
                    var idTest = /^[a-zA-Z0-9]{4,12}$/;

                    if(!this.check(idTest,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")){
                       id.focus();
                       return false;
                    }

                       $.ajax({
                                type : "GET",
                            	url : "/user/"+id+"/exists",
                            	data:{id}
                            }).done(function(result){
                                if(result==false){
                                    alert('사용 가능한 아이디입니다');
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