<!DOCTYPE html>
<html>
<head>
    <title>webSocket Demo使用</title>

    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html" charset="UTF-8">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <!-- 公共JS -->
    <script type="text/javascript" src="/static/jquery.js"></script>
</head>
<body>

    <div id="connDiv" hidden>
        用户id <input id = "userId" type="text">
        <button onclick="connectWebSocket()">Connection</button>
    </div>
    <div id="msgDiv" hidden>
        消息 <input id="text" type="text"/>
        <button onclick="send()">Send</button>
        <button onclick="closeWebSocket()">Close</button>
    </div>

    <div id="message">

    </div>

    <script type="text/javascript">
        var websocket = null;
        var userID;
        $(function () {
            $("#connDiv").show();
            $("msgDiv").hide();
        })

        //强制关闭浏览器  调用websocket.close（）,进行正常关闭
        window.onunload = function () {

            //关闭连接
            closeWebSocket();
        }

        //建立WebSocket连接
        function connectWebSocket() {

            console.log("开始...");

            userID = $("#userId").val();

            //建立webSocket连接
            websocket = new WebSocket("ws://127.0.0.1:8080/connect/webSocket?userId=" + userID);

            //打开webSokcet连接时，回调该函数
            websocket.onopen = function () {
                console.log("onpen");
                $("#connDiv").hide();
                $("#msgDiv").show();
            }

            //关闭webSocket连接时，回调该函数
            websocket.onclose = function () {
                //关闭连接
                console.log("onclose");
            }

            //接收信息
            websocket.onmessage = function (msg) {
                console.log(msg.data);
                $("#message").append(msg.data+"<br/>");
            }
        }

        //发送消息
        function send() {
            var postValue = {};
            postValue.id = userID;
            postValue.message = $("#text").val();
            websocket.send(JSON.stringify(postValue));
        }

        //关闭连接
        function closeWebSocket() {
            if (websocket != null) {
                websocket.close();
                $("#message").append("连接关闭<br/>");
                $("#connDiv").show();
                $("#msgDiv").hide();
            }
        }

    </script>
</body>
<html>