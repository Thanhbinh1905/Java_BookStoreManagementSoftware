package com.quanlybansach_java.Model;

import javafx.beans.property.*;

public class OrderItems {
    private IntegerProperty orderDetailId;
    private IntegerProperty orderId;
    private IntegerProperty bookIdSold;
    private IntegerProperty quantity;
    private DoubleProperty subtotal;
    private StringProperty bookNameSold;
    private DoubleProperty bookPriceSold;

    public OrderItems(int orderDetailId, int orderId, int bookIdSold, int quantity, double subtotal, String bookNameSold, double bookPriceSold) {
        this.orderDetailId = new SimpleIntegerProperty(orderDetailId);
        this.orderId = new SimpleIntegerProperty(orderId);
        this.bookIdSold = new SimpleIntegerProperty(bookIdSold);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.bookNameSold = new SimpleStringProperty(bookNameSold);
        this.bookPriceSold = new SimpleDoubleProperty(bookPriceSold);
    }
    public int getOrderDetailId() {
        return orderDetailId.get();
    }
    public int getOrderId() {
        return orderId.get();
    }
    public int getBookIdSold() {
        return bookIdSold.get();
    }
    public int getQuantity() {
        return quantity.get();
    }
    public double getSubtotal() {
        return subtotal.get();
    }
    public String getBookNameSold() {return bookNameSold.get();}
    public double getBookPriceSold() {return bookPriceSold.get();}
    public IntegerProperty getOrderDetailIdProperty() {return orderDetailId;}
    public IntegerProperty getOrderIdProperty() {return orderDetailId;}
    public IntegerProperty getBookIdSoldProperty() {return orderDetailId;}
    public IntegerProperty getQuantityProperty() {return quantity;}
    public DoubleProperty getSubtotalProperty() {return subtotal;}
    public StringProperty getBookNameProperty() {return bookNameSold;}
    public DoubleProperty getBookPriceProperty() {return bookPriceSold;}
}

