package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.EmptyShoppingCartException;
import org.atumar4031.exceptions.InsufficientFundException;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.model.Staff;

import java.io.IOException;

public interface TransactionManagementService {
    void processCustomersInQueue(Store store, Staff staff) throws StaffNotAuthorizedException, EmptyShoppingCartException, InsufficientFundException, IOException;
}
