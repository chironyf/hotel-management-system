package entity;

import java.sql.Date;

public class OrderTracking {

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
