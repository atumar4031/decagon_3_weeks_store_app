package org.atumar4031.cashier;

import org.atumar4031.PhoneAndAccessoriesStore;
import org.atumar4031.customer.Customer;
import org.atumar4031.exceptions.InsufficientFundException;
import org.atumar4031.exceptions.NullProductException;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.product.Product;

import java.util.Map;

public interface iCashierService {
    Map<Product, Integer> addProductToStore(Product product, int Quantity, PhoneAndAccessoriesStore store) throws NullProductException;
    boolean removeProduct(int productId, PhoneAndAccessoriesStore store);
    boolean restockProduct(int productId, int Quantity,  PhoneAndAccessoriesStore store);
    void sellProducts(Cashier cashier, PhoneAndAccessoriesStore store, Customer customer) throws StaffNotAuthorizedException, InsufficientFundException;
}
