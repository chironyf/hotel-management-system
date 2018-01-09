<%@ page import="entity.*" %>
<%@ page import="static tool.Query.getAllRooms" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="config.GCON" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="tool.DataBase" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("hoteladmin") == null) {
        response.sendRedirect("/index.jsp");
    }


    if(GCON.status==1){ //修改权限
        DataBase.setConnection(DataBase.MAP.get(GCON.HOTELUSERNAME));
        GCON.status=0 ;
    }
%>
<html>

<head>

    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>宾馆管理系统</title>
    <link rel="stylesheet" type="text/css" href="/semantic/dist/semantic.min.css">
    <script src="/semantic/dist/jquery.min.js"></script>
    <script src="/semantic/dist/semantic.js"></script>
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/reset.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/site.css">

    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/container.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/divider.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/grid.css">

    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/header.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/segment.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/table.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/icon.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/menu.css">
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/message.css">

    <style type="text/css">
        h2 {
            margin: 1em 0em;
        }
        .ui.container {
            padding-top: 5em;
            padding-bottom: 5em;
        }
    </style>

    <script>
        var i=0;
        function myDate(){
            var now=new Date();
            var year=now.getFullYear();
            var month=now.getMonth()+1;
            var day=now.getDate();
            var hours=now.getHours();
            var minutes=now.getMinutes();
            var seconds=now.getSeconds();
            document.getElementById("div").innerHTML=year+"年"+fix(month, 2)+"月"+fix(day, 2)+"日"+fix(hours, 2)+" : "+fix(minutes, 2)+" : "+fix(seconds, 2);
        }

        function fix(num, length) {
            return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
        }
        setInterval(myDate,1000);
    </script>

</head>
<body>
<div class="ui inverted menu">

    <div class="ui simple dropdown item">
        <i class="tasks icon"></i>
        业务办理
        <i class="dropdown icon"></i>
        <div class="menu">
            <a class="item" href="/roomOrder.jsp?op=1"><i class="arrow right icon"></i>订房</a>
            <a class="item" href="/roomRenew.jsp?op=1"><i class="spinner icon"></i>续费</a>
            <a class="item" href="/roomCheckOut.jsp?op=1"><i class="arrow left icon"></i>退房</a>
        </div>
    </div>
    <div class="ui simple dropdown item">
        <i class="building icon"></i>
        客房管理
        <i class="dropdown icon"></i>
        <div class="menu">
            <a class="item" href="/RoomManage?op=1"><i class="search icon"></i>查询房间</a>
            <a class="item" href="/RoomManage?op=2"><i class="plus icon"></i>增加房间</a>
            <a class="item" href="/roomManagement/roomDisplay.jsp"><i class="zoom out icon"></i>房间概览</a>
        </div>
    </div>
    <div class="ui simple dropdown item">
        <i class="browser icon"></i>
        订单浏览
        <i class="dropdown icon"></i>
        <div class="menu">
            <a class="item" href="/OrderManage?op=1"><i class="book icon"></i>预定订单</a>
            <a class="item" href="/OrderManage?op=2"><i class="file icon"></i>入住订单</a>
            <a class="item" href="/OrderManage?op=3"><i class="file text outline icon"></i>续费订单</a>
            <a class="item" href="/OrderManage?op=4"><i class="file archive outline icon"></i>历史订单</a>
            <a class="item" href="/OrderManage?op=5"><i class="folder open icon"></i>所有订单</a>
        </div>
    </div>
    <div class="right menu">
        <div class="ui simple dropdown item">
            <div id="div"></div>
        </div>
    </div>

    <div class="right menu">
        <div class="ui simple dropdown item">
            <i class="user icon"></i>
            <%="宾馆管理员: " + session.getAttribute("hoteladmin")%>  <%-- 后期换成session("name")  --%>
            <i class="dropdown icon"></i>
            <div class="menu">
                <a class="item" href="/ServiceManage?op=2"><i class="sign out icon"></i>注销</a>
            </div>
        </div>
    </div>

</div>

</body>
</html>