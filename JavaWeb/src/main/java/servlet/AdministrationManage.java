package servlet;
import entity.Waiter;
import tool.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
@WebServlet(name = "AdministrationManage")
public class AdministrationManage extends HttpServlet{
    //  本servlet用于办理三种业务：新增服务员  编辑服务员  删除服务员
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap() ;
        String queryString ="" ;
        String[] values =map.get("mop") ; // mop=4/5/6
        int mop = Integer.parseInt( values[0] ) ;
        if(mop==4){
            response.sendRedirect("/systemManagement/waiterAdd.jsp?mop=4");
            Waiter waiter = new Waiter();
//            var url = "&waiterID=" + waiterID +
//                    "&waiterName="+waiterName+
//                    "&waiterBirthday="+waiterBirthday+
//                    "&waiterIDCard="+waiterIDCard+
//                    "&waiterPassword="+waiterPassword+
//                    "&waiterJoinDate="+waiterJoinDate+
//                    "&waiterPhoneNumber="+waiterPhoneNumber+
//                    "&remarks="+remarks

            waiter.setWaiterID(request.getParameter("waiterID"));
            waiter.setWaiterName(request.getParameter("waiterName"));
            waiter.setWaiterID(request.getParameter("waiterBirthday"));
            waiter.setWaiterID(request.getParameter("waiterIDCard"));
            waiter.setWaiterID(request.getParameter("waiterPassword"));
            waiter.setWaiterID(request.getParameter("waiterJoinDate"));
            waiter.setWaiterID(request.getParameter("waiterPhoneNumber"));
            waiter.setWaiterID(request.getParameter("remarks"));
            System.out.println("---------------------------" +waiter);
            Query.insertWaiter(waiter);
        }else if(mop==5) {
            response.sendRedirect("/systemManagement/waiterEdit.jsp?mop=5");
        }else if(mop==6){
            response.sendRedirect("/systemManagement/waiterUpdate.jsp?mop=6");
        }else if(mop==7){
            response.sendRedirect("/systemManagement/waiterShow.jsp?mop=7");
        } else if (mop == 10) {//业务数据统计
            response.sendRedirect("/systemManagement/statistics.jsp?mop=10");
        }

    }
}
