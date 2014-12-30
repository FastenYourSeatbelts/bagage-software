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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.LocationModel;
import luggage.database.models.UserModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;
import luggage.security.Encryption;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * UsersController
 *
 * Controller for users/list.fxml, users/new.fxml, users/edit, users/view,
 * and users/help.
 *
 * @package luggage.controllers
 * @author ITopia IS102-5
 */
public class UsersController extends BaseController implements Initializable {
    
    public TabPane tabs;

    /**
     * LIST ELEMENTS
     */
    @FXML
    private TableView listTableView;

    @FXML
    private TableColumn listTableViewUsername;

    @FXML
    private TableColumn listTableViewName;

    @FXML
    private TableColumn listTableViewRole;

    @FXML
    private TextField listSearchField;

    @FXML
    private Button listNew;

    @FXML
    private Button listHelp;

    @FXML
    private Button listEdit;

    @FXML
    private Button listView;

    @FXML
    private Button listRemove;

    /**
     * NEW ELEMENTS
     */
    @FXML
    private TextField newAddress;

    @FXML
    private TextField newPostalcode;

    @FXML
    private TextField newResidence;

    @FXML
    private ChoiceBox<LocationModel> newWorkplace;

    @FXML
    private ChoiceBox newRole;

    @FXML
    private TextField newTelephone;

    @FXML
    private TextField newMobile;

    @FXML
    private TextField newFirstname;

    @FXML
    private TextField newPrefix;

    @FXML
    private TextField newLastname;

    @FXML
    private ChoiceBox newGender;

    @FXML
    private TextField newUsername;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPasswordRepeat;

    @FXML
    private Button newSave;

    @FXML
    private Button newReset;

    @FXML
    private Button newCancel;

