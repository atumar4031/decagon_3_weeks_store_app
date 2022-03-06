package org.atumar4031.model;

import org.atumar4031.Store;
import org.atumar4031.enums.Gender;
import org.atumar4031.enums.Role;
import org.atumar4031.exceptions.ProductNotFoundException;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class MyPriorityQueueTest {
    Store phoneStore;
    CustomerServiceImple customerService;
    Customer bala;
    Customer musa;
    Customer kamal;
    @BeforeEach
    void setUp() throws IOException {
        customerService = new CustomerServiceImple();
        bala = new Customer("Bala", "abu@gmail.com","08066765467", Gender.MALE, "Tudun wada");
        musa = new Customer("Musa", "musa@gmail.com","07078987654",Gender.MALE, "Edo tech park");
        kamal = new Customer("Kamal", "kamal@gmail.com","07078987654",Gender.MALE, "Edo tech park");
        customerService.fundMyWallet(bala, 500000.0);
        customerService.fundMyWallet(musa, 700000.0);
        customerService.fundMyWallet(kamal, 900000.0);
        phoneStore = new Store(101, "phoneStore");
        Staff cashierUmar = new Staff("101","Umar","umar@gmail.com","0000999888","", Role.CASHIER, Gender.MALE,25);
        phoneStore.getStaffList().add(cashierUmar);
//        CashierStaff cashierStaff = new CashierStaff();
    }

    @Before

    @Test
    void tocheckIfTheCustomerWithLowerQuantityIsAddedBeforeCustomerWithHightQuantity() throws ProductNotFoundException {
        customerService.addProductToShoppingCart("Nokia", phoneStore,2, bala);
        customerService.addProductToShoppingCart("Nokia", phoneStore,3, kamal);
        customerService.addProductToShoppingCart("iphone Xr", phoneStore,4, musa);

    }

    @Test
    void size() {
    }

    @Test
    void peekMe() {
    }
}