package org.atumar4031.services.cashier;

import org.atumar4031.Store;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Cashier;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Product;

import java.io.IOException;
import java.util.Map;

public interface CashierService {
    void addProductToStore(Cashier cashier, Product product, int Quantity, Store store) throws NullProductException, EmptyInputException, AutorizationException, IOException;
    boolean removeProduct(int productId, Store store);
    boolean restockProduct(Product product, int Quantity,  Store store);
    void sellProducts(Cashier cashier, Store store, Customer customer) throws StaffNotAuthorizedException, InsufficientFundException, IOException, EmptyShoppingCartException;
}
