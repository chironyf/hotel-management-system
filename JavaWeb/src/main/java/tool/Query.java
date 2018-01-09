package tool;

import config.GCON;
import display.ExtensionOrderView;
import display.OrderView;
import display.InComeView;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//数据库查询功能
public class Query {

    //房间指定型号对应的空和非空房间数量
    public static ArrayList<Integer> getNumofRoom(String roomType){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer>list =new ArrayList<Integer>() ;
        try{
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement(
                    "select * from  (select roomType,roomStatus ,count(*) from room group by roomType,roomStatus) c where c.roomStatus='空' and c.roomType='"+roomType+"'");
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add( resultSet.getInt("count(*)")) ;
            }
            preparedStatement = connection.prepareStatement(
                    "select * from  (select roomType,roomStatus ,count(*) from room group by roomType,roomStatus) c where c.roomStatus='非空' and c.roomType='"+roomType+"'");
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add( resultSet.getInt("count(*)")) ;
            }

            return list ;
        }catch(Exception exception) {
            exception.printStackTrace();
        }
        return null ;
    }

    //获得已入住的orderview
    public static OrderView getFullOrderViews(String roomid) {
        ArrayList<OrderView> fullOrderViews = new ArrayList<OrderView>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();

            preparedStatement = connection.prepareStatement("select * from orderviews where roomNumber='"+roomid+"' and orderStatus='已入住'");
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderView orderView = new OrderView();
                orderView.setOrderNumder(resultSet.getString("orderNumber"));
                orderView.setCustomer(resultSet.getString("customerName"));
                orderView.setRoomNumber(resultSet.getString("roomNumber"));
                orderView.setRoomType(resultSet.getString("roomType"));
                orderView.setOrderTime(resultSet.getDate("orderTime"));
                orderView.setCheckInTime(resultSet.getDate("checkInTime"));
                orderView.setCheckOutTime(resultSet.getDate("checkOutTime"));
                int days = (int)((orderView.getCheckOutTime().getTime() - orderView.getCheckInTime().getTime())/1000/60/60/24/1);
                orderView.setDays(days);
                orderView.setCustomerPhoneNumber(resultSet.getString("customerPhoneNumber"));
                orderView.setPrice(resultSet.getInt("totalMoney"));
                orderView.setOrderStatus(resultSet.getString("orderStatus"));
                return orderView;

            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
    //删除waiter
    public static void deleteWaiter(Waiter waiter) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection =  DataBase.getConnection();
            statement = connection.createStatement();
            statement.execute("DELETE  FROM waiter where waiterID='" + waiter.getWaiterID() + "'");

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    //更新waiter
    public static void editWaiter(Waiter waiter) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection =  DataBase.getConnection();
            statement = connection.createStatement();
            statement.execute("UPDATE waiter SET waiterIDCard='" + waiter.getWaiterIDCard() + "' where waiterID ='"+waiter.getWaiterID() +"'");
            statement.execute("UPDATE waiter SET waiterName='" + waiter.getWaiterName() + "' where waiterID ='"+waiter .getWaiterID() +"'");
            statement.execute("UPDATE waiter SET waiterBirthday='" + waiter.getWaiterBirthday() + "' where waiterID ='"+waiter.getWaiterID() +"'");
            statement.execute("UPDATE waiter SET waiterPassword='" + waiter.getWaiterPassword() + "' where waiterID ='"+waiter.getWaiterID() +"'");
            statement.execute("UPDATE waiter SET waiterJoinDate='" + waiter.getWaiterJoinDate() + "' where waiterID ='"+waiter.getWaiterID() +"'");
            statement.execute("UPDATE waiter SET waiterPhoneNumber='" + waiter.getWaiterPhoneNumber() + "'where waiterID ='"+waiter.getWaiterID() +"'");
            statement.execute("UPDATE waiter SET remarks='" + waiter.getRemarks() + "'where waiterID ='"+waiter.getWaiterID() +"'");

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    //使用incomeView
    public static ArrayList<InComeView> getInComeView(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Waiter waiter =null;
        ArrayList<InComeView>inComeViews =new ArrayList<InComeView>();
        try {
            connection =  DataBase.getConnection();
            String sql = "select * from incomeView";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                inComeViews.add(
           new InComeView(resultSet.getDate("co").toString() ,resultSet.getInt("tot"),
                   resultSet.getInt("num"))
                );
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return inComeViews;
    }

    //根据waiterid 来返回waiter
    public  static Waiter getWaiter(String waiterID){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Waiter waiter =null ;

        try {
            connection =  DataBase.getConnection();
            String sql ="select * from waiter where waiterID='"+waiterID+"'" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                waiter =new Waiter(resultSet.getString("waiterID")
                ,resultSet.getString("waiterName")
                ,resultSet.getDate("waiterBirthday")
                ,resultSet.getString("waiterIDCard")
                ,resultSet.getString("waiterPassword")
                ,resultSet.getDate("waiterJoinDate")
                ,resultSet.getString("waiterPhoneNumber")
                ,resultSet.getString("remarks")) ;
                return waiter ;
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return null ;
    }

    //根据房间的型号查询房间的相关信息
    public  static HashMap<String,RoomTypeAndPrice> getRoomTypeMap(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<String,RoomTypeAndPrice> map =new HashMap<String, RoomTypeAndPrice>() ;
        try {
            connection =  DataBase.getConnection();
            String sql ="select * from roomtypeandprice " ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               map.put(resultSet.getString("roomType") ,
                       new  RoomTypeAndPrice(resultSet.getString("roomType")
                        ,Integer.parseInt(resultSet.getString("price")),
                        resultSet.getString("desc"),
                        resultSet.getString("url")) );

            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return map ;
    }

    //查询所有房间的信息
    public static  ArrayList<Room> getAllRoomsInfo(String s ,String search) {

        ArrayList<Room>Rooms = new ArrayList<Room>() ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql  ;
            if(!search.equals("")){
                sql ="select * from room where roomNumber like '%"+search+"%'" ;

            }else if(s.equals("")||s.equals("任意"))
                sql="select * from room " ;
            else
                sql="select * from room where roomStatus='"+s+"'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rooms.add(new Room(
                        resultSet.getString("roomNumber"),
                        resultSet.getString("roomType"),
                        resultSet.getString("roomStatus"),
                        resultSet.getString("remarks")));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return Rooms ;
    }


    //*****************lx****************
    public static  ArrayList<String> searchFullRooms() {

        ArrayList<String>fullRooms =new ArrayList<String>() ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select roomNumber from room where roomStatus='非空' " ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fullRooms.add(resultSet.getString("roomNumber"));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return fullRooms ;
    }

    public static  ArrayList<String> searchEmptyRooms(String roomtype) {
        ArrayList<String>emptyRooms =new ArrayList<String>() ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select roomNumber from room where roomStatus='空' and roomType='"+roomtype+"'" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emptyRooms.add(resultSet.getString("roomNumber"));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return emptyRooms ;
    }
    //查询所有房间信息
    public static  ArrayList<RoomTypeAndPrice> getAllRooms(){
        ArrayList<RoomTypeAndPrice> allRooms =new ArrayList<RoomTypeAndPrice>() ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement(GCON.SQL_ALL_ROOMS);
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoomTypeAndPrice roomTypeAndPrice= new RoomTypeAndPrice(
                        resultSet.getString("roomtype"),
                        resultSet.getInt("price"),
                        resultSet.getString("desc"),
                        resultSet.getString("url")
                );
                allRooms.add(roomTypeAndPrice);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return allRooms ;
    }
    //查询所有订单信息
    public static ArrayList<Order> getAllOrders() {
        ArrayList<Order> allOrders = new ArrayList<Order>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement(GCON.SQL_ALL_ORDERS);
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();
            //获取数据库中订单信息
            while (resultSet.next()) {
//                    public Order(String orderNumber,
//                        String orderStatus,
//                        String customerIDCard,
//                        String roomNumber,
//                        Date checkInTime,
//                        Date checkOutTime,
//                        int totalMoney,
//                        String remarks)
                Order orderItem = new Order(resultSet.getString("orderNumber"),
                        resultSet.getString("orderStatus"),
                        resultSet.getString("customerIDCard"),
                        resultSet.getString("roomNumber"),
                        resultSet.getDate("checkInTime"),
                        resultSet.getDate("checkOutTime"),
                        resultSet.getInt("totalMoney"),
                        resultSet.getString("waiterID"),
                        resultSet.getString("remarks"),
                        resultSet.getDate("orderTime"));
                allOrders.add(orderItem);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
            //关闭数据集

        }
        return allOrders;
    }


    //查询系统管理员信息
    public static ArrayList<SystemAdministrator> getAllSystemAdmin() {
        ArrayList<SystemAdministrator> allSystemAdmins = new ArrayList<SystemAdministrator>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();
            if (connection != null) {
                System.out.println("查询---数据库连接成功");
            }

            preparedStatement = connection.prepareStatement(GCON.SQL_ALL_ADMINS);

            //获取结果数据集
            resultSet = preparedStatement.executeQuery();
            //获取数据库中订单信息
            int i = 0;
            while (resultSet.next()) {
                SystemAdministrator systemAdministrator = new SystemAdministrator(resultSet.getString("userID"),
                        resultSet.getString("userName"), resultSet.getString("userPassword"));
                allSystemAdmins.add(systemAdministrator);
                System.out.println("userID" + resultSet.getString("userID") + "\n" +
                        resultSet.getString("userName") + "\n" +
                        "userPassword" + resultSet.getString("userPassword"));
                i++;
            }
            System.out.println("i = " + i);

        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
            //关闭数据集

        }
        return allSystemAdmins;
    }

    //新建管理员（测试）
    public static void insertSystemAdministrator(SystemAdministrator systemAdministrator) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection =  DataBase.getConnection();
            if (connection != null) {
                System.out.println("插入---数据库连接成功");
            }
            statement = connection.createStatement();
//            preparedStatement = connection.prepareStatement();

            statement.execute("INSERT INTO systemAdministrator VALUES('" +
                    systemAdministrator.getUserID() + "', '" +
                    systemAdministrator.getUserName() + "', '" +
                    systemAdministrator.getUserPassword() + "');");
        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
            //关闭语句

        }
    }

    //查询服务员
    public static ArrayList<Waiter> getAllWaiters() {
        ArrayList<Waiter> allWaiters = new ArrayList<Waiter>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();
            if (connection != null) {
                System.out.println("查询---数据库连接成功");
            }

            preparedStatement = connection.prepareStatement(GCON.SQL_ALL_WAITERS);

            //获取结果数据集
            resultSet = preparedStatement.executeQuery();
            //获取数据库中订单信息
            while (resultSet.next()) {
//                    public Waiter(String waiterID,
//                        String waiterName,
//                        Date waiterBirthday,
//                        String waiterIDCard,
//                        String waiterPassword,
//                        Date waiterJoinDate
//                        String waiterPhoneNumber,
//                        String remarks) {
                Waiter waiter = new Waiter(resultSet.getString("waiterID"),
                        resultSet.getString("waiterName"),
                        resultSet.getDate("waiterBirthday"),
                        resultSet.getString("waiterIDCard"),
                        resultSet.getString("waiterPassword"),
                        resultSet.getDate("waiterJoinDate"),
                        resultSet.getString("waiterPhoneNumber"),
                        resultSet.getString("remarks"));
                allWaiters.add(waiter);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return allWaiters;
    }


    //得到页面显示的预定订单
    public static ArrayList<OrderView> getAllOrderViews(String orderStatus) {
        ArrayList<OrderView> allOrderViews = new ArrayList<OrderView>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();
            if (orderStatus.equals("")) {
                preparedStatement = connection.prepareStatement("select * from orderviews");
            } else {
                preparedStatement = connection.prepareStatement("select * from orderviews where orderStatus = '" + orderStatus + "'");
            }

            //获取结果数据集
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderView orderView = new OrderView();
                orderView.setOrderNumder(resultSet.getString("orderNumber"));
                orderView.setCustomer(resultSet.getString("customerName"));
                orderView.setRoomNumber(resultSet.getString("roomNumber"));
                orderView.setRoomType(resultSet.getString("roomType"));
                orderView.setOrderTime(resultSet.getDate("orderTime"));
                orderView.setCheckInTime(resultSet.getDate("checkInTime"));
                orderView.setCheckOutTime(resultSet.getDate("checkOutTime"));

                int days = (int)((orderView.getCheckOutTime().getTime() - orderView.getCheckInTime().getTime())/1000/60/60/24);

                orderView.setDays(days);

                orderView.setCustomerPhoneNumber(resultSet.getString("customerPhoneNumber"));
                orderView.setPrice(resultSet.getInt("totalMoney"));
                orderView.setOrderStatus(resultSet.getString("orderStatus"));
                allOrderViews.add(orderView);
            }
            return allOrderViews;
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return allOrderViews;
    }

    //添加新的房间
    public static void insertRoom(Room room) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection =  DataBase.getConnection();
            statement = connection.createStatement();
            statement.execute("INSERT INTO room VALUES('" +
                    room.getRoomNumber() + "', '" +
                    room.getRoomType() + "', '" +
                    room.getRoomStatus() + "', '" +
                    room.getRemarks() + "');");
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public static ArrayList<RoomTypeAndPrice> searchRoom(String s1 ,String s2,String s3){
        ArrayList<RoomTypeAndPrice> allRooms =new ArrayList<RoomTypeAndPrice>() ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String[]arr = s3.split("-") ;
        if(s1.equals("任意"))
            s1="" ;
        if(s2.equals("任意"))
            s2="" ;
        if(s3.equals("任意"))
            s3="" ;
        try {
            connection =  DataBase.getConnection();
            if(!s3.equals(""))
                preparedStatement = connection.prepareStatement(
                        "select * from roomtypeandprice where roomType like '%"+s1+"%' and roomType like '%"+s2+
                                "%' and  price BETWEEN "+arr[0]+" and "+arr[1]
                );
            else
                preparedStatement = connection.prepareStatement(
                        "select * from roomtypeandprice where roomType like '%"+s1+"%' and roomType like '%"+s2+
                                "%'"
                );
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoomTypeAndPrice roomTypeAndPrice= new RoomTypeAndPrice(
                        resultSet.getString("roomtype"),
                        resultSet.getInt("price"),
                        resultSet.getString("desc"),
                        resultSet.getString("url")
                ) ;
                allRooms.add(roomTypeAndPrice);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return allRooms ;
    }

    public static int getRenewNum(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select count(*) from timeextension " ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("count(*)");

            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return 1 ;
    }

    public static void  updateNewDate(Date newdate ,String orderNumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement(
                    "update orders set checkOutTime='"+newdate+"' where orderNumber='"+orderNumber+"'");
            preparedStatement.execute();
        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
        }
    }


    public static void  addRenew(TimeExtension renew){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement("insert into timeextension values("
                    +renew.getOperatingID()+",'"+renew.getOrderNumber()+"','"+renew.getOldExpiryDate()+"','"
                    +renew.getNewExpiryDate()+"',"+renew.getAddedMoney()+")");
            preparedStatement.execute();


            System.out.println("---++++++++++续费订单插入");
        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
        }
    }

    public static double getRoomPrice(String roomid){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select roomType from room where roomNumber='"+roomid+"'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return getPrice(resultSet.getString("roomType")) ;
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    public static void checkOutRoom(String orderNumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="update orders set orderStatus='已退房' where orderNumber='"+orderNumber+"'" ;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch(Exception exception) {
            exception.printStackTrace();
        }

    }

    public static Order getOrder(String roomid){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select * from orders where roomNumber='"+roomid+"' and orderStatus='已入住'" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Order( resultSet.getString("orderNumber"),
                        resultSet.getString("orderStatus")
                        ,resultSet.getString("customerIDCard")
                        ,resultSet.getString("roomNumber")
                        ,resultSet.getDate("checkInTime")
                        ,resultSet.getDate("checkOutTime")
                        ,resultSet.getInt("totalMoney")
                        ,resultSet.getString("waiterID")
                        ,resultSet.getString("remarks")
                        ,resultSet.getDate("orderTime")) ;
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return null ;
    }

    public static int getOrderNum(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select count(*) from orders " ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("count(*)");

            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return 1 ;
    }


    public static double getPrice(String type ){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBase.getConnection();
            String sql ="select price from roomtypeandprice where roomType='"+type+"'" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getDouble("price");
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return 1.00 ;
    }

    public static void  addCustomer(Customer cu){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Customers VALUES('" +
                    cu.getCustomerIDCard()+"','"+cu.getCustomerGender()+"','"+cu.getCustomerName()+"','"+cu.getCustomerBirthday()
                    +"',"+Integer.parseInt(cu.getCustomerVIPLevel())+",'"+cu.getCustomerPhoneNumber()+"',"+cu.getTotalAmount()
                    +",'"+cu.getRemarks()+ "');");
            preparedStatement.execute();
        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
        }
    }

    public static double searchDiscount(String id ){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int level =searchVIPlevel(id) ; //该用户的会员等级
        try {
            connection =  DataBase.getConnection();
            String sql ="select discount from viplevel where level="+level ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getDouble("discount") ;
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return 1.00 ;
    }

    public static int searchVIPlevel(String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select customerVIPLevel from customers where customerIDCard=' "+id+"'" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int level = resultSet.getInt("customerVIPLevel") ;
                return level ;
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return 1 ;
    }

    public static  boolean isIDexists(String id ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            String sql ="select * from Customers where customerIDCard='"+id+"'" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true ;
            }
            return false ;
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return false ;
    }


    //插入一条订单信息
    public static void insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES('" +
                    order.getOrderNumber() + "','" +
                    order.getOrderStatus() + "','" +
                    order.getCustomerIDCard() + "','" +
                    order.getRoomNumber() + "','" +
                    order.getCheckInTime() + "','" +
                    order.getCheckOutTime() + "'," +
                    order.getTotalMoney() + ",'" +
                    order.getWaiterID()+"','"+
                    order.getRemarks() + "','"+
                    order.getOrderTime()+
                    "')");

            preparedStatement.execute();

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    //查询续费订单(timeextension tt, orders od, customers ct)
    public static ArrayList<ExtensionOrderView> getAllTimeExtensionOrders() {
        ArrayList<ExtensionOrderView> allTimeExtensionOrders = new ArrayList<ExtensionOrderView>() ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  DataBase.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM timeExtensionOrdersView");
            //获取结果数据集
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ExtensionOrderView extensionOrderView = new ExtensionOrderView();
                extensionOrderView.setOrderNumber(resultSet.getString("orderNumber"));
                extensionOrderView.setCustomer(resultSet.getString("customerName"));
                extensionOrderView.setRoomNumber(resultSet.getString("roomNumber"));
                extensionOrderView.setOldDate(resultSet.getDate("oldExpiryDate"));
                extensionOrderView.setNewDate(resultSet.getDate("newExpiryDate"));
                extensionOrderView.setAddedMoney(resultSet.getInt("addedMoney"));
                extensionOrderView.setCustomerPhoneNumber(resultSet.getString("customerPhoneNumber"));
                extensionOrderView.setCheckInTime(resultSet.getDate("checkInTime"));
                allTimeExtensionOrders.add(extensionOrderView);
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return allTimeExtensionOrders;
    }
    public static void insertWaiter(Waiter waiter) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection =  DataBase.getConnection();
            statement = connection.createStatement();
            statement.execute("INSERT INTO waiter VALUES('" +
                    waiter.getWaiterID() + "', '" +
                    waiter.getWaiterName() + "', '" +
                    waiter.getWaiterBirthday() + "', '" +
                    waiter.getWaiterIDCard() + "', '" +
                    waiter.getWaiterPassword() + "', '" +
                    waiter.getWaiterJoinDate() + "', '" +
                    waiter.getWaiterPhoneNumber() + "', '" +
                    waiter.getRemarks() + "');");

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
    //添加新的房型
    public static void inserRoomType(RoomTypeAndPrice roomTypeAndPrice) {

    }

    //数据库测试
    public static void main(String[] args) {
        ArrayList<Waiter> allWaiters = Query.getAllWaiters();

        StringBuilder jsonWaiterValues = new StringBuilder("");

        StringBuilder jsonData = new StringBuilder("");

            for (int i = 0; i < allWaiters.size(); i++) {
                Waiter w = allWaiters.get(i);
//                jsonWaiterValues.append("{\"name\": \"姓名\", \"value\": \"" + w.getWaiterName() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"WaiterID\", \"value\": \"" + w.getWaiterID() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"系统密码\", \"value\": \"" + w.getWaiterPassword() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"手机号码\", \"value\": \"" + w.getWaiterPhoneNumber() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"身份证号码\", \"value\": \"" + w.getWaiterIDCard() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"生日\", \"value\": \"" + w.getWaiterBirthday().toString() + "\"}\n");
//                jsonData.append("{\n\"name\" : \"" + w.getWaiterName() + "\",\n" +
//                            "\"children\" : [\n" + jsonWaiterValues.toString() + "]\n}");
                jsonWaiterValues.append("{\"name\": \"" + w.getWaiterName() + "\", \"value\" :\"姓名\"},\n");
                jsonWaiterValues.append("{\"name\": \"" + w.getWaiterID() + "\", \"value\" :\"WaiterID\"},\n");
                jsonWaiterValues.append("{\"name\": \"" + w.getWaiterPassword() + "\", \"value\" :\"系统密码\"},\n");
                jsonWaiterValues.append("{\"name\": \"" + w.getWaiterPhoneNumber() + "\", \"value\" :\"手机号码\"},\n");
                jsonWaiterValues.append("{\"name\": \"" + w.getWaiterIDCard() + "\", \"value\" :\"身份证号码\"},\n");
                jsonWaiterValues.append("{\"name\": \"" + w.getWaiterBirthday().toString() + "\", \"value\" :\"生日\"},\n");
//                jsonWaiterValues.append("{\"name\": \"WaiterID\", \"value\": \"" + w.getWaiterID() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"系统密码\", \"value\": \"" + w.getWaiterPassword() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"手机号码\", \"value\": \"" + w.getWaiterPhoneNumber() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"身份证号码\", \"value\": \"" + w.getWaiterIDCard() + "\"},\n");
//                jsonWaiterValues.append("{\"name\": \"生日\", \"value\": \"" + w.getWaiterBirthday().toString() + "\"}\n");
                jsonData.append("{\n\"name\" : \"" + w.getWaiterName() + "\",\n" +
                        "\"children\" : [\n" + jsonWaiterValues.toString() + "]\n}");

                if (i != allWaiters.size() - 1) {
                    jsonData.append(",\n");
                }
                jsonWaiterValues = new StringBuilder("");
            }
            jsonData.insert(0, "{\n" +
                    " \"name\": \"员工\",\n" +
                    " \"children\": [\n").append("\n]\n}");
            System.out.println(jsonData.toString());
    }

}
