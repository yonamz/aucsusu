var main3 = {
    init : function () {
        var _this = this;

        $('#btnSubmit').on('click', function () {
            _this.submit();
        });

        $('#btn-delete').on('click', function () {
            if(confirm("쪽지를 삭제하시겠습니까?") == false){
                return;
            }
            _this.delete();
        });

        $('#deleteMsg').on('click', function () {
              if(confirm("쪽지를 삭제하시겠습니까?") == false){
                  return;
              }
                 _this.deleteMsg();
          });

      },

      submit: function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            recipient: $('#recipient').val()
        };

        $.ajax({
                type: 'POST',
                url: '/message/sendMsg',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
        }).done(function() {
                alert('쪽지 전송 성공!');
                window.location.href = '/message/getSendList';
         }).fail(function (error) {
                alert("에러 발생");
                alert(JSON.stringify(error));
        });

      },

      delete : function(){
        var msgNo = $('#msgNo').val();

                $.ajax({
                    type: 'DELETE',
                    url: '/message/'+msgNo,
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8'
                }).done(function() {
                    alert('쪽지가 삭제되었습니다.');
                    window.location.href = '/message/getMsgList';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
      },

      deleteMsg : function(){
                        var msgNo = $('#msgNo').val();

                                $.ajax({
                                    type: 'DELETE',
                                    url: '/message/delete/'+msgNo,
                                    dataType: 'json',
                                    contentType:'application/json; charset=utf-8'
                                }).done(function() {
                                    alert('쪽지가 삭제되었습니다.');
                                    window.location.href = '/message/getSendList';
                                }).fail(function (error) {
                                    alert(JSON.stringify(error));
                                });
                      }

};


main3.init();