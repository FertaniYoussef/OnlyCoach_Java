package Interface;

import java.sql.SQLException;
import java.util.List;

public interface ICategorieProdService<T> {
    public int createCategorie(String t) throws SQLException;

    public void modifyCategorie(T t) throws SQLException;

    public void deleteCategorie(int t) throws SQLException;



    public T getCategorie(String t) throws SQLException;

    public List<T> getAllCategories() throws SQLException;
}
