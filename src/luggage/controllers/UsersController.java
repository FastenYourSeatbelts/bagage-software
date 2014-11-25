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
import luggage.MainActivity;
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
public class UsersController extends BaseController implements Initializable {

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

    /*
     *all ADD fields
     */
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
    private TextField addPrefix;

    @FXML
    private TextField addLastname;

    @FXML
    private ChoiceBox addGender;

    @FXML
    private TextField addUsername;

    @FXML
    private TextField addPassword;

    /*
     * all EDIT fields
     */
    @FXML
    private TextField editFirstname;

    @FXML
    private TextField editPrefix;

    @FXML
    private TextField editLastname;

    @FXML
    private TextField editUsername;

    @FXML
    private TextField editPassword;

    @FXML
    private TextField editAddress;

    @FXML
    private TextField editPostalcode;

    @FXML
    private TextField editResidence;

    @FXML
    private TextField editTelephone;

    @FXML
    private TextField editMobile;

    @FXML
    private ChoiceBox editGender;

    @FXML
    private ChoiceBox editRole;
    
    @FXML
    private Button editReset;
    
    @FXML
    private Button editSave;
    
    @FXML
    private Button editCancel;

    private ObservableList<UserModel> listData = FXCollections.observableArrayList();

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
        new Thread(new Runnable() {
            @Override
            public void run() {
               
                // List
                if (listTableView != null) 
                {
                    listResetTableView("", new String[0]);
                }

                // Add
                if (addGender != null && addRole != null)
                {
                    setAddChoiceBox();
                }

                //Edit
                if(editGender != null && editRole != null)
                {
                    setEditFields();
                    setEditChoiceBoxes();
                }
            
            }
        }).start();
    }

    public void setAddChoiceBox() {
        addGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female"
        ));

        addRole.setItems(FXCollections.observableArrayList(
                "Manager",
                "Moderator",
                "Employee"
        ));
    }

    public void setEditFields() {
        UserModel user = new UserModel(MainActivity.editId);

        editUsername.setText(user.getFirstname());
        editPassword.setText(user.getPassword());
        editFirstname.setText(user.getFirstname());
        editPrefix.setText(user.getPrefix());
        editLastname.setText(user.getLastname());
        editAddress.setText(user.getAddress());
        editPostalcode.setText(user.getPostalcode());
        editResidence.setText(user.getResidence());
        editTelephone.setText(user.getTelephone());
        editMobile.setText(user.getTelephone());
        
        editGender.getSelectionModel().select("Male");
        editRole.getSelectionModel().select("Employee");

    }
    
    public void setEditChoiceBoxes() {
        editGender.setItems(FXCollections.observableArrayList(
            "Male", 
            "Female"
        ));
        
        editRole.setItems(FXCollections.observableArrayList(
            "Manager",
            "Moderator",
            "Employee"
        ));
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

    @FXML
    public void listEdit() {
        UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        MainActivity.editId = user.getId();

        StageHelper.addStage("users/edit", this, false, true);
    }
    
    public void listRemove() {
        UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();
        
        if(user == null)
            return;
        
        user.delete();
        listOnSearch();
    }
    

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        addPassword.setText("");
        addUsername.setText("");
        addFirstname.setText("");
        addPrefix.setText("");
        addLastname.setText("");
        addAddress.setText("");
        addPostalcode.setText("");
        addResidence.setText("");
        addTelephone.setText("");
        addMobile.setText("");
    }

    public void newSave() {
        if(addGender.getSelectionModel().getSelectedItem() == null) 
        {
            return;
        }

        if(addRole.getSelectionModel().getSelectedItem() == null) 
        {
            return;
        }

        UserModel users = new UserModel();
        users.setPassword(addPassword.getText());
        users.setUsername(addUsername.getText());
        users.setFirstname(addFirstname.getText());
        users.setPrefix(addPrefix.getText());
        users.setLastname(addLastname.getText());
        users.setGender(addGender.getSelectionModel().getSelectedItem().toString());
        users.setAddress(addAddress.getText());
        users.setPostalcode(addPostalcode.getText());
        users.setResidence(addResidence.getText());
        users.setRole(addRole.getSelectionModel().getSelectedItem().toString());
        users.setTelephone(addTelephone.getText());
        users.setMobile(addMobile.getText());
        users.save();

        UsersController usersController = (UsersController) StageHelper.callbackController;
        usersController.listOnSearch();

        newCancel();
    }
    
    public void editReset() {
        editUsername.setText("");
        editPassword.setText("");
        editFirstname.setText("");
        editPrefix.setText("");
        editLastname.setText("");
        editPostalcode.setText("");
        editAddress.setText("");
        editResidence.setText("");
        editTelephone.setText("");
        editMobile.setText("");
    }
    
    public void editSave() {
        if(editGender.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        
        if(editRole.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        
        UserModel user = new UserModel(MainActivity.editId);
        user.setUsername(editUsername.getText());
        user.setPassword(editPassword.getText());
        user.setFirstname(editFirstname.getText());
        user.setPrefix(editPrefix.getText());
        user.setLastname(editLastname.getText());
        user.setPostalcode(editPostalcode.getText());
        user.setAddress(editAddress.getText());
        user.setResidence(editResidence.getText());
        user.setTelephone(editTelephone.getText());
        user.setMobile(editMobile.getText());
        user.save();
        
        UsersController userController = (UsersController) StageHelper.callbackController;
        userController.listOnSearch();
        
        editCancel();
    }
    
    public void editCancel() {
        Stage cancelStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
