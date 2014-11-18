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

import java.awt.event.KeyEvent;
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
import luggage.database.models.CustomerModel;
import luggage.database.models.Model;

/**
 * CustomersController
 *
 * Controller for customers/list.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class CustomersController implements Initializable {

    @FXML
    private TableView customerTableView;
    
    @FXML
    private TableColumn tableViewName;
    
    @FXML
    private TableColumn tableViewInsurer;
    
    @FXML
    private TableColumn tableViewAddress;
    
    @FXML
    private TableColumn tableViewPhone;
    
    @FXML
    private TableColumn tableViewEmail;
    
    @FXML
    private TextField searchbox;
    
    @FXML
    protected void onKeyTyped(){ 
        String[] params = new String[6];
        params[0] = "%" + searchbox.getText() + "%";
        params[1] = "%" + searchbox.getText() + "%";
        params[2] = "%" + searchbox.getText() + "%";
        params[3] = "%" + searchbox.getText() + "%";
        params[4] = "%" + searchbox.getText() + "%";
        params[5] = "%" + searchbox.getText() + "%";
        
        resetTableView("firstname LIKE ? OR lastname LIKE ? OR address LIKE ? OR residence LIKE ? or postalcode LIKE ? OR email LIKE ?", params);
    }
    
    private ObservableList<CustomerModel> data = FXCollections.observableArrayList();   

    
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
        CustomerModel customers = new CustomerModel();
        List<Model> allCustomers = customers.findAll(where, params);
       
        data = FXCollections.observableArrayList(); 
        for(int i = 0; i < allCustomers.size(); i ++)
        {
            CustomerModel customer = (CustomerModel) allCustomers.get(i);
            data.add(customer);
        }
        
        tableViewName.setCellValueFactory(new PropertyValueFactory("fullname"));
        tableViewInsurer.setCellValueFactory(new PropertyValueFactory("insurerName"));
        tableViewAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tableViewPhone.setCellValueFactory(new PropertyValueFactory("telephone"));
        tableViewEmail.setCellValueFactory(new PropertyValueFactory("email"));
                    
        
        customerTableView.setItems(data);
    }
   
}