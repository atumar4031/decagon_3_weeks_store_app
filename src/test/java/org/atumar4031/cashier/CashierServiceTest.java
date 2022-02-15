package org.atumar4031.cashier;

import org.atumar4031.ElectronicsStore;
import org.atumar4031.constants.Gender;
import org.atumar4031.exceptions.NullProductException;
import org.atumar4031.product.Category;
import org.atumar4031.product.Product;
import org.atumar4031.utilities.Applicant;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class CashierServiceTest {
    private CashierService cashierService;
    private Product product;
    private Category category;
    private ElectronicsStore store;
    private List<Applicant> applicantList;
    private Applicant applicant1;
    private Applicant applicant2;
    LocalDateTime t;
    @Before
    public void setUp() throws Exception {
        t = LocalDateTime.now();
        store = new ElectronicsStore();
        cashierService = new CashierService(store);
        category = new Category("Television","Black","Lead display Screen");
        product = new Product(101, "Samsung 1012", category);
        applicantList = new ArrayList<>();
        applicant1 = new Applicant("Umar","umar@gmail.com",
                "08166666666","Yar  yara",
                "MSc",30, Gender.MALE, 2);
        applicant2 = new Applicant("Asmau","asmau@gmail.com",
                "08166616752","T wada",
                "BSC",30,Gender.FEMALE, 2);
    }

    @Test
    public void toCheckIfProductIsAddedToStore() throws NullProductException {
        //given
        Map<Product,Integer> result = cashierService.addProductToStore(product, 2);
        //when
        assertTrue(store.getProducts().size() > 0);
        //then
    }

    @Test
    public void isProductListIncreasing() throws NullProductException {
        //given
        int beforSize = store.getProducts().size();
        Map<Product,Integer> result = cashierService.addProductToStore(product , 3);
        int afterSize = store.getProducts().size();
        //when
        assertTrue(beforSize < afterSize);
        //then
    }

    @Test
    public void isProductRemoved() throws NullProductException {
        cashierService.addProductToStore(product,3);
        boolean removeFlag  = cashierService.removeProduct(product.getProductId());
        assertTrue(removeFlag);
    }
    @Test
    public void isProductRestocked() throws NullProductException {
        cashierService.addProductToStore(product,3);
        boolean restock = cashierService.restockProduct(product.getProductId(), 10);
        assertTrue(restock);

    }
    @Test
    public void checkProductQuantityAfterRestock() throws NullProductException {
        int quantityBeforeRestock = 3;
        int quantityAfterRestock = 0;
        cashierService.addProductToStore(product,quantityBeforeRestock);
        boolean restock = cashierService.restockProduct(product.getProductId(), 10);
        Map<Product, Integer> products = store.getProducts();
        Set<Map.Entry<Product, Integer>> entrySet = products.entrySet();
        for (Map.Entry<Product, Integer> e: entrySet){
            if(e.getKey().getProductId() == product.getProductId()){
                quantityAfterRestock = e.getValue();
            }
        }
        assertTrue(quantityBeforeRestock < quantityAfterRestock);

    }
}