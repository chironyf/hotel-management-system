package servlet;
import entity.Customer;
import entity.Order;
import entity.TimeExtension;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

import tool.Query;
import tool.Query.*;

import static tool.Query.*;

@WebServlet(name = "ServiceManage")
public class ServiceManage extends HttpServlet {

    //  本servlet用于办理三种业务： 订房  退房  续费
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Map<String, String[]> map = request.getParameterMap() ;
//        String queryString ="" ;
//        String[] values =map.get("op") ; // op=1 /2 /3
//        int op = Integer.parseInt( values[0] ) ;
//        if(op==1){
//            response.sendRedirect("/roomOrder.jsp?op=1");
//        }else if(op==2) {
//            response.sendRedirect("/roomRenew.jsp?op=1");
//        }else if(op==3){
//            response.sendRedirect("/roomCheckOut.jsp?op=1");
//        }
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap() ;
        String queryString ="" ;
        String[] values =map.get("op") ; // op=1 /2 /3
        int op = Integer.parseInt( values[0] ) ;
        if(op==1) {
            String id = map.get("idcard")[0].toString() ;
            //用户登记  查询折扣
            //根据身份证查询用户是否存在
            Double discount ;

            if(isIDexists(id)){
                //老客户
                System.out.println(id+"存在,是老客户");
                discount= searchDiscount(id) ;
                System.out.println("折扣是"+discount);
            }else {
                //新客户
                System.out.println(id+"不存在,是新客户");

                StringTokenizer st=new StringTokenizer(map.get("birthdata")[0],"-");
                //添加客户
                Customer cu = null;
                try {
                    cu = new Customer(id,map.get("sex")[0],map.get("name")[0]
                            ,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("birthdata")[0]).getTime()),new Integer(1).toString(),map.get("phonenumber")[0],
                            0,"");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                addCustomer(cu) ;
                discount = searchDiscount(id) ;
                System.out.println("折扣是"+discount);
            }
            String url ="/roomOrder.jsp?op=3&discount="+discount.toString()+"&price="+getPrice(map.get("roomtype")[0])
                    +"&pay="+getPrice(map.get("roomtype")[0])*Integer.parseInt(map.get("time")[0])*discount;
            HashSet<String> set =new HashSet<String>() ;
            set.add("roomtype") ;
            set.add("name") ;
            set.add("sex") ;
            for(String key : map.keySet()){
                if(!key.equals("op")){
                    if(set.contains(key))
                        url = url+"&"+key+"="+ URLEncoder.encode(map.get(key)[0],"utf-8");
                    else
                        url = url+"&"+key+"="+map.get(key)[0];
                }
            }
            response.sendRedirect(url);
        }else if(op==2){
            //注销当前的登录用户  ;
            request.getSession().removeAttribute("hoteladmin");
            response.sendRedirect("/index.jsp");
        }
        else if(op==3){
            // 添加订单
            // 添加触发器 订单导致用户金额提高 续费订单 导致金额提升
            int num =getOrderNum()+1 ;
            String orderNumber = String.format("%03d", num) ;
            java.util.Date day=new java.util.Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String str = df.format(day);
            Date date= null;
            Date  date1= null;
            try {
                date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar =Calendar.getInstance();
            System.out.println(Integer.parseInt(map.get("time")[0]) );
            calendar.add(5, Integer.parseInt(map.get("time")[0]));

            java.util.Date date2 = calendar.getTime();
            date1 =new Date(date2.getTime()) ;
            System.out.println(date);
            System.out.println(date1);

            Order order =new Order(orderNumber,"已入住",map.get("idcard")[0],
                    map.get("roomid")[0],date,date1, (int)Double.parseDouble(map.get("pay")[0]) ,request.getSession().getAttribute("hoteladmin").toString(),
                    "",date) ;
            insertOrder(order);
            response.sendRedirect("/roomOrder.jsp?op=4") ;
        }
        else if(op==4){
            // 续费
            /**
             * 提交续费订单
             * 订单提交后要修改order表的退房日期 ;
             */
            TimeExtension renew = (TimeExtension) request.getSession().getAttribute("renew");
            //插入续费订单 ;
            Query.addRenew(renew) ;
            //修改相应的order日期 ;
            Date newdate = renew.getNewExpiryDate() ;
            updateNewDate(newdate,renew.getOrderNumber()) ;
            response.sendRedirect("/roomOrder.jsp?op=1") ;
        }
        else if(op==5){
            //退房
//            String orderNumber= map.get("orderNumber")[0] ;
//            System.out.println("订单:"+orderNumber);
//            checkOutRoom(orderNumber) ;
//            System.out.println("退房成功");
//            response.sendRedirect("/hotelAdmin.jsp") ;
        } else if (op == 10) {
            //注销当前管理员用户
            request.getSession().removeAttribute("systemadmin");
            response.sendRedirect("/index.jsp");

        }
    }
}
