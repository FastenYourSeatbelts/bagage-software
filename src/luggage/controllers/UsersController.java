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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import luggage.database.models.UserModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;

/**
 * UsersController
 *
 * Controller for users/list.fxml
 *
 * @package luggage.controllers
 * @author Alexander + Nick
 */
public class UsersController extends BaseController  implements Initializable {

    @FXML
    private TableView listTableView;

    @FXML
    private TableColumn listTableViewUsername;

    @FXML
    private TableColumn listTableViewName;

    @FXML
    private TableColumn listTableViewInsurer;

    @FXML
    private TableColumn listTableViewAddress;

    @FXML
    private TableColumn listTableViewPhone;

    @FXML
    private TableColumn listTableViewEmail;

    @FXML
    private TableColumn listTableViewWorkplace;

    @FXML
    private TableColumn listTableViewRole;
    @FXML
    private TextField listSearchField;

    @FXML
    private Button newAdd;

    @FXML
    private Button newReset;

    @FXML
    private Button newCancel;
    @FXML
    private TextField addAddress;

    @FXML
    private TextField addPostalcode;

    @FXML
    private TextField addResidence;

    @FXML
    private ChoiceBox addRole;

    @FXML
    private TextField addTelephone;

    @FXML
    private TextField addMobile;

    @FXML
    private TextField addFirstname;

    @FXML
    private TextField addMiddlename;

    @FXML
    private TextField addLastname;

    @FXML
    private ChoiceBox addGender;

    @FXML
    public void listOnSearch() {

        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[4 * keywords.length];
        boolean firstColumn = true;
        String query = "";

        for (int i = 0; i < keywords.length; i++) {
            if (firstColumn) {
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

        listResetTableView(query, params);
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
        
        // List
        if(listTableView != null)
        {
            listResetTableView("", new String[0]);
        }
    }

    public void listResetTableView(String where, String... params) {
        UserModel users = new UserModel();
        List<Model> allUsers = users.findAll(where, params);

        data = FXCollections.observableArrayList();
        for (Model allUser : allUsers) {
            UserModel user = (UserModel) allUser;
            data.add(user);
        }

        listTableViewUsername.setCellValueFactory(new PropertyValueFactory("username"));
        listTableViewName.setCellValueFactory(new PropertyValueFactory("fullname"));
        listTableViewWorkplace.setCellValueFactory(new PropertyValueFactory("workplace"));
        listTableViewRole.setCellValueFactory(new PropertyValueFactory("role"));

        listTableView.setItems(data);
    }

    @FXML
    public void listNew() {
        StageHelper.addStage("users/add", this, false, true);
    }

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        addFirstname.setText("");
        addMiddlename.setText("");
        addLastname.setText("");
        addAddress.setText("");
        addPostalcode.setText("");
        addResidence.setText("");
        addTelephone.setText("");
        addMobile.setText("");
    }

    public void newSave() {

    }
}
