package org.atumar4031.services.cashier;

import org.atumar4031.Store;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.*;
import java.io.*;
import java.util.*;
import java.util.Arrays;

public class CashierServiceImple implements CashierService {
    public CashierServiceImple(){
    }
    @Override
    public void addProductToStore(Cashier cashier, Product product, int quantity,  Store store)
            throws NullProductException, EmptyInputException, AutorizationException, IOException {
/* MAKE ASSUMPTIONS
*   1. What if Cashier pass empty product or -tive quantity --> done
*   2. Ensure the dats are correct before insert --> done
*   3. Ensure that only registered cashier can add product --> done;
* */
        if (!store.getCashierList().contains(cashier)){
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
//                System.out.println(writeFlag ?" Product inserted": "insertion failed");
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
                            && product.getProductCategory().getColor().equalsIgnoreCase(productToRestock.getProductCategory().getColor())
                            && product.getProductCategory().getDescription().equalsIgnoreCase(productToRestock.getProductCategory().getDescription())
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
    public void sellProducts(Cashier cashier, Store store, Customer customer) throws StaffNotAuthorizedException, InsufficientFundException, IOException, EmptyShoppingCartException {

        Product[] products = store.getProducts();
        Map<Product,Integer> customerShoppingCart = customer.getShoppingCart(); // naming
        double customerWallet =  customer.getCustomerWallet();
        double storeAccount = store.getStoreAccount();
        double totalAmount = 0.00;
        double totalAmountPerProduct = 0.00;
        Product productInCart = new Product();


        if (cashier.getRole().equals(Role.CASHIER) && store.getCashierList().contains(cashier)) {
            if (customer.getShoppingCart().size() == 0){
                throw new EmptyShoppingCartException("This shopping cart is empty");
            }
            for (Map.Entry<Product,Integer> shoppingCartEntry : customerShoppingCart.entrySet()) {
                productInCart = shoppingCartEntry.getKey();
                double productPrice = shoppingCartEntry.getKey().getProductPrice();
                int  productQuantity = shoppingCartEntry.getValue();
                totalAmountPerProduct = productPrice * productQuantity;
                totalAmount += totalAmountPerProduct;
                // reduce product quantity
                for (Product productInStore : store.getProducts()) {
                    // To ensure that I'm reducing the right product quantity from the store
                    if (
                            productInStore.getProductQuantity() > productInCart.getProductQuantity()
                            && productInStore.getProductName()
                            .equalsIgnoreCase(productInCart.getProductName())
                             && productInStore.getProductCategory().getColor()
                            .equalsIgnoreCase(productInCart.getProductCategory().getColor())
                            && productInStore.getProductCategory().getDescription()
                            .equalsIgnoreCase(productInCart.getProductCategory().getDescription())
                    ) productInStore.setProductQuantity(productInStore.getProductQuantity() - productQuantity);

                }
            }

            // ==============
            if (totalAmount > customerWallet) {
                throw  new InsufficientFundException("Insufficient Funds");
            } else {
                customerWallet -= totalAmount;
                customer.setCustomerWallet(customerWallet);
                storeAccount += totalAmount;
                store.setStoreAccount(storeAccount);
                generateReceipt(customerShoppingCart, totalAmount, storeAccount, customer, cashier);
                customerShoppingCart.clear();
            }
        } else {
            throw new StaffNotAuthorizedException(" You're not Authorized to perform this operation");
        }
    }
    private String generateReceipt(Map<Product, Integer> shoppingCart, double expectedAmount, double payment, Customer customer, Cashier cashier) throws IOException {
        String receipt = "===== Thanks for patronizing " + customer.getName() + " ========\n" +
                "Transaction Details\n" +
                "=============================================\n";
        for( Map.Entry<Product, Integer> cartItem: shoppingCart.entrySet()){
            receipt += "Product Name: "+ cartItem.getKey().getProductName()+"\n" +
                    "Price       : "+ cartItem.getKey().getProductPrice()+"\n" +
                    "Units       : "+ cartItem.getValue()+"\n" +
                    "Cost        : "+ (cartItem.getKey().getProductPrice() * (double) cartItem.getValue())+"\n" +
                    "=============================================\n";
        }
        receipt += "Total Price: "+expectedAmount+"\n" +
                "Amount paid: "+payment+"\n" +
                "Balance:    "+(payment-expectedAmount)+"\n" +
                "Validated by "+cashier.getName();

        try(FileWriter writer = new FileWriter(new File("receipt.txt")))
        {
            writer.write(receipt);
        }catch (IOException io){
            System.out.println("IO exception found");
        }
        return receipt;
    }
}
