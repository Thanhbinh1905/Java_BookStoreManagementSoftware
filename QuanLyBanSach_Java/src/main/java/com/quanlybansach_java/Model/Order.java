package com.quanlybansach_java.Model;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import org.apache.poi.hpsf.Property;

import java.util.Date;

public class Order {
    private IntegerProperty orderId;
    private IntegerProperty userid;
    private IntegerProperty customerId;
    private StringProperty couponId;
    private ObjectProperty<Date> orderDate;
    private StringProperty customerFirstName;
    private StringProperty customerLastName;
    private DoubleProperty totalAmount;
    private StringProperty userUsername;
    public Order(int orderId, int userid, int customerId, String couponId, Date orderDate, String customerFirstName, String customerLastName, double totalAmount, String userUsername) {
        this.orderId = new SimpleIntegerProperty(orderId);
        this.userid = new SimpleIntegerProperty(userid);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.couponId = new SimpleStringProperty(couponId);
        this.orderDate = new SimpleObjectProperty<Date>(orderDate);
        this.customerFirstName = new SimpleStringProperty(customerFirstName);
        this.customerLastName = new SimpleStringProperty(customerLastName);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
        this.userUsername = new SimpleStringProperty(userUsername);
    }
    public Order(int orderId, int customerId, String couponId, Date orderDate) {
        this.orderId = new SimpleIntegerProperty(orderId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.couponId = new SimpleStringProperty(couponId);
        this.orderDate = new SimpleObjectProperty<Date>(orderDate);
    }
    public Order(int orderId, String couponId, Date orderDate, double totalAmount) {
        this.orderId = new SimpleIntegerProperty(orderId);
        this.couponId = new SimpleStringProperty(couponId);
        this.orderDate = new SimpleObjectProperty<Date>(orderDate);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
    }
    public int getOrderId() {
        return orderId.get();
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public String getCouponId() {
        return couponId.get();
    }

    public Date getOrderDate() {
        return orderDate.get();
    }
    public String getCustomerFirstName() {return customerFirstName.get();}
    public String getCustomerLastName() {return customerLastName.get();}
    public double gettotalAmount() {return totalAmount.get();}
    public IntegerProperty orderIdProperty() {return orderId;}
    public IntegerProperty customerIdProperty() {return customerId;}
    public StringProperty couponIdProperty() {return couponId;}
    public ObjectProperty<Date> orderDateProperty() {return orderDate;}
    public StringProperty customerFirstNameProperty() {return customerFirstName;}
    public StringProperty customerLastNameProperty() {return customerLastName;}
    public StringProperty customerNameProperty() {return new SimpleStringProperty(customerLastName.get() + " " + customerFirstName.get());}
    public DoubleProperty totalAmountProperty() {return totalAmount;}
    public StringProperty getUserUsernameProperty() {return userUsername;}
    public String getUserUsername() {return  userUsername.get();}

    public int getUserid() {
        return userid.get();
    }
}

