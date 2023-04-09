/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlycoach;

import Entities.Commentaire;
import Services.CommentaireService;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CommentaireController implements Initializable {

    @FXML
    private TextArea TFContent;
    @FXML
    private TextField TFname;
    @FXML
    private Button Bajout;

    /**
     * Initializes the controller class.
     */
    CommentaireService cs = new CommentaireService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddComment(ActionEvent event) {
         Commentaire commentaire = new Commentaire();
        commentaire.setContenu(TFContent.getText());
        commentaire.setAuteur(TFname.getText());
        commentaire.setDate(new Date());
        
        cs.AjouterComment(commentaire);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Comment ajouté ");
    alert.setContentText("Votre Commentaire a été Ajouter avec succès.");
    alert.showAndWait();
        
        // Clear fields after adding the comment
        TFContent.clear();
        TFname.clear();
    }
    
}
