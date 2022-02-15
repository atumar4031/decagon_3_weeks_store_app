package org.atumar4031.cashier;

import org.atumar4031.exceptions.NullProductException;
import org.atumar4031.product.Product;

import java.util.Map;

public interface iCashierService {
    Map<Product, Integer> addProductToStore(Product product, int Quantity) throws NullProductException;
    boolean removeProduct(int productId);
    boolean restockProduct(int productId, int Quantity);
}
