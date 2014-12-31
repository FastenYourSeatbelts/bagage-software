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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.LogModel;
import luggage.helpers.StageHelper;
import luggage.security.Authentication;
import luggage.security.Permissions;

/**
 * DashboardController
 *
 * Controller for dashboard.fxml
 *
 * @package luggage.controllers
 * @author ITopia IS102-5
 */
public class DashboardController extends BaseController implements Initializable {

    @FXML
    private TabPane tabs;

    @FXML
    private Button logout;

    @FXML
    private Label fullname;

    /**
     * Called on controller start.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("DASHBOARD CONTROLLER-----------------------------------------------------------------");
        fullname.setText(Authentication.getCurrentUser().getFullname());

        addTabs();
    }

    /**
     * Only add the tabs the current user has permissions on.
     */
    public void addTabs() {
        List<Permissions.Tab> tabPermissions = Permissions.getPermissions(Authentication.getCurrentUser());

        String image = MainActivity.class.getResource("/resources/images/loading.gif").toExternalForm();
        tabs.setStyle("-fx-background-image: url('" + image + "'); -fx-background-position: center center; -fx-background-repeat: no-repeat;");

        Task task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                for (Permissions.Tab tabPermission : tabPermissions) {

                    Tab newTab = new Tab(tabPermission.getText());
                    newTab.setId(tabPermission.getId());

                    if (tabPermission.getId().equals("view_log")) {
                        MainActivity.logTab = newTab;
                    }
                    
                    if (tabPermission.getId().equals("manage_luggage")) {
                        MainActivity.luggageTab = newTab;
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                long startTime = System.nanoTime();

                                FXMLLoader oFXMLLoader = new FXMLLoader();
                                Parent primaryLoader = (Parent) oFXMLLoader.load(this.getClass().getResource("/luggage/views/" + tabPermission.getView() + ".fxml").openStream());
                                BaseController ctrl = oFXMLLoader.getController();
                                newTab.setContent(primaryLoader);

                                tabs.getTabs().add(newTab);

                                long endTime = System.nanoTime();
                                long microseconds = ((endTime - startTime) / 1000);
                                Debug.print("Adding tab " + tabPermission.getView() + " took " + microseconds + " microseconds.");
                            } catch (IOException ex) {
                                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
                MainActivity.tabs = tabs;
                return null;
            }
        };
        new Thread(task).start();
    }

    /**
     * Called on logout button click Handles the logout for the user
     *
     * @param event
     */
    @FXML
    private void logout(ActionEvent event) {
        Debug.logToDatabase(LogModel.TYPE_INFO, "User succesfully logged out.");
        Authentication.logout();
        Stage currentStage = (Stage) logout.getScene().getWindow();
        StageHelper.replaceStage(currentStage, "login", this);
    }

}
