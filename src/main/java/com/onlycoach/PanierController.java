package com.onlycoach;

import Service.PanierService;
import Service.ProduitService;
import entities.Panier;
import entities.Produits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PanierController implements Initializable {

    @FXML
    private Text btnGoToCoaches;

    @FXML
    private Text btnGoToCourses;

    @FXML
    private Text btnGoToProducts;

    @FXML
    private Button consulterItembtn;

    @FXML
    private Button deletItemButton1;

    @FXML
    private ImageView imageUser1;

    @FXML
    private Label labelCategorie;

    @FXML
    private Label labelCategorie1;

    @FXML
    private Label labelMail;

    @FXML
    private Label labelMail1;

    @FXML
    private Label labelTitre1;

    @FXML
    private Label labelTitre11;

    @FXML
    private Button modifyItem1;

    @FXML
    private Button myAccountButton1;

    @FXML
    private GridPane productGrid;

    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private VBox vbox;

    private PanierService panierService;
    private ObservableList<Panier> paniers = FXCollections.observableArrayList();


    @FXML
    void goToProducts(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProducts.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToCourses(MouseEvent event) {

    }

    @FXML
    void handleDeleteItem(ActionEvent event) {

    }

    @FXML
    void handleGoToCoaches(MouseEvent event) {

    }

    @FXML
    void handleModifyItem(ActionEvent event) {

    }

    @FXML
    void handleShowItem(ActionEvent event) {

    }

    @FXML
    void routeToAccountInformationUser(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    panierService = new PanierService();
        try {
         paniers.setAll(panierService.getPanierByUser(24));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            updateGrid(paniers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void updateGrid(List<Panier> filteredProduits) throws SQLException, IOException {

        productGrid.getChildren().clear();
        Label noResultsLabel = new Label();
        HBox vbox= new HBox();
        if (filteredProduits.isEmpty()) {
            productGrid.setVisible(false);
            scrollPane1.setVisible(false);
            noResultsLabel.setVisible(true);
            noResultsLabel.setAlignment(Pos.CENTER);
            noResultsLabel.setStyle("-fx-font-size: 14; -fx-text-fill: red;-fx-background-color: red");
            vbox.setStyle("-fx-background-color: red");
            noResultsLabel.setText("Aucun produit choisi");
            vbox.getChildren().add(noResultsLabel);
            productGrid.add(vbox, 1, 1);

        }else {
            int col = 0;
            int row = 1;
            scrollPane1.setVisible(true);
            productGrid.setVisible(true);
            noResultsLabel.setVisible(false);

            for (Panier produit : filteredProduits) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProductPanier.fxml"));
                HBox vBox = fxmlLoader.load();
                ProductPanierController productController = fxmlLoader.getController();
                productController.setData(produit);
                if (col == 1) {
                    col = 0;
                    row++;
                }

                productGrid.add(vBox, col++, row);
                productGrid.setMargin(vBox, new Insets(10));
            }

        }



    }
    public void refresh(Panier panier) throws SQLException, IOException {
        paniers.remove(panier);

        updateGrid(paniers);
    }

}
