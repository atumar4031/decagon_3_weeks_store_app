package org.atumar4031.model;

import org.atumar4031.constants.Gender;


public class Customer extends User implements Comparable<Customer> {
    private Cart<Product, Integer> shoppingCart;
    private Wallet wallet;

    public Customer(
            String name, String email, String phone,
            Gender gender, String address
    ) {
        super(name, email, phone,gender, address);
        this.wallet = new Wallet();
        this.shoppingCart = new Cart();
    }

    public Cart<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Cart<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Wallet getWallet() {return wallet;}



    @Override
    public int compareTo(Customer otherCustomer) {
        if(this.getShoppingCart().getProduct().getProductName().equalsIgnoreCase(otherCustomer.getShoppingCart().getProduct().getProductName()));
            int sortValue = this.getShoppingCart().getQuantity() - otherCustomer.getShoppingCart().getQuantity();
        return sortValue;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
