<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录首页</title>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <style>
        *{
            box-sizing: border-box;
            overflow: hidden;
        }
        body {
            width: 100%;
            height: 100vh;
            margin: 0;
            padding: 0;
        }
        #all {
            width: 100%;
            height: 100%;
            margin: 0 0 0 0;
            padding: 0;
            background-image: url("static/img/dk.png");
            background-repeat: no-repeat;
            background-size:100%;
        }
        #header {
            width: 100%;
            height: 10%;
            /*background-color: #f3ece4;*/
            position: relative;
            overflow: hidden;  //为什么要设置这样就不会有滚动条
        }
        #spn {
            width: 100%;
            height: 100%;
            position: absolute;
            left:40%;
            top:30%;
            color: black;
            font-size: 28px;
            font-style: italic;
            font-family: Ebrima;
        }
        #main {
            width: 100%;
            height: 75%;
            padding-top: 10%;
            padding-left: 40%;
            /*background-image: url("static/img/dk.png");*/
            /*background-repeat: no-repeat;*/
            /*background-size:100%;*/
        }
        #bottom {
            width: 100%;
            height: 15%;
            /*background-color: antiquewhite;*/
        }
    </style>
</head>
<body>
<div id="all">
    <div id="header">
        <span id="spn">xx系统--欢迎您！</span>
    </div>
    <div id="main">
        <form action="/dubboConsumer/login" method="post">
            用户名：<input type="text" name="username" style="margin-bottom: 10px"/><br>
            密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" style="margin-left: 2px"><br>
            <input type="submit" value="登录" style="margin-left: 109px;margin-top: 13px;">
        </form>
    </div>
    <div id="bottom"></div>
</div>
</body>
<script>
    (function ($) {
        ktw.InitDatagrid=function () {
            alert(1111111111111);
        }
        $(function () {
            Ready.getJson();
        });
        var Ready = {
            getJson: function () {
                $.getJSON('http://localhost:8085/static/test.json', function (res) {
                    debugger
                    console.log(res);
                });
            }
        }
    })(jQuery);
</script>
</html>
