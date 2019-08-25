package main.com.entity.order;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    private int OrderID;
    private int ProductID;
    private long Price;

    public OrderDetails(int orderID, int productID, long price) {
        OrderID = orderID;
        ProductID = productID;
        Price = price;
    }

    public OrderDetails() {}

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }
}
