package Models;


public class Offre {

  private long id;
  private long idCoachId;
  private String nom;
  private double prix;
  private double discount;
  private java.sql.Date dateDeb;
  private java.sql.Date dateFin;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIdCoachId() {
    return idCoachId;
  }

  public void setIdCoachId(long idCoachId) {
    this.idCoachId = idCoachId;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }


  public java.sql.Date getDateDeb() {
    return dateDeb;
  }

  public void setDateDeb(java.sql.Date dateDeb) {
    this.dateDeb = dateDeb;
  }


  public java.sql.Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(java.sql.Date dateFin) {
    this.dateFin = dateFin;
  }

}
