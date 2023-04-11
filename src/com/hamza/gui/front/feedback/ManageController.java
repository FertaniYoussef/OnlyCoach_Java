package com.hamza.gui.front.feedback;


import com.hamza.entities.Feedback;
import com.hamza.gui.front.MainWindowController;
import com.hamza.services.FeedbackService;
import com.hamza.utils.AlertUtils;
import com.hamza.utils.Constants;
import com.hamza.utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> userCB;
    @FXML
    public ComboBox<String> sujetCB;
    @FXML
    public TextField emailTF;
    @FXML
    public TextField descriptionTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Feedback currentFeedback;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (RelationObject user : FeedbackService.getInstance().getAllUsers()) {
            userCB.getItems().add(user);
        }

        sujetCB.getItems().add("Paiement");
        sujetCB.getItems().add("Cours");
        sujetCB.getItems().add("Coach");
        sujetCB.getItems().add("Other");

        currentFeedback = ShowAllController.currentFeedback;

        if (currentFeedback != null) {
            topText.setText("Modifier feedback");
            btnAjout.setText("Modifier");

            try {
                userCB.setValue(currentFeedback.getUserId());
                sujetCB.setValue(currentFeedback.getSujet());
                emailTF.setText(currentFeedback.getEmail());
                descriptionTF.setText(currentFeedback.getDescription());
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter feedback");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            Feedback feedback = new Feedback(
                    userCB.getValue(),
                    sujetCB.getValue(),
                    emailTF.getText(),
                    descriptionTF.getText(),
                    LocalDate.now(),
                    0
            );

            if (currentFeedback == null) {
                if (FeedbackService.getInstance().add(feedback)) {
                    AlertUtils.makeSuccessNotification("Feedback ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_FEEDBACK);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            } else {
                feedback.setId(currentFeedback.getId());
                feedback.setStatus(currentFeedback.getStatus());

                if (FeedbackService.getInstance().edit(feedback)) {
                    AlertUtils.makeSuccessNotification("Feedback modifié avec succés");
                    ShowAllController.currentFeedback = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_FEEDBACK);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            }

        }
    }

    private boolean controleDeSaisie() {


        if (userCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir user");
            return false;
        }

        if (sujetCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir sujet");
            return false;
        }

        if (emailTF.getText().isEmpty()) {
            AlertUtils.makeInformation("email ne doit pas etre vide");
            return false;
        }
        if (!Pattern.compile("^(.+)@(.+)$").matcher(emailTF.getText()).matches()) {
            AlertUtils.makeInformation("Email invalide");
            return false;
        }

        if (descriptionTF.getText().isEmpty()) {
            AlertUtils.makeInformation("description ne doit pas etre vide");
            return false;
        }

        return true;
    }
}