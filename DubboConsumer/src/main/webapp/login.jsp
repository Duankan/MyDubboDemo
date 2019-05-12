<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录首页</title>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="static/js/linq/linq.min.js"></script>
    <style>
        * {
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
            background-size: 100%;
        }

        #header {
            width: 100%;
            height: 10%;
            position: relative;
            overflow: hidden;
        }

        #spn {
            width: 100%;
            height: 100%;
            position: absolute;
            left: 40%;
            top: 30%;
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
        }

        #bottom {
            width: 100%;
            height: 15%;
        }

        .taskls {
            width: 65%;
            height: 50%;
            background: #e3dddd;;
            margin: 0 auto;
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
            <input class="tj" type="submit" value="登录" style="margin-left: 109px;margin-top: 13px;">
        </form>
        <button class="export">导出</button>
        <button class="async">异步</button>
        <!--显示任务列表-->
        <div class="taskls"></div>
    </div>
    <div id="bottom"></div>
</div>
</body>
<script>
    (function ($) {
            var tasklist = [];
            var features = [];
            var total = 20000;
            var radtio = (5000 / 20000) * 99.9;

            function SetPost() {
                console.log("开始发起请求!");
                $.ajax({
                    url: "http://localhost:8085/dubboConsumer/HttpGet",
                    dataType: 'json',
                    type: 'GET',
                    cache: false,
                    // timeout: 1000 * 60 * 2,
                    async: true,
                    error: function (res) {
                        console.error(arguments);
                    },
                    success: function (res) {
                        console.log(res);
                    }
                });
            };

            function test(_taskId) {
                $.ajax({
                    url: "http://localhost:8085/dubboConsumer/HttpGet",
                    dataType: 'json',
                    type: 'GET',
                    cache: false,
                    async: true,
                    error: function (res) {
                        console.error(arguments);
                    },
                    success: function (res) {
                        features.push(res);
                        console.log("正在下载!");
                        for (var i = 0; i < tasklist.length; i++) {
                            if (tasklist[i]._taskId == _taskId) {
                                if (tasklist[i]._taskCount < total) {
                                    tasklist[i]["_taskCount"] += 5000;
                                    tasklist[i]["_taskProgress"] = (parseFloat(tasklist[i]["_taskProgress"].split("%")[0]) + radtio).toString() + "%";
                                    //数据已经加载完毕，关闭定时器,开始下载excel
                                    if (tasklist[i]._taskCount >= total) {
                                        clearInterval(tasklist[i]._taskInterval);
                                        exportdata(features, tasklist[i]);
                                    }
                                }
                            }
                            createOrUpdateTaskDom(tasklist[i]);
                        }
                    }
                });
            }

            /**
             * @function 创建下载任务id
             * @desc 为每一个任务创建唯一的id，以便查询下载操作
             * @author duankang
             **/
            function createTaskId() {
                var myDate = new Date();//获取系统当前时间
                var year = myDate.getFullYear();
                var mon = myDate.getMonth() + 1;
                var day = myDate.getDate();
                var hour = myDate.getHours();
                var min = myDate.getMinutes();
                var sec = myDate.getSeconds();
                var _taskId = year.toString() + mon.toString() + day.toString() + hour.toString() + min.toString() + sec.toString();
                return _taskId;
            }

            /**
             * @function 创建或更新Task页面显示函数
             * @desc 页面动态地显示任务的进度和绑定任务事件
             * @author duankang
             **/
            function createOrUpdateTaskDom(task) {
                var taskDiv = $("#" + task._taskId);
                //如果div存在，更新状态
                if (taskDiv.length > 0) {
                    var progressSpan = taskDiv.find(".taskProgress");
                    var openExcelOld = taskDiv.find(".openExcel");
                    $(progressSpan).html(task._taskProgress);
                    if (task._taskProgress == "100%" && openExcelOld.length == 0) {
                        var openExcel = $('<button style="margin-left: 10px" class="openExcel">打开</button>');
                        taskDiv.append(openExcel);
                        openExcel.bind('click', function () {
                            window.open(task.url);
                        });
                    }
                }
                //新建Task列表
                else {
                    var tskDom = $(".taskls");
                    var div = $('<div id="' + task._taskId + '" style="margin-top: 10px"></div>');
                    var spanId = $('<sapn style="margin-left: 10px">' + task._taskId + '</sapn>');
                    var spanName = $('<sapn style="margin-left: 10px">' + task._taskName + '</sapn>');
                    var spanProgress = $('<sapn style="margin-left: 10px" class="taskProgress">' + task._taskProgress + '</sapn>');
                    var spanDel = $('<button style="margin-left: 10px">删除</button>');
                    div.append(spanId);
                    div.append(spanName);
                    div.append(spanProgress);
                    div.append(spanDel);
                    tskDom.append(div);
                    spanDel.bind('click', function () {
                        var taskid = this.parentNode.getAttribute("id");
                        for (var i = 0; i < tasklist.length; i++) {
                            if (tasklist[i]._taskId == taskid) {
                                clearInterval(tasklist[i]._taskInterval);
                                tasklist.splice(i, 1);
                            }
                        }
                        this.parentNode.remove();
                    });
                }
            }

            /**
             * @function 导出excel
             * @param task
             * @param features
             * @desc 数据已经加载完毕后，导出excel
             * @author duankang
             **/
            function exportdata(features, task) {
                $.ajax({
                    url: "http://localhost:8085/dubboConsumer/export",
                    dataType: 'json',
                    type: 'GET',
                    cache: false,
                    async: true,
                    error: function (res) {
                        console.error(arguments);
                    },
                    success: function (res) {
                        task._taskProgress = "100%";
                        task.url = res.object;
                        createOrUpdateTaskDom(task);
                    }
                });
            }

            /***
             * @function 导出函数
             * @desc 同时只允许存在五个正咋下载的任务
             * @author duankang
             */
            $(".export").click(function () {
                //用enum表达式判断
                var isDownloaingCount = Enumerable.From(tasklist).Where('p=>p._taskProgress!="100%"').ToArray().length;
                if (isDownloaingCount > 2) {
                    alert("当前下载任务过多,请稍后下载");
                    return false;
                }
                var _taskId = createTaskId();
                var _taskName = "下载地类图斑空间分析要素信息";
                var _taskProgress = "0%";
                var Interval = setInterval(test, 1000 * 20, _taskId);
                var task = {
                    _taskId: _taskId,
                    _taskName: _taskName,
                    _taskProgress: _taskProgress,
                    _taskInterval: Interval,
                    _taskCount: 0,
                };
                tasklist.push(task);
                createOrUpdateTaskDom(task);

            });
            $(".async").click(function () {
                var _taskId = createTaskId();
                var data = {taskId: _taskId, taskName: "下载测试"};
                $.ajax({
                    url: "http://localhost:8085/dubboConsumer/startDownAsync",
                    dataType: 'json',
                    type: 'POST',
                    cache: false,
                    async: true,
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    error: function (res) {
                        console.error(arguments);
                    },
                    success: function (res) {
                        console.log(res);
                    }
                });
            });
            $(function () {
            });
            var Ready = {
                getJson: function () {
                    $.getJSON('http://localhost:8085/static/test.json', function (res) {
                        debugger
                        console.log(res);
                    });
                }
            }
        }

    )(jQuery);
</script>
</html>
