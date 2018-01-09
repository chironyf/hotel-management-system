package display;

public class RoomCountValue {

    private int emptyNum;

    private int fullNum;

    public RoomCountValue(int emptyNum, int fullNum) {
        this.emptyNum = emptyNum;
        this.fullNum = fullNum;
    }

    public int getEmptyNum() {
        return emptyNum;
    }

    public void setEmptyNum(int emptyNum) {
        this.emptyNum = emptyNum;
    }

    public int getFullNum() {
        return fullNum;
    }

    public void setFullNum(int fullNum) {
        this.fullNum = fullNum;
    }
}
