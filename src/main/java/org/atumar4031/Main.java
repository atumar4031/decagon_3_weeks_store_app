package org.atumar4031;

import org.atumar4031.constants.Category;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Customer;
import org.atumar4031.services.customer.CustomerServiceImple;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NullApplicantException, InvalidEmailException, NullProductException, StaffNotAuthorizedException, InsufficientFundException, IOException, EmptyShoppingCartException, productNotAvailableException, NoSuchQuantityAvailabe, StaffAlreadyExistException, MinimalRequarimentException, ApplicantAlreadyExistException, AutorizationException, EmptyInputException {
        CustomerServiceImple customerService = new CustomerServiceImple();
        Store phoneStore = new Store(101, "phoneStore");

        Customer mustapha = new Customer(
                "Mustapha",
                "mustapha@gmail.com",
                "070766616752",
                "Edo Tech Park",
                500000.00
        );
        Category productToBuyCategory = new Category(
                "iphone",
                "white",
                "6GB"
        );
        Category productToBuyCategory2 = new Category(
                "iphone",
                "black",
                "4GB"
        );

        customerService.addProductToShoppingCart("iphone XR",
                productToBuyCategory,
                phoneStore,
                2,
                mustapha);

        customerService.addProductToShoppingCart("iphone XR",
                productToBuyCategory,
                phoneStore,
                2,
                mustapha);
        customerService.addProductToShoppingCart("iphone 7",
                productToBuyCategory2,
                phoneStore,
                1,
                mustapha);

        System.out.println(mustapha.getShoppingCart());

//        System.out.println(Arrays.toString(phoneStore.getProducts()));

        // addProductToShoppingCart(String productName, Category productCategory, Store store, int quantityToBuy, Customer customer)
    }
}
