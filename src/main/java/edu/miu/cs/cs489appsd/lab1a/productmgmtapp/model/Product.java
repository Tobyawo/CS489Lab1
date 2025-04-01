package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;


public class Product {
    private long productId;
    private String name;
    private String dateSupplied;
    private int quantityInStock;
    private double unitPrice;

    public Product() {
    }

    public Product(long productId, String name, String dateSupplied, int quantityInStock, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public Product(Product other) {
        this.productId = other.productId;
        this.name = other.name;
        this.dateSupplied = other.dateSupplied;
        this.quantityInStock = other.quantityInStock;
        this.unitPrice = other.unitPrice;
    }

    public long getProductId() { return productId; }
    public String getName() { return name; }
    public String getDateSupplied() { return dateSupplied; }
    public int getQuantityInStock() { return quantityInStock; }
    public double getUnitPrice() { return unitPrice; }

    public void setProductId(long productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setDateSupplied(String dateSupplied) { this.dateSupplied = dateSupplied; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Date Supplied: %s | Stock: %d | Price: $%.2f",
                productId, name, dateSupplied, quantityInStock, unitPrice);
    }
}

