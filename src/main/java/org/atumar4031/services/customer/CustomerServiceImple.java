package org.atumar4031.services.customer;

import org.atumar4031.Store;
import org.atumar4031.constants.Category;
import org.atumar4031.exceptions.productNotAvailableException;
import org.atumar4031.exceptions.NoSuchQuantityAvailabe;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Product;

import java.util.Map;
import java.util.Set;

public class CustomerServiceImple implements CustomerService {
    public CustomerServiceImple(){}

    @Override
    public Map<Product, Integer> addProductToShoppingCart(String productName, Category productCategory, Store store, int quantityToBuy, Customer customer) throws productNotAvailableException, NoSuchQuantityAvailabe {
        if(productName.trim().isEmpty()) throw new productNotAvailableException("This product is not available in this store");
        Product[] productListFromStore = store.getProducts();
        Map<Product, Integer> myCart = customer.getShoppingCart();
        Product productToBuy = new Product();
        int productSearchTracker = 0;
        for (Product product: productListFromStore){
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                if(
                        product.getProductQuantity() > quantityToBuy
                        && product.getProductCategory().getColor().equalsIgnoreCase(productCategory.getColor())
                        && product.getProductCategory().getDescription().equalsIgnoreCase(productCategory.getDescription())
                        && product.getProductStatus().equalsIgnoreCase("Available")
                ) {productToBuy = product;break;}
            }
            productSearchTracker += 1;
        }
        if(productSearchTracker == productListFromStore.length){
            throw new productNotAvailableException("this product is not available now");
        }

        if(myCart.size() > 0){
            if(myCart.containsKey(productToBuy)){
                Set<Map.Entry<Product, Integer>> CartEntries = myCart.entrySet();
                for (Map.Entry<Product, Integer> CartEntry: CartEntries){
                    Product cartKey = CartEntry.getKey();
                    if(cartKey.equals(productToBuy)){
                        int cartValue = CartEntry.getValue();
                        myCart.replace(cartKey, cartValue, cartValue + quantityToBuy);
                       break;
                    } // action out of loop
                } // action out of loop
            }
            else {
                customer.getShoppingCart().put(productToBuy, quantityToBuy);
            }
        }else {
            customer.getShoppingCart().put(productToBuy, quantityToBuy);
        }
        return customer.getShoppingCart();
    }
}
