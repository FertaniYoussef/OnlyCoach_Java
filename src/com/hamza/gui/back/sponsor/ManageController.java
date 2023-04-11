package com.hamza.gui.back.sponsor;

import com.hamza.MainApp;
import com.hamza.entities.Sponsor;
import com.hamza.gui.back.MainWindowController;
import com.hamza.services.SponsorService;
import com.hamza.utils.AlertUtils;
import com.hamza.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public TextField nomTF;
    @FXML
    public DatePicker dateDP;
    @FXML
    public TextField descriptionTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Sponsor currentSponsor;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentSponsor = ShowAllController.currentSponsor;

        if (currentSponsor != null) {
            topText.setText("Modifier sponsor");
            btnAjout.setText("Modifier");

            try {
                nomTF.setText(currentSponsor.getNom());
                dateDP.setValue(currentSponsor.getDate());
                descriptionTF.setText(currentSponsor.getDescription());
                selectedImagePath = FileSystems.getDefault().getPath(currentSponsor.getImage());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter sponsor");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            String imagePath;
            if (imageEdited) {
                imagePath = currentSponsor.getImage();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }

            Sponsor sponsor = new Sponsor(
                    nomTF.getText(),
                    dateDP.getValue(),
                    descriptionTF.getText(),
                    imagePath
            );

            if (currentSponsor == null) {
                if (SponsorService.getInstance().add(sponsor)) {
                    AlertUtils.makeInformation("Sponsor ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_SPONSOR);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            } else {
                sponsor.setId(currentSponsor.getId());
                if (SponsorService.getInstance().edit(sponsor)) {
                    AlertUtils.makeInformation("Sponsor modifié avec succés");
                    ShowAllController.currentSponsor = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_SPONSOR);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            }

            if (selectedImagePath != null) {
                createImageFile();
            }
        }
    }

    @FXML
    public void chooseImage(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(MainApp.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/com/hamza/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {


        if (nomTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nom ne doit pas etre vide");
            return false;
        }


        if (dateDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour date");
            return false;
        }


        if (descriptionTF.getText().isEmpty()) {
            AlertUtils.makeInformation("description ne doit pas etre vide");
            return false;
        }


        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }


        return true;
    }
}