package entity;

import java.sql.Date;

public class Customer {

    private String customerIDCard;

    private String customerGender;

    private String customerName;

    private Date customerBirthday;

    private String customerVIPLevel;

    private String customerPhoneNumber;

    private int totalAmount;

    private String remarks;

    public Customer() {

    }

    public Customer(String customerIDCard,
                    String customerGender,
                    String customerName,
                    Date customerBirthday,
                    String customerVIPLevel,
                    String customerPhoneNumber,
                    int totalAmount,
                    String remarks) {
        this.customerIDCard = customerIDCard;
        this.customerGender = customerGender;
        this.customerName = customerName;
        this.customerBirthday = customerBirthday;
        this.customerVIPLevel = customerVIPLevel;
        this.customerPhoneNumber = customerPhoneNumber;
        this.totalAmount = totalAmount;
        this.remarks = remarks;

    }

    public String getCustomerIDCard() {
        return customerIDCard;
    }

    public void setCustomerIDCard(String customerIDCard) {
        this.customerIDCard = customerIDCard;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(Date customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerVIPLevel() {
        return customerVIPLevel;
    }

    public void setCustomerVIPLevel(String customerVIPLevel) {
        this.customerVIPLevel = customerVIPLevel;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
