package org.atumar4031.model;

import java.util.Date;

public class Receipt {
    private int id;
    private double totalAmount;
    private boolean status;
    private Date date;

    public Receipt(int id, double totalAmount, boolean status, Date date) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
