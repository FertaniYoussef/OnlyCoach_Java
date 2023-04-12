package Models;


public class Feedback {

  private long id;
  private long userId;
  private String sujet;
  private String email;
  private String description;
  private java.sql.Date dateFeedback;
  private long status;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getSujet() {
    return sujet;
  }

  public void setSujet(String sujet) {
    this.sujet = sujet;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Date getDateFeedback() {
    return dateFeedback;
  }

  public void setDateFeedback(java.sql.Date dateFeedback) {
    this.dateFeedback = dateFeedback;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
