/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Offre {
        public int id ;
        public String nom ;
        public float prix ;
        public float discount ;
        public Date  deb ;
        public Date fin;
        public float prixFin;
        public int coachId;

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public Offre(String nom, float prix, float discount, Date deb, Date fin, float prixFin, int coachId) {
        this.nom = nom;
        this.prix = prix;
        this.discount = discount;
        this.deb = deb;
        this.fin = fin;
        this.prixFin = prixFin;
        this.coachId = coachId;
    }

    public Offre(int id, String nom, float prix, float discount, Date deb, Date fin, float prixFin, int coachId) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.discount = discount;
        this.deb = deb;
        this.fin = fin;
        this.prixFin = prixFin;
        this.coachId = coachId;
    }

    public Offre(int id, String nom, float prix, float discount, Date deb, Date fin, float prixFin) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.discount = discount;
        this.deb = deb;
        this.fin = fin;
        this.prixFin = prixFin;
    }

    public Offre() {
    }
    

    public Offre(String nom, float prix, float discount, Date deb, Date fin, float prixFin) {
        this.nom = nom;
        this.prix = prix;
        this.discount = discount;
        this.deb = deb;
        this.fin = fin;
        this.prixFin = prixFin;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public float getPrix() {
        return prix;
    }

    public float getDiscount() {
        return discount;
    }

    public Date getDeb() {
        return deb;
    }

    public Date getFin() {
        return fin;
    }

    public float getPrixFin() {
        return prixFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setDeb(Date deb) {
        this.deb = deb;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setPrixFin(float prixFin) {
        this.prixFin = prixFin;
    }
    
}
