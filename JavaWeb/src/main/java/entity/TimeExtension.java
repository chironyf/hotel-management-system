package entity;

import java.sql.Date;

public class TimeExtension {
    //    CREATE TABLE timeExtension
//            (
//	# 操作记录号
//                    operatingID INT UNSIGNED AUTO_INCREMENT,
//	# 操作的订单号(是记录中的订单号，外码，参照订单表)
//    orderNumber CHAR(32),
//	# 住房原到期日期
//    oldExpiryDate DATE NOT NULL,
//	# 住房新到期日期
//    newExpiryDate DATE NOT NULL,
//	# 需要添加的金额
//    addedMoney INT UNSIGNED NOT NULL,
//            # 主键
//    PRIMARY KEY (operatingID),
//	# 外键
//    FOREIGN KEY (orderNumber) REFERENCES orders(orderNumber)
//
//            ) ENGINE=InnoDB;
    //每次初始化之后, 自增1
    private int operatingID;

    private String orderNumber;

    private Date oldExpiryDate;

    private Date newExpiryDate;

    private int addedMoney;

    public TimeExtension() {

    }

    public TimeExtension(int id , String orderNumber,
                         Date oldExpiryDate,
                         Date newExpiryDate,
                         int addedMoney) {
        this.orderNumber = orderNumber;
        this.oldExpiryDate = oldExpiryDate;
        this.newExpiryDate = newExpiryDate;
        this.addedMoney = addedMoney;
        this.operatingID =id ;
    }

    public int getOperatingID() {
        return operatingID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOldExpiryDate() {
        return oldExpiryDate;
    }

    public void setOldExpiryDate(Date oldExpiryDate) {
        this.oldExpiryDate = oldExpiryDate;
    }

    public Date getNewExpiryDate() {
        return newExpiryDate;
    }

    public void setNewExpiryDate(Date newExpiryDate) {
        this.newExpiryDate = newExpiryDate;
    }

    public int getAddedMoney() {
        return addedMoney;
    }

    public void setAddedMoney(int addedMoney) {
        this.addedMoney = addedMoney;
    }
}