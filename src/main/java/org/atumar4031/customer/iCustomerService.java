package org.atumar4031.customer;

import org.atumar4031.product.Product;

import java.util.Map;

public interface iCustomerService {
    Map<Product, Integer> addToCart(Product product, int quantityToBuy,  Customer customer);
}
