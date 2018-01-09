package entity;

import java.sql.Date;

public class Order {
    
    private String orderNumber;

    private String orderStatus;

    private String customerIDCard;

    private String roomNumber;

    private Date checkInTime;

    private Date checkOutTime;

    private Date orderTime;

    private String waiterID;

    private int totalMoney;

    private String remarks;

    public Order() {

    }

    public Order(String orderNumber,
                 String orderStatus,
                 String customerIDCard,
                 String roomNumber,
                 Date checkInTime,
                 Date checkOutTime,
                 int totalMoney,String waiterID
            ,String remarks,Date orderTime) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.customerIDCard = customerIDCard;
        this.roomNumber = roomNumber;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.totalMoney = totalMoney;
        this.waiterID =waiterID ;
        this.remarks = remarks;
        this.orderTime =orderTime ;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setWaiterID(String waiterID) {
        this.waiterID = waiterID;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerIDCard() {
        return customerIDCard;
    }

    public void setCustomerIDCard(String customerIDCard) {
        this.customerIDCard = customerIDCard;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}