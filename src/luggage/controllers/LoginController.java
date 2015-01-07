/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 ITopia IS102-5
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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import luggage.AppConfig;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.LogModel;

/**
 * LoginController
 *
 * Controller for login.fxml and login_help.fxml
 *
 * @package luggage.controllers
 * @author ITopia IS102-5
 */
public class LoginController extends BaseController implements Initializable {

    @FXML
    private Button exit;

    @FXML
    private Button help;

    @FXML
    private Button login;

    @FXML
    private Button viewClose;

    @FXML
    private Label copyright;

    @FXML
    private Label error;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private void onKeyPress() {
        error.setText("");
    }

    @FXML
    private void exit() {
        System.out.println("User " + closed + " the application.");
        Date date = new Date();
        Debug.print("Application session ended. Session length: " + MainActivity.appSessionStart + " - " + MainActivity.dateFormatFull.format(date));
        System.exit(0);
    }
    private String closed = "exited";

    /**
     * Opens the login page's help view.
     */
    @FXML
    public void listHelp() {
        StageHelper.addStage("login_help", this, false, true);
    }

    /**
     * Called on enter in username or password field or when the user clicks on
     * the login button Handles the login for the user.
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
            Debug.logLoginscreenToDatabase("User \"" + username.getText() + "\" login failed.");
            error.setText("Wrong login, please try again!");
            username.requestFocus();
            return;
        }
        Date date = new Date();
        MainActivity.userSessionStart = MainActivity.dateFormatFull.format(date);
        Debug.print("User session start: " + MainActivity.userSessionStart);

        Authentication.setCurrentUser(user);
        Stage loginStage = (Stage) username.getScene().getWindow();
        StageHelper.replaceStage(loginStage, "dashboard", this);

        Debug.logToDatabase(LogModel.TYPE_INFO, "User succesfully logged in.");
    }

    /**
     * Called on controller start.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("");
        if (MainActivity.firstStart) {
            debugInfo();
        }
        osDependable();
        Debug.print("LOGIN CONTROLLER-----------------------------------------------------------------");
        copyright.setText(AppConfig.ApplicationCopyRight);
    }

    /**
     * Posts readable system information.
     */
    public void debugInfo() {
        Date date = new Date();
        MainActivity.appSessionStart = MainActivity.dateFormatFull.format(date);
        Debug.print("DEBUG INFO START-----------------------------------------------------------------"
                + "\nApplication session start: " + MainActivity.appSessionStart
                + "\nSystem properties:"
                + "\njava.version:\t\t\t" + System.getProperty("java.version")
                + "\njava.vendor:\t\t\t" + System.getProperty("java.vendor")
                + "\njava.vendor.url:\t\t" + System.getProperty("java.vendor.url")
                + "\njava.home:\t\t\t" + System.getProperty("java.home")
                + "\njava.vm.specification.version:\t" + System.getProperty("java.vm.specification.version")
                + "\njava.vm.specification.vendor:\t" + System.getProperty("java.vm.specification.vendor")
                + "\njava.vm.specification.name:\t" + System.getProperty("java.vm.specification.name")
                + "\njava.vm.version:\t\t" + System.getProperty("java.vm.version")
                + "\njava.vm.vendor:\t\t\t" + System.getProperty("java.vm.vendor")
                + "\njava.vm.name:\t\t\t" + System.getProperty("java.vm.name")
                + "\njava.specification.version:\t" + System.getProperty("java.specification.version")
                + "\njava.specification.vendor:\t" + System.getProperty("java.specification.vendor")
                + "\njava.specification.name:\t" + System.getProperty("java.specification.name")
                + "\njava.class.version:\t\t" + System.getProperty("java.class.version")
                + "\njava.class.path:\t\t" + System.getProperty("java.class.path")
                + "\njava.library.path:\t\t" + System.getProperty("java.library.path")
                + "\njava.io.tmpdir:\t\t\t" + System.getProperty("java.io.tmpdir")
                + "\njava.compiler:\t\t\t" + System.getProperty("java.compiler")
                + "\njava.ext.dirs:\t\t\t" + System.getProperty("java.ext.dirs")
                + "\nos.name:\t\t\t" + System.getProperty("os.name")
                + "\nos.arch:\t\t\t" + System.getProperty("os.arch")
                + "\nos.version:\t\t\t" + System.getProperty("os.version")
                + "\nfile.separator:\t\t\t" + System.getProperty("file.separator")
                + "\npath.separator:\t\t\t" + System.getProperty("path.separator")
                + "\nuser.name:\t\t\t" + System.getProperty("user.name")
                + "\nuser.home:\t\t\t" + System.getProperty("user.home")
                + "\nuser.dir:\t\t\t" + System.getProperty("user.dir"));
        MainActivity.firstStart = false;
    }

//    public void keyActions() {
//        listTableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
//            if (b.getCode().equals(KeyCode.F1)) {
//                listHelp();
//            }
//        });
        /**
         * Makes non-fundamental changes based on OS. For now merely sets native
         * terminology.
         */
    private void osDependable() {
        final String os = System.getProperty("os.name");
        closed = "exited";
        if (!os.contains("Windows")) {
            closed = "quit";
            exit.setText("Quit");
        }
    }

    /**
     * Closes current view.
     */
    private void viewClose() {
        Stage addStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }
}
