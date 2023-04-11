/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author user
 */
public class CategorieEvent {
    
    private int id;
    private String categorie;
    

    // Constructeur

    public CategorieEvent() {
    }

    public CategorieEvent(String categorie) {
        this.categorie = categorie;
    }
    
    

    public CategorieEvent(int id, String categorie) {
        this.id = id;
        this.categorie = categorie;
    }
    
    

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String nom) {
        this.categorie = nom;
    }

    @Override
    public String toString() {
        return "CategorieEvent{" + "id=" + id + ", categorie=" + categorie + '}';
    }

  

   
}

