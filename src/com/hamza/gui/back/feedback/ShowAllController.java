package com.hamza.gui.back.feedback;

import com.hamza.entities.Feedback;
import com.hamza.gui.back.MainWindowController;
import com.hamza.services.FeedbackService;
import com.hamza.utils.AlertUtils;
import com.hamza.utils.Constants;
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

    public static Feedback currentFeedback;

    @FXML
    public Text topText;

    public VBox mainVBox;

    List<Feedback> listFeedback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listFeedback = FeedbackService.getInstance().getAll();

        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listFeedback);

        if (!listFeedback.isEmpty()) {
            for (Feedback feedback : listFeedback) {
                mainVBox.getChildren().add(makeFeedbackModel(feedback));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeFeedbackModel(Feedback feedback) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_FEEDBACK)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#userIdText")).setText("User : " + feedback.getUserId());
            ((Text) innerContainer.lookup("#sujetText")).setText("Sujet : " + feedback.getSujet());
            ((Text) innerContainer.lookup("#emailText")).setText("Email : " + feedback.getEmail());
            ((Text) innerContainer.lookup("#descriptionText")).setText("Description : " + feedback.getDescription());
            ((Text) innerContainer.lookup("#dateText")).setText("Date : " + feedback.getDate());

            String status = feedback.getStatus() == 0 ? "En cours" : feedback.getStatus() == 1 ? "Approuvé" : "Refusé";

            ((Text) innerContainer.lookup("#statusText")).setText("Status : " + status);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
}
