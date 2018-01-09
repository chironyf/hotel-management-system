package entity;

import java.sql.Date;

public class TimeExtension {
    
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