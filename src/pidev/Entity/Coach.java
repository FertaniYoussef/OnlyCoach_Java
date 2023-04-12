/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.Entity;

/**
 *
 * @author Mega-PC
 */
public class Coach {
    private int id;
    private String nom;
    private String prenom;
    private User id_user_id;
    private Categorie categorie_id;
    private String picture;
    private String description;
    private float prix;
    private float rating;

    public Coach() {
    }

    
    public Coach(int id, String nom, String prenom, User id_user_id, Categorie categorie_id, String picture, String description, float prix, float rating) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.id_user_id = id_user_id;
        this.categorie_id = categorie_id;
        this.picture = picture;
        this.description = description;
        this.prix = prix;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public User getId_user_id() {
        return id_user_id;
    }

    public void setId_user_id(User id_user_id) {
        this.id_user_id = id_user_id;
    }

    public Categorie getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(Categorie categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    
}
