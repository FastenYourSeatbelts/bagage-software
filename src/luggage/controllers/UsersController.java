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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import luggage.database.models.UserModel;
import luggage.database.models.Model;

/**
 * UsersController
 *
 * Controller for users/list.fxml
 *
 * @package luggage.controllers
 * @author Alexander + Nick
 */
public class UsersController implements Initializable {

    @FXML
    private TableView userTableView;
    
    @FXML
    private TableColumn tableViewUsername;
    
    @FXML
    private TableColumn tableViewName;
    
    @FXML
    private TableColumn tableViewWorkplace;
    
    @FXML
    private TableColumn tableViewRole;
    @FXML
    private TextField searchbox;
    
    @FXML
    protected void onKeyReleased()  {
        
        String[] keywords = searchbox.getText().split("\\s+");
        
        String[] params = new String[4 * keywords.length];
        boolean firstColumn = true;
        String query = "";
        
        for(int i = 0; i < keywords.length; i ++)
        {
            if(firstColumn) {
                params[0 + i] = "%" + keywords[i] + "%";
                query += "username LIKE ?";
            } else {
                params[0 + i] = "%" + keywords[i] + "%";
                query += " OR username LIKE ?";
            }
            
            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR firstname LIKE ?";
            
            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR lastname LIKE ?";
 
            params[3 + i] = "%" + keywords[i] + "%";
            query += " OR residence LIKE ?";
            
            firstColumn = false;
        }
        
        resetTableView(query, params);
    }
    
    private ObservableList<UserModel> data = FXCollections.observableArrayList();   

    
    /**
     * Called on controller start
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetTableView("", new String[0]);
    }
    
    public void resetTableView(String where, String... params) {
        UserModel users = new UserModel();
        List<Model> allUsers = users.findAll(where, params);
       
        data = FXCollections.observableArrayList(); 
        for(int i = 0; i < allUsers.size(); i ++)
        {
            UserModel user = (UserModel) allUsers.get(i);
            data.add(user);
        }
        
        tableViewUsername.setCellValueFactory(new PropertyValueFactory("username"));
        tableViewName.setCellValueFactory(new PropertyValueFactory("fullname"));
        tableViewWorkplace.setCellValueFactory(new PropertyValueFactory("workplace"));
        tableViewRole.setCellValueFactory(new PropertyValueFactory("role"));
                    
        userTableView.setItems(data);
    }
   
}
