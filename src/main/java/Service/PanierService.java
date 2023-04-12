package Service;

import Interface.IPanierService;
import com.onlycoach.DbConnector;
import entities.CategorieProduit;
import entities.Panier;
import entities.Produits;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierService implements IPanierService<Panier> {

    private Connection connection;
    public PanierService(){
        connection= DbConnector.getInstance().getCon();
    }

    @Override
    public int ajouterProduit(Panier panier) throws SQLException {
        int id = -1;
        String sql = "INSERT INTO panier (user_id, produit_id, quantite) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 24);
            statement.setInt(2, panier.getProduit().getId());
            statement.setInt(3, panier.getQuantite());
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
    public void modifierQuantite(Panier panier) throws SQLException {
    String sql = " Update panier set quantity=? where user_id=? and produit_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, panier.getQuantite());
            statement.setInt(2, panier.getUser().getId());
            statement.setInt(3, panier.getProduit().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimerProduit(Panier panier) throws SQLException {
    String sql = "DELETE FROM panier WHERE user_id=? and produit_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, panier.getUser().getId());
            statement.setInt(2, panier.getProduit().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void viderPanier(Panier panier) throws SQLException {
    String sql = "DELETE FROM panier WHERE user_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, panier.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Panier> getPanier(int user_id) throws SQLException {
        String sql = "SELECT * FROM panier WHERE user_id=?";
        List<Panier> paniers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                User user = getUserById(user_id);
                Produits produit = getProduitById(res.getInt("produit_id"));
                int quantity = res.getInt("quantite");

                paniers.add(new Panier(user, produit, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return paniers;
    }

    @Override
    public void passerCommande(Panier panier) throws SQLException {

    }
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                user = new User();
                user.setId(res.getInt("id"));
                user.setLast_Name(res.getString("last_name"));
                user.setFirst_Name(res.getString("first_name"));
                user.setEmail(res.getString("email"));
                user.setPicture(res.getString("picture"));
                user.setTel(res.getInt("tel"));
                user.setRoles(res.getString("roles"));
                user.setPassword(res.getString("password"));
                user.setDescription(res.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;

    }
    public Produits getProduitById(int id) throws SQLException {
        String sql = "SELECT * FROM produits WHERE id=?";
        Produits produit = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                produit.setId(res.getInt("id"));
                produit.setName(res.getString("name"));
                produit.setDescription(res.getString("description"));
                produit.setPrice(res.getDouble("price"));
                produit.setQuantity(res.getInt("quantity"));
                produit.setImage(res.getString("image"));
                produit.setCategory(getCategorieById(res.getInt("category_id")));
                produit.setStatus(res.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produit;
    }

    private CategorieProduit getCategorieById(int category_id) {
        String sql = "SELECT * FROM categories WHERE id=?";
        CategorieProduit categorie = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category_id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                categorie.setId(res.getInt("id"));
                categorie.setNom(res.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorie;
    }

}
