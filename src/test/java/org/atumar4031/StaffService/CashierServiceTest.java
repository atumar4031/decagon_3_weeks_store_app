package org.atumar4031.StaffService;

import org.atumar4031.Store;
import org.atumar4031.enums.Category;
import org.atumar4031.enums.Gender;
import org.atumar4031.enums.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import org.atumar4031.services.staff.CashierStaff;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CashierServiceTest {
    private Product iphone13Pro;
    private Product iphoneXr;
    private Store PhoneStore;
    private  Staff staffUsman;
    private Staff staffUmar;
    private CashierStaff cashierService;
    Customer customer;
    CustomerServiceImple customerService;

    LocalDateTime t;

    @Before
    public void setUp() throws Exception {
        t = LocalDateTime.now();
        PhoneStore = new Store(101, "PhoneStore");
        cashierService = new CashierStaff();
        iphoneXr = new Product(101, "iphone Xr",22000,2, Category.PHONE,"available");
        iphone13Pro = new Product(101, "iphone 7",22000,2, Category.PHONE,"available");
        staffUmar = new Staff("101","Umar","umar@gmail.com","0000999888","", Role.CASHIER, Gender.MALE,25);
        PhoneStore.getStaffList().add(staffUmar);
        staffUsman = new Staff("10101","usman","usman@gmail.com","0000999888","", Role.CASHIER, Gender.MALE,21);
        customer = new Customer("Bala", "abu@gmail.com","08066765467",Gender.MALE,"Tudun wada");
        customer.getWallet().setBalance(996000.00);
        customerService = new CustomerServiceImple();
    }

    @Test
    public void toCheckIfProductIsAddedToStore()
            throws InvalidInputException, InvalidInputException, StaffNotAuthorizedException, IOException {
        cashierService.addProductToStore(staffUmar, iphone13Pro, 2, PhoneStore);
        Product InsertedProduct = PhoneStore.getProducts()[PhoneStore.getProducts().length - 1];

        assertEquals(iphone13Pro, InsertedProduct);
    }

    @Test
    public void shouldCheckIfUserIsAuthorized() {
        assertThrows(StaffNotAuthorizedException.class, () ->  cashierService.addProductToStore(staffUsman, iphone13Pro, 2, PhoneStore));
    }

    @Test
    public void removeProductIfAvailableInTheStore() throws InvalidInputException, InvalidInputException, StaffNotAuthorizedException, IOException {
        cashierService.addProductToStore(staffUmar, iphone13Pro, 2, PhoneStore);
       assertThrows(ProductNotFoundException.class, () ->  cashierService.removeProduct(staffUmar,iphone13Pro.getProductId(), PhoneStore));
   }

   @Test
    public void isProductRestocked() throws InvalidInputException, StaffNotAuthorizedException {
        boolean restock = cashierService.restockProduct(staffUmar,iphoneXr, 10, PhoneStore);
        assertTrue(restock);
    }

    @Test
    public void sellProducts() throws InvalidInputException, NoSuchQuantityAvailabe, ProductNotFoundException, EmptyShoppingCartException, InsufficientFundException, IOException {
        customerService.addProductToShoppingCart(iphoneXr.getProductName(), PhoneStore, 2, customer);
        Product[] products = PhoneStore.getProducts();
        double StoreAccountBalanceBefore = PhoneStore.getStoreAccount();
        cashierService.sellProducts(PhoneStore,customer);
        double StoreAccountBalanceAfter = PhoneStore.getStoreAccount();
        assertTrue(StoreAccountBalanceBefore < StoreAccountBalanceAfter);
    }
}