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
import pidev.Entity.Coach;
import pidev.Entity.User;

/**
 *
 * @author Mega-PC
 */
public class CoachController {
    Connection connection;
    Statement st;
    PreparedStatement sta;
    
    public List<Coach> getAllCoachs()
   {
        List<Coach> Coachlist = new ArrayList<>();
        try {
            st = DataSource.openConnection().createStatement();
            ResultSet result =  st.executeQuery("SELECT * FROM coach AS c " +
                    "RIGHT JOIN user AS u ON c.id_user_id = u.id " +
                    "RIGHT JOIN categorie AS x ON c.categorie_id = x.id " +
                    "WHERE c.id_user_id = u.id " +
                    "AND c.categorie_id = x.id");
            while(result.next())
            {
                 Coachlist.add(new Coach(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        new User(
                            result.getInt("u.id"),
                            result.getString("u.email"),
                            result.getString("u.nom"),
                            result.getString("u.picture"),
                            result.getString("u.description"),
                            result.getString("u.phone")
                        ),
                        new Categorie(
                                result.getInt("x.id"),
                                result.getString("x.type")
                        ),
                        result.getString(6),
                        result.getString(7),
                        result.getFloat(8),
                        result.getFloat(9)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       return Coachlist;
   }
     public void SupprimerCoach(int id)
    {
        try {
            st = DataSource.openConnection().createStatement();
            st.executeUpdate("Delete FROM `coach` WHERE id = " + id);
            DataSource.closeConnection();
        } catch (SQLException ex) {
            DataSource.closeConnection();
            Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
