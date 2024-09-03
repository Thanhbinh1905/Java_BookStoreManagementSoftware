package com.quanlybansach_java.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String permission;
    private IntegerProperty userIdProperty;
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty nameProperty;
    private StringProperty phoneProperty;
    private StringProperty emailProperty;
    private StringProperty permissionProperty;
    public User(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public User(int userId, String username, String password, String name, String phone, String email, String permission) {
        this.userIdProperty = new SimpleIntegerProperty(userId);
        this.usernameProperty = new SimpleStringProperty(username);
        this.passwordProperty = new SimpleStringProperty(password);
        this.nameProperty = new SimpleStringProperty(name);
        this.phoneProperty = new SimpleStringProperty(phone);
        this.emailProperty = new SimpleStringProperty(email);
        this.permissionProperty = new SimpleStringProperty(permission);
    }
    public IntegerProperty getUserIdProperty() {
        return userIdProperty;
    }
    public int getUserId() {
        return userIdProperty.get();
    }
    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }
    public StringProperty getPasswordProperty() {
        return passwordProperty;
    }
    public StringProperty getNameProperty() {
        return nameProperty;
    }
    public StringProperty getPhoneProperty() {
        return phoneProperty;
    }
    public StringProperty getEmailProperty() {
        return emailProperty;
    }
    public StringProperty getPermissionProperty() {
        return permissionProperty;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getName1() {
        return nameProperty.get();
    }
    public String getPhone1() {
        return phoneProperty.get();
    }
    public String getEmail1() {
        return emailProperty.get();
    }
    public String getUsername() {
        return usernameProperty.get();
    }
    public String getPassword() {
        return passwordProperty.get();
    }

    public String getPermission() {
        return permissionProperty.get();
    }
}

