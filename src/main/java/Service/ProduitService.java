package Service;

import com.onlycoach.DbConnector;
import entities.CategorieProduit;
import entities.Produits;
import Interface.IProduitService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IProduitService<Produits> {
    private Connection connection;
    public ProduitService(){
        connection= DbConnector.getInstance().getCon();
    }

    @Override
    public int createProduit(Produits produit) {
        int id = -1;
        String sql = "INSERT INTO produits (name, description, price,image ,quantity, categorie_id,status) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produit.getName());
            statement.setString(2, produit.getDescription());
            statement.setDouble(3, produit.getPrice());
            statement.setString(4, produit.getImage());
            statement.setInt(5, produit.getQuantity());
            statement.setInt(6, produit.getCategory().getId());
            statement.setBoolean(7, produit.isStatus());
            statement.executeUpdate();
            ResultSet res = statement.getGeneratedKeys();
            if (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    @Override
    public void modifyProduit(Produits produits)  {
        int id = -1;

String sql="UPDATE produits SET name=?, description=?, price=?, quantity=?,image=?, categorie_id=?,status=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produits.getName());
            statement.setString(2, produits.getDescription());
            statement.setDouble(3, produits.getPrice());
            statement.setInt(4, produits.getQuantity());
            statement.setString(5, produits.getImage());
            statement.setInt(6, produits.getCategory().getId());
            statement.setBoolean(7, produits.isStatus());
            statement.setInt(8, produits.getId());
            statement.executeUpdate();
            ResultSet res = statement.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void deleteProduit(int id) throws SQLException {
        String sql = "DELETE FROM produits WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                // handle the case where no rows were deleted
            }
        }
    }

    @Override
    public List<Produits> searchProduit(String query) throws SQLException {

        List<Produits> produits = new ArrayList<>();

        String sql = "SELECT * FROM produits WHERE name LIKE ? or description LIKE ? ORDER BY price DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");
            statement.setString(2, "%" + query + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

               int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String description=resultSet.getString("description");
                double price=resultSet.getDouble("price");
                String image=resultSet.getString("image");
                int quantity=resultSet.getInt("quantity");
                boolean status=resultSet.getBoolean("status");
                int categoryId = resultSet.getInt("categorie_id");
                CategorieProduit category = getCategory(categoryId);
             produits.add(new Produits(id,name,description,price,image,quantity,status,category));

            }
        }
        System.out.println(produits);
        return produits;
    }

    @Override
    public Produits getProduit(int id) throws SQLException {

        String sql = "Select * from produits where id=?";
        Produits produit = new Produits();
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                    produit.setId(resultSet.getInt("id"));
                    produit.setName(resultSet.getString("name"));
                    produit.setDescription(resultSet.getString("description"));
                    produit.setPrice(resultSet.getDouble("price"));
                    produit.setImage(resultSet.getString("image"));
                    produit.setQuantity(resultSet.getInt("quantity"));
                    produit.setStatus(resultSet.getBoolean("status"));
                   produit.setCategory(getCategory(resultSet.getInt("categorie_id")));

                    System.out.println(produit);
                }
            }

        }
        return produit;
    }
        public CategorieProduit getCategory(int categoryId) throws SQLException {
            CategorieProduit category = null;
            String sql = "SELECT * FROM categorie_produit WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, categoryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String categoryName = resultSet.getString("nom");
                        category = new CategorieProduit(categoryId, categoryName);
                    }
                }
            }

            return category;
        }

    @Override
    public List<Produits> getAllProduits() throws SQLException {
        List<Produits> produits = new ArrayList<>();
        String sql= "SELECT * FROM produits";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int categoryId = rs.getInt("categorie_id");
                CategorieProduit category = getCategory(categoryId);
                produits.add(new Produits(id, name, description, price, image, quantity, status, category));
            }
        }
        return produits;
    }

    @Override
    public List<Produits> getProduitCategory(String category) throws SQLException {
        List<Produits> produits = new ArrayList<>();
        String sql = "SELECT p.*,c.nom FROM produits p JOIN categorie_produit c ON p.categorie_id = c.id WHERE c.nom = ? ORDER BY p.price DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int categoryId = rs.getInt("categorie_id");
                CategorieProduit cat = getCategory(categoryId);
                produits.add(new Produits(id, name, description, price, image, quantity, status, cat));
            }
            }
        return produits;
    }
}
