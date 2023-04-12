package Models;


import java.util.ArrayList;

public class Sections {

  private int id;
  private int coursId;
  private int indexSection;
  private String titre;
  private int nbresources;

  private ArrayList<Ressources> resourcesList = new ArrayList<Ressources>();

  public Sections() {
  }
  public Sections(int id, int coursId, int indexSection, String titre, int nbresources, ArrayList<Ressources> resourcesList) {
    this.id = id;
    this.coursId = coursId;
    this.indexSection = indexSection;
    this.titre = titre;
    this.nbresources = nbresources;
    this.resourcesList = resourcesList;
  }

  public ArrayList<Ressources> getResourcesList() {
    return resourcesList;
  }

  public void setResourcesList(ArrayList<Ressources> resourcesList) {
    this.resourcesList = resourcesList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getCoursId() {
    return coursId;
  }

  public void setCoursId(int coursId) {
    this.coursId = coursId;
  }


  public int getIndexSection() {
    return indexSection;
  }

  public void setIndexSection(int indexSection) {
    this.indexSection = indexSection;
  }


  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }


  public int getNbresources() {
    return nbresources;
  }

  public void setNbresources(int nbresources) {
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
            ", resourcesList=" + resourcesList +
            '}';
  }
}
