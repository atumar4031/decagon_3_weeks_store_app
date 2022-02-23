package org.atumar4031.services.customer;

import org.atumar4031.Store;
import org.atumar4031.constants.Category;
import org.atumar4031.exceptions.productNotAvailableException;
import org.atumar4031.exceptions.NoSuchQuantityAvailabe;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Product;

import java.util.Map;

public interface CustomerService {
    Map<Product, Integer> addProductToShoppingCart(String product, Category category, Store store, int quantityToBuy, Customer customer) throws productNotAvailableException, NoSuchQuantityAvailabe;
}
