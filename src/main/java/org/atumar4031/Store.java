package org.atumar4031;

import org.atumar4031.inputOutputs.Reading;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Cashier;
import org.atumar4031.model.Receipt;
import org.atumar4031.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private int storeId;
    private String storeName;
    private List<Applicant> applicants;
    private Product[] products;
    private final List<Receipt> receipts;
    private List<Cashier> cashierList;
    private double storeAccount = 0.00;

    public Store(int storeId, String storeName) throws IOException {
        this.storeId = storeId;
        this.storeName = storeName;
        this.products = Reading.readProductFromExcel();
        this.receipts = new ArrayList<>();
        this.cashierList = new ArrayList<>();
        applicants = new ArrayList<>();

    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public List<Cashier> getCashierList() {
        return cashierList;
    }
    public void setCashierList(List<Cashier> cashierList) {
        this.cashierList = cashierList;
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
