var socket;
if(typeof(WebSocket) == "undefined") {
    alert("您的浏览器不支持WebSocket");
    //return;
}
function chickActive(){
    for (let i=0;i<users.length;i++){
        if (users[i].Name==$(".active h4").text()){
            return users[i]
        }
    }
}


function websocketConnection(userID1) {
    //实现化WebSocket对象，指定要连接的服务器地址与端口
    console.log(userID1)
    socket = new WebSocket("ws://localhost:8080/webSocket/"+userID1);
    //打开事件
    socket.onopen = function() {
        //socket.send("这是来自客户端的消息" + location.href + new Date());
    };
    //获得消息事件
    socket.onmessage = function(msg) {
        let message=JSON.parse(msg.data)

        if (message.from==$(".active p").attr('id')){
            addChat(message)
        }else {
            let b=$("#"+message.from).next()
            b.text(parseInt(b.text())+1)
            b.attr('class',"")
        }
        $("#"+message.from).text(message.message)

        console.log(msg.data)
    };
    //关闭事件
    socket.onclose = function() {
        alert("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function(err) {
        console.log(err)
        alert("发生了错误");
    }
}
//发送消息
$("#send_btn").click(function() {
    socket.send(`{from:'${userID}',to:'${$(".active h4").text()}',message:'${$("#send_text").val()}'}`)
    addChat({"from":userID,"message":$("#send_text").val(),"date":new Date()})
});
queryUserId(websocketConnection)
//关闭
// $("#btnClose").click(function() {
//     socket.close();
// });