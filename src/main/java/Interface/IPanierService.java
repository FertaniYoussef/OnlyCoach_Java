package Interface;

import java.sql.SQLException;
import java.util.List;

public interface IPanierService<T> {
    public int ajouterProduit(T t) throws SQLException;

    public void modifierQuantite(T t) throws SQLException;

    public void supprimerProduit(T t) throws SQLException;
    public void viderPanier(T t) throws SQLException;
    public List<T> getPanier(int t) throws SQLException;
    public void passerCommande(T t) throws SQLException;


}
