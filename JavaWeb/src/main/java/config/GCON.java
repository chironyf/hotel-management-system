package config;

import java.util.ArrayList;

public class GCON {
    //数据库连接信息
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String URL = "jdbc:mysql://localhost:3306/new?autoReconnect=true&useSSL=false&useUnicode=true&charaterEncoding=UTF";

    //登录时默认使用system用户验证登录名与密码是否正确
    public static  String USERNAME = "system";

    public static  String PASSWORD = "1234";

    public static final String SYSTEMUSERNAME = "system";

    public static final String SYSTEMPASSWORD = "1234";

    public static final String HOTELUSERNAME = "hotel";

    public static final String HOTELPASSWORD = "1234";

    public static int status = 1 ; //1是系统管理员  0 是宾馆管理员;
    //订单
    //查询所有订单信息
    public static final String SQL_ALL_ORDERS = "SELECT * FROM orders";
    //查询管理员
    public static final String SQL_ALL_ADMINS = "SELECT * FROM systemAdministrator";

    //查询所有房间
    public static final String SQL_ALL_ROOMS ="SELECT * FROM roomtypeandprice" ;

    public static final String SQL_ALL_WAITERS ="SELECT * FROM waiter" ;

    //查询所有续费订单
    public static final String SQL_ALL_TIME_EXTENSION_ORDERS = "SELECT * FROM timeextension";


    //----------------------jsp-----------------------//
    //客房管理
    public static final String SEARCH_ROOM = "1";

    public static final String ADD_ROOM = "2";

    public static final String EDIT_ROOM = "3";

    public static final String DELETE_ROOM = "4";

}
