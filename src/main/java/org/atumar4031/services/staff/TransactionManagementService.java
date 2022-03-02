package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.EmptyShoppingCartException;
import org.atumar4031.exceptions.InsufficientFundException;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.exceptions.productNotAvailableException;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Staff;

import java.io.IOException;
import java.util.PriorityQueue;

public interface TransactionManagementService {
    void attendCustomer(Store store, Staff staff) throws StaffNotAuthorizedException, EmptyShoppingCartException, InsufficientFundException, IOException, productNotAvailableException;
//    void sellProducts(Customer customer) throws , InsufficientFundException, IOException, EmptyShoppingCartException;

}
