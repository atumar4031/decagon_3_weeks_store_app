package org.atumar4031.cashier;

import org.atumar4031.PhoneAndAccessoriesStore;
import org.atumar4031.customer.Customer;
import org.atumar4031.exceptions.InsufficientFundException;
import org.atumar4031.exceptions.NullProductException;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.product.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CashierService implements  iCashierService{
    public CashierService(){
    }
    @Override
    public Map<Product, Integer> addProductToStore(Product product, int quantity,  PhoneAndAccessoriesStore store) throws NullProductException {
        Map<Product, Integer> addProduct;
        if(product != null){
            store.setProducts(product, quantity);
            addProduct = store.getProducts();
        }else
            throw new NullProductException("Product is can not be null");

        return addProduct;
    }

    @Override
    public boolean removeProduct(int productId,  PhoneAndAccessoriesStore store) {
        boolean removeFlag = false;
        Map<Product, Integer>  products = store.getProducts();
         Set<Map.Entry<Product, Integer>> productEntrySet = products.entrySet();

        for (Map.Entry<Product, Integer> entry : productEntrySet) {
            Product product = entry.getKey();
            if (product.getProductId() == productId){
                products.remove(entry);
                removeFlag = !removeFlag;
                break;
            }
        }
        return removeFlag;
    }

    @Override
    public boolean restockProduct(int productId, int quantity,  PhoneAndAccessoriesStore store) {
        boolean restockFlag = false;
        Map<Product,Integer> products = store.getProducts();
        Set<Map.Entry<Product, Integer>> productEntrySet = products.entrySet();

        for (Map.Entry<Product, Integer> entry : productEntrySet) {
            Product product = entry.getKey();
            if (product.getProductId() == productId){
                    Product asKey = entry.getKey();
                    int prevalue = entry.getValue();
                    products.remove(asKey);
                    products.put(asKey, (prevalue + quantity));
                restockFlag = !restockFlag;
                break;
            }
        }
        return restockFlag;
    }

    @Override
    public void sellProducts(Cashier cashier, PhoneAndAccessoriesStore store, Customer customer) throws StaffNotAuthorizedException, InsufficientFundException, IOException {
        Map<Product,Integer> map = customer.getCart();
        double customerWallet =  customer.getWallet();
        double storeAccount = store.getStoreAccount();
        double totalAmount = 0.00;
        double totalAmountPerProduct = 0.00;

        if (cashier instanceof Cashier) {
            for (Map.Entry<Product,Integer> entry : map.entrySet()) {
                double productPrice = entry.getKey().getPrice();
                int  productQuantity = entry.getValue();
                totalAmountPerProduct = productPrice * productQuantity;
                totalAmount += totalAmountPerProduct;
            }
        } else {
            throw new StaffNotAuthorizedException(" You're not Authorized to perform this operation");
        }
        if (totalAmount > customerWallet) {
            throw  new InsufficientFundException("Insufficient Funds");
        }
        else {

            customerWallet -= totalAmount;
            customer.setWallet(customerWallet);
            storeAccount += totalAmount;
            storeAccount += totalAmount;
            store.setStoreAccount(storeAccount);
        }
        generateReceipt(map, totalAmount, storeAccount, customer, cashier);
        map.clear();
    }
    private String generateReceipt(Map<Product, Integer> cart, double expectedAmount, double payment, Customer customer, Cashier cashier) throws IOException {
        String receipt = "===== Thanks for patronizing " + customer.getName() + " ========\n" +
                "Transaction Details\n" +
                "=============================================\n";
        for( Map.Entry<Product, Integer> each: cart.entrySet()){
            receipt += "Product Name: "+ each.getKey().getName()+"\n" +
                    "Price       : "+ each.getKey().getPrice()+"\n" +
                    "Units       : "+ each.getValue()+"\n" +
                    "Cost        : "+ (each.getKey().getPrice() * (double) each.getValue())+"\n" +
                    "=============================================\n";
        }
        receipt += "Total Price: "+expectedAmount+"\n" +
                "Amount paid: "+payment+"\n" +
                "Balance:    "+(payment-expectedAmount)+"\n" +
                "Validated by "+cashier.getName();

        try(FileWriter writer = new FileWriter(new File("/constants/receipts/receipt.txt")))
        {
            writer.write(receipt);
        }catch (IOException io){
            System.out.println("IO exception found");
        }



        return receipt;
    }
}
