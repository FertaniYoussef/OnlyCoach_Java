package com.onlycoach.entities;

import java.util.HashMap;
import java.util.Map;

public class Panier {
    private Map<Produits, Integer> items;
    private User user;

    public Panier(User user) {
        this.user = user;
        items = new HashMap<>();
    }

    public void addItem(Produits produit, int quantity) {
        if (items.containsKey(produit)) {
            int currentQuantity = items.get(produit);
            items.put(produit, currentQuantity + quantity);
        } else {
            items.put(produit, quantity);
        }
    }

    public void removeItem(Produits produit) {
        items.remove(produit);
    }

    public Map<Produits, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Produits, Integer> entry : items.entrySet()) {
            Produits produit = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += produit.getPrice() * quantity;
        }
        return totalPrice;
    }

    public int getTotalItems() {
        int totalItems = 0;
        for (int quantity : items.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}