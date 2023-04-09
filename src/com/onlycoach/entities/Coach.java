package com.onlycoach.entities;
import javafx.scene.image.Image;
public class Coach extends User {

    private double price;
    private double rating;
    private Categorie categorie;

    public Coach(int id, String firstName, String lastName, Image picture, int tel, String email, String password, String roles, String description, double price, double rating, Categorie categorie) {
        super(id, firstName, lastName, picture, tel, email, password, roles, description);
        this.price = price;
        this.rating = rating;
        this.categorie = categorie;
    }

    // getters and setters for the additional attributes

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Categorie getCategorieId() {
        return categorie;
    }

    public void setCategorieId(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "price=" + price +
                ", rating=" + rating +
                ", categorie=" + categorie.getType() +
                '}';
    }
}