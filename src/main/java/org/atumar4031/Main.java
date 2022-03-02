package org.atumar4031;

import org.atumar4031.constants.Category;
import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Product;
import org.atumar4031.model.Staff;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.atumar4031.services.staff.CashierStaff;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NullApplicantException, InvalidEmailException, NullProductException, StaffNotAuthorizedException, InsufficientFundException, IOException, EmptyShoppingCartException, productNotAvailableException, NoSuchQuantityAvailabe, StaffAlreadyExistException, MinimalRequarimentException, ApplicantAlreadyExistException, AutorizationException, EmptyInputException {

        // add to store

        Store phoneStore = new Store(101, "phoneStore");
        Staff cashierUmar = new Staff("101","Umar","umar@gmail.com","0000999888","", Role.CASHIER, Gender.MALE,25);
        phoneStore.getStaffList().add(cashierUmar);
        CashierStaff cashierStaff = new CashierStaff();


        Product iphone7 = new Product(101, "iphone 7",22000,2, Category.PHONE,"available");

//        Category productToBuyCategory = new Category("iphone", "white", "6GB");
//        Product iphoneXr = new Product(101, "iPhone XR",57000,2, productToBuyCategory,"available");

        System.out.println("Size before adding "+ phoneStore.getProducts().length);
//        System.out.println("Products before adding "+ Arrays.toString(phoneStore.getProducts()));
        cashierStaff.addProductToStore(cashierUmar, iphone7, 2, phoneStore);

        System.out.println("Size after adding "+phoneStore.getProducts().length);
//        System.out.println("Products before adding "+ Arrays.toString(phoneStore.getProducts()));


//
//
////         add to cart
        Customer bala = new Customer("Bala", "abu@gmail.com","08066765467",Gender.MALE, "Tudun wada");
        Customer musa = new Customer("Musa", "musa@gmail.com","07078987654",Gender.MALE, "Edo tech park");
        Customer kamal = new Customer("Kamal", "kamal@gmail.com","07078987654",Gender.MALE, "Edo tech park");
        Customer samira = new Customer("Samira", "samira@gmail.com","07078987654",Gender.FEMALE, "Edo tech park");
        CustomerServiceImple customerService = new CustomerServiceImple();
        customerService.fundMyWallet(bala, 500000.0);
        customerService.fundMyWallet(musa, 700000.0);
        customerService.fundMyWallet(kamal, 900000.0);
        customerService.fundMyWallet(samira, 800000.0);
        System.out.println();
//        Category blackIPhone = new Category("iphone","Black","4GB");
//        customerService.addProductToShoppingCart("iphone 7", phoneStore,2, bala);
//        Map<Product, Integer> balaCart = bala.getShoppingCart().getCart();


//        Category goldIPhone = new Category("iphone","black","4GB");
        customerService.addProductToShoppingCart("Nokia", phoneStore,3, bala);
        customerService.addProductToShoppingCart("Nokia", phoneStore,1, kamal);
        customerService.addProductToShoppingCart("Nokia", phoneStore,2, samira);
        customerService.addProductToShoppingCart("iphone Xr", phoneStore,4, musa);

//        System.out.println(balaCart);
//        System.out.println(musa.getShoppingCart());
//        System.out.println(phoneStore.getStoreAccount());
//        cashierStaff.attendCustomer(phoneStore,cashierUmar);
//        System.out.println(phoneStore.getStoreAccount());
        System.out.println(musa.getShoppingCart());
        phoneStore.getCustomersToAttend().removeFromQueue();
        System.out.println(phoneStore.getCustomersToAttend().peek());
        // view product is working
//        customerService.viewByCategory("main", phoneStore);

//        customerService.addProductToShoppingCart(iphoneXr.getProductName(), productToBuyCategory,phoneStore,1, bala);
//
//        System.out.println(bala.getShoppingCart());
////
////        // sell products
////        cashierService.sellProducts(cashierUmar, phoneStore,bala);
////
////        // sell product when it is not available
////
////        Customer mustapha = new Customer(
////                "Mustapha",
////                "mustapha@gmail.com",
////                "070766616752",
////                "Edo Tech Park",
////                960000.00
////        );
////
////        Category productToBuyCategoryNA = new Category(
////                "iphone",
////                "golden",
////                "6GB"
////        );
////
////        customerService.addProductToShoppingCart("iphone XR",
////                productToBuyCategoryNA,
////                phoneStore,
////                2,
////                mustapha);
////        cashierService.sellProducts(cashierUmar, phoneStore,mustapha);
    }
}
