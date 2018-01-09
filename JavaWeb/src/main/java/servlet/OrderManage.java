package servlet;
import config.GCON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "OrderManage")
public class OrderManage extends HttpServlet {
    //  此servlet用于 管理订单
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        String[] values =map.get("op");
        if (values[0].equals("1")) {
            response.sendRedirect("/orderManagement/bookedOrder.jsp?op=1&pageIndex=0");
        } else if (values[0].equals("2")) {
            response.sendRedirect("/orderManagement/checkInOrder.jsp?op=2&pageIndex=0");
        } else if (values[0].equals("3")) {
            response.sendRedirect("/orderManagement/extensionOrder.jsp?op=3&pageIndex=0");
        } else if (values[0].equals("4")) {
            response.sendRedirect("/orderManagement/historyOrder.jsp?op=4&pageIndex=0");
        } else if (values[0].equals("5")) {
            response.sendRedirect("/orderManagement/allOrder.jsp?op=5&pageIndex=0");
        }
    }
}
