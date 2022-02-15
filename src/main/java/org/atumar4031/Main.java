package org.atumar4031;

import org.atumar4031.cashier.Cashier;
import org.atumar4031.constants.Gender;
import org.atumar4031.customer.Customer;
import org.atumar4031.customer.CustomerService;
import org.atumar4031.exceptions.InvalidEmailException;
import org.atumar4031.exceptions.NullApplicantException;
import org.atumar4031.manager.Manager;
import org.atumar4031.manager.ManagerService;
import org.atumar4031.product.Category;
import org.atumar4031.product.Product;
import org.atumar4031.utilities.Applicant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NullApplicantException, InvalidEmailException {
        Manager manager = new Manager();
        CustomerService service = new CustomerService();
        Category category1 = new Category("A-series","Golden","24GB");
        Category category2 = new Category("X-series","white","68GB");
        Product product1 = new Product(101, "Samsung 1012",57000.00, category1);
        Product product2 = new Product(102, "Samsung 7865",58000.00, category2);
        Customer customer1 = new Customer("Bala", "abu@gmail.com","08066765467","Tudun wada",500000.00);
//        Customer customer2 = new Customer("Musty", "a@gmail.com","07066616752","Tudun wada",3700.00);
        var result = service.addToCart(product1, 1, customer1);
        var result2 = service.addToCart(product2, 5, customer1);
        System.out.println(customer1.getCart().toString());
    }
}
