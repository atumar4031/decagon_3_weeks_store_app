package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Product;
import org.atumar4031.model.Staff;

import java.io.IOException;

public interface ProductManagementService {
    void addProductToStore(Staff staff, Product product, int Quantity, Store store) throws NullProductException, EmptyInputException, AutorizationException, IOException;
    boolean removeProduct(int productId, Store store);
    boolean restockProduct(Product product, int Quantity,  Store store);}
