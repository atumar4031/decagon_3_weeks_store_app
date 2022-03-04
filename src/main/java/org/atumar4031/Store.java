package org.atumar4031;

import org.atumar4031.inputOutputs.Reading;
import org.atumar4031.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Store {
    private int storeId;
    private String storeName;

    private List<Applicant> applicants;
    private Product[] products;
    private final List<Receipt> receipts;
    private List<Staff> StaffList;

    private MyPriorityQueue customersToAttend;

    private double storeAccount = 0.00;

    public Store(int storeId, String storeName) throws IOException {
        this.storeId = storeId;
        this.storeName = storeName;
        this.products = Reading.readProductFromExcel();
        this.receipts = new ArrayList<>();
        this.StaffList = new ArrayList<>();
        this.applicants = new ArrayList<>();
        this.customersToAttend = new MyPriorityQueue();
    }

    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public MyPriorityQueue getCustomersToAttend() {
        return customersToAttend;
    }

    public void setCustomersToAttend(MyPriorityQueue customersToAttend) {
        this.customersToAttend = customersToAttend;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public double getStoreAccount() {
        return storeAccount;
    }
    public void setStoreAccount(double storeAccount) {
        this.storeAccount = storeAccount;
    }

    public List<Staff> getStaffList() {
        return StaffList;
    }
    public void setStaffList(List<Staff> staffList) {
        this.StaffList = staffList;
    }

    public Product[] getProducts() {
        return products;
    }
    public void setProducts(Product[] products) {
        this.products = products;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }
    public void setReceipts(Receipt receipts) {

        this.receipts.add(receipts);
    }

}
