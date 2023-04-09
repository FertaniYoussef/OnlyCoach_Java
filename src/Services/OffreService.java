/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Offre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author ASUS
 */
public class OffreService {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Offre o) {

        try {
//Missing where close to affect the offer to the Coach logged
            String query = "INSERT INTO offre ( nom, prix, discount,date_deb,date_fin,prix_fin) VALUES ( ?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = cnx.prepareStatement(query);

            statement.setString(1, o.getNom());
            statement.setFloat(2, o.getPrix());
            statement.setFloat(3, o.getDiscount());
            statement.setDate(4, (Date) o.getDeb());
            statement.setDate(5, (Date) o.getFin());
            statement.setFloat(6, o.getPrixFin());
        
            statement.executeUpdate();
            System.out.println("Offre ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //this method with the CoachId ajouterOffrrelatedTOCoach
    public void ajouterOC(Offre o, int idCoach) {
    try {
        String query = "INSERT INTO offre (nom, prix, discount, date_deb, date_fin, prix_fin, id_coach) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = cnx.prepareStatement(query);

        statement.setString(1, o.getNom());
        statement.setFloat(2, o.getPrix());
        statement.setFloat(3, o.getDiscount());
        statement.setDate(4, (Date) o.getDeb());
        statement.setDate(5, (Date) o.getFin());
        statement.setFloat(6, o.getPrixFin());
        statement.setInt(7, idCoach);

        statement.executeUpdate();
        System.out.println("Offre ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    
    public List<Offre> getAllOffres() {
    List<Offre> offres = new ArrayList<>();
    String query = "SELECT * FROM offre";
    try (PreparedStatement statement = cnx.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            float prix = resultSet.getFloat("prix");
            float discount = resultSet.getFloat("discount");
            Date dateDeb = resultSet.getDate("date_deb");
            Date dateFin = resultSet.getDate("date_fin");
            float prixFin = resultSet.getFloat("prix_fin");

            Offre offre = new Offre(id, nom, prix, discount, dateDeb, dateFin, prixFin);
            offres.add(offre);
        }
    } catch (SQLException ex) {
        System.out.println("Error while retrieving all offres: " + ex.getMessage());
    }
    return offres;
}
    
    public List<Offre> getOffresByIdCoach(int idCoach) {
    List<Offre> offres = new ArrayList<>();
    String query = "SELECT * FROM offre WHERE id_coach = ?";
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, idCoach);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            float prix = resultSet.getFloat("prix");
            float discount = resultSet.getFloat("discount");
            Date dateDeb = resultSet.getDate("date_deb");
            Date dateFin = resultSet.getDate("date_fin");
            float prixFin = resultSet.getFloat("prix_fin");

            Offre offre = new Offre(id, nom, prix, discount, dateDeb, dateFin, prixFin);
            offres.add(offre);
        }
    } catch (SQLException ex) {
        System.out.println("Error while retrieving all offres: " + ex.getMessage());
    }
    return offres;
}
    
    public void supprimer(int id) {
    try {
        String query = "DELETE FROM offre WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Offre supprimée !");
        } else {
            System.out.println("Aucune offre trouvée avec cet identifiant !");
        }
    } catch (SQLException ex) {
        System.out.println("Error while deleting offre: " + ex.getMessage());
    }
}

public void modifier(int id, Offre o) {
    try {
        String query = "UPDATE offre SET nom = ?, prix = ?, discount = ?, date_deb = ?, date_fin = ?, prix_fin = ? WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(query);

        statement.setString(1, o.getNom());
        statement.setFloat(2, o.getPrix());
        statement.setFloat(3, o.getDiscount());
        statement.setDate(4, (Date) o.getDeb());
        statement.setDate(5, (Date) o.getFin());
        statement.setFloat(6, o.getPrixFin());
        statement.setInt(7, id);

        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Offre modifiée !");
        } else {
            System.out.println("Aucune offre trouvée avec cet identifiant !");
        }
    } catch (SQLException ex) {
        System.out.println("Error while updating offre: " + ex.getMessage());
    }
}

public List<Offre> searchOffreByName(String name) {
    List<Offre> offres = new ArrayList<>();
    String query = "SELECT * FROM offre WHERE nom LIKE ?";
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            float prix = resultSet.getFloat("prix");
            float discount = resultSet.getFloat("discount");
            Date dateDeb = resultSet.getDate("date_deb");
            Date dateFin = resultSet.getDate("date_fin");
            float prixFin = resultSet.getFloat("prix_fin");

            Offre offre = new Offre(id, nom, prix, discount, dateDeb, dateFin, prixFin);
            offres.add(offre);
        }
    } catch (SQLException ex) {
        System.out.println("Error while retrieving offres by name: " + ex.getMessage());
    }
    return offres;
}

public List<String> getAllOfferNames() {
    List<String> offreName = new ArrayList<>();
    try {
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT nom FROM offre";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            offreName.add(rs.getString("nom"));
        }
      //  rs.close();
        //st.close();
       // conn.close();
    } catch (SQLException ex) {
        System.out.println("Error while getting coach names: " + ex.getMessage());
    }
    return offreName;
}
 
public List<String> getAllCoachNames() {
    List<String> coachNames = new ArrayList<>();
    try {
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT nom FROM coach";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            coachNames.add(rs.getString("nom"));
        }
    //    rs.close();
     //   st.close();
     //   conn.close();
    } catch (SQLException ex) {
        System.out.println("Error while getting coach names: " + ex.getMessage());
    }
    return coachNames;
}

    public List<Offre> SearchOffreByCoach(String coachName) {
    Connection cnx = DataSource.getInstance().getCnx();
    List<Offre> offres = new ArrayList<>();

    String query = "SELECT * FROM offre o JOIN coach c ON o.id_coach_id = c.id WHERE c.nom = ?";
    try {
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setString(1, coachName);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("o.id");
            String nom = rs.getString("o.nom");
            float prix = rs.getFloat("o.prix");
            float discount = rs.getFloat("o.discount");
            Date deb = rs.getDate("o.date_deb");
            Date fin = rs.getDate("o.date_fin");
            float prixFinal = rs.getFloat("o.prixfin");

            Offre offre = new Offre(nom, prix, discount, deb, fin, prixFinal);
            offre.setId(id);
            offres.add(offre);
        }

    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return offres;
}
public int getOfferIdByName(String name) {
    int coachId = -1;
    String query = "SELECT id FROM offre WHERE nom = ?";
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            coachId = resultSet.getInt("id");
        }
    } catch (SQLException ex) {
        System.out.println("Error while retrieving offer id: " + ex.getMessage());
    }
    return coachId;
}
public Offre getOffreByName(String offreName) {
    try {
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM offre WHERE nom = ?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setString(1, offreName);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Offre offre = new Offre();
            offre.setId(rs.getInt("id"));
            offre.setNom(rs.getString("nom"));
            offre.setPrix(rs.getFloat("prix"));
            offre.setDiscount(rs.getInt("discount"));
            
            offre.setDeb(rs.getDate("date_deb"));
            offre.setFin(rs.getDate("date_fin"));
            offre.setCoachId(rs.getInt("id_coach_id"));
            return offre;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}


    
}
