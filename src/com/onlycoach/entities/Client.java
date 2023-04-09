package com.onlycoach.entities;
import javafx.scene.image.Image;

public class Client extends User {

    public Client(int id, String firstName, String lastName, Image picture, int tel, String email, String password, String roles, String description) {
        super(id, firstName, lastName, picture, tel, email, password, roles, description);
    }

    public Client() {
    }

}