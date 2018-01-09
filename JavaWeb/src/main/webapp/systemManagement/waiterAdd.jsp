<%@ page import="entity.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="tool.Query.*" %>
<%@ page import="tool.Query" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="static tool.Query.getWaiter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/25
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%

    Map<String, String[]> map =request.getParameterMap() ;
    int mop = Integer.parseInt(map.get("mop")[0]) ; //通过mop选项来控制页面显示的内容

    Waiter waiter= null ;
    if(mop==4 &&  map.get("waiterID")!=null){
        String waiterid =map.get("waiterID")[0] ;
        waiter =getWaiter(waiterid)   ;// 根据waiterid来构造waiter ;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>宾馆管理系统</title>
    <link rel="stylesheet" type="text/css" href="/semantic/dist/semantic.min.css">
    <script src="/semantic/dist/jquery.min.js"></script>
    <script src="/semantic/dist/semantic.js"></script>
    <script>
        function sub1() {
            var waiterID = document.getElementById("waiterID").value
            var waiterName = document.getElementById("waiterName").value
            var waiterIDCard = document.getElementById("waiterIDCard").value;
            var waiterBirthday = waiterIDCard.substring(6, 10) + '-' + waiterIDCard.substring(10, 12) + '-' + waiterIDCard.substring(12, 14);
            var waiterPassword = document.getElementById("waiterPassword").value
            var waiterJoinDate = document.getElementById("waiterJoinDate").value
            var waiterPhoneNumber = document.getElementById("waiterPhoneNumber").value
            var remarks = document.getElementById("remarks").value;
            var url = "waiterID=" + waiterID +
                "&waiterName="+waiterName+
                "&waiterBirthday="+waiterBirthday+
                "&waiterIDCard="+waiterIDCard+
                "&waiterPassword="+waiterPassword+
                "&waiterJoinDate="+waiterJoinDate+
                "&waiterPhoneNumber="+waiterPhoneNumber+
                "&remarks="+remarks;
            if(/^[a-z0-9A-Z]{1,10}$/.test(waiterID)
                && /^[0-9]{17}[0-9|X]$/.test(waiterIDCard)
                && /^[a-z0-9A-Z]{1,18}$/.test(waiterPassword)
                && /^1[3|4|5|8][0-9]\d{4,8}$/.test(waiterPhoneNumber)
            ){
                window.location.href="/systemManagement/waiterAdd.jsp?mop=5&" +url;
            }
            return false ;
        }

        function ensure() {
            var urln = window.location.href.split("&")[1] + "&" +
                window.location.href.split("&")[2] + "&" +
                window.location.href.split("&")[3] + "&" +
                window.location.href.split("&")[4] + "&" +
                window.location.href.split("&")[5] + "&" +
                window.location.href.split("&")[6] + "&" +
                window.location.href.split("&")[7] + "&" +
                window.location.href.split("&")[8];
            window.location.href="/systemManagement/waiterAdd.jsp?mop=6&" +urln;
        }

        function returnm() {
            window.location.href="/systemManagement/waiterAdd.jsp?mop=4";
        }

    </script>
</head>
<%@include file="/systemAdmin.jsp"%>
<body>


<div class="pusher">

    <div class="ui container">
        <div class="ui column grid">
            <div class="four wide column">
                <div class="ui vertical steps">

                    <div class="<%=(mop<=4)?"active step ":"completed step"%>" >
                        <i class="add user icon"></i>
                        <div class="content">
                            <div class="title">新增信息填写</div>
                            <%--<div class="description">Choose your shipping options</div>--%>
                        </div>
                    </div>

                    <div class="<%=(mop>=5)?"active step ":"step"%>">
                        <i class="adjust icon"></i>
                        <div class="content">
                            <div class="title">新增信息确认</div>
                            <%--<div class="description">Enter billing information</div>--%>
                        </div>
                    </div>

                    <div class="<%=(mop==6)?"active step ":"step"%>">
                        <i class="minus icon"></i>
                        <div class="content">
                            <div class="title">新增信息提交</div>
                            <%--<div class="description">Verify order details</div>--%>
                        </div>
                    </div>
                </div>
            </div>

            <div class="eleven wide column">
                <%if (mop == 4) {%>

                <form class="ui form" onsubmit="return sub1(this)">
                    <div class="two fields">
                        <div class="field">
                            <label>工号</label>
                            <input type="text" id="waiterID" name="waiterID" value="<%=(waiter==null)?"":waiter.getWaiterID()%>" placeholder="工号">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>姓名</label>
                            <input type="text" id="waiterName" name="waiterName" value="<%=(waiter==null)?"":waiter.getWaiterName()%>" placeholder="姓名">
                        </div>
                    </div>
                    <%--<div class="four fields">--%>
                    <%--<div class="six wide field">--%>
                    <%--<label>出生日期</label>--%>
                    <%--&lt;%&ndash;<input type="text" name="card[cvc]" maxlength="3" placeholder="出生日期">&ndash;%&gt;--%>
                    <%--<input type="date" value="2018-01-01" value="<%=(waiter==null)?"":waiter.getWaiterBirthday()%>"  id="waiterBirthday"/>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="two fields">
                        <div class=" field">
                            <label>身份证</label>
                            <input type="text" id="waiterIDCard" name="waiterIDCard"value="<%=(waiter==null)?"":waiter.getWaiterIDCard()%>"  placeholder="身份证">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>密码</label>
                            <input type="text" id="waiterPassword" name="waiterPassword" value="<%=(waiter==null)?"":waiter.getWaiterPassword()%>"  placeholder="密码">
                        </div>
                    </div>
                    <div class="four fields">
                        <div class="six wide field">
                            <label>加入日期</label>
                            <%--<input type="text" name="card[cvc]" maxlength="3" placeholder="加入日期">--%>
                            <input type="date" value="2018-01-01" value="<%=(waiter==null)?"":waiter.getWaiterJoinDate()%>" id="waiterJoinDate"/>


                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>手机号</label>
                            <input type="text" id="waiterPhoneNumber"  name="waiterPhoneNumber" value="<%=(waiter==null)?"":waiter.getWaiterPhoneNumber()%>" placeholder="手机号">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>备注</label>
                            <input type="text" id="remarks" name="last-name" value="<%=(waiter==null)?"":waiter.getRemarks()%>" placeholder="备注">
                        </div>
                    </div>
                    <div class="ui right submit  floated button" tabindex="0" >提交</div>
                </form>
                <%} else if (mop == 5) {%>

                <h2 class="ui dividing header">待添加员工信息确认</h2>
                <form class="ui form">
                    <table class="ui table">
                        <%--<thead>--%>
                        <%--<tr>--%>
                        <%--<th class="six wide">工号</th>--%>
                        <%--</tr></thead>--%>
                        <tbody>
                        <tr>
                            <td>工号</td>
                            <td><%=request.getParameter("waiterID")%></td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td><%=request.getParameter("waiterName")%></td>
                        </tr>
                        <tr>
                            <td>出生日期</td>
                            <td><%=request.getParameter("waiterBirthday")%></td>
                        </tr>
                        <tr>
                            <td>身份证</td>
                            <td><%=request.getParameter("waiterIDCard")%></td>
                        </tr>

                        <tr>
                            <td>密码</td>
                            <td><%=request.getParameter("waiterPassword")%></td>
                        </tr>
                        <tr>
                            <td>加入日期</td>
                            <td><%=request.getParameter("waiterJoinDate")%></td>
                        </tr>
                        <tr>
                            <td>手机号</td>
                            <td><%=request.getParameter("waiterPhoneNumber")%></td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td><%=request.getParameter("remarks")%></td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="ui button" onclick="ensure()">确认</div>
                </form>

                <%} else if (mop == 6) {
                    waiter = new Waiter();
                    waiter.setWaiterID(request.getParameter("waiterID"));
                    waiter.setWaiterName(request.getParameter("waiterName"));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = format.parse(request.getParameter("waiterBirthday").toString());

                    Date sDate = new Date(date.getTime());
                    waiter.setWaiterBirthday(sDate);
                    waiter.setWaiterIDCard(request.getParameter("waiterIDCard"));
                    waiter.setWaiterPassword(request.getParameter("waiterPassword"));
                    java.util.Date Jdate = format.parse(request.getParameter("waiterJoinDate").toString());
                    Date jDate = new Date(Jdate.getTime());
                    waiter.setWaiterJoinDate(jDate);
                    waiter.setWaiterPhoneNumber(request.getParameter("waiterPhoneNumber"));
                    waiter.setRemarks(request.getParameter("remarks"));
                    Query.insertWaiter(waiter);

                %>
                <h2 class="ui diving heade">添加成功</h2>

                <div class="ui right button" onclick="returnm()">返回</div>
                <%}%>

            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
        $('.ui.form').form({
                //  if(/^[a-z0-9A-Z]{1,10}$/.test(waiterID)
                //                && /^[0-9]{17}[0-9|X]$/.test(waiterIDCard)
                //                && /^[a-z0-9A-Z]{1,18}$/.test(waiterPassword)
                //                && /^1[3|4|5|8][0-9]\d{4,8}$/.test(waiterPhoneNumber)
                //            )
                waiterID: {
                    identifier: 'waiterID',
                    rules: [
                        {
                            type: 'regExp[/^[a-z0-9A-Z]{1,10}$/]',
                            prompt: 'ID不符合规范'
                        }
                    ]
                }
                ,waiterIDCard: {
                    identifier: 'waiterIDCard',
                    rules: [
                        {
                            type: 'regExp[/^[0-9]{17}[0-9|X]$/]',
                            prompt: '身份证号不符合规范'
                        }
                    ]
                },waiterPassword: {
                    identifier: 'waiterPassword',
                    rules: [
                        {
                            type: 'regExp[/^[a-z0-9A-Z]{1,18}$/]',
                            prompt: '密码不符合规范'
                        }
                    ]
                }

                ,waiterPhoneNumber: {
                    identifier: 'waiterPhoneNumber',
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
