package entities;
public class Categorie {
    private int id;
    private String type;
    private Coach coach;

    public Categorie(String type, Coach coach) {
        this.type = type;
        this.coach = coach;
    }

    // getters and setters for all attributes

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "type='" + type + '\'' +
                ", coach=" + coach +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
