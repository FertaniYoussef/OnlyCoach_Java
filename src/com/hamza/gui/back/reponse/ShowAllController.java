package com.hamza.gui.back.reponse;

import com.hamza.entities.Reponse;
import com.hamza.gui.back.MainWindowController;
import com.hamza.services.ReponseService;
import com.hamza.utils.AlertUtils;
import com.hamza.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShowAllController implements Initializable {

    public static Reponse currentReponse;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;

    List<Reponse> listReponse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listReponse = ReponseService.getInstance().getAll();
        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listReponse);

        if (!listReponse.isEmpty()) {
            for (Reponse reponse : listReponse) {

                mainVBox.getChildren().add(makeReponseModel(reponse));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeReponseModel(
            Reponse reponse
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_REPONSE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#feedbackIdText")).setText("Feedback : " + reponse.getFeedbackId());
            ((Text) innerContainer.lookup("#texteText")).setText("Texte : " + reponse.getTexte());


            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierReponse(reponse));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerReponse(reponse));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterReponse(ActionEvent event) {
        currentReponse = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_REPONSE);
    }

    private void modifierReponse(Reponse reponse) {
        currentReponse = reponse;
        MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_REPONSE);
    }

    private void supprimerReponse(Reponse reponse) {
        currentReponse = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer reponse ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (ReponseService.getInstance().delete(reponse.getId())) {
                MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_REPONSE);
            } else {
                AlertUtils.makeError("Could not delete reponse");
            }
        }
    }
}
