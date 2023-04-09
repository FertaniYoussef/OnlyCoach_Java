/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlycoach;

import Entities.Offre;
import Services.OffreService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DisplayOffreController implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private TextField TFSearchbar;
    @FXML
    private ComboBox<String> ComboGetOffreByCoach;
            OffreService os = new OffreService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherOffres();
         // Initialize the ComboBox with the names of the coaches
        List<String> coachNames = os.getAllCoachNames();
        ComboGetOffreByCoach.getItems().addAll(coachNames);
        
        // Set the event handler for the ComboBox
        
     //   ComboGetOffreByCoach.setOnAction(this::handleGetOffreByCoach);
     
       // List<Offre> OffersByCoach = os.SearchOffreByCoach(ComboGetOffreByCoach.getValue());
        // Display all offers
      //  afficherOffresC(os.SearchOffreByCoach(ComboGetOffreByCoach.getValue()));
        ComboGetOffreByCoach.setOnAction(event -> {
    // Retrieve the selected coach name
    String selectedCoach = ComboGetOffreByCoach.getSelectionModel().getSelectedItem();

    // Retrieve the offers related to the selected coach
    List<Offre> offers = os.SearchOffreByCoach(selectedCoach);

    // Clear existing offers from the VBox
    vBox.getChildren().clear();

    // Display the offers related to the selected coach
    afficherOffresC(offers);
});
    }  
    
    private void afficherOffresC(List<Offre> offres) {
               OffreService serviceOffre = new OffreService();
     try (    Connection cnx = DataSource.getInstance().getCnx();) {


        vBox.getChildren().clear(); // Clear the VBox of any existing offers

        for (Offre offre : offres) {
            HBox offreBox = createOffreBox(offre);
            vBox.getChildren().add(offreBox);
        }
           } catch (SQLException ex) {
        // Handle the exception
        ex.printStackTrace();
    }
    }
     public void afficherOffres() {
                 try (    Connection cnx = DataSource.getInstance().getCnx();) {

        OffreService serviceOffre = new OffreService();
        List<Offre> offres = serviceOffre.getAllOffres();

        for (Offre offre : offres) {
            HBox offreBox = createOffreBox(offre);
            vBox.getChildren().add(offreBox);
        }
           } catch (SQLException ex) {
        // Handle the exception
        ex.printStackTrace();
    }
    }
     private HBox createOffreBox(Offre offre) {
        HBox offreBox = new HBox();
        offreBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: black; -fx-border-width: 2px;");
        offreBox.setAlignment(Pos.CENTER_LEFT);
        offreBox.setPadding(new Insets(10));
        offreBox.setSpacing(10);
        offreBox.setPrefWidth(400);
        offreBox.setPrefHeight(100);

        Label title = new Label(offre.getNom());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setWrapText(true);

        Label price = new Label(String.format("Prix: %.2f", offre.getPrix()));
        price.setFont(Font.font("Arial", 14));
        price.setWrapText(true);

        Label discount = new Label(String.format("Discount: %.2f", offre.getDiscount()));
        discount.setFont(Font.font("Arial", 14));
        discount.setWrapText(true);

        offreBox.getChildren().addAll(title, price, discount);

        return offreBox;
    }


    private void handleGetOffreByCoach(ActionEvent event)  {
       // Retrieve the selected coach name
    String selectedCoach = ComboGetOffreByCoach.getSelectionModel().getSelectedItem();
        try (    Connection cnx = DataSource.getInstance().getCnx();) {

    // Retrieve the offers related to the selected coach
    List<Offre> offers = os.SearchOffreByCoach(selectedCoach);
    
    // Clear existing offers from the VBox
    vBox.getChildren().clear();
    
    // Display the offers related to the selected coach
    afficherOffresC(offers);
     } catch (SQLException ex) {
        // Handle the exception
        ex.printStackTrace();
    }
    }
   
    
}
