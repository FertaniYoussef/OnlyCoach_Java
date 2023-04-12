package controllers;

import Models.Cours;
import Models.Ressources;
import Models.Sections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.connectionController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.connectionController.getConnection;

public class CoursUtils {

    Connection connection;
    private static PreparedStatement statement;
    private static PreparedStatement statement2;
    private static PreparedStatement statement3;


    // add photo later when you fix the file uploader
    public void modifyCourse(Cours cours) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE cours SET titre = ?, description = ? WHERE id = ?");
            statement.setString(1, cours.getTitre());
            statement.setString(2, cours.getDescription());
            statement.setInt(3, (int) cours.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cours> getAllCours() throws SQLException {
        try {
            connection = getConnection();
            ObservableList<Cours> cours = FXCollections.observableArrayList();
            statement = connection.prepareStatement("SELECT * FROM cours");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cours course = new Cours();
                course.setId(resultSet.getInt("id"));
                course.setTitre(resultSet.getString("titre"));
                course.setDescription(resultSet.getString("description"));
                course.setDateCreation(resultSet.getDate("date_creation"));
                course.setNbVues(resultSet.getInt("nb_vues"));
                course.setCoursPhoto(resultSet.getString("cours_Photo"));
                course.setIdCoachId(resultSet.getInt("id_coach_id"));
                statement2 = connection.prepareStatement("SELECT * FROM sections WHERE cours_id = ?");
                statement2.setInt(1, (int)course.getId());
                ResultSet res = statement2.executeQuery();
                while (res.next()) {
                    Sections section = new Sections();
                    section.setId(res.getInt("id"));
                    section.setCoursId(res.getInt("cours_id"));
                    section.setTitre(res.getString("titre"));
                    course.getSectionsList().add(section);

                    // get ressources
                    PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM ressources WHERE sections_id = ?");
                    statement3.setInt(1, (int)section.getId());
                    ResultSet res2 = statement3.executeQuery();
                    while (res2.next()) {
                        Ressources resource = new Ressources();
                        resource.setId(res2.getInt("id"));
                        resource.setSectionsId(res2.getInt("sections_id"));
                        resource.setDescription(res2.getString("description"));
                        resource.setLien(res2.getString("lien"));
                        resource.setIndexRessources(res2.getInt("index_ressources"));
                         section.getResourcesList().add(resource);
                    }
                }
                cours.add(course);

            }
            // resultSet = statement.executeQuery();
            return cours;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void deleteSection(int ids) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = connectionController.getConnection();
        try {
            stmt = conn.prepareStatement("DELETE FROM sections WHERE id = ?");
            stmt.setInt(1, ids);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        // Redirect to another page or update UI as needed
    }

    public void modifySection(int ids, String title, String link, String description) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = connectionController.getConnection();
        try {
            stmt = conn.prepareStatement("UPDATE sections s JOIN ressources r ON r.sections_id = s.id SET s.titre = ?, r.lien = ?, r.description = ? WHERE s.id = ?");
            stmt.setString(1, title);
            stmt.setString(2, link);
            stmt.setString(3, description);
            stmt.setInt(4, ids);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        // Redirect to another page or update UI as needed
    }

    public void addSection(Sections section) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO sections (cours_id, titre) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, section.getCoursId());
            statement.setString(2, section.getTitre());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            // insert resources
            if (rs.next()) {
                int sectionId = rs.getInt(1);
                for (Ressources resource : section.getResourcesList()) {
                    statement2 = connection.prepareStatement("INSERT INTO ressources (sections_id, description, lien, index_ressources) VALUES (?, ?, ?, ?)");
                    statement2.setInt(1, sectionId);
                    statement2.setString(2, resource.getDescription());
                    statement2.setString(3, resource.getLien());
                    statement2.setInt(4, resource.getIndexRessources());
                    statement2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCours(Cours cours) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM cours WHERE id = ?");
            statement.setInt(1, (int) cours.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
