package org.atumar4031.model;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
    private Map<Product, Integer> shoppingCart;
    private double CustomerWallet = 0.00;

    public Customer() {
        this.shoppingCart = new HashMap<>();
    }

    public Customer(
                    String name, String email, String phone,
                    String address,
                    double wallet
    ) {
        super(name, email, phone, address);
        this.shoppingCart = new HashMap<>();
        this.CustomerWallet = wallet;
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product , Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getCustomerWallet() {
        return CustomerWallet;
    }

    public void setCustomerWallet(double customerWallet) {
        this.CustomerWallet = customerWallet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
