package org.atumar4031.customer;

import org.atumar4031.Store;
import org.atumar4031.constants.Category;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerServiceTest {
    Manager manager;
    CustomerServiceImple service;
    Category productToBuyCategory;
    Product iphoneXr;
    Customer customer;
    Store phoneStore;

    @Before
    public void setUp() throws Exception {
        phoneStore = new Store(101, "phoneStore");
        service = new CustomerServiceImple();
        manager = new Manager();
        productToBuyCategory = new Category(
                "iphone",
                "white",
                "6GB"
        );
        iphoneXr = new Product(101, "iPhone XR",57000,2, productToBuyCategory,"available");
        customer = new Customer("Bala", "abu@gmail.com","08066765467","Tudun wada",500000.00);
    }
  // addProductToShoppingCart(String productName, Category productCategory, Store store, int quantityToBuy, Customer customer)

    @Test
    public void shouldCheckIfTheShoppingCartSizeIncreaseAfterAdding() throws productNotAvailableException, NoSuchQuantityAvailabe {
        int sizeBeforeAdding = customer.getShoppingCart().size();
        service.addProductToShoppingCart(iphoneXr.getProductName(), productToBuyCategory,phoneStore,1, customer);
         int sizeAfterAdding = customer.getShoppingCart().size();
        assertTrue(sizeBeforeAdding < sizeAfterAdding);
    }
    @Test
    public void productOutOfQuantityException(){
        assertThrows(productNotAvailableException.class,
                ()-> service.addProductToShoppingCart("          ", productToBuyCategory,phoneStore,1, customer));
    }
}