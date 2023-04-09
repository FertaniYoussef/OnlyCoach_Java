/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlycoach.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Commentaire {
 public String auteur ; 
 public Date date ; 
 public int id ; 
 public String contenu ;

 public int  id_course; //foreignKey in table cours 

    public Commentaire(String auteur, Date date, int id, String contenu, int id_course) {
        this.auteur = auteur;
        this.date = date;
        this.id = id;
        this.contenu = contenu;
        this.id_course = id_course;
    }

    public Commentaire() {
    }
    
    

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public int getId_course() {
        return id_course;
    }
 

    public Commentaire(String auteur, Date date, int id, String contenu) {
        this.auteur = auteur;
        this.date = date;
        this.id = id;
        this.contenu = contenu;
    }

    public Commentaire(String auteur, Date date, String contenu) {
        this.auteur = auteur;
        this.date = date;
        this.contenu = contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Commentaire de  " + auteur +  ", son contenu contenu=" + contenu + ", date=" + date ;
    }
 
 
}
