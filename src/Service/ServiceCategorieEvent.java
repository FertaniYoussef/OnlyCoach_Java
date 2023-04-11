/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Controller.DbConnector;
import entities.CategorieEvent;
import entities.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ServiceCategorieEvent {

    private Connection cnx;
    private PreparedStatement ste;
    public ServiceCategorieEvent() {
        cnx = DbConnector.getInstance().getCon();
    }
    
    public void createCat (CategorieEvent E) {
        System.out.println("test");
        try {
            String req = "INSERT INTO `categorie_event`(`categorie`) VALUES (?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, E.getCategorie());
            

            st.executeUpdate();
            System.out.println("cat ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }
    
    public void deleteCat(CategorieEvent e) {
        try {

            if (e.getId() != 0) {
                String sql = "delete from categorie_event WHERE id=?";
                String sql0 = "delete from events WHERE categorie_id=?";
                PreparedStatement st = cnx.prepareStatement(sql0);
                st.setInt(1, e.getId());
                st.executeUpdate();
                PreparedStatement st1 = cnx.prepareStatement(sql);
                st1.setInt(1, e.getId());
                st1.executeUpdate();
                System.out.println("catégorie supprimée !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public List<CategorieEvent> readCategories() {
        ArrayList<CategorieEvent> events = new ArrayList();
        String req = "SELECT * FROM categorie_event";
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                events.add(
                        new CategorieEvent(rs.getInt(1), rs.getString(2)));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }  
    
    public CategorieEvent readCategorieById(int id) {
        CategorieEvent ce=new CategorieEvent();
        String req = "SELECT * FROM categorie_event where id="+id+"";
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

               
                  ce=new CategorieEvent(rs.getInt(1), rs.getString(2));

            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ce;
    } 
    
    public CategorieEvent readCategorieByNom(String id) {
        CategorieEvent ce=new CategorieEvent();
        String req = "SELECT * FROM categorie_event where categorie='"+id+"'";
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

               
                  ce=new CategorieEvent(rs.getInt(1), rs.getString(2));

            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ce;
    }  
}
