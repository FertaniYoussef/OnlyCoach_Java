/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlycoach.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Participation {
    
    private int id;
    private Event Evenement;
    private User user;
    private Date dateInscription;

    // Constructeur
    public Participation( User user,Event Evenement,  Date dateInscription) {

        this.Evenement = Evenement;
        this.user = user;
        this.dateInscription = dateInscription;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getIdEvenement() {
        return Evenement;
    }

    public void setIdEvenement(Event idEvenement) {
        this.Evenement = idEvenement;
    }

    public User getUtilisateur() {
        return user;
    }

    public void setUtilisateur(User idUtilisateur) {
        this.user = user;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", idEvenement=" + Evenement.getNom() + ", Utilisateur=" + user.getFirst_Name() +" "+ user.getLast_Name() + ", dateInscription=" + dateInscription + '}';
    }
    
}

