package org.example;

public class Product {
    private String productName;
    private String barCode;
    private float price;

    Product(String productName, String barCode, float price){
        this.productName = productName;
        this.barCode = barCode;
        this.price = price;
    }
    public String getBarCode(){
        return barCode;
    }
    public float getPrice(){
        return price;
    }
    public String getProductName(){
        return productName;
    }
}
