package org.atumar4031.services.customer;

import org.atumar4031.Store;
import org.atumar4031.enums.Category;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Cart;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Product;

public class CustomerServiceImple implements CustomerService {
    //TODO: Test Junit and Main
    public CustomerServiceImple(){}
    @Override
    public void addProductToShoppingCart(String productName, Store store, int quantityToBuy, Customer customer) throws ProductNotFoundException {
        if(productName.trim().isEmpty()) throw new ProductNotFoundException("This product name not found");
        Product[] productListFromStore = store.getProducts();
        for (Product product: productListFromStore){
            if(product.getProductName().equalsIgnoreCase(productName)) {
                if(product.getProductQuantity() > quantityToBuy
                        && product.getProductStatus().equalsIgnoreCase("AVAILABLE")
                ) {
                    customer.setShoppingCart(new Cart(product, quantityToBuy));
                    store.getCustomersToAttend().addToQueue(customer);
                    break;
                }
            }
        }
    }
    @Override
    public void fundMyWallet(Customer customer, double amount){
        if (amount <= 0)
            throw new InvalidInputException("This input are not allowed");

        customer.getWallet()
                .setBalance(customer.getWallet().getBalance() + amount);
        System.out.println("your account has been funded with "+amount);
    }

    @Override
    public void viewByCategory(String categoryName, Store store){
        Product[] products = store.getProducts();
        switch (categoryName){
            case "phone":{
                printProductByCategory(Category.PHONE,products);
            }break;
            case "headset":{
                printProductByCategory(Category.HEADSET,products);
            }break;
            case "charger":{
                printProductByCategory(Category.CHARGER,products);
            }break;
            case "other" : {
                printProductByCategory(Category.OTHER,products);
            }break;
            default: {
                throw new ProductNotFoundException("No such product found in this store");
            }
        }

    }
    private void printProductByCategory(Category category, Product[] products){
        for (Product product: products){
            if (product.getProductCategory().equals(category)){
                System.out.println(product.getProductName()+", "+ product.getProductPrice());
            }
        }
    }
}
