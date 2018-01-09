package display;

import java.sql.Date;

//将要显示的订单信息（多个表查询得到）
public class OrderView {

//        <tr><th>订单号</th>
    private String orderNumder;
//        <th>预定客户</th>
    private String customer;
//        <th>预定房间</th>
    private String  roomNumber;
//        <th>房间类型</th>
    private String roomType;
//        <th>入住时间</th>
    private Date checkInTime;

    private Date checkOutTime;

    private Date orderTime;

    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    //入住天数
    private int days;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    //        <th>手机号码</th>
    private String customerPhoneNumber;
//        <th>订单价格</th>
    private int price;

    public String getOrderNumder() {
        return orderNumder;
    }

    public void setOrderNumder(String orderNumder) {
        this.orderNumder = orderNumder;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderView() {

    }

    @Override
    public String toString() {
        return "ordernumber" + orderNumder + ", " + days;
    }


}
