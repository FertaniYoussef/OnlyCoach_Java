/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.Entity.Categorie;

/**
 *
 * @author Mega-PC
 */
public class CategorieController {
    Connection connection;
    Statement st;
    PreparedStatement sta;
    
    public List<Categorie> getAllCategorie()
   {
        List<Categorie> Categorielist = new ArrayList<>();
        try {
            st = DataSource.openConnection().createStatement();
            ResultSet result =  st.executeQuery("SELECT * FROM Categorie");
            while(result.next())
            {
                 Categorielist.add(new Categorie(
                        result.getInt(1),
                        result.getString(2)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       return Categorielist;
    }
    public void SupprimerCategorie(int id)
    {
        try {
            st = DataSource.openConnection().createStatement();
            st.executeUpdate("Delete FROM `Categorie` WHERE id = " + id);
            DataSource.closeConnection();
        } catch (SQLException ex) {
            DataSource.closeConnection();
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
