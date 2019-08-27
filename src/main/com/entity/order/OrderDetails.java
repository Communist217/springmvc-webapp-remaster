package main.com.entity.order;

import main.com.entity.order.Composite_ID.Composite_ID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "OrderDetails")
@Table(name = "orderDetails")
public class OrderDetails implements Serializable {
    @EmbeddedId
    private Composite_ID composite_id;
    @Column(name = "Price", nullable = false, columnDefinition = "int default 1")
    private long Price;
    @Column(name = "Product_Quantity", nullable = false, columnDefinition = "int default 1")
    private int Quantity;

    public OrderDetails(Composite_ID composite_id, long price, int quantity) {
        this.composite_id = composite_id;
        Price = price;
        Quantity = quantity;
    }

    public OrderDetails() {
    }

    public Composite_ID getComposite_id() {
        return composite_id;
    }

    public void setComposite_id(Composite_ID composite_id) {
        this.composite_id = composite_id;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
