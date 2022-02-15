package org.atumar4031.product;

import org.atumar4031.exceptions.EmptyInputException;

public class Product {
    private int productId;
    private String name;
    private Category category;
    private int quantity;

    private double price;

    public Product() {
    }

    public Product(int productId, String name, double price, Category category) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) throws EmptyInputException {
        if(productId > 0)
            this.productId = productId;
        else
            throw new EmptyInputException("Invalid input");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmptyInputException {
        if(!name.isEmpty() && name.length() >= 3)
            this.name = name;
        else
            throw new EmptyInputException("Invalid input");
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) throws EmptyInputException {
        if(category != null)
            this.category = category;
        else
            throw new EmptyInputException("Invalid input");
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws EmptyInputException{

        if(quantity >= 1)
            this.quantity = quantity;
        else
            throw new EmptyInputException("Invalid input");
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", quantity=" + quantity +
                '}';
    }
}
