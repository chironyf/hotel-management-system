<%@ page import="entity.*" %>
<%@ page import="static tool.Query.getAllRooms" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="static tool.Query.searchEmptyRooms" %>
<%@ page import="static tool.Query.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String[]> map =request.getParameterMap() ;
    int op = Integer.parseInt(map.get("op")[0]) ; //通过op选项来控制页面显示的内容
    String roomid ="" ;
    if(map.get("roomid")!=null){
        roomid=map.get("roomid")[0] ;
    }
%>
<html>
<head>
    <meta charset="UTF-8">
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
    <script >
        function returnMainPage() {
            window.location.href="/roomOrder.jsp?op=1" ;
        }

        function fun4() {
            var room = document.getElementById("roomid").value
            var name = document.getElementById("name").value
            var idcard =document.getElementById("idcard").value.toString()
            var year=idcard.substring(6,10)
            var month = idcard.substring(10,12)
            var day =idcard.substring(12,14)
            var birthdata = year+'-'+month+'-'+day
            var sex = document.getElementById("sex").value
            var phonenumber = document.getElementById("phonenumber").value
            var time = document.getElementById("time").value
            if( /^[0-9]{6}$/.test(room) && /^[1-9][0-9]?$/.test(time) && /^[0-9]{17}[0-9|X]$/.test(idcard)
                && /^1[3|4|5|8][0-9]\d{4,8}$/.test(phonenumber) ){
                var url = "&roomid=" + room + "&name=" + name + "&idcard=" + idcard
                    + "&birthdata=" + birthdata + "&sex=" + sex + "&phonenumber=" + phonenumber + "&time=" + time

                var url1 = window.location.search.split("&")[1]

                window.location.href = "/ServiceManage?op=1&" + url1 + url;
            }
            return false ;

        }

    </script>

