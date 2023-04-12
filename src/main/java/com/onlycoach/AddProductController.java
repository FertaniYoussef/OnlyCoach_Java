package com.onlycoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import Service.CategorieProdService;
import Service.ProduitService;
import entities.CategorieProduit;
import entities.Produits;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AddProductController implements Initializable {


    private String image;
    private Produits produitstoupdate;
    private String selectedCategorie="";
    @FXML
    private ChoiceBox ProduitCategory;

    @FXML
    private Button addButton;

    @FXML
    private Button backToListButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label categorieErrorLabel;

    @FXML
    private TextArea desc;

    @FXML
    private Label errorLocationLabel;

    @FXML
    private Text formTitle;

    @FXML
    private Button imageProduct;

    @FXML
    private Label photoSelectedName;

    @FXML
    private TextField quantityfield;

    @FXML
    private Label titleErrorLabel;
    private CategorieProdService categorieservice;
    @FXML
    private TextField titre;

    @FXML
    private TextField pricefield;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProduitService service= new ProduitService();
        categorieservice = new CategorieProdService();
        List<CategorieProduit>  categories = categorieservice.getAllCategories();
        List<String> categoriesNames= categories.stream().map(CategorieProduit::getNom).collect(Collectors.toList());
        ProduitCategory.getItems().addAll(categoriesNames);
        ProduitCategory.setValue("Choisir une categorie");
        quantityfield.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));
        quantityfield.setText("0");
        titre.setText("Donnez un nom au produit");
        desc.setText("Donnez une description au produit");
        pricefield.setText("0");
        pricefield.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));

        if (produitstoupdate!=null) {
            formTitle.setText("Modifier un produit");
            addButton.setText("Modifier");
            titre.setText(produitstoupdate.getName());
            desc.setText(produitstoupdate.getDescription());
            quantityfield.setText(String.valueOf(produitstoupdate.getQuantity()));
            ProduitCategory.setValue(produitstoupdate.getCategory().getNom());
            image=produitstoupdate.getImage();
            photoSelectedName.setText(image);
        }


    }
    public void setItemToUpdate(Produits produitstoupdate) {

        this.produitstoupdate = produitstoupdate;
    }



    @FXML
    void addProduct(ActionEvent event) {
if (produitstoupdate!=null) {
    selectedCategorie = ProduitCategory.getValue().toString();
    categorieservice = new CategorieProdService();
    CategorieProduit categorie = categorieservice.getCategorie(selectedCategorie);
    ProduitService service = new ProduitService();
    produitstoupdate.setName(titre.getText());
    produitstoupdate.setDescription(desc.getText());
    produitstoupdate.setPrice(Integer.parseInt(pricefield.getText()));
    produitstoupdate.setQuantity(Integer.parseInt(quantityfield.getText()));
    produitstoupdate.setCategory(categorie);
    produitstoupdate.setImage(image);
    service.modifyProduit(produitstoupdate);
    try {


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Produit modifié avec succès");
            alert.showAndWait();

    } catch (Exception e) {
        throw e;

    }
}else {
    selectedCategorie = ProduitCategory.getValue().toString();
    categorieservice = new CategorieProdService();
    CategorieProduit categorie = categorieservice.getCategorie(selectedCategorie);
    ProduitService service = new ProduitService();
    Produits produit = new Produits(0, titre.getText(), desc.getText(), Integer.parseInt(pricefield.getText()), image, Integer.parseInt(quantityfield.getText()), true, categorie);

    try {
        int verif = service.createProduit(produit);
        System.out.println(verif);
        if (verif !=-1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Produit ajouté avec succès");
            alert.showAndWait();
        }
    } catch (Exception e) {
        throw e;

    }
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
    void handleAddImage(ActionEvent event) {
        FlowPane imagePane= new FlowPane();
        imagePane.setPrefWrapLength(400);
        imagePane.setPadding(new Insets(10, 10, 10, 10));
        imagePane.setHgap(10);
        imagePane.setVgap(10);
        String destinationFolderPath ="src/main/resources/assets/";
        imageProduct.setOnAction(event1 -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            fileChooser.setInitialDirectory(new java.io.File("src/main/resources/assets/"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                String imageFile = selectedFile.getName();
                String fileExtension = imageFile.substring(imageFile.lastIndexOf(".") );
                Path sourcePath = selectedFile.toPath();
                Path destinationPath = Paths.get(destinationFolderPath + selectedFile.getName());
                ImageView imageView = new ImageView(new Image(destinationPath.toUri().toString()));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                imagePane.getChildren().add(imageView);
                try {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                image = imageFile;
                photoSelectedName.setWrapText(true);
                photoSelectedName.setText(String.join(", ", image));

            }
        });



    }

    @FXML
    void handleBackToList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowProducts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}
