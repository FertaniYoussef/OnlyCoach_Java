/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlycoach;

import Entities.Commentaire;
import Services.CommentaireService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CommentaireBackController implements Initializable {

    @FXML
    private Button BDelete;
    @FXML
    private ListView<Commentaire> ListComment;
    private ObservableList<Commentaire> comments = FXCollections.observableArrayList();
    CommentaireService cs= new CommentaireService();
    @FXML
    private Label tfauthor;
    @FXML
    private Label tfContent;
    @FXML
    private Label tfDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                  comments.addAll(cs.getAllCommentaires());
    ListComment.setItems(comments);
ListComment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        tfauthor.setText(newSelection.getAuteur());
        tfContent.setText(newSelection.getContenu());
        tfDate.setText(String.valueOf(newSelection.getDate()));
    } 
});
        
     }    

    @FXML
    private void DeleteComment(ActionEvent event) {
          Commentaire selectedOffre = ListComment.getSelectionModel().getSelectedItem();
    if (selectedOffre != null) {
        cs.supprimerCommentaire(selectedOffre.getId()); // Assuming OffreService has a deleteOffre method that takes an id
        comments.remove(selectedOffre);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Comment deleted ");
    alert.setContentText("Votre Commentaire a été Supprimer avec succès.");
    alert.showAndWait();
    }
    }
 
}
