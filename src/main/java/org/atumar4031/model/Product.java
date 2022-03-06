package org.atumar4031.model;

import org.atumar4031.enums.Category;
import org.atumar4031.exceptions.InvalidInputException;

public class Product {
    private int productId;
    private String productName;
    private int productQuantity;
    private Category productCategory;
    private double productPrice;
    private String productStatus;

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Product() {

    }

    public Product(int productId, String productName, double productPrice, int productQuantity, Category productCategory, String productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) throws InvalidInputException {
        if(productId > 0)
            this.productId = productId;
        else throw new InvalidInputException("Invalid input");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) throws InvalidInputException {
        if(productName.length() >= 3) this.productName = productName;
        else throw new InvalidInputException("Invalid input");
    }
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) throws InvalidInputException {
        if(productPrice > 0.00 )
             this.productPrice = productPrice;
        else throw new InvalidInputException("Invalid input");
    }
    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) throws InvalidInputException {
        if(productCategory != null)
            this.productCategory = productCategory;
        else
            throw new InvalidInputException("Invalid input");
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productCategory=" + productCategory +
                ", productStatus=" + productStatus +
                '}';
    }
}
