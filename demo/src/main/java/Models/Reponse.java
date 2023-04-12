package Models;


public class Reponse {

  private long id;
  private long idFeedbackId;
  private String texte;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIdFeedbackId() {
    return idFeedbackId;
  }

  public void setIdFeedbackId(long idFeedbackId) {
    this.idFeedbackId = idFeedbackId;
  }


  public String getTexte() {
    return texte;
  }

  public void setTexte(String texte) {
    this.texte = texte;
  }

}
