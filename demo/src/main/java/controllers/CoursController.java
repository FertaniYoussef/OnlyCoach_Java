package controllers;

import Models.Cours;
import Models.Ressources;
import Models.Sections;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import static utils.connectionController.getConnection;

public class CoursController {

    Connection connection;
    PreparedStatement statement;
    PreparedStatement statement2;

    @FXML
    ScrollPane scrollPaneContainer;

    @FXML
    private VBox sectionContainer;
    @FXML
    private VBox sections;

    @FXML
    TextField courseTitle;

    @FXML
    TextArea courseDescription;

    @FXML
    Label uploadedFile;

    @FXML
    Label nbsections;

    @FXML
    TableView<Cours> tableView;

    CoursUtils coursUtils = new CoursUtils();


    private int count = 0;

    public CoursController() {
    }

    @FXML
    private void initialize() throws SQLException {
            System.out.println(coursUtils.getAllCours());
    }


    // open create new course page

    @FXML
    public void openNewCourse(ActionEvent event) throws IOException {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("DashboardCoachNewCourse.fxml"));
        Parent root = loader.load();

        // Get the stage from the event
        Button addCourse = (Button) event.getSource();
        Stage stage = (Stage) addCourse.getScene().getWindow();

        // Set the new scene on the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Navigate to home page

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


