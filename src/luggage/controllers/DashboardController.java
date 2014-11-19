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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import luggage.helpers.StageHelper;
import luggage.security.Authentication;

/**
 * DashboardController
 *
 * Controller for dashboard.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class DashboardController extends BaseController  implements Initializable {

    @FXML
    private TabPane tabs;
    
    @FXML
    private Button logout;
    
    @FXML
    private Label fullname;

    /**
     * Called on controller start
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fullname.setText(Authentication.getCurrentUser().getFullname());
          
        removeForbiddenTabs();   
    }
    
    /**
     * Remove all the tabs the current user doesn't have permissions on
     */
    public void removeForbiddenTabs()
    {
        ArrayList<Tab> tabsToRemove = new ArrayList<Tab>();
        for(int i = 0; i < tabs.getTabs().size(); i ++) {
            if(!Authentication.getCurrentUser().hasPermissionsOn(tabs.getTabs().get(i).getId()))
            {
                tabsToRemove.add(tabs.getTabs().get(i));
            }
        }
        
        for(Tab tabToRemove : tabsToRemove) {
            tabs.getTabs().remove(tabToRemove);
        }
    }
    
    /**
     * Called on logout button click Handles the logout for the user
     *
     * @param event
     */
    @FXML
    private void logout(ActionEvent event) {
        Authentication.logout();
        Stage currentStage = (Stage) logout.getScene().getWindow();
        StageHelper.replaceStage(currentStage, "login", this);
    }

}
