/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Controller.DbConnector;
import entities.CategorieEvent;
import entities.Event;
import entities.Participation;
import entities.User;
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
public class ServiceParticipation {
    private Connection cnx;
    private PreparedStatement ste;

    public ServiceParticipation() {
        cnx = DbConnector.getInstance().getCon();
    }
    
    public void createPart (Participation E) {
        try {
            String req = "INSERT INTO `participation`(`evenement_id`,`user_id`,`date_inscription`) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, E.getIdEvenement().getId());
            st.setInt(2, E.getUtilisateur().getId());
            st.setDate(3, E.getDateInscription());

            st.executeUpdate();
            System.out.println("participation ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }
    
    
    public void deletePart(Participation e) {
        try {

            if (e.getId() != 0) {
                String sql = "delete from participation WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, e.getId());
                st.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public User readUserById(int id) {
        User user = new User();
        String req = "SELECT * FROM user where id="+id+"";
        ServiceCategorieEvent serCE=new ServiceCategorieEvent();
       
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                user=
                        new User(rs.getInt(1), rs.getString(2), 
                                rs.getString(3),rs.getInt(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }   
    //métier
    public List<Event> readEvenementsByUser(int id) {
        ArrayList<Participation> parts = new ArrayList(); 
        String lis="(";
        String req = "SELECT * FROM participation where user_id ="+id+"";
        ServiceEvent se=new ServiceEvent();
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                parts.add(
                        new Participation(rs.getInt(1),se.readEvenementsById(rs.getInt(2)), 
                                readUserById(rs.getInt(3)),rs.getDate(4)));

            }
        
        for(int i=0; i<parts.size();i++){
            lis=lis+parts.get(i).getIdEvenement();
        }
        lis=lis+")";
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
            
            
            
        ArrayList<Event> events = new ArrayList();
        String req1 = "SELECT * FROM evenement where id in"+lis+"";
        ServiceCategorieEvent serCE=new ServiceCategorieEvent();
       
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req1);
            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {

                events.add(
                        new Event(rs.getInt(1), rs.getString(2), 
                                serCE.readCategorieById(rs.getInt(3)),rs.getDate(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getInt(8),rs.getDouble(9),rs.getString(10)));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }   
    
    
    
}
