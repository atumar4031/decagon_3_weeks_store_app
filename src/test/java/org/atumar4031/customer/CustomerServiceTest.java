package org.atumar4031.customer;

import org.atumar4031.manager.Manager;
import org.atumar4031.product.Category;
import org.atumar4031.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    Manager manager;
    CustomerService service;
    Category category1;
    Category category2;
    Product product1;
    Product product2;
    Customer customer;

    @Before
    public void setUp() throws Exception {
        service = new CustomerService();
        manager = new Manager();
        category1 = new Category("A-series","Golden","24GB");
        category2 = new Category("X-series","white","68GB");
        product1 = new Product(101, "Samsung 1012",57000, category1);
        product2 = new Product(102, "Samsung 7865",32000, category2);
        customer = new Customer("Bala", "abu@gmail.com","08066765467","Tudun wada",500000.00);
    }

    @Test
    public void shouldCompareTheCartSizeBefoAndAfterCustomerAddProduct() {
        int sizeBeforeAdding = customer.getCart().size();
        service.addToCart(product1, 1, customer);
        service.addToCart(product2, 5, customer);
        int sizeAfterAdding = customer.getCart().size();
        assertTrue(sizeBeforeAdding < sizeAfterAdding);
    }

    @Test
    public void toCheckIfProductQuantityIsIncreasingAfterAddingSameProductToCart() {
        service.addToCart(product1, 1, customer);
        Map<Product, Integer> cart = customer.getCart();
        var beforeUpdate = cart.get(product1);
        service.addToCart(product1, 1, customer);
        var afterUpdate = cart.get(product1);
        assertTrue(beforeUpdate < afterUpdate);
    }
}