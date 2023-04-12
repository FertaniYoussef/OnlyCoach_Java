package Models;


import java.util.ArrayList;

public class Cours {

  private long id;
  private String titre;
  private String description;
  private java.sql.Date dateCreation;
  private int nbVues;
  private String coursPhoto;
  private long idCoachId;

  private ArrayList<Sections> sectionsList = new ArrayList<Sections>();

  public ArrayList<Sections> getSectionsList() {
    return sectionsList;
  }

  public void setSectionsList(ArrayList<Sections> sectionsList) {
    this.sectionsList = sectionsList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public Cours() {
  }

  public Cours(long id, String titre, String description, java.sql.Date dateCreation, int nbVues, ArrayList<Sections> sectionsList) {
    this.id = id;
    this.titre = titre;
    this.description = description;
    this.dateCreation = dateCreation;
    this.nbVues = nbVues;
    this.sectionsList = sectionsList;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(java.sql.Date dateCreation) {
    this.dateCreation = dateCreation;
  }


  public int getNbVues() {
    return nbVues;
  }

  public void setNbVues(int nbVues) {
    this.nbVues = nbVues;
  }


  public String getCoursPhoto() {
    return coursPhoto;
  }

  public void setCoursPhoto(String coursPhoto) {
    this.coursPhoto = coursPhoto;
  }


  public long getIdCoachId() {
    return idCoachId;
  }

  public void setIdCoachId(long idCoachId) {
    this.idCoachId = idCoachId;
  }

  @Override
  public String toString() {
    return "Cours{" +
            "id=" + id +
            ", titre='" + titre + '\'' +
            ", description='" + description + '\'' +
            ", dateCreation=" + dateCreation +
            ", nbVues=" + nbVues +
            ", coursPhoto='" + coursPhoto + '\'' +
            ", idCoachId=" + idCoachId +
            ", sections=" + sectionsList +
            '}';
  }
}


