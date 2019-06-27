package com.example.rar;

public class listItem {
    private int id;
    private String deptTime;
    private String arrivalTime;
    private int availSeats;
    private float fair;

    public listItem(int id, String deptTime, String arrivalTime, int availSeats, float fair) {
        this.id = id;
        this.deptTime = deptTime;
        this.arrivalTime = arrivalTime;
        this.availSeats = availSeats;
        this.fair = fair;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailSeats() {
        return availSeats;
    }

    public void setAvailSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    public float getFair() {
        return fair;
    }

    public void setFair(float fair) {
        this.fair = fair;
    }
}
