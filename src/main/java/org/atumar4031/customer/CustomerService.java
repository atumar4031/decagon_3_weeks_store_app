package org.atumar4031.customer;

import org.atumar4031.product.Product;

import java.util.Map;
import java.util.Set;

public class CustomerService implements iCustomerService {

    @Override
    public Map<Product, Integer> addToCart(Product product, int quantityToBuy, Customer customer) {
        Map<Product, Integer> cart = customer.getCart();
        if(cart != null && cart.size() > 0){
            if(cart.containsKey(product)){
                Set<Map.Entry<Product, Integer>> entries = cart.entrySet();
                for (Map.Entry<Product, Integer> singleEntry: entries){
                    Product key = singleEntry.getKey();
                    if(key.equals(product)){
                        int value = singleEntry.getValue();
                        cart.remove(key);
                        cart.put(key, value + quantityToBuy);
                        break;
                    }
                }
            }
        }
        customer.setCart(product, quantityToBuy);
        return customer.getCart();
    }
}
