package com.onlycoach;

import Service.PanierService;
import Service.ProduitService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowSelectedProduct implements Initializable {

    @FXML
    private Button AjoutPanier;

    @FXML
    private Button ModifierProduit;

    @FXML
    private Button RetirerPanier;

    @FXML
    private Button SupprimerProd;

    @FXML
    private Button backToListButton;

    @FXML
    private ImageView imageContainer;

    @FXML
    private Label labelCategorie;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelTitre;

    @FXML
    private TextField quantitefield;

    private Produits produitSelected;
    public int produitId;
    @FXML
    private Label prix;

    public ShowSelectedProduct() {
    };
    public void setProduitSelected(Produits produitSelected) {

        this.produitSelected = produitSelected;
    }
    public Produits getProduitSelected() {
        return this.produitSelected;
    }
    @FXML
    void handleAjoutPanier(ActionEvent event) throws SQLException {
        ProduitService service = new ProduitService();
        PanierService panierService = new PanierService();
        int id = produitSelected.getId();
        int quantite = Integer.parseInt(quantitefield.getText());
        Panier panier = new Panier(produitSelected, quantite);

        try {
            int verif=panierService.ajouterProduit(panier);
            if (verif!=-1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Panier ajouté avec succès");
                alert.showAndWait();
            }
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Produit non ajouté");
            alert.showAndWait();
        }

    }

    @FXML
    void handleBackToList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowProducts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void handleDeletePanier(ActionEvent event) throws IOException {

    }
    @FXML
    void changePrice(InputMethodEvent event) {
        int quantite = Integer.parseInt(quantitefield.getText());
        double prixProduit = produitSelected.getPrice();
        double prixTotal = quantite * prixProduit;
        prix.setText(String.valueOf(prixTotal));

    }
    @FXML
    void handleDeleteProduit(ActionEvent event) throws SQLException, IOException {
      ProduitService  produitService= new ProduitService();
        produitService.deleteProduit(produitSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit supprimé avec succès");
        alert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("ShowProducts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void modifierProduit(ActionEvent event) throws IOException {
        ProduitService service = new ProduitService();

        AddProductController controller = new AddProductController();
        controller.setItemToUpdate(produitSelected);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (this.produitSelected != null) {
            RetirerPanier.setVisible(false);
           String image = this.produitSelected.getImage();
              File file = new File("src/main/resources/assets/"+image);



                Image image1 = new Image(file.toURI().toString());
                imageContainer.setImage(image1);
                labelTitre.setText(produitSelected.getName());
                labelDescription.setText(produitSelected.getDescription());
                labelCategorie.setText(produitSelected.getCategory().getNom());
                prix.setText(String.valueOf(produitSelected.getPrice()));
                 quantitefield.setText(String.valueOf(1));
                 produitId=produitSelected.getId();




        }
    }


}

