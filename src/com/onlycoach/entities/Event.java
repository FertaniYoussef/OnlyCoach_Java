/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlycoach.entities;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author user
 */
public class Event {
    
    private int id;
    private String nom;
    private CategorieEvent categorie;
    private Date date;
    private String heure;
    private String description;
    private Set<Participation> eventParticipants;
    private String lieu;
    private int placesDisponibles;
    private double cout;
    private String image;

    
    public Event(int id, String nom, CategorieEvent categorie, Date date, String heure, String description, String lieu, int placesDisponibles, double cout,String image ) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.date = date;
        this.heure = heure;
        this.description = description;
        this.lieu = lieu;
        this.eventParticipants = new HashSet<>();
        this.placesDisponibles = placesDisponibles;
        this.cout = cout;
        this.image = image;
    }

    public Event() {
    }
    public void addParticipant(User user) {
        Participation participant = new Participation(user, this, new Date());
        eventParticipants.add(participant);
    }
    public void removeParticipant(User user) {
        for (Participation participation : eventParticipants) {
            if (participation.getUtilisateur().equals(user)) {
                eventParticipants.remove(participation);
                break;
            }
        }
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

    public CategorieEvent getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieEvent categorie) {
        this.categorie = categorie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public double getCout() {
        return cout;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", categorie=" + categorie.getCategorie() + ", date=" + date + ", heure=" + heure + ", description=" + description + ", lieu=" + lieu + ", placesDisponibles=" + placesDisponibles + ", cout=" + cout + ", image=" + image + '}';
    }
    
}

