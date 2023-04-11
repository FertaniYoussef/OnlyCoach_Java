package com.hamza.entities;

import java.time.LocalDate;

public class Sponsor {

    private int id;
    private String nom;
    private LocalDate date;
    private String description;
    private String image;

    public Sponsor(int id, String nom, LocalDate date, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.image = image;
    }

    public Sponsor(String nom, LocalDate date, String description, String image) {
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.image = image;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}