package Models;


public class Coach {

  private long id;
  private String nom;
  private String prenom;
  private long idUserId;
  private long categorieId;
  private String picture;
  private String description;
  private double prix;
  private double rating;


  public long getId() {
    return id;
  }

  public void setId(long id) {
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


  public long getIdUserId() {
    return idUserId;
  }

  public void setIdUserId(long idUserId) {
    this.idUserId = idUserId;
  }


  public long getCategorieId() {
    return categorieId;
  }

  public void setCategorieId(long categorieId) {
    this.categorieId = categorieId;
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


  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }


  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

}
