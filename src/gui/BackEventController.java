/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import Service.ServiceCategorieEvent;
import Service.ServiceEvent;
import entities.Categorie;
import entities.CategorieEvent;
import entities.Event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BackEventController implements Initializable {

    @FXML
    private Button btMenuEvent;
    @FXML
    private Button btMenuCateg;
    @FXML
    private Button btAjoutEvent;
    @FXML
    private TableView<Event> tvEvent;
    @FXML
    private TableColumn<Event, String> colNomEvent;
    @FXML
    private TableColumn<Event, String> colCategEvent;
    @FXML
    private TableColumn<Event, String> colLieuEvent;
    @FXML
    private VBox vboxDetailEvent;
    @FXML
    private ImageView ImageEvent;
    @FXML
    private Label lbDateEvent;
    @FXML
    private Label lbDescriptionEvent;
    @FXML
    private Label lbPrixEvent;
    @FXML
    private Label lbPlaceDispoEvent;
    @FXML
    private Button btSupprimerEvent;
    @FXML
    private Label lbIdEvent;
    @FXML
    private Pane pnFormEvent;
    @FXML
    private Pane pnEvent;
    @FXML
    private TextField tfNomEvent;
    @FXML
    private TextField tfDescriptionEvent;
    @FXML
    private TextField tfLieuEvent;
    @FXML
    private DatePicker tfDateEvent;
    @FXML
    private TextField tfDateHeure;
    @FXML
    private ComboBox<String> tfCategorieEvent;
    @FXML
    private TextField tfPlaceEvent;
    @FXML
    private TextField tfPrixEvent;
    @FXML
    private Button tfImageEvent;
    @FXML
    private Button btnConfirmerEvent;
    @FXML
    private Label lbTitleAjouterEvent;
    @FXML
    private Label lbtitleModifierEvent;
    @FXML
    private Label lbpathImage;
    @FXML
    private Button btnModifierEvent;
    @FXML
    private Pane pnCateg;
    @FXML
    private TableView<CategorieEvent> tvCateg;
    @FXML
    private TableColumn<CategorieEvent, String> colCateg;
    @FXML
    private Button btDeleteCateg;
    @FXML
    private Button btAjoutCATEG;
    @FXML
    private TextField tfCateg;
    @FXML
    private Label lbidCateg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnEvent.toFront();
        vboxDetailEvent.setVisible(false);
        fnShow();
        fnShowCateg();
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        ServiceCategorieEvent sr=new ServiceCategorieEvent();
        for (CategorieEvent comp : sr.readCategories()) {
            valuesList.add(comp.getCategorie());
        }
        tfCategorieEvent.setItems(valuesList);
        // TODO
    }    
    
    public void fnShow(){
        Service.ServiceEvent se=new ServiceEvent();
        ObservableList<Event> list =  FXCollections.observableList(se.readEvenements() );
         colNomEvent.setCellValueFactory(new PropertyValueFactory<>("nom"));
          colCategEvent.setCellValueFactory(new PropertyValueFactory<>("nomC"));
          colLieuEvent.setCellValueFactory(new PropertyValueFactory<>("lieu"));
          tvEvent.setItems(list);
    }
    
        public void fnShowCateg(){
        Service.ServiceCategorieEvent se=new ServiceCategorieEvent();
        ObservableList<CategorieEvent> list =   FXCollections.observableList(se.readCategories());
         colCateg.setCellValueFactory(new PropertyValueFactory<>("categorie"));
          tvCateg.setItems(list);
    }

    @FXML
    private void fnMenuEvent(ActionEvent event) {
        pnEvent.toFront();
    }

    @FXML
    private void fnMenuCateg(ActionEvent event) {
        pnCateg.toFront();
    }

    @FXML
    private void fnAjoutEvent(ActionEvent event) {
        pnFormEvent.toFront();
        lbtitleModifierEvent.setVisible(false);
        lbTitleAjouterEvent.setVisible(true);
        
    }

    @FXML
    private void fnSelectedEvent(MouseEvent event) {
        Event e=tvEvent.getSelectionModel().getSelectedItem();
        lbIdEvent.setText(e.getId()+"");
        vboxDetailEvent.setVisible(true);
        lbDateEvent.setText(e.getDate().toString()+" "+e.getHeure());
        lbDescriptionEvent.setText(e.getDescription());
        lbPlaceDispoEvent.setText(e.getPlacesDisponibles()+"");
        lbPrixEvent.setText(e.getCout()+"");
       String img="file:///"+e.getImage();
        Image image=new Image(img);
       ImageEvent.setImage(image);
    }

    @FXML
    private void fnModifierEvent(ActionEvent event) {
        ServiceEvent se=new ServiceEvent();
        Event e=se.readEvenementsById(Integer.parseInt(lbIdEvent.getText()));
        pnFormEvent.toFront();
        lbtitleModifierEvent.setVisible(true);
        lbTitleAjouterEvent.setVisible(false);
        tfCategorieEvent.setValue(e.getNomC());
        tfDateEvent.setValue(e.getDate().toLocalDate());
        tfDateHeure.setText(e.getHeure());
        tfDescriptionEvent.setText(e.getDescription());
        tfLieuEvent.setText(e.getLieu());
        lbpathImage.setText("");
        tfNomEvent.setText(e.getNom());
        tfPlaceEvent.setText(e.getPlacesDisponibles()+"");
        tfPrixEvent.setText(e.getCout()+"");
        lbpathImage.setText(e.getImage());
    }

    @FXML
    private void fnSupprimerEvent(ActionEvent event) {
        ServiceEvent se=new ServiceEvent();
        se.deleteEvenement(se.readEvenementsById(Integer.parseInt(lbIdEvent.getText())));
        fnShow();
        vboxDetailEvent.setVisible(false);
    }

    @FXML
    private void fnConfirmerEvent(ActionEvent event) throws IOException {
        if(lbTitleAjouterEvent.isVisible()){
            
        ServiceCategorieEvent sc=new ServiceCategorieEvent();
        ServiceEvent se=new ServiceEvent();
            Event e=new Event();
            String ERROR_MSG="";
            if("".equals(tfNomEvent.getText())){
                ERROR_MSG="Le champs nom ne dois pas pas étre null";
            }
            if("".equals(tfDescriptionEvent.getText())){
                ERROR_MSG="Le champs description ne dois pas pas étre null";
            }
            if("".equals(tfLieuEvent.getText())){
                ERROR_MSG="Le champs lieu ne dois pas pas étre null";
            }
            if("".equals(tfPlaceEvent.getText())){
                ERROR_MSG="Le champs places disponibles ne dois pas pas étre null";
            }
            if("".equals(tfDateHeure.getText())){
                ERROR_MSG="Le champs heure ee dois pas pas étre null";
            }
            if("".equals(tfPrixEvent.getText())){
                ERROR_MSG="Le champs prix ne dois pas pas étre null";
            }
            if("".equals(lbpathImage.getText())){
                ERROR_MSG="Le champs image ne dois pas pas étre null";
            }
            
            if(tfDateEvent.getValue()==null){
                ERROR_MSG="Le champs date de dois pas pas étre null";
            }
            if(tfCategorieEvent.getValue()==null){
                ERROR_MSG="Le champs catégorie ne dois pas pas étre null";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEvent.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEvent.toFront();
        }}else{
        e.setNom(tfNomEvent.getText());
        e.setDescription(tfDescriptionEvent.getText());
        e.setLieu(tfLieuEvent.getText());
        e.setDate(Date.valueOf(tfDateEvent.getValue()));
        e.setPlacesDisponibles(Integer.parseInt(tfPlaceEvent.getText()));
        e.setHeure(tfDateHeure.getText());
        e.setCout(Double.parseDouble(tfPrixEvent.getText()));
        e.setCategorie(sc.readCategorieByNom(tfCategorieEvent.getValue()));
        e.setImage(lbpathImage.getText());
            System.out.println(e.toString());
        se.createEvent(e);
        fnShow();
        pnEvent.toFront();
        tfCategorieEvent.setValue(null);
        tfDateEvent.setValue(null);
        tfDateHeure.setText("");
        tfDescriptionEvent.setText("");
        tfLieuEvent.setText("");
        lbpathImage.setText("");
        tfNomEvent.setText("");
        tfPlaceEvent.setText("");
        tfPrixEvent.setText("");}
        }else{
               
        ServiceCategorieEvent sc=new ServiceCategorieEvent();
        ServiceEvent se=new ServiceEvent();
            Event e=new Event();
         String ERROR_MSG="";
            if("".equals(tfNomEvent.getText())){
                ERROR_MSG="Le champs nom ne dois pas pas étre null";
            }
            if("".equals(tfDescriptionEvent.getText())){
                ERROR_MSG="Le champs description ne dois pas pas étre null";
            }
            if("".equals(tfLieuEvent.getText())){
                ERROR_MSG="Le champs lieu ne dois pas pas étre null";
            }
            if("".equals(tfPlaceEvent.getText())){
                ERROR_MSG="Le champs places disponibles ne dois pas pas étre null";
            }
            if("".equals(tfDateHeure.getText())){
                ERROR_MSG="Le champs heure ee dois pas pas étre null";
            }
            if("".equals(tfPrixEvent.getText())){
                ERROR_MSG="Le champs prix ne dois pas pas étre null";
            }
            if("".equals(lbpathImage.getText())){
                ERROR_MSG="Le champs image ne dois pas pas étre null";
            }
            
            if(tfDateEvent.getValue()==null){
                ERROR_MSG="Le champs date de dois pas pas étre null";
            }
            if(tfCategorieEvent.getValue()==null){
                ERROR_MSG="Le champs catégorie ne dois pas pas étre null";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEvent.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEvent.toFront();
        }}else{   
        e.setNom(tfNomEvent.getText());
        e.setDescription(tfDescriptionEvent.getText());
        e.setLieu(tfLieuEvent.getText());
        e.setDate(Date.valueOf(tfDateEvent.getValue()));
        e.setPlacesDisponibles(Integer.parseInt(tfPlaceEvent.getText()));
        e.setHeure(tfDateHeure.getText());
        e.setCout(Double.parseDouble(tfPrixEvent.getText()));
        e.setCategorie(sc.readCategorieByNom(tfCategorieEvent.getValue()));
        e.setImage(lbpathImage.getText());
        e.setId(Integer.parseInt(lbIdEvent.getText()));
        se.modifyEvenement(e);
        fnShow();
        pnEvent.toFront();
        tfCategorieEvent.setValue(null);
        tfDateEvent.setValue(null);
        tfDateHeure.setText("");
        tfDescriptionEvent.setText("");
        tfLieuEvent.setText("");
        lbpathImage.setText("");
        tfNomEvent.setText("");
        tfPlaceEvent.setText("");
        tfPrixEvent.setText("");
        }}
        vboxDetailEvent.setVisible(false);
    }

    @FXML
    private void fnDeleteCateg(ActionEvent event) {
        ServiceCategorieEvent sc=new ServiceCategorieEvent();
        
        sc.deleteCat(sc.readCategorieById(Integer.parseInt(lbidCateg.getText())));
        tfCateg.setText("");
        fnShowCateg();
        btDeleteCateg.setVisible(false);
        lbidCateg.setText("");
    }

    @FXML
    private void fnAjoutCateg(ActionEvent event) {
        ServiceCategorieEvent sc=new ServiceCategorieEvent();
        CategorieEvent c= new CategorieEvent();
        String ERROR_MSG="";
            if("".equals(tfCateg.getText())){
                ERROR_MSG="Le champs categorie ne dois pas pas étre null";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnCateg.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnCateg.toFront();
        }}else{
        c.setCategorie(tfCateg.getText());
        sc.createCat(c);
        tfCateg.setText("");
        fnShowCateg();
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        ServiceCategorieEvent sr=new ServiceCategorieEvent();
        for (CategorieEvent comp : sr.readCategories()) {
            valuesList.add(comp.getCategorie());
        }
        tfCategorieEvent.setItems(valuesList);}
    }

    @FXML
    private void fnSelectedCateg(MouseEvent event) {
        CategorieEvent c=tvCateg.getSelectionModel().getSelectedItem();
        lbidCateg.setText(c.getId()+"");
        btDeleteCateg.setVisible(true);
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        ServiceCategorieEvent sr=new ServiceCategorieEvent();
        for (CategorieEvent comp : sr.readCategories()) {
            valuesList.add(comp.getCategorie());
        }
        tfCategorieEvent.setItems(valuesList);
    }

    @FXML
    private void fnImage(ActionEvent event) throws IOException {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG FILE","*.jpg"));
        chooser.setTitle("Choose Image");
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            File file=chooser.showOpenDialog(stage);
        if(file !=null){
        String filename=file.getAbsolutePath();
        filename=filename.replace("\\" , "\\\\");
        String rndchars = RandomStringUtils.randomAlphanumeric(16);
            try {
        

               Path save=Paths.get("C:\\Users\\user\\Desktop\\Java\\Images\\"+rndchars+"_"+rndchars+".jpg");
                Files.copy(Paths.get(filename), save);
        lbpathImage.setVisible(false);
        String pathbd=save.toString().replace("\\" , "\\\\");
        lbpathImage.setText(pathbd);
            System.out.println(save); 
            } catch (java.nio.file.FileAlreadyExistsException ex) {
               Path save=Paths.get("C:\\Users\\user\\Desktop\\Java\\Images\\"+rndchars+"_"+rndchars+".jpg");
                Files.copy(Paths.get(filename), save);
                String pathbd=save.toString().replace("\\" , "\\\\");
        lbpathImage.setVisible(false);
        lbpathImage.setText(pathbd);
        }}
    }
    
}
