package main.com.entity.order.Composite_ID;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Composite_ID implements Serializable {
    @NotNull
    private int OrderID;
    @NotNull
    private int ProductID;


    public Composite_ID(int orderID, int productID) {
        OrderID = orderID;
        ProductID = productID;
    }

    public Composite_ID() { }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Composite_ID)) return false;
        Composite_ID that = (Composite_ID) obj;
        return Objects.equals(getOrderID(), that.getOrderID()) &&
                Objects.equals(getProductID(), that.getProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderID(), getProductID());
    }

}
