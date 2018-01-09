package entity;

public class SystemAdministrator {

//CREATE TABLE systemAdministrator
//        (
//        # 用户ID
//        userID VARCHAR(10),
//        # 用户名
//        userName VARCHAR(32) NOT NULL,
//        # 登录密码
//        userPassword VARCHAR(32) NOT NULL,
//        # 主键
//        PRIMARY KEY (userID)
//        ) ENGINE=InnoDB;

    private String userID;

    private String userName;

    private String userPassword;

    public SystemAdministrator() {

    }

    public SystemAdministrator(String userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
