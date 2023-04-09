/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlycoach;

import Entities.Offre;
import Services.OffreService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateOffreController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdiscount;
    @FXML
    private DatePicker tfdeb;
    @FXML
    private DatePicker tffin;
    @FXML
    private Button BUpdate;
    @FXML
    private ComboBox<String> CGetOffreName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               

        OffreService os = new OffreService();
        List<String> offerNames = os.getAllOfferNames();
        CGetOffreName.getItems().addAll(offerNames);
           
        CGetOffreName.setOnAction(event -> {
            String selectedOfferName = CGetOffreName.getValue();
            if (selectedOfferName != null) {
                OffreService offreService = new OffreService();
                Offre offre = offreService.getOffreByName(selectedOfferName);
                if (offre != null) {
                    tfnom.setText(offre.getNom());
                    tfprix.setText(Float.toString(offre.getPrix()));
                       tfdiscount.setText(Integer.toString((int) offre.getDiscount()));
                  //  tfdeb.setValue(LocalDate.parse( offre.getDeb()));
                //    tffin.setValue(LocalDate.parse(offre.getFin()));
                    //                tfdeb.setValue(offre.getDeb());
                    //              tffin.setValue(offre.getFin());
                }
            }
        });
       
    }    

@FXML
private void updateOffer(ActionEvent event) {
    // Get the values from the text fields and date pickers
    String nom = tfnom.getText();
    float prix = Float.parseFloat(tfprix.getText());
    float discount = Float.parseFloat(tfdiscount.getText());
    LocalDate deb =  tfdeb.getValue();
    LocalDate fin = tffin.getValue();
    String offreName = CGetOffreName.getValue();
    
   float prixFinal = prix - (prix * discount / 100);

    // Get the offer by name
    OffreService os = new OffreService();
    Offre offre = os.getOffreByName(offreName);
    int idOffre = os.getOfferIdByName(offreName);
    
        //conversion de type LocalDate 
     java.sql.Date ddeb = java.sql.Date.valueOf(deb);
     java.sql.Date dfin = java.sql.Date.valueOf(fin);
    
      // Update the offer
       offre.setNom(nom);
      offre.setPrix(prix);
      offre.setDiscount(discount);


      offre.setDeb(ddeb);
     offre.setFin(dfin);
      offre.setPrixFin(prixFinal);
     // Save the updated offer
       os.modifier(idOffre,offre);
    
    // Show a success message
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Offre mise à jour");
    alert.setContentText("L'offre a été mise à jour avec succès.");
    alert.showAndWait();
}


    
}
