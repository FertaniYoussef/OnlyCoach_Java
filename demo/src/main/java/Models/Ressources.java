package Models;


public class Ressources {

  private int id;
  private int sectionsId;
  private String lien;
  private int indexRessources;
  private String description;


    public Ressources() {

    }

    public Ressources(int id, int sectionsId, String lien, int indexRessources, String description) {
        this.id = id;
        this.sectionsId = sectionsId;
        this.lien = lien;
        this.indexRessources = indexRessources;
        this.description = description;
    }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getSectionsId() {
    return sectionsId;
  }

  public void setSectionsId(int sectionsId) {
    this.sectionsId = sectionsId;
  }


  public String getLien() {
    return lien;
  }

  public void setLien(String lien) {
    this.lien = lien;
  }


  public int getIndexRessources() {
    return indexRessources;
  }

  public void setIndexRessources(int indexRessources) {
    this.indexRessources = indexRessources;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Ressources{" +
            "id=" + id +
            ", sectionsId=" + sectionsId +
            ", lien='" + lien + '\'' +
            ", indexRessources=" + indexRessources +
            ", description='" + description + '\'' +
            '}';
  }
}
