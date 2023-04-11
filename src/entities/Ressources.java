package entities;

public class Ressources {

  private long id;
  private long sectionsId;
  private String lien;
  private long indexRessources;
  private String description;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSectionsId() {
    return sectionsId;
  }

  public void setSectionsId(long sectionsId) {
    this.sectionsId = sectionsId;
  }


  public String getLien() {
    return lien;
  }

  public void setLien(String lien) {
    this.lien = lien;
  }


  public long getIndexRessources() {
    return indexRessources;
  }

  public void setIndexRessources(long indexRessources) {
    this.indexRessources = indexRessources;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
