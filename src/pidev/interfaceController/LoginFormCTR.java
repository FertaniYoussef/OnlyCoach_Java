/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.interfaceController;

import pidev.Entity.User;
import pidev.utils.LoginController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author aziz3
 */
public class LoginFormCTR {
    
    @FXML
    TextField txtEmail;
    @FXML
    PasswordField txtPassword;
    @FXML
    Button btnSignIn;    
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtPhone;
    @FXML
    Label labelMSG;
    @FXML
    Label labelMSG1;
    @FXML
    Button btnSignUp;
    
    User us= new User();
    LoginController Lc = new LoginController();
    
    public void isSign(Event e) throws SQLException, IOException{
        
        us.setEmail(txtEmail.getText());
        us.setPassword(txtPassword.getText());
       
        
        if(Lc.isLoggedIn(us)){
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root=FXMLLoader.load(getClass().getResource("/pidev/interfaces/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
            labelMSG1.setText("email or password is uncorrect");
        
    }
    public void redirectSignUp(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("/pidev/interfaces/SignUp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void redirectSignIn(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("/pidev/interfaces/LoginForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void CreationSuccess(Event e) throws SQLException, IOException{
        
        us.setEmail(txtEmail.getText());
        us.setPassword(txtPassword.getText());
        us.setNom(txtLastName.getText());
        us.setPrenom(txtFirstName.getText());
        us.setPhone(txtPhone.getText());
        
        if(Lc.RegisterAccount(us)){
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            labelMSG1.setText("Account created successfully");
        }
        else
            labelMSG1.setText("Something went wrong");  
    }
}
