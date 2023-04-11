package Service;

import com.onlycoach.DbConnector;
import entities.CategorieProduit;
import Interface.ICategorieProdService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieProdService implements ICategorieProdService<CategorieProduit> {

    private Connection connection;
    public CategorieProdService(){
        connection= DbConnector.getInstance().getCon();
    }

    @Override
    public int createCategorie(String categorieProduit) throws SQLException {
        int id = -1;
        String sql="INSERT INTO categorie_produit (nom) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, categorieProduit);
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
    public void modifyCategorie(CategorieProduit categorieProduit) throws SQLException {
        String sql = " Update categorie_produit set nom=? where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categorieProduit.getNom());
            statement.setInt(2, categorieProduit.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategorie(int t) throws SQLException {
        String sql = "DELETE FROM categorie_produit WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, t);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public CategorieProduit getCategorie(String t) {
        String sql = "SELECT * FROM categorie_produit WHERE nom=?";
        CategorieProduit categorieProduit = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, t);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                categorieProduit = new CategorieProduit(res.getInt("id"), res.getString("nom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorieProduit;
    }

    @Override
    public List<CategorieProduit> getAllCategories() {
        String sql = "SELECT * FROM categorie_produit";
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                System.out.println(res.getInt("id") + " " + res.getString("nom"));
                CategorieProduit temp=new CategorieProduit(res.getInt("id"), res.getString("nom"));

                categorieProduits.add(temp);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(categorieProduits);
        return categorieProduits;
    }
}
