package entity;

public class VIPLevel {
    
    private String level;

    private int discount;

    private int totalAmount;

    private String remarks;

    public VIPLevel() {

    }

    public VIPLevel(String level,
                    int discount,
                    int totalAmount,
                    String remarks) {
        this.level = level;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.remarks = remarks;

    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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
