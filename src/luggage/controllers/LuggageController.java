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
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;

/**
 * LuggageController
 *
 * Controller for customers/list.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class LuggageController extends BaseController  implements Initializable {

    @FXML
    private TableView luggageTableView;
    
    @FXML
    private TableColumn tableViewId;
    
    @FXML
    private TableColumn tableViewStatus;
    
    @FXML
    private TableColumn tableViewLocation;
    
    @FXML
    private TableColumn tableViewTags;
    
    @FXML
    private TableColumn tableViewDate;
    
    @FXML
    private TextField searchbox;
    
    @FXML
    protected void onKeyReleased()  {
        
        String[] keywords = searchbox.getText().split("\\s+");
        
        String[] params = new String[3 * keywords.length];
        boolean firstColumn = true;
        String query = "";
        
        for(int i = 0; i < keywords.length; i ++)
        {
            if(firstColumn) {
                params[0 + i] = "%" + keywords[i] + "%";
                query += "id LIKE ?";
            } else {
                params[0 + i] = "%" + keywords[i] + "%";
                query += " OR id LIKE ?";
            }
            
            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR tags LIKE ?";
 
            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR datetime LIKE ?";
            
            firstColumn = false;
        }
        
        resetTableView(query, params);
    }
    
    private ObservableList<LuggageModel> data = FXCollections.observableArrayList();   

    
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
        LuggageModel luggage = new LuggageModel();
        List<Model> allLuggage = luggage.findAll(where, params);
        
        data = FXCollections.observableArrayList(); 
        for(int i = 0; i < allLuggage.size(); i ++){
            LuggageModel luggage2 = (LuggageModel) allLuggage.get(i);
            data.add(luggage2);
        }
        
        
        tableViewId.setCellValueFactory(new PropertyValueFactory("id"));
        tableViewStatus.setCellValueFactory(new PropertyValueFactory("status"));
        tableViewLocation.setCellValueFactory(new PropertyValueFactory("locationName"));
        tableViewTags.setCellValueFactory(new PropertyValueFactory("tags"));
        tableViewDate.setCellValueFactory(new PropertyValueFactory("datetime"));
                    
        luggageTableView.setItems(data);
    }
    
}
