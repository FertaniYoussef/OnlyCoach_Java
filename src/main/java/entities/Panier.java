package entities;

public class Panier {
    private int id;

    private Produits produit;
    private int quantite;
    private User user;
    private boolean bought;

    public Panier( User user, Produits produit, int quantite) {
        this.user = user;
        this.produit = produit;
        this.quantite = quantite;
        this.bought= false;
    }
    public Panier(Produits produit , int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.bought= false;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produits getProduit() {
        return produit;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}