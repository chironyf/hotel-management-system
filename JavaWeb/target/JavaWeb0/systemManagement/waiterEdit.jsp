<%@ page import="entity.*" %>
<%@ page import="static tool.Query.getAllRooms" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="static tool.Query.searchEmptyRooms" %>
<%@ page import="tool.Query" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%--%>
<%--Map<String, String[]> map =request.getParameterMap() ;--%>
<%--int mop = Integer.parseInt(map.get("mop")[0]) ; //通过mop选项来控制页面显示的内容--%>

<%--%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>宾馆管理系统</title>
    <link rel="stylesheet" type="text/css" href="/semantic/dist/semantic.min.css">
    <script src="/semantic/dist/jquery.min.js"></script>
    <script src="/semantic/dist/semantic.js"></script>
    <script >

        function sub() {

            alert("提交成功,返回显示服务员列表页!")
            window.location.href="/systemManagement/waiterShow.jsp"
        }

        function edit(waiterid) {
            window.location.href="/systemManagement/waiterUpdate.jsp?mop=4&waiterID="+waiterid;
        }

        function del(waiterid) {
            var f=confirm("是否确定删除？");
            if(f){

                window.location.href="/systemManagement/waiterShow.jsp?mop=7&waiterID="+waiterid;
            }else{
                alert("您取消删除");
            }

        }

    </script>

</head>
<%@include file="/systemAdmin.jsp"%>
<body>

<%ArrayList<Waiter> waiters = Query.getAllWaiters();%>
<div class="pusher">
    <div class="ui container">

        <h2 class="ui header">编辑服务员信息</h2>
        <table class="ui selectable celled table">
            <thead>
            <tr class="center aligned"><th name="waiterID">工号</th>
                <th name="waiterName">姓名</th>
                <th name="waiterBirthday">生日</th>
                <th name="waiterIDCard">身份证号</th>
                <th name="waiterPassword">密码</th>
                <th name="waiterJoinDate">入职时间</th>
                <th name="waiterPhoneNumber">手机号码</th>
                <th name="remarks">备注</th>
                <th></th>
                <th></th>
            </tr></thead>
            <tbody>
            <%for (int i=0;i<waiters.size();i++) {%>
            <tr class="center aligned">
                <td>
                    <%=(waiters.get(i).getWaiterID())%>
                </td>
                <td>
                    <%out.print(waiters.get(i).getWaiterName());%>
                </td>
                <td>
                    <% out.print(waiters.get(i).getWaiterBirthday().toString());%>
                </td>
                <td>
                    <%out.print(waiters.get(i).getWaiterIDCard());%>
                </td>
                <td>
                    <%out.print(waiters.get(i).getWaiterPassword());%>
                </td>
                <td>
                    <%out.print(waiters.get(i).getWaiterJoinDate().toString());%>
                </td>
                <td>
                    <%out.print(waiters.get(i).getWaiterPhoneNumber());%>
                </td>
                <td>
                    <%out.print(waiters.get(i).getRemarks());%>
                </td>

                <td>
                    <div class="ui button" tabindex="0"  onclick="edit('<%=(waiters.get(i).getWaiterID())%>')">编辑</div>
                </td>
                <td>
                    <div class="ui button" tabindex="0" onclick="del('<%=(waiters.get(i).getWaiterID())%>')">删除</div>
                </td>
            </tr>
            <%}%>

            </tbody>
            <tfoot>
            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
