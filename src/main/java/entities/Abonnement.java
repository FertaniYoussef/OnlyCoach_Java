package entities;

import java.util.Date;

public class Abonnement {
    private int id;
    private User user;
    private Coach coach;
    private Date date_debut;
    private Date date_fin;

    public Abonnement(User user, Coach coach, Date date_debut, Date date_fin) {
        this.user = user;
        this.coach = coach;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
