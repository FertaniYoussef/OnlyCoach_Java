/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.interfaceController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.Entity.User;
import pidev.utils.UsersController;
import java.sql.SQLException;
import java.sql.SQLDataException;
import java.util.List;

/**
 *
 * @author Mega-PC
 */

public class UserList implements Initializable{
    @FXML
    Button redirectProfilePage;
    
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> phone;
    @FXML
    private TableColumn<?, ?> picture;
    @FXML
    Button btnsupp;
    
    UsersController Lc = new UsersController();
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
        ArrayList<User> u = new ArrayList<>();
        u = (ArrayList<User>) Lc.getAllUsers();
        ObservableList<User> obs2 = FXCollections.observableArrayList(u);
        table.setItems(obs2);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
    }
    public void resetTableData() throws SQLDataException, SQLException {
        UsersController Lu = new UsersController();
         List<User> listrec = new ArrayList<>();
        listrec = Lu.getAllUsers();
        ObservableList<User> data = FXCollections.observableArrayList(listrec);
        table.setItems(data);
    }
    @FXML
    private void supp(ActionEvent event) throws SQLException {
         if (event.getSource() == btnsupp) {
            User rec = new User();

        UsersController Lu = new UsersController();
            rec.setId(table.getSelectionModel().getSelectedItem().getId());
            System.out.print(rec.getId());
            Lu.SupprimerUser(rec.getId());
            System.out.println(rec.getId());
            resetTableData();
 
        }
 
    }
    
}
