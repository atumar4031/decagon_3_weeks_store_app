package org.atumar4031.customer;

import org.atumar4031.Store;
import org.atumar4031.enums.Category;
import org.atumar4031.enums.Gender;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerServiceTest {
    CustomerServiceImple customerService;
    Product iphoneXr;
    Customer customer;
    Store phoneStore;

    @Before
    public void setUp() throws Exception {
        phoneStore = new Store(101, "phoneStore");
        customerService = new CustomerServiceImple();
        iphoneXr = new Product(101, "iPhone XR",57000,2, Category.PHONE,"available");
        customer = new Customer("Bala", "abu@gmail.com","08066765467", Gender.MALE,"Tudun wada");
        customerService.fundMyWallet(customer, 500000.00);
    }
    @Test
    public void shouldAddProductToShoppingCart() throws ProductNotFoundException {
        customerService.addProductToShoppingCart("iphone Xr",phoneStore,2,customer);
        int customersQueue = phoneStore.getCustomersToAttend().size();
        assertEquals(1, customersQueue);
    }
    @Test
    public void shouldCheckIfWalletCanBeFunded(){
        var balanceBefore = customer.getWallet().getBalance();
        customerService.fundMyWallet(customer, 2000);
        var balanceAfterFund = customer.getWallet().getBalance();
        assertTrue(balanceBefore < balanceAfterFund);
    }
    @Test
    public void toCheckIfProductAreFoundBaseOnCategories(){
        assertThrows(ProductNotFoundException.class, () -> {
           customerService.viewByCategory("main", phoneStore);
        });
    }
    @Test
    public void productOutOfQuantityException(){
        assertThrows(ProductNotFoundException.class,
                ()-> customerService.addProductToShoppingCart(" ",phoneStore,1, customer));
    }


}