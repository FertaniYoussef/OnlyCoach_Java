/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.interfaceController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pidev.Entity.Coach;
import pidev.Entity.User;
import pidev.utils.CoachController;
import pidev.utils.UsersController;


/**
 *
 * @author Mega-PC
 */
public class CoachList implements Initializable {
    @FXML
    Button returnHomeCoach;
    
    @FXML
    private TableView<Coach> tableCoach;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> idUser;
    @FXML
    private TableColumn<?, ?> idCat;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> picture;
    @FXML
    private TableColumn<?, ?> rating;
    @FXML
    Button btnsuppCoach;
    
    CoachController Lc = new CoachController();
    public void redirectHome(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("/pidev/interfaces/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Coach> c = new ArrayList<>();
        c = (ArrayList<Coach>) Lc.getAllCoachs();
        ObservableList<Coach> obs2 = FXCollections.observableArrayList(c);
        tableCoach.setItems(obs2);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("id_user_id"));
        idCat.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        
    }
    
    public void resetTableData() throws SQLDataException, SQLException {
        CoachController Lu = new CoachController();
         List<Coach> listc = new ArrayList<>();
        listc = Lu.getAllCoachs();
        ObservableList<Coach> data = FXCollections.observableArrayList(listc);
        tableCoach.setItems(data);
    }
    
    @FXML
    private void suppCoach(ActionEvent event) throws SQLException {
         if (event.getSource() == btnsuppCoach) {
            User rec = new User();

        CoachController Lu = new CoachController();
            rec.setId(tableCoach.getSelectionModel().getSelectedItem().getId());
            System.out.print(rec.getId());
            Lu.SupprimerCoach(rec.getId());
            System.out.println(rec.getId());
            resetTableData();
 
        }
 
    }
}