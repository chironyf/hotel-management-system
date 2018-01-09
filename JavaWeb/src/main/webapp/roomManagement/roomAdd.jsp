<%--
  Created by IntelliJ IDEA.
  User: chironyf
  Date: 2017/12/26
  Time: 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>
<%@ page import="static tool.Query.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="config.GCON" %>
<%@ page import="java.util.Map" %>
<%@ page import="tool.Query" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String[]> map = request.getParameterMap() ;
    int op = Integer.parseInt(map.get("op")[0]) ; //通过op选项来控制页面显示的内容
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>添加客房</title>
    <script>

        function returnMainPage() {
            window.location.href="/roomManagement/roomAdd.jsp?op=2";
        }

        function submitNewRoomInfo() {

            var roomNumber = document.getElementById("roomNumber").value;
            var roomType = document.getElementById("roomType").value;
            var remarks = document.getElementById("remarks").value;
            if( /^[0-9]{6}$/.test(roomNumber)){
                var url = "&roomNumber=" + roomNumber + "&roomType=" + roomType + "&remarks=" + remarks;
                window.location.href = "/roomManagement/roomAdd.jsp?op=3" + url;
            }
            return false ;
        }

        function ensureButtonClicked() {

            // var roomnumber = document.getElementById('roomNumber')
            // var roomtype= document.getElementById('roomType')

            var urlNew = window.location.href.split("&")[1] + "&" + window.location.href.split("&")[2]
                + "&" + window.location.href.split("&")[3];

            window.location.href = "/roomManagement/roomAdd.jsp?op=4&" + urlNew;


        }

    </script>

</head>
<%@include file="/hotelAdmin.jsp"%>
<body>

<div class="pusher">

    <div class="ui container">
        <h2 class="ui header">添加客房</h2>
        <div class="ui column grid">
            <div class="four wide column">
                <div class="ui vertical steps">

                    <div class="<%=(op == 2) ? "active step ":"completed step"%>" >
                        <i class="add circle icon"></i>
                        <div class="content">
                            <div class="title">客房信息</div>
                        </div>
                    </div>

                    <div class="<%=(op == 3) ? "active step ":(op== 2)?"step":"completed step"%>">
                        <i class="check circle icon"></i>
                        <div class="content">
                            <div class="title">信息确认</div>
                        </div>
                    </div>

                </div>

            </div>

            <div class="eleven wide  column" >

                <%//添加客房信息
                    if (op == 2) {
                %>
                <form class="ui form" onsubmit="return submitNewRoomInfo(this)">
                    <h2 class="ui dividing header">填写新增客房信息</h2>
                    <div class="two fields">
                        <div class="field">
                            <label>房间号</label>
                            <input type="text" id="roomNumber" name="roomid" placeholder="输入房间号">
                        </div>
                        <div class="field">
                            <label>房间类型</label>
                            <% ArrayList<RoomTypeAndPrice> rooms = getAllRooms();%>
                            <select class="ui fluid dropdown" id="roomType">
                                <%for(RoomTypeAndPrice room :rooms){%>
                                <option value=<%=room.getRoomType()%>><%=room.getRoomType()%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="field">
                        <label>备注</label>
                        <input type="text" id="remarks" placeholder="备注信息">
                    </div>
                    <div class="ui submit button">提交</div>
                </form>

                <%} else if (op == 3) {
                %>

                <h2 class="ui dividing header">待添加客房信息确认</h2>
                <form class="ui form">
                    <table class="ui table">
                        <thead>
                        <tr><th class="six wide">房间号</th>
                            <th class="six wide">房间类型</th>
                            <th class="six wide">备注</th>
                        </tr></thead>
                        <tbody>
                        <tr>
                            <td><%=request.getParameter("roomNumber")%></td>
                            <td><%=request.getParameter("roomType")%></td>
                            <td><%=request.getParameter("remarks")%></td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="ui button" onclick="ensureButtonClicked()">确认</div>
                </form>

                <%} else if (op == 4) {
                    Room newRoom = new Room();
                    newRoom.setRoomStatus("空");
                    newRoom.setRoomNumber(request.getParameter("roomNumber"));
                    newRoom.setRoomType(request.getParameter("roomType"));
                    newRoom.setRemarks(request.getParameter("remarks"));
                    Query.insertRoom(newRoom);
                %>
                <h4 class="ui dividing header">添加成功</h4>
                <div class="ui right button" onclick="returnMainPage()">返回</div>
                <%}%>
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
                roomid: {
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