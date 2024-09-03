package com.quanlybansach_java.Model;

import javafx.beans.property.*;

public class PurchaseOrderItems {
    private IntegerProperty item_id;
    private IntegerProperty purchaseOrder_id;
    private IntegerProperty bookId;
    private IntegerProperty quantity;
    private DoubleProperty total_amount;
    private StringProperty bookNameSold;
    private DoubleProperty bookBuyingPriceSold;
    public PurchaseOrderItems (int item_id, int purchaseOrder_id, int bookId, int quantity, double total_amount) {
        this.item_id = new SimpleIntegerProperty(item_id);
        this.purchaseOrder_id = new SimpleIntegerProperty(purchaseOrder_id);
        this.bookId = new SimpleIntegerProperty(bookId);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.total_amount = new SimpleDoubleProperty(total_amount);
    }
    public PurchaseOrderItems (int item_id, int purchaseorder_id, int bookId, int quantity, double total_amount, String bookNameSold, double bookBuyingPriceSold) {
        this.item_id = new SimpleIntegerProperty(item_id);
        this.purchaseOrder_id = new SimpleIntegerProperty(purchaseorder_id);
        this.bookId = new SimpleIntegerProperty(bookId);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.total_amount = new SimpleDoubleProperty(total_amount);
        this.bookNameSold = new SimpleStringProperty(bookNameSold);
        this.bookBuyingPriceSold = new SimpleDoubleProperty(bookBuyingPriceSold);
    }

    public double getTotal_amount() {
        return total_amount.get();
    }

    public int getItem_id() {
        return item_id.get();
    }

    public int getBookId() {
        return bookId.get();
    }

    public int getPurchaseOrder_id() {
        return purchaseOrder_id.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public double getBookBuyingPriceSold() {
        return bookBuyingPriceSold.get();
    }

    public String getBookNameSold() {
        return bookNameSold.get();
    }

    public IntegerProperty bookIdProperty() {
        return bookId;
    }

    public IntegerProperty item_idProperty() {
        return item_id;
    }

    public IntegerProperty purchaseOrder_idProperty() {
        return purchaseOrder_id;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public DoubleProperty bookBuyingPriceSoldProperty() {
        return bookBuyingPriceSold;
    }

    public StringProperty bookNameSoldProperty() {
        return bookNameSold;
    }

    public DoubleProperty total_amountProperty() {
        return total_amount;
    }
}
