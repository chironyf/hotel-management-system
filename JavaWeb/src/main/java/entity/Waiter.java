package entity;

import java.sql.Date;

public class Waiter {
//    CREATE TABLE waiter
//        (
//	# 工号
//    waiterID VARCHAR(10),
//	# 姓名
//    waiterName VARCHAR(32) NOT NULL,
//	# 出生日期
//    waiterBirthday DATE NOT NULL,
//	# 身份证
//    waiterIDCard CHAR(18) NOT NULL UNIQUE,
//	# 系统登录密码
//    waiterPassword VARCHAR(32) NOT NULL,
//	# 入职日期
//    waiterJoinDate DATE NOT NULL,
//	# 手机
//    waiterPhoneNumber CHAR(11) UNIQUE,
//	# 备注
//    remarks VARCHAR(32),
//	# 主键
//    PRIMARY KEY (waiterID)
//) ENGINE=InnoDB;

    private String waiterID;

    private String waiterName;

    private Date waiterBirthday;

    private String waiterIDCard;

    private String waiterPassword;

    private Date waiterJoinDate;

    private String waiterPhoneNumber;

    private String remarks;

    public Waiter() {

    }

    public Waiter(String waiterID,
                  String waiterName,
                  Date waiterBirthday,
                  String waiterIDCard,
                  String waiterPassword,
                  Date waiterJoinDate,
                  String waiterPhoneNumber,
                  String remarks) {
        this.waiterID = waiterID;
        this.waiterName = waiterName;
        this.waiterBirthday = waiterBirthday;
        this.waiterIDCard = waiterIDCard;
        this.waiterPassword = waiterPassword;
        this.waiterJoinDate = waiterJoinDate;
        this.waiterPhoneNumber = waiterPhoneNumber;
        this.remarks = remarks;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(String waiterID) {
        this.waiterID = waiterID;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public Date getWaiterBirthday() {
        return waiterBirthday;
    }

    public void setWaiterBirthday(Date waiterBirthday) {
        this.waiterBirthday = waiterBirthday;
    }

    public String getWaiterIDCard() {
        return waiterIDCard;
    }

    public void setWaiterIDCard(String waiterIDCard) {
        this.waiterIDCard = waiterIDCard;
    }

    public String getWaiterPassword() {
        return waiterPassword;
    }

    public void setWaiterPassword(String waiterPassword) {
        this.waiterPassword = waiterPassword;
    }

    public Date getWaiterJoinDate() {
        return waiterJoinDate;
    }

    public void setWaiterJoinDate(Date waiterJoinDate) {
        this.waiterJoinDate = waiterJoinDate;
    }

    public String getWaiterPhoneNumber() {
        return waiterPhoneNumber;
    }

    public void setWaiterPhoneNumber(String waiterPhoneNumber) {
        this.waiterPhoneNumber = waiterPhoneNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}