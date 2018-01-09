package entity;

public class Room {
//    CREATE TABLE room
//            (
//	# 房间号（主码）
//                    roomNumber CHAR(6),
//	# 房间类型
//    roomType VARCHAR(32) NOT NULL,
//	# 状态
//    roomStatus CHAR(6) NOT NULL,
//	# 备注
//    remarks VARCHAR(32),
//	# 主键
//    PRIMARY KEY (roomNumber),
//	# 外键
//    FOREIGN KEY (roomType) REFERENCES roomTypeAndPrice(roomType)
//
//            ) ENGINE=InnoDB;

    private String roomNumber;

    private String roomType;

    private String roomStatus;

    private String remarks;

    public Room() {
        this.roomStatus = "空";
        this.remarks = "";

    }

    public Room(String roomNumber,
                String roomType,
                String roomStatus,
                String remarks) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.remarks = remarks;
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

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
