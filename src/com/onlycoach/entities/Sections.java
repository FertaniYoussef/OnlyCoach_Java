package com.onlycoach.entities;

public class Sections {

  private long id;
  private long coursId;
  private long indexSection;
  private String titre;
  private long nbresources;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCoursId() {
    return coursId;
  }

  public void setCoursId(long coursId) {
    this.coursId = coursId;
  }


  public long getIndexSection() {
    return indexSection;
  }

  public void setIndexSection(long indexSection) {
    this.indexSection = indexSection;
  }


  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }


  public long getNbresources() {
    return nbresources;
  }

  public void setNbresources(long nbresources) {
    this.nbresources = nbresources;
  }

  @Override
  public String toString() {
    return "Sections{" +
            "id=" + id +
            ", coursId=" + coursId +
            ", indexSection=" + indexSection +
            ", titre='" + titre + '\'' +
            ", nbresources=" + nbresources +
            '}';
  }
}
