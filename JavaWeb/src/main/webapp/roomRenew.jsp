<%@ page import="entity.*" %>
<%@ page import="static tool.Query.getAllRooms" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="static tool.Query.searchFullRooms" %>
<%@ page import="static tool.Query.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String[]> map =request.getParameterMap() ;
    int op = Integer.parseInt(map.get("op")[0]) ; //通过op选项来控制页面显示的内容
    TimeExtension renew=null ;
    if(op==2){
        /**现在获得的只有 房间号 和 续费天数
         * 需要通过房间号先获得续费订单的订单号,
         * 通过订单号获得获得房间编号、房间入住和到期时间
         * 通过房间编号获得房间价格
         *
         */
        //查询原订单号
        Order order = getOrder(map.get("roomid")[0]);
        String orderid =order.getOrderNumber() ;
        //查询员订单截止时间
        Date olddate = order.getCheckOutTime();
        Date newdate = order.getCheckOutTime();
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(newdate);
        System.out.println(Integer.parseInt(map.get("time")[0]) );
        calendar.add(5, Integer.parseInt(map.get("time")[0]));
        //算出新截止时间
        newdate = new Date(calendar.getTime().getTime()) ;
        //算出 价格 =单价*折扣*天数 ;
        double discount = searchDiscount(order.getCustomerIDCard());

        int price = 1  ;
        renew=new TimeExtension(getRenewNum()+1,orderid,olddate,newdate,(int)(discount*Integer.parseInt(map.get("time")[0])
                *getRoomPrice(order.getRoomNumber()))) ;
        request.getSession().setAttribute("renew",renew);

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

        function fun1() {

            alert("续费成功,返回首页!")
            window.location.href="/ServiceManage?op=4";
        }

        function fun2() {

            var roomid = document.getElementById("roomid").value
            var time = document.getElementById("time").value
            var pat1 = /^[0-9]{6}$/ ;
            var pat2 =/^[1-9][0-9]?$/ ;

            if(pat1.test(roomid) && pat2.test(time)){
                window.location.href="/roomRenew.jsp?op=2&roomid="+roomid+"&time="+time
            }
            return false
        }

    </script>
</head>

<%@include file="/hotelAdmin.jsp"%>


<body>

<div class="pusher">


    <div class="ui container">
        <h2 class="ui header">房间续费</h2>
        <div class="ui column grid">
            <div class="four wide column">
                <div class="ui vertical steps">

                    <div class="<%=(op<=1)?"active step ":"completed step"%>" >
                        <i class="building icon"></i>
                        <div class="content">
                            <div class="title">选择房号</div>
                            <%--<div class="description">Choose your shipping options</div>--%>
                        </div>
                    </div>

                    <div class="<%=(op==2)?"active step ":(op==1)?"step":"completed step"%>">
                        <i class="info icon"></i>
                        <div class="content">
                            <div class="title">订单支付</div>
                            <%--<div class="description">Enter billing information</div>--%>
                        </div>
                    </div>

                </div>
            </div>
            <div class="eleven wide  column" >

                <%  if(op==1){ %>
                <form class="ui form" onsubmit="return fun2(this)">
                    <h4 class="ui dividing header">房间选择</h4>
                    <div class="four wide column">
                        <label>Room</label>


                        <div class="five wide field">


                            <select class="ui fluid search dropdown" id="roomid" name="roomid">

                                <%
                                    ArrayList<String> list = searchFullRooms();
                                    if(list.size()==0){
                                %>
                                <option value="无房可续">无房可续</option>
                                <%
                                    }
                                    for(String str : list){
                                %>
                                <option value=<%=str%>> <%=str%> </option>
                                <% } %>
                            </select>
                            <%--<input type="text" name="roomid" placeholder="房间号">--%>
                        </div>
                    </div>
                    <h4 class="ui dividing header">续费时间</h4>
                    <div class="eight wide field">
                        <label>Time</label>
                        <div class=" fields">
                            <div class="eight wide field">

                                <input type="text" maxlength="8"  placeholder="time" id="time" name="time">
                            </div>
                        </div>

                    </div>
                    <br/>
                    <div class="ui right submit floated button" tabindex="0" >Submit Order</div>
                </form>
                <% } else if(op==2){ %>


                <h4 class="ui dividing header">订单确认</h4>
                <table class="ui table">
                    <thead>
                    <tr><th class="six wide">Name</th>
                        <th class="ten wide">Info</th>
                    </tr></thead>
                    <tbody>
                    <tr>
                        <td>续费订单编号</td>
                        <td><%=renew.getOperatingID() %></td>
                    </tr>
                    <tr>
                        <td>原订单编号</td>
                        <td><%=renew.getOrderNumber() %></td>
                    </tr>
                    <tr>
                        <td>原到期时间</td>
                        <td><%=renew.getOldExpiryDate() %></td>
                    </tr>
                    <tr>
                        <td>支付金额</td>
                        <td><%=renew.getAddedMoney() %></td>
                    </tr>
                    <tr>
                        <td>现到期时间</td>
                        <td><%=renew.getNewExpiryDate() %></td>
                    </tr>
                    </tbody>
                </table>


                <h4 class="ui dividing header">完成支付</h4>
                <div class="ui right floated labeled button" tabindex="0">
                    <a class="ui basic right pointing label">
                        <%-- 去数据库查询价格 * 天数 *相应的折扣 --%>
                        ¥<%=renew.getAddedMoney() %>
                    </a>
                    <div class="ui right button" onclick="fun1()">
                        <i class="shopping icon"></i> 支付
                    </div>
                </div>
                <%}%>

            </div>
            <%--<h1>欢迎续费</h1>--%>
            <%--  续费房间号 下拉列表   续费时长 缴纳金额  续费要改相应的order表格的退房日期 --%>

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
                }

            }, {

                inline : true,
                on     : 'submit'

            }
        )

        ;
    });
</script>
