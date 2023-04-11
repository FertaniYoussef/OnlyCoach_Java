package com.onlycoach;

import Service.ProduitService;
import entities.Produits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private Button consulterItembtn;

    @FXML
    private Button deletItemButton1;

    @FXML
    private ImageView imageContainer;


    @FXML
    private Label labelCategorie;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelTitre1;

    @FXML
    private Label labelValue;

    @FXML
    private Button modifyItem1;

    @FXML
    private VBox vbox;



    private int idProduit;
    private ProduitService produitService;
    private Image image;
    @FXML
    void handleDeleteItem(ActionEvent event) throws SQLException {


    }

    @FXML
    void handleModifyItem(ActionEvent event) {

    }

    @FXML
    void handleShowItem(ActionEvent event) throws IOException, SQLException {

    ProduitService produitService = new ProduitService();
    Produits produitchoisi = produitService.getProduit(idProduit);
        System.out.println(produitchoisi);
    ShowSelectedProduct showSelectedProduct = new ShowSelectedProduct();
    showSelectedProduct.setProduitSelected(produitchoisi);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProductSelected.fxml"));
    loader.setController(showSelectedProduct);

    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setData(Produits produit) throws SQLException {
        String image = produit.getImage();
File file = new File("src/main/resources/assets/"+image);
        Image image1 = new Image(file.toURI().toString());
        imageContainer.setImage(image1);
    labelTitre1.setText(produit.getName());
    labelDescription.setText(produit.getDescription());
    labelCategorie.setText(produit.getCategory().getNom());
labelValue.setText(String.valueOf(produit.getPrice()));
    if (produit.isStatus()) {
        labelStatus.setText("Disponible");
        labelStatus.setStyle("-fx-text-fill: green;");
    } else {
        labelStatus.setText("Indisponible");
        labelStatus.setStyle("-fx-text-fill: red;");
    }
    this.idProduit = produit.getId();




    }

}
