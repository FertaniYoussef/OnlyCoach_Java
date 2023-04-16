package com.onlycoach;

import Service.CategorieProdService;
import Service.ProduitService;
import entities.CategorieProduit;
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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShowProductsController implements Initializable {

    @FXML
    private Text btnGoToCoaches;

    @FXML
    private Text btnGoToCourses;

    @FXML
    private Text btnGoToProducts;

    @FXML
    private ChoiceBox filterQuery;

    @FXML
    private ImageView imageUser1;

    @FXML
    private Button myAccountButton1;

    @FXML
    private GridPane productGrid;

    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private TextField searchQuery;

    private ObservableList<Produits> filteredProduits = FXCollections.observableArrayList();
    private ObservableList<Produits> searchedProduits = FXCollections.observableArrayList();

    private Stage stage;

    private ProduitService serviceProduit;
    private List<Produits> listeProduits;
    @FXML
    private Button Ajoutbtn1;

    @FXML
    private ImageView BtnPanier;

    @FXML
    private Button Ajoutbtn;
    @FXML
    void AjouterProduit(ActionEvent event) throws IOException {
        AddProductController addProductController = new AddProductController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        loader.setController(addProductController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public ShowProductsController() {

    }
    @FXML
    void handleGoToPanier(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AjouterCategorie(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategorieProd.fxml"));

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
    void handleGoToCoaches(MouseEvent event) {

    }

    @FXML
    void handleSearchItem(ActionEvent event) {
    serviceProduit = new ProduitService();
    searchQuery.textProperty().addListener((observable, oldValue, newValue) -> {

        try {
            searchedProduits.setAll(serviceProduit.searchProduit(newValue));


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try {

            updateGrid(searchedProduits);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    });

    }

    @FXML
    void routeToAccountInformationUser(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategorieProdService catservice= new CategorieProdService();
        serviceProduit = new ProduitService();
        List<CategorieProduit> categories = catservice.getAllCategories();
        List<String> categoryNames = categories.stream().map(CategorieProduit::getNom).collect(Collectors.toList());
        filterQuery.getItems().addAll("Tous");
        filterQuery.setValue("Tous");
        filterQuery.getItems().addAll(categoryNames);

        filterQuery.setOnAction(e -> {

            String selectedCategorie = filterQuery.getValue().toString();

            if (selectedCategorie.equals("Tous")  ) {
               try {
                   filteredProduits.setAll(serviceProduit.getAllProduits());

               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
                try {
                    updateGrid(filteredProduits);
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    filteredProduits.setAll(serviceProduit.getProduitCategory(selectedCategorie));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    updateGrid(filteredProduits);
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        listProduitfeed();
        



    }
private void updateGrid(List<Produits> filteredProduits) throws SQLException, IOException {

        productGrid.getChildren().clear();
        Label noResultsLabel = new Label();
        VBox vbox= new VBox();
        if (filteredProduits.isEmpty()) {
            productGrid.setVisible(false);
            scrollPane1.setVisible(false);
            noResultsLabel.setVisible(true);
            noResultsLabel.setAlignment(Pos.CENTER);
            noResultsLabel.setStyle("-fx-font-size: 14; -fx-text-fill: red;-fx-background-color: red");
            vbox.setStyle("-fx-background-color: red");
            noResultsLabel.setText("Aucun résultat");
            vbox.getChildren().add(noResultsLabel);
            productGrid.add(vbox, 1, 1);

        }else {
            int col = 0;
            int row = 1;
            scrollPane1.setVisible(true);
            productGrid.setVisible(true);
            noResultsLabel.setVisible(false);

            for (Produits produit : filteredProduits) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Product.fxml"));
                VBox vBox = fxmlLoader.load();
                ProductController productController = fxmlLoader.getController();
                productController.setData(produit);
                if (col == 3) {
                    col = 0;
                    row++;
                }

                productGrid.add(vBox, col++, row);
                productGrid.setMargin(vBox, new Insets(10));
            }

        }



    }
    private void listProduitfeed() {
        serviceProduit= new ProduitService();
        int row =1 ;
        int col=0;
        try {
            listeProduits = serviceProduit.getAllProduits();
            for (Produits produit : listeProduits) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Product.fxml"));
                VBox vbox = fxmlLoader.load();
                vbox.setStyle("-fx-background-color: #F7F8FD;");
                ProductController productController = fxmlLoader.getController();
                productController.setData(produit);
                if (col == 3) {
                    col = 0;
                    row++;
                }
                productGrid.add(vbox, col++, row);
                productGrid.setMargin(vbox, new Insets(10));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void refreshList() throws SQLException, IOException {

        listProduitfeed();
    }
}
