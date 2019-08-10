package com.entity.product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

//POJO product for table product in onlineshopdatabase
public class Product implements Serializable {
    private int ProductID;
    private String Productname;
    @ManyToOne
    @JoinColumn(name = "typeid", nullable = false)
    private int TypeID;
    private String Description;
    private long Stock;
    private long Popularity;
    private long Price;
    private String Imagesource;
    private String Warranty;

    public Product(int productID, String productname, int typeID, String description, long stock, long popularity, long price, String imagesource) {
        ProductID = productID;
        Productname = productname;
        TypeID = typeID;
        Description = description;
        Stock = stock;
        Popularity = popularity;
        Price = price;
        Imagesource = imagesource;
    }

    public Product() { }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public long getStock() {
        return Stock;
    }

    public void setStock(long stock) {
        Stock = stock;
    }

    public long getPopularity() {
        return Popularity;
    }

    public void setPopularity(long popularity) {
        this.Popularity = popularity;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public String getImagesource() {
        return Imagesource;
    }

    public void setImagesource(String imagesource) {
        Imagesource = imagesource;
    }

    public String getWarranty() {
        return Warranty;
    }

    public void setWarranty(String warranty) {
        Warranty = warranty;
    }

}
