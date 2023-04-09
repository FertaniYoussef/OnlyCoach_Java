/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlycoach;

import Entities.Offre;
import Services.OffreService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutOffreController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdiscount;
    @FXML
    private TextField tfprixFinal;
    @FXML
    private DatePicker tfdatedeb;
    @FXML
    private DatePicker tfdatefin;
    @FXML
    private Label LprixFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML 
private void AjoutOffre(ActionEvent event) {
    // Get the values from the text fields and date pickers
    String nom = tfnom.getText();
    float prix = Float.parseFloat(tfprix.getText());
    float discount = Float.parseFloat(tfdiscount.getText());
    java.sql.Date deb = java.sql.Date.valueOf(tfdatedeb.getValue());
    java.sql.Date fin = java.sql.Date.valueOf(tfdatefin.getValue());

    // Calculate the final price
    float prixFinal = prix - (prix * discount / 100);

    // Create a new Offre object with the entered values
    Offre offre = new Offre(nom, prix, discount, deb, fin, prixFinal);

    // Call the OffreService to add the new offer to the database
    OffreService offreService = new OffreService();
    offreService.ajouter(offre);

    // Clear the text fields and date pickers
    tfnom.setText("");
    tfprix.setText("");
    tfdiscount.setText("");
    tfprixFinal.setText("");
    tfdatedeb.setValue(null);
    tfdatefin.setValue(null);
}

    
}
