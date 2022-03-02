package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import java.io.*;
import java.util.*;
import java.util.Arrays;

public class CashierStaff implements ProductManagementService, TransactionManagementService {

    @Override
    public void addProductToStore(Staff staff, Product product, int quantity,  Store store)
            throws NullProductException, EmptyInputException, AutorizationException, IOException {
        if (!store.getStaffList().contains(staff)){
            throw new AutorizationException("You are not authorized to perform this operation");
        }
        if(product != null){
            if (
                    quantity > 0
                    && product.getProductId() > 0
                    && !product.getProductName().isEmpty()
                    && product.getProductCategory() != null
            ){
                Product[] productsCopy = Arrays.copyOf(store.getProducts(), store.getProducts().length + 1);
                productsCopy[productsCopy.length - 1] = product;
                store.setProducts(productsCopy);
            }
             else
                throw new EmptyInputException("empty, negative, and null values are not allowed");
        }else
            throw new NullProductException("Product is can not be null");
    }
int itemtracker = 1;

    @Override
    public boolean removeProduct(int productId,  Store store) {
        boolean removeFlag = false;
        Product[]  products = store.getProducts();
        for (Product product : products) {
            if (product.getProductId() == productId){
                Product[] newProducts = new Product[products.length];
                int index = 0;
                for (Product product1: products)
                {
                    if (!product1.equals(product)){
                        newProducts[index] = product;
                    }
                    index++;
                }
                store.setProducts(newProducts);
                removeFlag = true;
                break;
            }
            itemtracker++;
        }
        if(itemtracker == products.length){
                throw new ProductNotFoundException("This product is not available in our store");
        }
        return removeFlag;
    }

    @Override
    public boolean restockProduct(Product  productToRestock, int quantity,  Store store) {
        boolean restockFlag = false;
       Product[] products = store.getProducts();
//        Set<Map.Entry<Product, Integer>> productEntrySet = products.entrySet();

        for (Product product : products) {
            if (
                    product.getProductName().equalsIgnoreCase(productToRestock.getProductName())
                            && product.getProductCategory().equals(productToRestock.getProductCategory())
                            && product.getProductStatus().equalsIgnoreCase("Available")

            ){
                  product.setProductQuantity(product.getProductQuantity() + quantity);
                restockFlag = true;
                break;
            }
        }
        return restockFlag;
    }

    @Override
    public void attendCustomer(Store store, Staff staff) throws StaffNotAuthorizedException, EmptyShoppingCartException, InsufficientFundException, IOException, productNotAvailableException {
        if (!staff.getRole().equals(Role.CASHIER))
            throw new StaffNotAuthorizedException("Your are not authorized to make this transaction");

        PriorityQueue<Customer> customers = store.getCustomersToAttend();
        for (Customer customer: customers){
            sellProducts(store, customer);
        }

    }


    public void sellProducts(Store store, Customer customer) throws InsufficientFundException, IOException, EmptyShoppingCartException, productNotAvailableException {
       boolean productPresent = false;
        Cart<Product,Integer> customerShoppingCart = customer.getShoppingCart(); // naming
        Product productInStore = new Product();
        Product productToBuy = customerShoppingCart.getProduct();
        double storeAccount = store.getStoreAccount();
        double customerWallet =  customer.getWallet().getBalance();
        int quantityToBuy = customer.getShoppingCart().getQuantity();
        int quantityInStore = 0;
        int index = 0;
        for (Product product: store.getProducts()){
            if(product.getProductName().equalsIgnoreCase(productToBuy.getProductName())){
                productInStore = store.getProducts()[index];
                quantityInStore = product.getProductQuantity();
                productPresent = true;
                break;
            }
            index++;
        }
        if (!productPresent)
            throw new productNotAvailableException("this product "+productToBuy.getProductName()+" is not available");
        if(quantityInStore < quantityToBuy)
            throw new productNotAvailableException("Insufficient product quantity ");
        if (customer.getShoppingCart().getQuantity() <= 1)
            throw new EmptyShoppingCartException("This shopping cart cannot be empty");

            double productPrice = productToBuy.getProductPrice();
            double totalAmountOfProduct = (productPrice * quantityToBuy);
            customerWallet -= totalAmountOfProduct;
            customer.getWallet().setBalance(customerWallet);
            storeAccount += totalAmountOfProduct;
            store.setStoreAccount(storeAccount);
            productInStore.setProductQuantity(productInStore.getProductQuantity() - quantityToBuy);
            customer.setShoppingCart(new Cart<>());
    }
    //
    private String generateReceipt(Cart<Product, Integer> shoppingCart, double expectedAmount, double payment, Customer customer, Staff staff) throws IOException {
        String receipt = "===== Thanks for patronizing " + customer.getName() + " ========\n" +
                "Transaction Details\n" +
                "=============================================\n";
            receipt += "Product Name: "+ shoppingCart.getProduct().getProductName()+"\n" +
                    "Price       : "+ shoppingCart.getProduct().getProductPrice()+"\n" +
                    "Units       : "+ shoppingCart.getProduct().getProductQuantity()+"\n" +

                    "Cost        : "+ (shoppingCart.getProduct().getProductPrice() * (double) shoppingCart.getProduct().getProductQuantity())+"\n" +
                    "=============================================\n";

        receipt += "Total Price: "+expectedAmount+"\n" +
                "Amount paid: "+payment+"\n" +
                "Balance:    "+(payment-expectedAmount)+"\n" +
                "Validated by "+staff.getName();

        try(FileWriter writer = new FileWriter(new File("receipt.txt")))
        {
            writer.write(receipt);
        }catch (IOException io){
            System.out.println("IO exception found");
        }
        return receipt;
    }
}
