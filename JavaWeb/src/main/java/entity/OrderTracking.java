package entity;

import java.sql.Date;

public class OrderTracking {
//    CREATE TABLE orderTracking
//            (
//	# 订单号
//    orderNumber CHAR(32),
//	# 预定时间（订单表中一旦有预定订单加入，那么本表新增一条记录，本字段不可能为空）
//    orderTime DATE NOT NULL,
//	# 实际入住时间
//    checkInTime DATE,
//	# 实际退房时间
//    checkOutTime DATE,
//	# 备注
//    remarks VARCHAR(32),
//	# 主键
//    PRIMARY KEY (orderNumber),
//	# 同时作为外键
//    FOREIGN KEY (orderNumber) REFERENCES orders(orderNumber)
//
//            ) ENGINE=InnoDB;

    private String orderNumber;

    private Date orderTime;

    private Date checkInTime;

    private Date checkOutTime;

    private String remarks;

    public OrderTracking() {

    }

    public OrderTracking(String orderNumber,
                 Date orderTime,
                 Date checkInTime,
                 Date checkOutTime,
                 String remarks) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.remarks = remarks;

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
