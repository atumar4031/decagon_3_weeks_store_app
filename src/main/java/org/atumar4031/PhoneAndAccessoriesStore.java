package org.atumar4031;

import org.atumar4031.cashier.Cashier;
import org.atumar4031.utilities.Receipt;
import org.atumar4031.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneAndAccessoriesStore {
    private Map<Product,Integer> products;
    private List<Receipt> receipts;
    private List<Product> featureProducts;
    private List<Cashier> cashierList;
    private double storeAccount = 0.00;

    public PhoneAndAccessoriesStore() {
        this.products = new HashMap<>();
        this.receipts = new ArrayList<>();
        this.featureProducts = new ArrayList<>();
        this.cashierList = new ArrayList<>();
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


    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Product product, Integer quantity) {
        this.products.put(product, quantity);
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Receipt receipts) {

        this.receipts.add(receipts);
    }
    public List<Product> getFeatureProducts() {
        return featureProducts;
    }

    public void setFeatureProducts(Product featureProducts) {
        this.featureProducts.add(featureProducts);
    }
}
