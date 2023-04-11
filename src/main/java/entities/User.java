package entities;

public class User {
    private int id;
    private String First_Name;
    private String Last_Name;
    private String picture;
    private int Tel;
    private String Email;
    private String Password;
    private String roles;
    private String Description;

    public User() {
    }

    public User(int id, String first_Name, String last_Name, String picture, int tel, String email, String password, String roles, String description) {
        this.id = id;
        First_Name = first_Name;
        Last_Name = last_Name;
        this.picture = picture;
        Tel = tel;
        Email = email;
        Password = password;
        this.roles = roles;
        Description = description;
    }

    public User(int id, String First_Name, String Last_Name, int Tel, String Email, String Password, String roles, String Description) {
        this.id = id;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.roles = roles;
        this.Description = Description;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getTel() {
        return Tel;
    }

    public void setTel(int tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", First_Name='" + First_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", picture=" + picture +
                ", Tel=" + Tel +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", roles='" + roles + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
