package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Product;
import org.atumar4031.model.Staff;

import java.io.IOException;

public interface ProductManagementService {
    void addProductToStore(Staff staff, Product product, int Quantity, Store store) throws  InvalidInputException, StaffNotAuthorizedException, IOException;
    boolean removeProduct(Staff staff, int productId, Store store) throws StaffNotAuthorizedException;
    boolean restockProduct(Staff staff, Product product, int Quantity,  Store store) throws StaffNotAuthorizedException;
}
