package Models;


public class Abonnement {

  private long id;
  private long coachId;
  private long userId;
  private java.sql.Date dateDeb;
  private java.sql.Date dateFin;
  private double prix;
  private long isFav;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCoachId() {
    return coachId;
  }

  public void setCoachId(long coachId) {
    this.coachId = coachId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
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


  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }


  public long getIsFav() {
    return isFav;
  }

  public void setIsFav(long isFav) {
    this.isFav = isFav;
  }

}
