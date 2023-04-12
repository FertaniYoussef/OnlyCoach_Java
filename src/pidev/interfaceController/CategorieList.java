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
import pidev.Entity.Categorie;
import pidev.Entity.Coach;
import pidev.Entity.User;
import pidev.utils.CategorieController;
import pidev.utils.CoachController;

/**
 *
 * @author Mega-PC
 */
public class CategorieList implements Initializable  {
    @FXML
    Button redirectHomeCat;
    @FXML
    private TableView<Categorie> tableCat;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    Button btnsuppCat;
    
    
    CategorieController Lc = new CategorieController();
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
        ArrayList<Categorie> c = new ArrayList<>();
        c = (ArrayList<Categorie>) Lc.getAllCategorie();
        ObservableList<Categorie> obs2 = FXCollections.observableArrayList(c);
        tableCat.setItems(obs2);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
    }
    
    public void resetTableData() throws SQLDataException, SQLException {
        CategorieController Lu = new CategorieController();
         List<Categorie> listc = new ArrayList<>();
        listc = Lu.getAllCategorie();
        ObservableList<Categorie> data = FXCollections.observableArrayList(listc);
        tableCat.setItems(data);
    }
    
    @FXML
    private void suppCategorie(ActionEvent event) throws SQLException {
         if (event.getSource() == btnsuppCat) {
            Categorie rec = new Categorie();

        CategorieController Lu = new CategorieController();
            rec.setId(tableCat.getSelectionModel().getSelectedItem().getId());
            System.out.print(rec.getId());
            Lu.SupprimerCategorie(rec.getId());
            System.out.println(rec.getId());
            resetTableData();
 
        }
 
    }
}