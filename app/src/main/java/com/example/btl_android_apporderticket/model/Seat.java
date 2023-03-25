package com.example.btl_android_apporderticket.model;

public class Seat {
    private int seatId;
    private String rowNumber;
    private String status;
    private String typeSeat;
    private String roomId;
    private String scheduleId;

    public Seat() {
    }

    public Seat(int seatId, String rowNumber, String status, String typeSeat, String roomId, String scheduleId) {
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.status = status;
        this.typeSeat = typeSeat;
        this.roomId = roomId;
        this.scheduleId = scheduleId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(String typeSeat) {
        this.typeSeat = typeSeat;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", rowNumber='" + rowNumber + '\'' +
                ", status='" + status + '\'' +
                ", typeSeat='" + typeSeat + '\'' +
                ", roomId='" + roomId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                '}';
    }
}
