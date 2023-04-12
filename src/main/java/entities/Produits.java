package entities;

public class Produits {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int quantity;
    private boolean status;
    private CategorieProduit category;

    public Produits(int id, String name, String description, double price, String image, int quantity, boolean status, CategorieProduit category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.category = category;
    }
    public Produits() {};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CategorieProduit getCategory() {
        return category;
    }

    public void setCategory(CategorieProduit category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", category=" + category.getNom() +
                '}';
    }
}


