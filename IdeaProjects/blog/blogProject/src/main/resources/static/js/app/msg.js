var main3 = {
    init : function () {
        var _this = this;

        $('#btnSubmit').on('click', function () {
            _this.submit();
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

      }

};


main3.init();