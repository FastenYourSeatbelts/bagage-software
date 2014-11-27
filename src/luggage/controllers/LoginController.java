/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 FastenYourSeatbelts
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package luggage.controllers;

import luggage.database.models.UserModel;
import luggage.helpers.StageHelper;
import luggage.security.Authentication;
import luggage.security.Encryption;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import luggage.AppConfig;
import luggage.Debug;

/**
 * LoginController
 *
 * Controller for login.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class LoginController extends BaseController  implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label error;
    
    @FXML
    private Label copyright;

    @FXML
    private Button login;

    @FXML
    private void onKeyPress() {
        error.setText("");
    }

    /**
     * Called on enter in username or password field or when the user clicks on
     * the login button Handles the login for the user
     *
     * @param event
     */
    @FXML
    private void login(ActionEvent event) {
        String[] params = new String[2];
        params[0] = username.getText();
        params[1] = Encryption.hash(password.getText());
    
        UserModel user = new UserModel("username = ? AND password = ?", params);
        if (!user.exists()) {
            error.setText("Wrong login, please try again!");
            username.requestFocus();
            return;
        }
    
        Authentication.setCurrentUser(user);
        Stage loginStage = (Stage) username.getScene().getWindow();
        StageHelper.replaceStage(loginStage, "dashboard", this);
    }

    /**
     * Called on controller start
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("LOGIN CONTROLLER-----------------------------------------------------------------");
        copyright.setText(AppConfig.ApplicationCopyRight);
    }

}
