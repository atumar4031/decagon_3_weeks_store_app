package org.atumar4031.customer;

import org.atumar4031.product.Category;
import org.atumar4031.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    CustomerService service;
    Category category = new Category("A-series","Golden","24GB");
    Product product = new Product(101, "Samsung 1012", category);
    Customer customer = new Customer("Musty", "a@gmail.com","07066616752","Tudun wada",3700.00);
    Map<Product, Integer> result = service.addToCart(product, 1, customer);

    @Before
    public void setUp() throws Exception {
        service = new CustomerService();
    }

    @Test
    public void addToCart() {

    }
}