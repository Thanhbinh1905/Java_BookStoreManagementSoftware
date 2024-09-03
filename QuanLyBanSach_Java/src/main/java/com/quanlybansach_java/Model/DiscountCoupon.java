package com.quanlybansach_java.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class DiscountCoupon {
    private StringProperty couponId;
    private DoubleProperty discountPercentage;

    public DiscountCoupon(String couponId, double discountPercentage) {
        this.couponId = new SimpleStringProperty(couponId);
        this.discountPercentage = new SimpleDoubleProperty(discountPercentage);
    }
    public String getCouponId() {
        return couponId.get();
    }
    public double getDiscountPercentage() {
        return discountPercentage.get();
    }
    public StringProperty CouponIdProperty() {return couponId;}
    public DoubleProperty DiscountPercentageProperty() {return discountPercentage;}
}

