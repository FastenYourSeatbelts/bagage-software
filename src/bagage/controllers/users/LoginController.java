/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bagage.controllers.users;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Developer
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;

    @FXML
    private void login(ActionEvent event) {
        if(!username.getText().equals("admin") || !password.getText().equals("admin")) {
            System.out.println("Wrong login");
            return;
        }
        
        Stage currentStage = (Stage) username.getScene().getWindow();
        currentStage.close();
        
        System.out.println("Login success");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}
