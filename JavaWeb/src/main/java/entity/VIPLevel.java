package entity;

public class VIPLevel {
//    CREATE TABLE VIPLevel
//            (
//	# 会员等级（主码）
//                    level CHAR(4),
//	# 享受折扣
//    discount INT UNSIGNED,
//            # 等级对应消费金额
//    totalAmount INT UNSIGNED,
//            # 备注
//    remarks VARCHAR(32),
//	# 主键
//    PRIMARY KEY (level)
//) ENGINE=InnoDB;
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
