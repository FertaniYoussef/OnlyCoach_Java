package com.hamza.gui.front.message;


import com.hamza.MainApp;
import com.hamza.entities.Message;
import com.hamza.gui.front.MainWindowController;
import com.hamza.services.MessageService;
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
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> userCB;
    @FXML
    public TextField messageTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Message currentMessage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (RelationObject user : MessageService.getInstance().getAllUsers()) {
            if (user.getId() != MainApp.session.getId()) {
                userCB.getItems().add(user);
            }
        }

        currentMessage = ShowAllController.currentMessage;

        if (currentMessage != null) {
            topText.setText("Modifier message");
            btnAjout.setText("Modifier");

            try {
                userCB.setValue(currentMessage.getReceiver());
                messageTF.setText(currentMessage.getMessage());

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter message");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            Message message = new Message(
                    MainApp.session,
                    userCB.getValue(),
                    messageTF.getText()
            );

            if (currentMessage == null) {
                if (MessageService.getInstance().add(message)) {
                    AlertUtils.makeInformation("Message ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_MESSAGE);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            } else {
                message.setId(currentMessage.getId());
                if (MessageService.getInstance().edit(message)) {
                    AlertUtils.makeInformation("Message modifié avec succés");
                    ShowAllController.currentMessage = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_MESSAGE);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            }

        }
    }


    private boolean controleDeSaisie() {

        if (userCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir destinataire");
            return false;
        }


        if (messageTF.getText().isEmpty()) {
            AlertUtils.makeInformation("message ne doit pas etre vide");
            return false;
        }


        return true;
    }
}