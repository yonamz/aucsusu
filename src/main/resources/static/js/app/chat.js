var main = {
           $(document).ready(function (){
               var roomName = [[${room.name}]];
               var roomId = [[${room.roomId}]];
               var username = [[${user.name}]];

               var sockJs = new SockJS("/stomp/chat");
               var stomp = Stomp.over(sockJs);

               stomp.connect({},function(){
                   console.log("STOMP Connection");

                   stomp.subscribe("/sub/chat/room/"+roomId, function(chat){
                       var content = JSON.parse(chat.body);

                       var writer = content.writer;
                       var str = '';

                       if(writer==username){
                           str = "<div class='col-6'>";
                           str += "<div class='alert alert-secondary'>";
                           str += "<b>"+sessionId+" : "+message+"</b>";
                           str += "</div></div>";
                           $("#msgArea").append(str);
                       }else{
                           str = "<div class='col-6'>";
                           str += "<div class='alert alert-warning'>";
                           str += "<b>"+sessionId+" : "+message+"</b>";
                           str += "</div></div>";
                           $("#msgArea").append(str);
                       }
                       $("#msgArea").append(str);
                   });
                   stomp.send('/pub/chat/enter',{},JSON.stringify({roomId:roomId, writer:username}));
               });

               $("#button-send").on("click", function(e){
                   var msg = document.getElementById("msg");

                   console.log(username+":"+msg.value);
                   stomp.send('/pub/chat/message',{},JSON.stringify({roomId:roomId,message:msg.value, writer:username}));
                   msg.value="";
               });
           });
           };
main.init();
