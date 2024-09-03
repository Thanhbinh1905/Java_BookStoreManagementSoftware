package com.quanlybansach_java.Model;

public class Publisher {
    private int publisherId;
    private String publisherName;

    public Publisher(int publisherId, String publisherName, String description) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}