    /**
     * EDIT ELEMENTS
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
    private TextField editPasswordRepeat;

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
    private ChoiceBox<LocationModel> editWorkplace;

    @FXML
    private Button editSave;

    @FXML
    private Button editReset;

    @FXML
    private Button editCancel;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private TextField viewUsername;

    @FXML
    private TextField viewFirstname;

    @FXML
    private TextField viewPrefix;

    @FXML
    private TextField viewLastname;

    @FXML
    private TextField viewAddress;

    @FXML
    private TextField viewPostalcode;

    @FXML
    private TextField viewResidence;

    @FXML
    private TextField viewTelephone;

    @FXML
    private TextField viewMobile;

    @FXML
    private ChoiceBox viewRole;

    @FXML
    private ChoiceBox viewGender;

    @FXML
    private ChoiceBox viewWorkplace;

    @FXML
    private TextField viewGenderAsText;
    
    @FXML
    private TextField viewRoleAsText;

    @FXML
    private TextField viewWorkplaceAsText;

    @FXML
    private Button viewClose;

    private ObservableList<UserModel> listData = FXCollections.observableArrayList();

    private final ObservableList<LocationModel> workplaceData = FXCollections.observableArrayList();

    /**
     * Handles the search field functionality.
     */
    @FXML
    public void listOnSearch() {

        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[3 * keywords.length];
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Debug.print("USERS CONTROLLER-----------------------------------------------------------------");

                // List
                if (listTableView != null) {
                    listResetTableView("", new String[0]);

                    listEdit.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listRemove.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listView.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    listKeyActions();
                }

                // New
                if (newGender != null && newRole != null && newWorkplace != null) {
                    setNewChoiceBox();
                    newKeyActions();
                }

                //Edit
                if (editGender != null && editRole != null && editWorkplace != null) {
                    setEditChoiceBoxes();
                    setEditFields();
                    editKeyActions();
                }

                // View
                if (viewGender != null && viewRole != null && viewWorkplace != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                    viewKeyActions();
                }

            }
        });
    }

    public void setViewChoiceBoxes() {
        viewGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Other"
        ));

        viewRole.setItems(FXCollections.observableArrayList(
                "Employee",
                "Manager",
                "Moderator",
                "Super"
        ));

        LocationModel locations = new LocationModel();
        List<Model> allLocations = locations.findAll("", new String[0]);

        int locationId = new UserModel(MainActivity.viewId).getLocation().getId();
        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            if (location.getId() == locationId) {
                selectedWorkplace = location;
            }

            workplaceData.add(location);
        }

        viewWorkplace.setItems(workplaceData);
    }

    public void setNewChoiceBox() {
        newGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Other"
        ));

        newRole.setItems(FXCollections.observableArrayList(
                "Employee",
                "Manager",
                "Moderator",
                "Super"
        ));

        LocationModel locations = new LocationModel();
        List<Model> allLocations = locations.findAll("", new String[0]);

        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            workplaceData.add(location);
        }

        newWorkplace.setItems(workplaceData);
    }

    public void setEditFields() {
        UserModel user = new UserModel(MainActivity.editId);

        editUsername.setText(user.getUsername());
        editFirstname.setText(user.getFirstname());
        editPrefix.setText(user.getPrefix());
        editLastname.setText(user.getLastname());
        editAddress.setText(user.getAddress());
        editPostalcode.setText(user.getPostalcode());
        editResidence.setText(user.getResidence());
        editTelephone.setText(user.getTelephone());
        editMobile.setText(user.getMobile());

        editGender.getSelectionModel().select(user.getGender());
        editRole.getSelectionModel().select(user.getRole());
        editWorkplace.getSelectionModel().select(selectedWorkplace);
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the list view.
     */
    public void listKeyActions() {
        listNew.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                listNew();
            }
        });
        listHelp.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                listHelp();
            }
        });
        listTableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.E)) {
                listEdit();
            } else if (b.getCode().equals(KeyCode.H)) {
                listHelp();
            } else if (b.getCode().equals(KeyCode.N)) {
                listNew();
            } else if (b.getCode().equals(KeyCode.R)) {
                listRemove();
            } else if (b.getCode().equals(KeyCode.V)) {
                listView();
            }
        });
        listEdit.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                listEdit();
            }
        });
        listView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                listView();
            }
        });
        listRemove.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                listRemove();
            }
        });
        listSearchField.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the new view.
     */
    public void newKeyActions() {
        newUsername.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            }
            if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPassword.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPasswordRepeat.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newGender.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newGender.show();
            }
        });
        newRole.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newRole.show();
            }
        });
        newWorkplace.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newWorkplace.show();
            }
        });
        newSave.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newReset.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newReset();
            }
        });
        newCancel.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                newCancel();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the edit view.
     */
    public void editKeyActions() {
        editUsername.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPassword.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPasswordRepeat.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editGender.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editGender.show();
            }
        });
        editRole.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editRole.show();
            }
        });
        editWorkplace.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editWorkplace.show();
            }
        });
        editSave.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editReset.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editReset();
            }
        });
        editCancel.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                editCancel();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the view page.
     */
    public void viewKeyActions() {
        viewUsername.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewGenderAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewRoleAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewWorkplaceAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewClose.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
    }

    /**
     * Calls LocationModel to enable mapping a Location ID to the Workplace's
     * name.
     */
    public LocationModel selectedWorkplace;

    public void setEditChoiceBoxes() {
        editGender.setItems(FXCollections.observableArrayList(
                "Male",
                "Female",
                "Other"
        ));

        editRole.setItems(FXCollections.observableArrayList(
                "Employee",
                "Manager",
                "Moderator",
                "Super"
        ));

        LocationModel locations = new LocationModel();
        List<Model> allLocations = locations.findAll("", new String[0]);

        int locationId = new UserModel(MainActivity.editId).getLocationId();
        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            if (location.getId() == locationId) {
                selectedWorkplace = location;
            }

            workplaceData.add(location);
        }

        editWorkplace.setItems(workplaceData);
    }

    /**
     *
     * @param where
     * @param params
     */
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
        listTableViewRole.setCellValueFactory(new PropertyValueFactory("role"));

        listTableView.setItems(data);
    }

    /**
     * Opens the 'New User' view.
     */
    @FXML
    public void listNew() {
        StageHelper.addPopup("users/new", this, false, true);
    }

    /**
     * Opens the Users list's help view.
     */
    @FXML
    public void listHelp() {
        StageHelper.addStage("users/help", this, false, true);
    }

    /**
     * Opens the User edit view for the selected customer.
     */
    @FXML
    public void listEdit() {
        UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        MainActivity.editId = user.getId();

        StageHelper.addPopup("users/edit", this, false, true);
    }

    /**
     * Triggers a confirmation dialog for removing the selected user.
     */
    @FXML
    public void listRemove() {
        Stage removeStage = (Stage) listTableView.getScene().getWindow();

        Action response = Dialogs.create().owner(removeStage)
                .title("Remove user")
                .message("Are you sure you want to delete this user?")
                .actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL)
                .showWarning();

        if (response == Dialog.ACTION_OK) {
            UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

            if (user == null) {
                return;
            }

            user.delete();
            listOnSearch();
        }
    }

    /**
     * Opens the Users list view.
     */
    @FXML
    public void listView() {
        UserModel user = (UserModel) listTableView.getSelectionModel().getSelectedItem();

        if (user == null) {
            return;
        }

        MainActivity.viewId = user.getId();

        StageHelper.addPopup("users/view", this, false, true);

    }

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        newPassword.setText("");
        newPasswordRepeat.setText("");
        newUsername.setText("");
        newFirstname.setText("");
        newPrefix.setText("");
        newLastname.setText("");
        newAddress.setText("");
        newPostalcode.setText("");
        newResidence.setText("");
        newTelephone.setText("");
        newMobile.setText("");
        newGender.setValue(null);
        newRole.setValue(null);
        newWorkplace.setValue(null);
    }
