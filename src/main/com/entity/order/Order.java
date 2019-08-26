package main.com.entity.order;

import java.io.Serializable;

public class Order implements Serializable {
    private int OrderID;
    private String OrderDate;
    private String ShippedDate;
    private String RequiredDate;
    private int UserID;
    private String NoteFromCus;
    private String Comments;
    private String Status;
    private long Payment;
    private String PaymentMethod;

    public Order() { }

    public Order(String orderDate, String shippedDate, String requiredDate, int userID, String noteFromCus, String comments, String status, long payment, String paymentMethod) {
        OrderDate = orderDate;
        ShippedDate = shippedDate;
        RequiredDate = requiredDate;
        UserID = userID;
        NoteFromCus = noteFromCus;
        Comments = comments;
        Status = status;
        Payment = payment;
        PaymentMethod = paymentMethod;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(String shippedDate) {
        ShippedDate = shippedDate;
    }

    public String getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        RequiredDate = requiredDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getNoteFromCus() {
        return NoteFromCus;
    }

    public void setNoteFromCus(String noteFromCus) {
        NoteFromCus = noteFromCus;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public long getPayment() {
        return Payment;
    }

    public void setPayment(long payment) {
        Payment = payment;
    }

    public String getPaymentMethod() { return PaymentMethod; }

    public void setPaymentMethod(String paymentMethod) { PaymentMethod = paymentMethod; }
}
