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
        Category category = new Category("A-series","Golden","24GB");
        Product product = new Product(101, "Samsung 1012", category);
        Customer customer = new Customer("Musty", "a@gmail.com","07066616752","Tudun wada",3700.00);
        var result = service.addToCart(product, 1, customer);
        System.out.println(result.toString());
    }
}
