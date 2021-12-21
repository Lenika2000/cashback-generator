package com.itmo.cashbackgenerator.model;

import java.util.Calendar;

public class Cashback {
    private String shopLogin;
    private String clientLogin;
    private String productName;
    private Double productPrice;
    private Calendar creationDate;

    public Cashback(String shopLogin, String clientLogin, String productName, Double productPrice, Calendar creationDate) {
        this.shopLogin = shopLogin;
        this.clientLogin = clientLogin;
        this.productName = productName;
        this.productPrice = productPrice;
        this.creationDate = creationDate;
    }

    public Cashback() {
    }

    public String getShopLogin() {
        return shopLogin;
    }

    public void setShopLogin(String shopLogin) {
        this.shopLogin = shopLogin;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Cashback{" +
                "shopLogin='" + shopLogin + '\'' +
                ", clientLogin='" + clientLogin + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", creationDate=" + creationDate +
                '}';
    }
}