</head><%@include file="hotelAdmin.jsp"%>
<body>
<div class="pusher">
    <div class="ui container">
        <h2 class="ui header">订房</h2>
        <div class="ui column grid">
            <div class="four wide column">
                <div class="ui vertical steps">

                    <div class="<%=(op<=1)?"active step ":"completed step"%>" >
                        <i class="building icon"></i>
                        <div class="content">
                            <div class="title">选定房型</div>
                            <%--<div class="description">Choose your shipping options</div>--%>
                        </div>
                    </div>

                    <div class="<%=(op==2)?"active step ":(op==1)?"step":"completed step"%>">
                        <i class="user icon"></i>
                        <div class="content">
                            <div class="title">用户登记</div>
                            <%--<div class="description">Enter billing information</div>--%>
                        </div>
                    </div>

                    <div class="<%=(op==3|| op==4)?"active step ":"step"%>">
                        <i class="info icon"></i>
                        <div class="content">
                            <div class="title">订单支付</div>
                            <%--<div class="description">Verify order details</div>--%>
                        </div>
                    </div>
                </div>


            </div>

            <div class="eleven wide  column" >


                <% if(op==1){ %>
                <div class="ui three column grid" >
                    <%

                        ArrayList<RoomTypeAndPrice> allRooms = getAllRooms();

                        for(RoomTypeAndPrice rtp : allRooms){
                            ArrayList<Integer> list = getNumofRoom(rtp.getRoomType());//list[0]非空  list[1]空
                    %>
                    <div class="column">
                        <div class="ui card">
                            <div class="image">
                                <img src=<%=rtp.getUrl()%> >
                            </div>
                            <div class="content">
                                <a class="header"><%=rtp.getRoomType()%></a>
                                <div class="description">
                                    <%=rtp.getDesc()%>
                                </div>
                                <div class="ui right floated statistic">
                                    <div class="value">
                                        <%=list.get(1)%>/<%=list.get(0)%>
                                    </div>
                                    <div class="label">
                                        入住/空房
                                    </div>
                                </div>
                            </div>
                            <a class="ui orange right ribbon label" href="<%=list.get(1)==0?"":"/roomOrder.jsp?op=2&roomtype="+rtp.getRoomType()%>">
                                ¥<%=rtp.getPrice()%>/天
                            </a>
                        </div>
                    </div>
                    <% }  %>
                    <%}
                    else if(op==2){
                    %>
                    <form class="ui form" onsubmit="return  fun4(this)" >
                        <h4 class="ui dividing header">房间选择</h4>
                        <div class="field">
                            <label>Room</label>
                            <div class="two fields">
                                <div class="field">

                                    <select class="ui fluid search dropdown" id="roomid" name="roomid">
                                        <%
                                            String roomtype =request.getParameterMap().get("roomtype")[0].toString() ;
                                            System.out.println(roomtype);
                                            ArrayList<String> list = searchEmptyRooms(roomtype);
                                            for(String str : list){
                                                System.out.println(str);
                                        %>
                                        <option value=<%=str%>
                                                    <% if(str.equals(roomid)){ %>
                                                        selected="selected"
                                                <%}%>
                                        ><%=str%></option>

                                        <%  }  %>


                                    </select>
                                    <%--<input type="text" name="roomid" placeholder="房间号">--%>
                                </div>


                            </div>
                        </div>
                        <h4 class="ui dividing header">预定天数</h4>
                        <div class="field">
                            <label>time</label>
                            <div class="two fields">
                                <div class="field">
                                    <input type="text" maxlength="5" id="time" name="time" placeholder="预定天数">

                                </div>
                            </div>
                        </div>
                        <h4 class="ui dividing header">个人信息</h4>
                        <div class="field">
                            <label>Name</label>
                            <div class="two fields">

                                <div class="field">
                                    <input type="text" id="name" name="name" placeholder="姓名">
                                </div>
                            </div>
                        </div>
                        <%--<h4 class="ui dividing header">Billing Information</h4>--%>
                        <div class="fields">
                            <div class="seven wide field">
                                <label>身份证号</label>
                                <input type="text" id="idcard" name="idcard" maxlength="18" placeholder="身份证号">
                            </div>
                            <%--<div class="six wide field">--%>
                            <%--<label>出生日期</label>--%>
                            <%--&lt;%&ndash;<input type="text" name="card[cvc]" maxlength="3" placeholder="出生日期">&ndash;%&gt;--%>
                            <%--<input type="date" value="2018-01-01" id="birthdata"/>--%>
                            <%--</div>--%>
                            <div class="six wide field">
                                <label>性别</label>
                                <div class="two fields">
                                    <div class="field">
                                        <select class="ui fluid search dropdown" id="sex">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="seven wide field">
                                <label>手机号</label>
                                <input type="text" id="phonenumber" name="phonenumber" maxlength="16" placeholder="手机号">
                            </div>
                        </div>
                        <div class="ui right submit floated button" tabindex="0"  >Submit Order</div>
                    </form>
                    <%}
                    else if(op==3){

                    %>

                    <h4 class="ui dividing header">订单确认</h4>
                    <table class="ui table">
                        <thead>
                        <tr><th class="six wide">Name</th>
                            <th class="ten wide">Info</th>
                        </tr></thead>
                        <tbody>
                        <%
                            Map<String, String[]> map1 = request.getParameterMap();
                            for(String key :  map1.keySet()) {
                                if(!key.equals("op") ){ %>
                        <tr>

                            <td><%=key%></td>
                            <td><%=map1.get(key)[0].toString()%></td>

                        </tr>
                        <%
                                }
                            } %>
                        </tbody>
                    </table>


                    <h4 class="ui dividing header">完成支付</h4>
                    <div class="ui right floated labeled button" tabindex="0">
                        <a class="ui basic right pointing label">
                            <%-- 去数据库查询价格 * 天数 *相应的折扣 --%>
                            ¥<%=map.get("pay")[0]%>
                        </a>
                        <div class="ui right button">
                            <i class="shopping icon"></i> <a href="ServiceManage?<%=request.getQueryString()%>">支付</a>
                        </div>
                    </div>
                    <%} else if (op == 4) {%>
                    <h4 class="ui dividing header">支付成功</h4>
                    <div class="ui right button" onclick="returnMainPage()">返回</div>
                    <%}%>
                </div>
            </div>
        </div>
    </div>

    <%--<h1>欢迎宾馆管理员登录！</h1>--%>

</div>
</body>
</html>
<script>
    $(document).ready(function () {
        $('.ui.form').form({
                // if( /^[0-9]{6}$/.test(room) && /^[1-9][0-9]?$/.test(time) && /^[0-9]{18}$/.test(idcard)
                //         && /^1[3|4|5|8][0-9]\d{4,8}$/.test(phonenumber) ){
                time: {
                    identifier: 'time',
                    rules: [
                        {
                            type: 'regExp[/^[1-9][0-9]?$/]',
                            prompt: '时间不符合规范'
                        }
                    ]
                }
                ,roomid: {
                    identifier: 'roomid',
                    rules: [
                        {
                            type: 'regExp[/^[0-9]{6}$/]',
                            prompt: '房间号不符合规范'
                        }
                    ]
                },idcard: {
                    identifier: 'idcard',
                    rules: [
                        {
                            type: 'regExp[/^[0-9]{17}[0-9|X]$/]',
                            prompt: '身份证号不符合规范'
                        }
                    ]
                }

                ,phonenumber: {
                    identifier: 'phonenumber',
                    rules: [
                        {
                            type: 'regExp[/^1[3|4|5|8][0-9]\\d{4,8}$/]',
                            prompt: '手机号不符合规范'
                        }
                    ]
                }

            }, {

                inline : true,
                on     : 'submit'

            }
        )

        ;
    });
</script>
