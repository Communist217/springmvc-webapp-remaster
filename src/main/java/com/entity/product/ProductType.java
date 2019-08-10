package com.entity.product;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "Producttype")
public class ProductType implements Serializable {
    private int TypeID;
    private String Typename;
    private List<Product> productList;

    public ProductType(int typeID, String typename, List<Product> productList) {
        TypeID = typeID;
        Typename = typename;
        this.productList = productList;
    }

    public ProductType() { }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getTypename() {
        return Typename;
    }

    public void setTypename(String typename) {
        Typename = typename;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Producttype")
    @Cascade(CascadeType.ALL)
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
