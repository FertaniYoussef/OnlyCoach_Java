package Interface;

import entities.Produits;

import java.sql.SQLException;
import java.util.List;

public interface IProduitService<T> {
    public int createProduit(T t) throws SQLException;

    public void modifyProduit(T t) throws SQLException;

    public void deleteProduit(int t) throws SQLException;

    public List<Produits> searchProduit(String query) throws SQLException;

    public T getProduit(int t) throws SQLException;

    public List<Produits> getAllProduits() throws SQLException;

    public List<Produits> getProduitCategory(String category) throws SQLException;


}
