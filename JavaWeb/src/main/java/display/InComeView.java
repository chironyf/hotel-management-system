package display;

public class InComeView {

    int AllPrice ;

    String date ;
    //新增退房订单数量
    private int checkOutNumber;

    public void setAllPrice(int allPrice) {
        AllPrice = allPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCheckOutNumber() {
        return checkOutNumber;
    }

    public void setCheckOutNumber(int checkOutNumber) {
        this.checkOutNumber = checkOutNumber;
    }

    public InComeView(String date, int AllPrice, int checkOutNumber){
        this.date =date ;
        this.AllPrice =AllPrice ;
        this.checkOutNumber = checkOutNumber;
    }

    public int getAllPrice() {
        return AllPrice;
    }

    public String getDate() {
        return date;
    }
}
