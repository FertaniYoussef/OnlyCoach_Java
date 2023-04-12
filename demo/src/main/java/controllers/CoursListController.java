package controllers;

import Models.Cours;
import Models.Ressources;
import Models.Sections;
import com.example.demo.HelloApplication;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CoursListController {

    @FXML
    private TableView<Cours> tableView;

    private ObservableList<Cours> Courss = FXCollections.observableArrayList();

    private CoursUtils CoursUtils = new CoursUtils();

    private void InitTable() {
        tableView.getColumns().clear();
        tableView.getItems().clear();
    }
    @FXML
    private void initialize() {
        // Set up table columns
        TableColumn<Cours, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<Cours, String> viewColumn = new TableColumn<>("Views");
        viewColumn.setCellValueFactory(new PropertyValueFactory<>("nbVues"));

        TableColumn<Cours, Cours> editColumn = new TableColumn<>("Edit");
        editColumn.setSortable(false);
        editColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        editColumn.setCellFactory(cellData -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Cours Cours = getTableView().getItems().get(getIndex());
                    showEditDialog(Cours);
                });
            }

            @Override
            protected void updateItem(Cours Cours, boolean empty) {
                super.updateItem(Cours, empty);

                if (empty || Cours == null) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });




        tableView.getColumns().addAll(titleColumn, viewColumn, editColumn);

        // Load data from database
        try {
            Courss = CoursUtils.getAllCours();
            System.out.println(Courss);
            tableView.setItems(Courss);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private void showEditDialog(Cours Cours) {
        Dialog<Models.Cours> dialog = new Dialog<>();
        dialog.setTitle("Editing "+Cours.getTitre()+" course");

        // Set up the dialog buttons
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the content for the dialog
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField titleTextField = new TextField(Cours.getTitre());
        gridPane.add(new Label("Title:"), 0, 0);
        gridPane.add(titleTextField, 1, 0);

        TextField viewTextField = new TextField(Cours.getDescription());
        gridPane.add(new Label("Description:"), 0, 1);
        gridPane.add(viewTextField, 1, 1);

         ArrayList<Sections> sections;
         sections = Cours.getSectionsList();


        // loop through sections and add them to the gridpane

        for (int i = 0; i < sections.size(); i++) {
            final Sections section = sections.get(i);
            TextField sectionTextField = new TextField(sections.get(i).getTitre());
            // disable it
            sectionTextField.setDisable(true);
            gridPane.add(new Label("Section"), 0, i+2);
            gridPane.add(sectionTextField, 1, i+2);
            // add a button to edit the section
            Button editSectionButton = new Button("Edit Section");
            gridPane.add(editSectionButton, 2, i+2);

            // add a button to delete the section
            Button deleteSectionButton = new Button("Delete Section");
            gridPane.add(deleteSectionButton, 3, i+2);

            deleteSectionButton.setOnAction(event -> {
                try {
                CoursUtils.deleteSection(section.getId());
                // remove the element from the list
                sections.remove(section);
                dialog.close();
                showEditDialog(Cours);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            // on click on edit button open dialog to edit section
            editSectionButton.setOnAction(event -> {
                showEditSectionDialog(section);
                dialog.close();

            });
        }

        // add a button to add a section
        Button addSectionButton = new Button("Add Section");
        gridPane.add(addSectionButton, 0, sections.size()+2);
        addSectionButton.setOnAction(event -> {
            showAddSectionDialog(Cours);
            dialog.close();
        });

        // add a button to delete the course
        Button deleteCourseButton = new Button("Delete Course");
        gridPane.add(deleteCourseButton, 1, sections.size()+2);
        deleteCourseButton.setOnAction(event -> {
                CoursUtils.deleteCours(Cours);
                dialog.close();
                InitTable();
                initialize();
        });




        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the name field by default
        Platform.runLater(titleTextField::requestFocus);

        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);

        saveButton.setOnMouseClicked(event -> {
            Cours.setTitre(titleTextField.getText());
            Cours.setDescription(viewTextField.getText());
            CoursUtils.modifyCourse(Cours);
            InitTable();
            initialize();
        });

        // Convert the result to a Cours when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
        // return cours
            }
            return null;
        });

        Optional<Cours> result = dialog.showAndWait();

        // If the user clicks the save button, update the Cours
        //result.ifPresent(Cours::updateCours);


    }

    private void showAddSectionDialog(Cours cours) {
        Dialog<Models.Sections> dialog = new Dialog<>();
        dialog.setTitle("Add Section");

        // Set up the dialog buttons
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the content for the dialog
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField titleTextField = new TextField();
        gridPane.add(new Label("Title:"), 0, 0);
        gridPane.add(titleTextField, 1, 0);

        TextField descriptionTextField = new TextField();
        gridPane.add(new Label("Description:"), 0, 1);
        gridPane.add(descriptionTextField, 1, 1);

        TextField linkTextField = new TextField();
        gridPane.add(new Label("Link:"), 0, 2);
        gridPane.add(linkTextField, 1, 2);

        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the name field by default
        Platform.runLater(titleTextField::requestFocus);

        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);

        saveButton.setOnMouseClicked(event -> {
            Sections section = new Sections();
            section.setTitre(titleTextField.getText());
            section.setCoursId((int)cours.getId());
            section.setIndexSection(cours.getSectionsList().size()+1);
            Ressources ressource = new Ressources();
            ressource.setDescription(descriptionTextField.getText());
            ressource.setLien(linkTextField.getText());
            section.getResourcesList().add(ressource);
            CoursUtils.addSection(section);
            dialog.close();
            InitTable();
            initialize();
        });

        // Convert the result to a Cours when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                // return cours
            }
            return null;
        });

        Optional<Sections> result = dialog.showAndWait();

    }

    private void showEditSectionDialog(Sections section) {
        Dialog<Models.Sections> dialog = new Dialog<>();
        dialog.setTitle("Edit Section");

        // Set up the dialog buttons
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);


        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the content for the dialog
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField titleTextField = new TextField(section.getTitre());
        gridPane.add(new Label("Title:"), 0, 0);
        gridPane.add(titleTextField, 1, 0);

        Ressources resources = section.getResourcesList().get(0);

        TextField descriptionTextField = new TextField(resources.getDescription());
        gridPane.add(new Label("Description:"), 0, 1);
        gridPane.add(descriptionTextField, 1, 1);

        TextField linkTextField = new TextField(resources.getLien());
        gridPane.add(new Label("Link:"), 0, 2);
        gridPane.add(linkTextField, 1, 2);

        // Enable/disable save button depending on whether a valid name is entered
        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setDisable(true);
        titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(gridPane);


        // Request focus on the name field by default
        Platform.runLater(titleTextField::requestFocus);

        // Convert the result to a Cours when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                ArrayList<Ressources> list = new ArrayList<>();
                list.add(new Ressources(resources.getId(),resources.getSectionsId(),linkTextField.getText(),resources.getIndexRessources(),descriptionTextField.getText()));
                return new Sections(section.getId(),section.getCoursId(),section.getIndexSection(),titleTextField.getText(),section.getNbresources(),list);
            }
            return null;
        });

        saveButton.setOnMouseClicked(event -> {
            try {
                CoursUtils.modifySection(section.getId(), titleTextField.getText(), descriptionTextField.getText(), linkTextField.getText());
                dialog.setResult(section);
                dialog.close();
                tableView.getColumns().clear();
                tableView.getItems().clear();

                initialize();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Optional<Sections> result = dialog.showAndWait();

        // If the user clicks the save button, update the section


    }


    @FXML
    public void homePage(ActionEvent event) throws IOException {
        // change scene to home page
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("DashboardCoachLanding.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }
}
