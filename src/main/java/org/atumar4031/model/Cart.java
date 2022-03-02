package org.atumar4031.model;

import org.atumar4031.exceptions.NoSuchQuantityAvailabe;

import java.util.HashMap;
import java.util.Map;

public class Cart<K , V>{
    private K product;
    private V quantity;

    public Cart(){
    }
    public Cart(K product, V quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public K getProduct() {return product;}
    public void setProduct(K product) {this.product = product;}

    public V getQuantity() {return quantity;}

    public void setQuantity(V quantity) {this.quantity = quantity;}

    @Override
    public String toString() {
        return "Cart{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
