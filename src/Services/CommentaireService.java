package Services;

import Entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

public class CommentaireService {
    
    private Connection cnx;
    
    public CommentaireService() {
        cnx = DataSource.getInstance().getCnx();
    }

public void AjouterComment(Commentaire c) {
    String req = "INSERT INTO commentaire (auteur, date, contenu) VALUES (?,?,?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getAuteur());
        ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        ps.setString(3, c.getContenu());
      //  ps.setInt(4, c.getId_course());
        ps.executeUpdate();
        System.out.println("Comment added successfully");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    public void modifierCommentaire(Commentaire c) {
        try {
            String req = "UPDATE commentaire SET auteur = ?, date = ?, contenu = ? WHERE id = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getAuteur());
            st.setDate(2, new java.sql.Date(c.getDate().getTime()));
            st.setString(3, c.getContenu());
            st.setInt(4, c.getId());
            st.executeUpdate();
            System.out.println("Commentaire modifié avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerCommentaire(int id) {
        try {
            String req = "DELETE FROM commentaire WHERE id = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Commentaire supprimé avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Commentaire> getAllCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();
        try {
            String req = "SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                c.setAuteur(rs.getString("auteur"));
                c.setDate(rs.getDate("date"));
                c.setContenu(rs.getString("contenu"));
               // c.setId_course(rs.getInt("id_course"));
                commentaires.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return commentaires;
    }
}
