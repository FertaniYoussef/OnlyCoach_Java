/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Controller.DbConnector;
import entities.Event;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;
/**
 *
 * @author user
 */
public class ServiceEvent {

    
 
    private Connection cnx;
    private PreparedStatement ste;

    public ServiceEvent() {
        cnx = DbConnector.getInstance().getCon();
    }
    public void createEvent(Event E) {
        try {
            String req = "INSERT INTO `events`(`nom`,`categorie_id`,`date`,`heure`,`description`,`lieu`,`places_disponibles`,`cout`, `image`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, E.getNom());
            st.setInt(2, E.getCategorie().getId());
            st.setDate(3, E.getDate());
            st.setString(4, E.getHeure());
            st.setString(5, E.getDescription());
            st.setString(6, E.getLieu());
            st.setInt(7,E.getPlacesDisponibles());
            st.setDouble(8, E.getCout());
            st.setString(9, E.getImage());
            

            st.executeUpdate();
            System.out.println("evenement ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }


    public void modifyEvenement(Event E) {
        String sql = "UPDATE events SET `nom`=?,`categorie_id`=?,`date`=?,`heure`=?,`description`=?,`lieu`=?,`places_disponibles`=?,`cout`=?, `image`=? where id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);

            st.setString(1, E.getNom());
            st.setInt(2, E.getCategorie().getId());
            st.setDate(3, E.getDate());
            st.setString(4, E.getHeure());
            st.setString(5, E.getDescription());
            st.setString(6, E.getLieu());
            st.setInt(7,E.getPlacesDisponibles());
            st.setDouble(8, E.getCout());
            st.setString(9, E.getImage());
            st.setInt(10, E.getId());

            st.executeUpdate();
            System.out.println("modification avec succees !");
            System.out.println(E);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEvenement(Event e) {
        try {

            if (e.getId() != 0) {
                String sql = "delete from events WHERE id=?";
                String sql0 = "delete from participation WHERE evenement_id=?";
                PreparedStatement st = cnx.prepareStatement(sql0);
                st.setInt(1, e.getId());
                st.executeUpdate();
                PreparedStatement st1 = cnx.prepareStatement(sql);
                st1.setInt(1, e.getId());
                st1.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
    public List<Event> readEvenements() {
        ArrayList<Event> events = new ArrayList();
        String req = "SELECT * FROM events";
        ServiceCategorieEvent serCE=new ServiceCategorieEvent();
       
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Event e= new Event(rs.getInt(1), rs.getString(2), 
                                serCE.readCategorieById(rs.getInt(3)),rs.getDate(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getInt(8),rs.getDouble(9),rs.getString(10));
                e.setNomC(e.getCategorie().getCategorie());
                events.add(e
                        );

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }   
    
    
    public Event readEvenementsById(int id) {
        Event events = new Event();
        String req = "SELECT * FROM events where id="+id+"";
        ServiceCategorieEvent serCE=new ServiceCategorieEvent();
       
        try {
            Statement st;

            st= DbConnector.getInstance().getCon().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                events=
                        new Event(rs.getInt(1), rs.getString(2), 
                                serCE.readCategorieById(rs.getInt(3)),rs.getDate(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getInt(8),rs.getDouble(9),rs.getString(10));
                events.setNomC(events.getCategorie().getCategorie());
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }   
}

