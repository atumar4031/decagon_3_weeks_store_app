package org.atumar4031.services.customer;

import org.atumar4031.Store;
import org.atumar4031.exceptions.productNotAvailableException;
import org.atumar4031.exceptions.NoSuchQuantityAvailabe;
import org.atumar4031.model.Cart;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Product;

public interface CustomerService {
    void addProductToShoppingCart(String product, Store store, int quantityToBuy, Customer customer) throws productNotAvailableException, NoSuchQuantityAvailabe;
    void fundMyWallet(Customer customer, double amount);
    void viewByCategory(String categoryName, Store store);
}
