package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.enums.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import java.io.*;
import java.util.Arrays;

public class CashierStaff implements ProductManagementService, TransactionManagementService {
//TODO : test this class function in main class

    @Override
    public void addProductToStore(Staff staff, Product product, int quantity,  Store store)
            throws InvalidInputException, InvalidInputException, StaffNotAuthorizedException, IOException {
        if (!store.getStaffList().contains(staff) || !staff.getRole().equals(Role.CASHIER)){
            throw new StaffNotAuthorizedException("You are not authorized to perform this operation");
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
                throw new InvalidInputException("empty, negative, and null values are not allowed");
        }else
            throw new InvalidInputException("Product is can not be null");
    }
int itemtracker = 1;

    @Override
    public boolean removeProduct(Staff staff, int productId,  Store store) throws StaffNotAuthorizedException {
        if (!store.getStaffList().contains(staff) || !staff.getRole().equals(Role.CASHIER)){
            throw new StaffNotAuthorizedException("You are not authorized to perform this operation");
        }
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
    public boolean restockProduct(Staff staff, Product  productToRestock, int quantity,  Store store) throws StaffNotAuthorizedException {
        if (!store.getStaffList().contains(staff) || !staff.getRole().equals(Role.CASHIER)){
            throw new StaffNotAuthorizedException("You are not authorized to perform this operation");
        }
        boolean restockFlag = false;
       Product[] products = store.getProducts();
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
    public void processCustomersInQueue(Store store, Staff staff) throws StaffNotAuthorizedException, EmptyShoppingCartException, InsufficientFundException, IOException{
        if (!store.getStaffList().contains(staff) || !staff.getRole().equals(Role.CASHIER)){
            throw new StaffNotAuthorizedException("You are not authorized to perform this operation");
        }
        int count = 1;
        while(!store.getCustomersToAttend().isEmpty()){
            Customer customerInqueue = store.getCustomersToAttend().poll();
            sellProducts(store, customerInqueue);
            System.out.println(count +") "+customerInqueue.getName() +" "+customerInqueue.getShoppingCart().getProduct().getProductName()+" "+ customerInqueue.getShoppingCart().getQuantity()+" Sold");
            count++;
        }

    }

    public void sellProducts(Store store, Customer customer) throws InsufficientFundException, IOException, EmptyShoppingCartException, ProductNotFoundException {
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
            throw new ProductNotFoundException("this product "+productToBuy.getProductName()+" is not available");
        if(quantityInStore < quantityToBuy)
            throw new NoSuchQuantityAvailabe("Product quantity not available now");

            double productPrice = productToBuy.getProductPrice();
            double totalAmountOfProduct = (productPrice * quantityToBuy);
            customerWallet -= totalAmountOfProduct;
            customer.getWallet().setBalance(customerWallet);
            storeAccount += totalAmountOfProduct;
            store.setStoreAccount(storeAccount);
            productInStore.setProductQuantity(productInStore.getProductQuantity() - quantityToBuy);
            generateReceipt(customer.getShoppingCart(), totalAmountOfProduct, totalAmountOfProduct, customer);
    }
    //
    private String generateReceipt(Cart<Product, Integer> shoppingCart, double expectedAmount, double payment, Customer customer) throws IOException {
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
                "Balance:    "+(payment-expectedAmount)+"\n" ;

        try(FileWriter writer = new FileWriter(new File("receipt.txt")))
        {
            writer.write(receipt);
        }catch (IOException io){
            System.out.println("IO exception found");
        }
        return receipt;
    }
}
