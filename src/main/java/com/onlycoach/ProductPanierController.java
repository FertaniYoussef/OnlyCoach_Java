package com.onlycoach;

import Service.PanierService;
import entities.Panier;
import entities.Produits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductPanierController implements Initializable {

    @FXML
    private Label NomProduit;
    @FXML
    private ImageView imageContainer;
    @FXML
    private Label PrixLabel;

    @FXML
    private ImageView deleteButton;

    @FXML
    private TextField quantityfield;
    private Panier panier;
    private PanierService panierService;

    private Image image;
    @FXML
    void deletePanier(MouseEvent event) throws SQLException, IOException {
        panierService = new PanierService();
        panierService.supprimerProduit(panier);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit supprimé avec succès");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void handleQuantity(ActionEvent event) throws SQLException {
        panierService = new PanierService();
        quantityfield.textProperty().addListener((observable, oldValue, newValue)-> {

            panier.setQuantite(Integer.parseInt(quantityfield.getText()));
            try {
                panierService.modifierQuantite(panier);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            PrixLabel.setText(String.valueOf(panier.getProduit().getPrice()*panier.getQuantite()));

            System.out.println(panier.getQuantite());
        });



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public  void setData(Panier panier) throws SQLException {
        NomProduit.setText(panier.getProduit().getName());
        PrixLabel.setText(String.valueOf(panier.getProduit().getPrice()*panier.getQuantite()));
        quantityfield.setText(String.valueOf(panier.getQuantite()));
        panierService = new PanierService();
       String img= panier.getProduit().getImage();
       File file = new File("src/main/resources/assets/"+img);
         image = new Image(file.toURI().toString());
        imageContainer.setImage(image);

        this.panier=panier;

    }
}
