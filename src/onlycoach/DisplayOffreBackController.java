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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DisplayOffreBackController implements Initializable {

    @FXML
    private Label titleLbl;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label priceLbl;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Offre> offres = FXCollections.observableArrayList();
    @FXML
    private ListView<Offre> offreListView;
    
    OffreService os = new OffreService();
    @FXML
    private Button BDelete;
    @FXML
    private Button Bupdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          offres.addAll(os.getAllOffres());
    offreListView.setItems(offres);
    
    
    offreListView.setCellFactory(param -> new ListCell<Offre>() {
    @Override
    protected void updateItem(Offre item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null || item.getNom()== null) {
            setText(null);
        } else {
            setText(item.getNom());
        }
    }
});

offreListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        titleLbl.setText(newSelection.getNom());
        descriptionLbl.setText(String.valueOf(newSelection.getDiscount()));
        priceLbl.setText(String.valueOf(newSelection.getPrixFin()));
    } else {
        titleLbl.setText("");
        descriptionLbl.setText("");
        priceLbl.setText("");
    }
});

    }

   @FXML
private void DeleteOffre(ActionEvent event) {
    Offre selectedOffre = offreListView.getSelectionModel().getSelectedItem();
    if (selectedOffre != null) {
        os.supprimer(selectedOffre.getId()); // Assuming OffreService has a deleteOffre method that takes an id
        offres.remove(selectedOffre);
    }
}

    @FXML
    private void UpdateOffre(ActionEvent event) {
    }

    
    
    
    
    
    
}
