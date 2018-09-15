<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录首页</title>
    <style>
        html {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #all {
            width: 100%;
            height: 100%;
            margin: 0 0 0 0;
            padding: 0;
            /*background-image: url("/static/img/back.jpg");*/
            /*!*padding-left: 40%;*!*/
            /*background-repeat: no-repeat;*/
            /*background-size: 100% 100%;*/
        }
        #header {
            width: 100%;
            height: 10%;
            /*margin-top: 20%;*/
            /*padding-top: 5%;*/
            background-color: red;
            position: relative;
            overflow: hidden;  //为什么要设置这样就不会有滚动条
        }
        #spn {
            width: 100%;
            height: 100%;
            /*margin-left: 40%;*/
            position: absolute;
            left:40%;
            top:30%;
        }
        #main {
            width: 100%;
            height: 75%;
            background-color: aqua;
            margin-left: 30%;
        }
        #bottom {
            width: 100%;
            height: 15%;
            background-color: antiquewhite;
        }
    </style>
</head>
<body>
<div id="all">
    <div id="header">
        <span id="spn">大数据中心--欢迎您！</span>
    </div>
    <div id="main">
        <form action="/dubboConsumer/login" method="post">
            用户名：<input type="text" name="username"/><br>
            密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"><br>
            <input type="submit" value="登录">
        </form>
    </div>
    <div id="bottom"></div>
</div>
</body>
</html>
