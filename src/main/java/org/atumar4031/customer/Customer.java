package org.atumar4031.customer;

import org.atumar4031.product.Product;
import org.atumar4031.utilities.User;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
    private Map<Product, Integer> cart;
    private double wallet = 0.00;

    public Customer() {
        this.cart = new HashMap<>();
    }

    public Customer(
                    String name, String email, String phone,
                    String address,
                    double wallet
    ) {
        super(name, email, phone, address);
        this.cart = new HashMap<>();
        this.wallet = wallet;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Product p, Integer quantity) {
        this.cart.put(p, quantity);
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cart=" + cart +
                '}';
    }
}
