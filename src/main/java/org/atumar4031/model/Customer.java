package org.atumar4031.model;

import org.atumar4031.enums.Gender;


public class Customer extends User  {

    private Cart<Product, Integer> shoppingCart;
    private Wallet wallet;

    public Customer(){}

    public Customer(
            String name, String email, String phone,
            Gender gender, String address
    ) {
        super(name, email, phone,gender, address);
        this.wallet = new Wallet();
        this.shoppingCart = new Cart();
    }

    public Wallet getWallet() {return wallet;}

    public Cart<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(Cart<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
