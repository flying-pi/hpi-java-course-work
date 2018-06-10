package edu.kpi.domain;

import java.util.List;

public class Order {
    private int orderNumber;
    private String  userId;
    private String  card;
    private String  address;
    private List<CartItem>  items;

    public Order() {
    }

    public Order(int _orderNumber, Order src) {
        this(_orderNumber, src.getUserId(), src.getCard(), src.getAddress(), src.getItems());
    }

    public Order(int orderNumber, String userId, String card, String address, List<CartItem> items) {
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.card = card;
        this.address = address;
        this.items = items;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
