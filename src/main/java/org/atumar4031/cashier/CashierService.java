package org.atumar4031.cashier;

import org.atumar4031.ElectronicsStore;
import org.atumar4031.exceptions.NullProductException;
import org.atumar4031.product.Product;

import java.util.Map;
import java.util.Set;

public class CashierService implements  iCashierService{
    ElectronicsStore store;
    public CashierService(ElectronicsStore store){
        this.store = store;
    }
    @Override
    public Map<Product, Integer> addProductToStore(Product product, int quantity) throws NullProductException {
        Map<Product, Integer> addProduct;
        if(product != null){
            store.setProducts(product, quantity);
            addProduct = store.getProducts();
        }else
            throw new NullProductException("Product is can not be null");

        return addProduct;
    }

    @Override
    public boolean removeProduct(int productId) {
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
    public boolean restockProduct(int productId, int quantity) {
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
}
