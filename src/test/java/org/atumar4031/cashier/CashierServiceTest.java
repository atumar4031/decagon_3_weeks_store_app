package org.atumar4031.cashier;

import org.atumar4031.Store;
import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import org.atumar4031.constants.Category;
import org.atumar4031.services.cashier.CashierServiceImple;
import org.atumar4031.services.customer.CustomerService;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CashierServiceTest {
    private Product iphone13Pro;
    private Product iphone7;
    private Category productCategory;
    private Store PhoneStore;
   private  Cashier cashierUsman;
    Cashier cashierUmar;
    private CashierServiceImple cashierService;
    Customer customer;
    CustomerServiceImple customerService;

    LocalDateTime t;

    @Before
    public void setUp() throws Exception {
        t = LocalDateTime.now();
        PhoneStore = new Store(101, "PhoneStore");
        cashierService = new CashierServiceImple();
        productCategory = new Category("iphone","Black","4GB");
        iphone7 = new Product(101, "iphone 7",22000,2, productCategory,"available");
        iphone13Pro = new Product(101, "iphone 7",22000,2, productCategory,"available");
        cashierUmar = new Cashier("101","Umar","umar@gmail.com","0000999888","", Role.CASHIER, Gender.MALE,25);
        PhoneStore.getCashierList().add(cashierUmar);
        cashierUsman = new Cashier("10101","usman","usman@gmail.com","0000999888","", Role.CASHIER, Gender.MALE,21);
        customer = new Customer("Bala", "abu@gmail.com","08066765467","Tudun wada",996000.00);
        customerService = new CustomerServiceImple();
    }

    @Test
    public void toCheckIfProductIsAddedToStore()
            throws NullProductException, EmptyInputException, AutorizationException, IOException {
        cashierService.addProductToStore(cashierUmar, iphone13Pro, 2, PhoneStore);
        Product InsertedProduct = PhoneStore.getProducts()[PhoneStore.getProducts().length - 1];
        assertEquals(iphone13Pro, InsertedProduct);
    }

    @Test
    public void shouldCheckIfUserIsAuthorized() {
        assertThrows(AutorizationException.class, () ->  cashierService.addProductToStore(cashierUsman, iphone13Pro, 2, PhoneStore));
    }


    @Test
    public void removeProductIfAvailableInTheStore() throws NullProductException, EmptyInputException, AutorizationException, IOException {
        cashierService.addProductToStore(cashierUmar, iphone13Pro, 2, PhoneStore);
       assertThrows(ProductNotFoundException.class, () ->  cashierService.removeProduct(iphone13Pro.getProductId(), PhoneStore));
   }

   @Test
    public void isProductRestocked() throws NullProductException, EmptyInputException, AutorizationException, IOException {
        boolean restock = cashierService.restockProduct(iphone7, 10, PhoneStore);
        assertTrue(restock);
    }

    @Test
    public void sellProducts() throws EmptyInputException, NoSuchQuantityAvailabe, productNotAvailableException, EmptyShoppingCartException, StaffNotAuthorizedException, InsufficientFundException, IOException {

        customerService.addProductToShoppingCart(iphone7.getProductName(), productCategory, PhoneStore, 2, customer);
        Product[] products = PhoneStore.getProducts();
        double StoreAccountBalanceBefore = PhoneStore.getStoreAccount();
        cashierService.sellProducts(cashierUmar, PhoneStore,customer);
        double StoreAccountBalanceAfter = PhoneStore.getStoreAccount();
        assertTrue(StoreAccountBalanceBefore < StoreAccountBalanceAfter);
    }
}