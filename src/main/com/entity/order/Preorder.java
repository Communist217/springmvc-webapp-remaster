package main.com.entity.order;

import java.io.Serializable;

public class Preorder implements Serializable {
    private int PreorderID;
    private int UserID;
    private int ProductID;
    private int Quantity;

    public Preorder(int preorderID, int userID, int productID) {
        PreorderID = preorderID;
        UserID = userID;
        ProductID = productID;
    }

    public Preorder() {}

    public int getPreorderID() {
        return PreorderID;
    }

    public void setPreorderID(int preorderID) {
        PreorderID = preorderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
