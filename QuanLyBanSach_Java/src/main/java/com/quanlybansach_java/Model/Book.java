package com.quanlybansach_java.Model;

import javafx.beans.property.*;

public class Book {
    private IntegerProperty bookId;
    private StringProperty title;
    private IntegerProperty authorId;
    private IntegerProperty publisherId;
    private StringProperty genre;
    private DoubleProperty purchase_price;
    private DoubleProperty price;
    private IntegerProperty quantityInStock;
    private StringProperty authorName;
    private StringProperty publisherName;

    public Book(int bookId, String title, int authorId, int publisherId, String genre, double purchase_price, double price, int quantityInStock, String authorName, String publisherName) {
        this.bookId = new SimpleIntegerProperty(bookId);
        this.title = new SimpleStringProperty(title);
        this.authorId = new SimpleIntegerProperty(authorId);
        this.publisherId = new SimpleIntegerProperty(publisherId);
        this.genre = new SimpleStringProperty(genre);
        this.purchase_price = new SimpleDoubleProperty(purchase_price);
        this.price = new SimpleDoubleProperty(price);
        this.quantityInStock = new SimpleIntegerProperty(quantityInStock);
        this.authorName = new SimpleStringProperty(authorName);
        this.publisherName = new SimpleStringProperty(publisherName);
    }
    public int getBookId() {
        return bookId.get();
    }
    public String getTitle() {
        return title.get();
    }
    public int getAuthorId() {
        return authorId.get();
    }

    public int getPublisherId() {
        return publisherId.get();
    }

    public double getPurchasePrice() {
        return purchase_price.get();
    }
    public String getGenre() {return genre.get();}
    public double getPrice() { return price.get();}
    public int getQuantityInStock() { return quantityInStock.get();}
    public String getAuthorName() {return authorName.get();}
    public String getPublisherName() { return publisherName.get();}

    public IntegerProperty getBookIdProperty() {
        return bookId;
    }

    public StringProperty getTitleProperty() {
        return title;
    }

    public void setTitle(StringProperty title) {
        this.title = title;
    }

    public IntegerProperty getAuthorIdProperty() {
        return authorId;
    }

    public void setAuthorId(IntegerProperty authorId) {
        this.authorId = authorId;
    }

    public IntegerProperty getPublisherIdProperty() {
        return publisherId;
    }

    public void setPublisherId(IntegerProperty publisherId) {
        this.publisherId = publisherId;
    }

    public StringProperty getGenreProperty() {
        return genre;
    }

    public void setGenre(StringProperty genre) {
        this.genre = genre;
    }

    public DoubleProperty getPriceProperty() {
        return price;
    }

    public void setPrice(DoubleProperty price) {
        this.price = price;
    }

    public IntegerProperty getQuantityInStockProperty() {
        return quantityInStock;
    }

    public void setQuantityInStock(IntegerProperty quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public StringProperty getAuthorNameProperty() { return authorName;}
    public StringProperty getPublisherNameProperty() { return publisherName;}
    public DoubleProperty getPurchase_priceProperty() {return purchase_price;}
}
