package tool;

import config.GCON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class DataBase {

    private static Connection connection = null;
    public static Connection hotelConnection =null ;
    public static Connection systemConnection =null ;
    public static  HashMap<String,Connection> MAP =null ;
    //加载数据库驱动
    static {

        try {
            Class.forName(GCON.DRIVER);
            MAP = new HashMap<String, Connection>();
            hotelConnection = DriverManager.getConnection(
                    GCON.URL,
                    GCON.HOTELUSERNAME,
                    GCON.HOTELPASSWORD);
            MAP.put(GCON.HOTELUSERNAME, hotelConnection);
            systemConnection = DriverManager.getConnection(
                    GCON.URL,
                    GCON.SYSTEMUSERNAME,
                    GCON.SYSTEMPASSWORD);
            MAP.put(GCON.SYSTEMUSERNAME,systemConnection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void disConnection() {
        connection = null;
    }


    //返回数据库连接
    public static Connection getConnection() throws Exception {
        //如果之前未连接
        if (connection == null) {
            connection = DriverManager.getConnection(GCON.URL, GCON.USERNAME, GCON.PASSWORD);
            return connection;
        }
        if (GCON.status == 0) {
            return MAP.get(GCON.HOTELUSERNAME) ;
        } else {
            return MAP.get(GCON.SYSTEMUSERNAME) ;
        }
    }
    public static void setConnection(Connection connection) throws Exception {

        DataBase.connection = connection ;
    }

}