//SELECT COUNT(*) as count FROM users WHERE username='whatever'

    public void newSave() {
        if (newUsername.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newUsername.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter a unique username.")
                    .showWarning();
            return;
        } else if (newPassword.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newPassword.getScene().getWindow())
                    .title("Warning")
                    .masthead("Password error")
                    .message("Please enter a password.")
                    .showWarning();
            return;
        } else if (newPasswordRepeat.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newPasswordRepeat.getScene().getWindow())
                    .title("Warning")
                    .masthead("Password error")
                    .message("Please repeat the password to confirm it.")
                    .showWarning();
            return;
        } else if (!newPassword.getText().equals(newPasswordRepeat.getText())) {
            Dialogs.create()
                    .owner((Stage) newPassword.getScene().getWindow())
                    .title("Warning")
                    .masthead("Password error")
                    .message("The passwords do not match.")
                    .showWarning();
            return;
        } else if (newFirstname.getText().equals("") || newLastname.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newLastname.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's first and last name.")
                    .showWarning();
            return;
        } else if (newGender.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newGender.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the user's gender.")
                    .showWarning();
            return;
        } else if (newAddress.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newAddress.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's address.")
                    .showWarning();
            return;
        } else if (newPostalcode.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newPostalcode.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's postal code.")
                    .showWarning();
            return;
        } else if (newResidence.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newResidence.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's residence.")
                    .showWarning();
            return;
        } else if (newWorkplace.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newWorkplace.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the user's workplace.")
                    .showWarning();
            return;
        } else if (newRole.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newRole.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the user's role.")
                    .showWarning();
            return;
        } else if (newTelephone.getText().equals("") && newMobile.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) newTelephone.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's regular phone number and / or their mobile number.")
                    .showWarning();
            return;
        }

        UserModel users = new UserModel();
        users.setPassword(Encryption.hash(newPassword.getText()));
        users.setUsername(newUsername.getText());
        users.setFirstname(newFirstname.getText());
        users.setPrefix(newPrefix.getText());
        users.setLastname(newLastname.getText());
        users.setGender(newGender.getSelectionModel().getSelectedItem().toString());
        users.setAddress(newAddress.getText());
        users.setPostalcode(newPostalcode.getText());
        users.setResidence(newResidence.getText());
        users.setLocationId(Integer.toString(newWorkplace.getSelectionModel().getSelectedItem().getId()));
        users.setRole(newRole.getSelectionModel().getSelectedItem().toString());
        users.setTelephone(newTelephone.getText());
        users.setMobile(newMobile.getText());
        users.save();

        UsersController usersController = (UsersController) StageHelper.callbackController;
        usersController.listOnSearch();

        newCancel();
    }

    /**
     * Resets all fields in the edit view.
     */
    public void editReset() {
        editUsername.setText("");
        editPassword.setText("");
        editPasswordRepeat.setText("");
        editFirstname.setText("");
        editPrefix.setText("");
        editLastname.setText("");
        editPostalcode.setText("");
        editAddress.setText("");
        editResidence.setText("");
        editTelephone.setText("");
        editMobile.setText("");
        editGender.setValue(null);
        editRole.setValue(null);
        editWorkplace.setValue(null);
    }

    /**
     * Handles saving changes to an existing User. Checks if all necessary
     * fields are filled and if so, writes to database, overwriting existing
     * data for selected Customer.
     */
    public void editSave() {
        if (editUsername.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editUsername.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter a unique username.")
                    .showWarning();
            return;
        } else if (!editPassword.getText().equals(editPasswordRepeat.getText())) {
            Dialogs.create()
                    .owner((Stage) editPassword.getScene().getWindow())
                    .title("Warning")
                    .masthead("Password error")
                    .message("The passwords do not match.")
                    .showWarning();
            return;
        } else if (editFirstname.getText().equals("") || editLastname.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editLastname.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's first and last name.")
                    .showWarning();
            return;
        } else if (editGender.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editGender.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the user's gender.")
                    .showWarning();
            return;
        } else if (editAddress.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editAddress.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's address.")
                    .showWarning();
            return;
        } else if (editPostalcode.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editPostalcode.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's postal code.")
                    .showWarning();
            return;
        } else if (editResidence.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editResidence.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the user's residence.")
                    .showWarning();
            return;
        } else if (editWorkplace.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editWorkplace.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the user's workplace.")
                    .showWarning();
            return;
        } else if (editRole.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editRole.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the user's role.")
                    .showWarning();
            return;
        } else if (editTelephone.getText().equals("") && editMobile.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editTelephone.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's regular phone number and / or their mobile number.")
                    .showWarning();
            return;
        }

        UserModel user = new UserModel(MainActivity.editId);
        user.setUsername(editUsername.getText());

        if (!editPassword.getText().equals("")) {
            user.setPassword(Encryption.hash(editPassword.getText()));
        }

        user.setFirstname(editFirstname.getText());
        user.setPrefix(editPrefix.getText());
        user.setLastname(editLastname.getText());
        user.setGender(editGender.getSelectionModel().getSelectedItem().toString());
        user.setPostalcode(editPostalcode.getText());
        user.setAddress(editAddress.getText());
        user.setResidence(editResidence.getText());
        user.setLocationId(Integer.toString(editWorkplace.getSelectionModel().getSelectedItem().getId()));
        user.setRole(editRole.getSelectionModel().getSelectedItem().toString());
        user.setTelephone(editTelephone.getText());
        user.setMobile(editMobile.getText());
        user.save();

        UsersController userController = (UsersController) StageHelper.callbackController;
        userController.listOnSearch();

        editCancel();
    }

    /**
     * Populates the view fields with the selected user's data.
     */
    public void setViewFields() {
        UserModel user = new UserModel(MainActivity.viewId);

        viewUsername.setText(user.getUsername());
        viewFirstname.setText(user.getFirstname());
        viewPrefix.setText(user.getPrefix());
        viewLastname.setText(user.getLastname());
        viewAddress.setText(user.getAddress());
        viewPostalcode.setText(user.getPostalcode());
        viewResidence.setText(user.getResidence());
        viewTelephone.setText(user.getTelephone());
        viewMobile.setText(user.getMobile());

//        viewGender.getSelectionModel().select(user.getGender());
//        viewRole.getSelectionModel().select(user.getRole());
//        viewWorkplace.getSelectionModel().select(selectedWorkplace);
        viewGenderAsText.setText((user.getGender()));
        viewRoleAsText.setText(user.getRole());
        viewWorkplaceAsText.setText(selectedWorkplace.toString());
    }

    /**
     * Shows the actions selected User has performed.
     * return username
     */
    @FXML
    public void viewUserActions() {
        MainActivity.setViewUserLogParam(MainActivity.viewId);
        Debug.print("Username dump (viewUserLogParam): \"" + MainActivity.viewUserLogParam + "\"");
        viewClose();
		
		MainActivity.tabs.getSelectionModel().select(MainActivity.logTab);
    }
    
    /**
     * Closes current view.
     */
    public void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }

    /**
     * Cancels editing a User, does not change saved data.
     */
    public void editCancel() {
        Stage cancelStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
