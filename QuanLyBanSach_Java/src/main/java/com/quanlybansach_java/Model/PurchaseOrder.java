package com.quanlybansach_java.Model;

import javafx.beans.property.*;

import java.util.Date;

public class PurchaseOrder {
    private IntegerProperty purchaseOrder_id;
    private IntegerProperty user_id;
    private IntegerProperty publisher_id;
    private ObjectProperty<Date> purchaseOrderDate;
    private StringProperty publisher_name;
    private StringProperty userUsername;
    private DoubleProperty totalAmount;
    public PurchaseOrder(int purchaseOrder_id, String userUsername, String publisher_name, Date purchaseOrderDate, double totalAmount) {
        this.purchaseOrder_id = new SimpleIntegerProperty(purchaseOrder_id);
        this.userUsername = new SimpleStringProperty(userUsername);
        this.publisher_name = new SimpleStringProperty(publisher_name);
        this.purchaseOrderDate = new SimpleObjectProperty<Date>(purchaseOrderDate);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
    }
    public PurchaseOrder(int purchaseOrder_id, int user_id, int publisher_id, Date purchaseOrderDate, String publisher_name, double totalAmount, String userUsername) {
        this.purchaseOrder_id = new SimpleIntegerProperty(purchaseOrder_id);
        this.user_id = new SimpleIntegerProperty(user_id);
        this.publisher_id = new SimpleIntegerProperty(publisher_id);
        this.purchaseOrderDate = new SimpleObjectProperty<Date>(purchaseOrderDate);
        this.publisher_name = new SimpleStringProperty(publisher_name);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
        this.userUsername = new SimpleStringProperty(userUsername);
    }
    public PurchaseOrder(int purchaseOrder_id, int user_id, int publisher_id, Date purchaseOrderDate) {
        this.purchaseOrder_id = new SimpleIntegerProperty(purchaseOrder_id);
        this.user_id = new SimpleIntegerProperty(user_id);
        this.publisher_id = new SimpleIntegerProperty(publisher_id);
        this.purchaseOrderDate = new SimpleObjectProperty<Date>(purchaseOrderDate);
    }
    public PurchaseOrder(int purchaseOrder_id, int publisher_id, Date purchaseOrderDate) {
        this.purchaseOrder_id = new SimpleIntegerProperty(purchaseOrder_id);
        this.publisher_id = new SimpleIntegerProperty(publisher_id);
        this.purchaseOrderDate = new SimpleObjectProperty<Date>(purchaseOrderDate);
    }
    public int getPurchaseOrder_id() {
        return purchaseOrder_id.get();
    }

    public Date getPurchaseOrderDate() {
        return purchaseOrderDate.get();
    }

    public int getPublisher_id() {
        return publisher_id.get();
    }

    public int getUser_id() {
        return user_id.get();
    }

    public String getPublisher_name() {
        return publisher_name.get();
    }

    public String getUserUsername() {
        return userUsername.get();
    }

    public double getTotalAmount() {
        return totalAmount.get();
    }

    public IntegerProperty purchaseOrder_idProperty() {
        return purchaseOrder_id;
    }

    public IntegerProperty publisher_idProperty() {
        return publisher_id;
    }

    public IntegerProperty user_idProperty() {
        return user_id;
    }

    public ObjectProperty<Date> purchaseOrderDateProperty() {
        return purchaseOrderDate;
    }

    public StringProperty publisher_nameProperty() {
        return publisher_name;
    }

    public StringProperty userUsernameProperty() {
        return userUsername;
    }
    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }
}