    // Navigate to courses page
    @FXML
    public void coursesPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("DashboardCoachCourses.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    // dynamically add new section upon button click
    @FXML
    public void addNewSection(ActionEvent event) {
        count += 1;
        nbsections.setText("("+count+")");

        // WIP

        VBox vbox = new VBox(10); // 10px spacing between elements
        vbox.setPadding(new Insets(20)); // 10px padding around the VBox
        vbox.setStyle("-fx-background-color: #f8f8f8;"); // light gray background

        // set the vbox width to the width of the scrollpane
        vbox.prefWidthProperty().bind(scrollPaneContainer.widthProperty());

        // Title and remove button
        HBox titleBox = new HBox(10); // 10px spacing between elements
        titleBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        Label titleLabel = new Label("Section details");
        titleLabel.setFont(Font.font("Arial", 16)); // larger font
        titleLabel.setTextFill(Color.web("#333")); // dark gray text

        Button removeButton = new Button("Remove");
        removeButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;"); // red background, white text
        removeButton.setOnAction(e -> {
            // remove the vbox from its parent container
            ((Pane) vbox.getParent()).getChildren().remove(vbox);
            count--;
            nbsections.setText("("+ count+")");
        });




        Region spacer = new Region(); // invisible element to push removeButton to the right
        HBox.setHgrow(spacer, Priority.ALWAYS);

        titleBox.getChildren().addAll(titleLabel, spacer, removeButton);
        vbox.getChildren().add(titleBox);

        // Spacing
        vbox.getChildren().add(new Region()); // invisible element to add vertical spacing

        // Three columns
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // 10px horizontal spacing between columns
        gridPane.setVgap(10); // 10px vertical spacing between rows

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20); // first column takes up 20% of available width
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60); // second column takes up 60% of available width
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20); // third column takes up 20% of available width

        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        // First row: title and textfield
        Label titleLabel2 = new Label("Title:");
        titleLabel2.setTextFill(Color.web("#333")); // dark gray text
        TextField titleTextField = new TextField();
        titleTextField.setId("titleTextField" + count);
        titleTextField.setStyle("-fx-background-color: #fff; -fx-border-color: #ccc;"); // white background, gray border

        gridPane.add(titleLabel2, 0, 0);
        gridPane.add(titleTextField, 1, 0);

        // Second row: description and textarea
        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setTextFill(Color.web("#333")); // dark gray text
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setId("descriptionTextArea" + count);
        descriptionTextArea.setStyle("-fx-background-color: #fff; -fx-border-color: #ccc;"); // white background, gray border

        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionTextArea, 1, 1);

        // Third row: link and textfield
        Label linkLabel = new Label("Link:");
        linkLabel.setTextFill(Color.web("#333")); // dark gray text
        TextField linkTextField = new TextField();
        linkTextField.setId("linkTextField" + count);
        linkTextField.setStyle("-fx-background-color: #fff; -fx-border-color: #ccc;"); // white background, gray border

        gridPane.add(linkLabel, 0, 2);
        gridPane.add(linkTextField, 1, 2);

        vbox.getChildren().add(gridPane);

        // WIP

        sections.getChildren().add(vbox);



        //sections.getChildren().add(newSection);
        sections.requestLayout();
        scrollPaneContainer.setContent(sections);
        scrollPaneContainer.layout();

    }


    // does nothing for now
    // make it reload page when clicked later on
    @FXML
    public void cancelForm(ActionEvent event) {
        // reload the page
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("DashboardCoach.fxml"));
            Parent root = loader.load();

            // Get the stage from the event
            Button addCourse = (Button) event.getSource();
            Stage stage = (Stage) addCourse.getScene().getWindow();

            // Set the new scene on the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // add new course to database
    // add redirection to homepage later on
    @FXML
    public void submitForm(ActionEvent event) {

        try {
            connection = getConnection();

            Cours cours = new Cours();
            cours.setTitre(courseTitle.getText());
            cours.setDescription(courseDescription.getText());
            cours.setCoursPhoto(uploadedFile.getText());
            // set datecreation to today's date
            cours.setDateCreation(new Date(System.currentTimeMillis()));
            cours.setNbVues(0);


            // insert cours
            String query = "INSERT INTO cours (titre, description, cours_photo, date_creation, nb_vues) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cours.getTitre());
            preparedStatement.setString(2, cours.getDescription());
            preparedStatement.setString(3, cours.getCoursPhoto());
            preparedStatement.setDate(4, cours.getDateCreation());
            preparedStatement.setInt(5, cours.getNbVues());
            preparedStatement.executeUpdate();

            // get the id of the inserted cours
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedID = 0;
            if (rs.next()) {
                generatedID = rs.getInt(1);
                System.out.println("Generated ID: " + generatedID);
            }

            for(int i=1; i<=count; i++) {

                Sections section = new Sections();
                section.setTitre(((TextField) sections.lookup("#titleTextField" + i)).getText());
                section.setIndexSection(i);
                section.setNbresources(1);
                section.setCoursId(generatedID);

                String query2 = "INSERT INTO sections (titre, index_section, nbresources, cours_id) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
                preparedStatement2.setString(1, section.getTitre());
                preparedStatement2.setInt(2, section.getIndexSection());
                preparedStatement2.setInt(3, section.getNbresources());
                preparedStatement2.setInt(4, section.getCoursId());
                preparedStatement2.executeUpdate();


                // get the id of the inserted section
                ResultSet rs2 = preparedStatement2.getGeneratedKeys();
                int generatedID2 = 0;
                if (rs2.next()) {
                    generatedID2 = rs2.getInt(1);
                    System.out.println("Generated ID: " + generatedID2);
                }




                Ressources resource = new Ressources();
                resource.setDescription(((TextArea) sections.lookup("#descriptionTextArea" + i)).getText());
                resource.setLien(((TextField) sections.lookup("#linkTextField" + i)).getText());
                resource.setSectionsId(generatedID2);
                resource.setIndexRessources(1);

                String query3 = "INSERT INTO ressources (description, lien, sections_id, index_ressources) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setString(1, resource.getDescription());
                preparedStatement3.setString(2, resource.getLien());
                preparedStatement3.setInt(3, resource.getSectionsId());
                preparedStatement3.setInt(4, resource.getIndexRessources());
                preparedStatement3.executeUpdate();


                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                // change page


                System.out.println(courseTitle.getText());
                System.out.println(courseDescription.getText());
                System.out.println("Title: " + ((TextField) sections.lookup("#titleTextField" + i)).getText());
                System.out.println("Description: " + ((TextArea) sections.lookup("#descriptionTextArea" + i)).getText());
                System.out.println("Link: " + ((TextField) sections.lookup("#linkTextField" + i)).getText());

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // file uploader
    // save file to folder later on
    @FXML
    public void fileUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            uploadedFile.setText("File: "+selectedFile.getName());


        } else {
            uploadedFile.setText("File is not valid!");
        }
    }


    // methods related to courses page(list of courses)





}
