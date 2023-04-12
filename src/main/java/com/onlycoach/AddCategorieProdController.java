package com.onlycoach;

import Service.CategorieProdService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCategorieProdController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private Button backToListButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Text formTitle;

    @FXML
    private TextField titre;
    private CategorieProdService categorieservice;
    @FXML
    void addCategorie(ActionEvent event) throws SQLException {
        categorieservice = new CategorieProdService();
        int verif = categorieservice.createCategorie(titre.getText());
        if (verif != -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Categorie ajouté avec succès");
            alert.showAndWait();
        }


    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowProducts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleBackToList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowProducts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategorieProdService categorieservice = new CategorieProdService();
        titre.setText("Donnez un nom à la  catégorie");

    }
}
