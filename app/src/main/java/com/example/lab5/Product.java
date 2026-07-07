package com.example.lab5;

public class Product {
    private int id;
    private String productName;
    private int sku;

    // Constructeur par défaut
    public Product() {}

    // Constructeur avec paramètres
    public Product(String productName, int sku) {
        this.productName = productName;
        this.sku = sku;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }
}