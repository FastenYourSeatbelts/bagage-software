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
import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.CustomerModel;
import luggage.database.models.InsurerModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * CustomersController
 *
 * Controller for customers/list.fxml Controller for customers/new.fxml
 * Controller for customers/edit.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class CustomersController extends BaseController implements Initializable {

    /**
     * LIST ELEMENTS
     */
    @FXML
    private Button listHelp;

    @FXML
    private TableView listTableView;

    @FXML
    private TableColumn listTableViewName;

    @FXML
    private TableColumn listTableViewAddress;

    @FXML
    private TableColumn listTableViewPhone;

    @FXML
    private TableColumn listTableViewEmail;

    @FXML
    private TextField listSearchField;

    @FXML
    private Button listNew;

    @FXML
    private Button listEdit;

    @FXML
    private Button listView;

    @FXML
    private Button listRemove;

    /**
     * ADD ELEMENTS
     */
    @FXML
    private Button newSave;

    @FXML
    private Button newReset;

    @FXML
    private Button newCancel;

    @FXML
    private TextField addFirstname;

    @FXML
    private TextField addPrefix;

    @FXML
    private TextField addLastname;

    @FXML
    private ChoiceBox addGender;

    @FXML
    private ChoiceBox<InsurerModel> addInsurerId;

    @FXML
    private TextField addAddress;

    @FXML
    private TextField addPostalcode;

    @FXML
    private TextField addResidence;

    @FXML
    private TextField addEmail;

    @FXML
    private TextField addTelephone;

    @FXML
    private TextField addMobile;

    /**
     * EDIT ELEMENTS
     */
    @FXML
    private Button editSave;

    @FXML
    private Button editReset;

    @FXML
    private Button editCancel;

    @FXML
    private TextField editFirstname;

    @FXML
    private TextField editPrefix;

    @FXML
    private TextField editLastname;

    @FXML
    private ChoiceBox editGender;

    @FXML
    private ChoiceBox<InsurerModel> editInsurerId;

    @FXML
    private TextField editAddress;

    @FXML
    private TextField editPostalcode;

    @FXML
    private TextField editResidence;

    @FXML
    private TextField editEmail;

    @FXML
    private TextField editTelephone;

    @FXML
    private TextField editMobile;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private Button viewAdd;

    @FXML
    private Button viewReset;

    @FXML
    private Button viewClose;

    @FXML
    private TextField viewFirstname;

    @FXML
    private TextField viewPrefix;

    @FXML
    private TextField viewLastname;

    @FXML
    private ChoiceBox viewGender;

    @FXML
    private ChoiceBox<InsurerModel> viewInsurerId;

    @FXML
    private TextField viewAddress;

    @FXML
    private TextField viewPostalcode;

    @FXML
    private TextField viewResidence;

    @FXML
    private TextField viewEmail;

    @FXML
    private TextField viewTelephone;

    @FXML
    private TextField viewMobile;

    private ObservableList<CustomerModel> listData = FXCollections.observableArrayList();

    private final ObservableList<InsurerModel> insurerData = FXCollections.observableArrayList();

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

                Debug.print("CUSTOMERS CONTROLLER-----------------------------------------------------------------");

                // List
                if (listTableView != null) {
                    listResetTableView("", new String[0]);

                    listEdit.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listRemove.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listView.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    listKeyActions();
                }

                // Add
                if (addGender != null && addInsurerId != null) {
                    setAddChoiceBoxes();
                    addKeyActions();
                }

                // Edit
                if (editGender != null && editInsurerId != null) {
                    setEditChoiceBoxes();
                    setEditFields();
                    editKeyActions();
                }

                // View
                if (viewGender != null && viewInsurerId != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                    viewKeyActions();
                }
            }
        });
    }

    public InsurerModel selectedInsurer;

    public void setViewFields() {
        CustomerModel customer = new CustomerModel(MainActivity.viewId);

        viewFirstname.setText(customer.getFirstname());
        viewPrefix.setText(customer.getPrefix());
        viewLastname.setText(customer.getLastname());
        viewAddress.setText(customer.getAddress());
        viewPostalcode.setText(customer.getPostalCode());
        viewResidence.setText(customer.getResidence());
        viewEmail.setText(customer.getEmail());
        viewTelephone.setText(customer.getTelephone());
        viewMobile.setText(customer.getMobile());

        viewInsurerId.getSelectionModel().select(selectedInsurer);
        viewGender.getSelectionModel().select(customer.getGender().toUpperCase());
    }

    public void setViewChoiceBoxes() {
        viewGender.setItems(FXCollections.observableArrayList(
                "MALE",
                "FEMALE",
                "OTHER"
        ));

        InsurerModel insurers = new InsurerModel();
        List<Model> allInsurers = insurers.findAll("", new String[0]);

        int insurerId = new CustomerModel(MainActivity.viewId).getInsurerId();

        for (Model allInsurer : allInsurers) {
            InsurerModel insurer = (InsurerModel) allInsurer;
            if (insurer.getId() == insurerId) {
                selectedInsurer = insurer;
            }
            insurerData.add(insurer);
        }

        viewInsurerId.setItems(insurerData);
    }

    public void setEditFields() {
        CustomerModel customer = new CustomerModel(MainActivity.editId);

        editFirstname.setText(customer.getFirstname());
        editPrefix.setText(customer.getPrefix());
        editLastname.setText(customer.getLastname());
        editAddress.setText(customer.getAddress());
        editPostalcode.setText(customer.getPostalCode());
        editResidence.setText(customer.getResidence());
        editEmail.setText(customer.getEmail());
        editTelephone.setText(customer.getTelephone());
        editMobile.setText(customer.getMobile());

        editInsurerId.getSelectionModel().select(selectedInsurer);
        editGender.getSelectionModel().select(customer.getGender().toUpperCase());
    }

    public void setEditChoiceBoxes() {
        editGender.setItems(FXCollections.observableArrayList(
                "MALE",
                "FEMALE",
                "OTHER"
        ));

        InsurerModel insurers = new InsurerModel();
        List<Model> allInsurers = insurers.findAll("", new String[0]);

        int selectedInsurerId = new CustomerModel(MainActivity.editId).getInsurerId();

        for (Model allInsurer : allInsurers) {
            InsurerModel insurer = (InsurerModel) allInsurer;
            if (insurer.getId() == selectedInsurerId) {
                selectedInsurer = insurer;
            }
            insurerData.add(insurer);
        }

        editInsurerId.setItems(insurerData);
    }

    public void setAddChoiceBoxes() {
        addGender.setItems(FXCollections.observableArrayList(
                "MALE",
                "FEMALE",
                "OTHER"
        ));

        InsurerModel insurers = new InsurerModel();
        List<Model> allInsurers = insurers.findAll("", new String[0]);
        for (Model allInsurer : allInsurers) {
            InsurerModel insurer = (InsurerModel) allInsurer;
            insurerData.add(insurer);
        }

        addInsurerId.setItems(insurerData);
    }

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

    public void addKeyActions() {
        addFirstname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addPrefix.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addLastname.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addGender.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addInsurerId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addAddress.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addPostalcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addResidence.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addEmail.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addTelephone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        addMobile.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
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

    public void editKeyActions() {
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
        editGender.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editInsurerId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
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
        editEmail.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
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

    public void viewKeyActions() {
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
        viewEmail.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
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
        viewClose.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
    }
    
    /**
     *
     * @param where
     * @param params
     */
    public void listResetTableView(String where, String... params) {
        CustomerModel customers = new CustomerModel();
        List<Model> allCustomers = customers.findAll(where, params);

        listData = FXCollections.observableArrayList();
        for (Model allCustomer : allCustomers) {
            CustomerModel customer = (CustomerModel) allCustomer;
            listData.add(customer);
        }

        listTableViewName.setCellValueFactory(new PropertyValueFactory("fullname"));
        listTableViewAddress.setCellValueFactory(new PropertyValueFactory("address"));
        listTableViewPhone.setCellValueFactory(new PropertyValueFactory("mobile"));
        listTableViewEmail.setCellValueFactory(new PropertyValueFactory("email"));

        listTableView.setItems(listData);
    }

    @FXML
    public void listOnSearch() {
        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[5 * keywords.length];
        boolean firstColumn = true;
        String query = "";

        for (int i = 0; i < keywords.length; i++) {
            if (firstColumn) {
                params[0 + i] = "%" + keywords[i] + "%";
                query += "firstname LIKE ?";
            } else {
                params[0 + i] = "%" + keywords[i] + "%";
                query += " OR firstname LIKE ?";
            }

            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR lastname LIKE ?";

            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR address LIKE ?";

            params[3 + i] = "%" + keywords[i] + "%";
            query += " OR email LIKE ?";

            params[4 + i] = "%" + keywords[i] + "%";
            query += " OR mobile LIKE ?";

            firstColumn = false;
        }

        listResetTableView(query, params);
    }

    @FXML
    public void listNew() {
        StageHelper.addPopup("customers/new", this, false, true);
    }

    @FXML
    public void listHelp() {
        StageHelper.addStage("customers/help", this, false, true);
    }

    @FXML
    public void listEdit() {
        CustomerModel customer = (CustomerModel) listTableView.getSelectionModel().getSelectedItem();

        if (customer == null) {
            return;
        }

        MainActivity.editId = customer.getId();

        StageHelper.addPopup("customers/edit", this, false, true);
    }

    @FXML
    public void listRemove() {
        Stage removeStage = (Stage) listTableView.getScene().getWindow();

        Action response = Dialogs.create().owner(removeStage)
                .title("Remove customer")
                //.masthead("Are you sure you want to delete this item? 2")
                .message("Are you sure you want to delete this customer?")
                .actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL)
                .showWarning();

        if (response == Dialog.ACTION_OK) {
            CustomerModel customer = (CustomerModel) listTableView.getSelectionModel().getSelectedItem();

            if (customer == null) {
                return;
            }

            customer.delete();
            listOnSearch();
        }
    }

    @FXML
    public void listView() {
        CustomerModel customer = (CustomerModel) listTableView.getSelectionModel().getSelectedItem();

        if (customer == null) {
            return;
        }

        MainActivity.viewId = customer.getId();

        StageHelper.addPopup("customers/view", this, false, true);
    }

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        addFirstname.setText("");
        addPrefix.setText("");
        addLastname.setText("");
        addAddress.setText("");
        addPostalcode.setText("");
        addResidence.setText("");
        addEmail.setText("");
        addTelephone.setText("");
        addMobile.setText("");
        addGender.setValue(null);
        addInsurerId.setValue(null);
    }

    public void newSave() {
        if (addFirstname.getText().equals("") || addLastname.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) addLastname.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's first and last name.")
                    .showWarning();
            return;
        } else if (addGender.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) addGender.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the gender.")
                    .showWarning();
            return;
        } else if (addInsurerId.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) addInsurerId.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the insurer.")
                    .showWarning();
            return;
        } else if (addAddress.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) addAddress.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's address.")
                    .showWarning();
            return;
        } else if (addPostalcode.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) addPostalcode.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's postal code.")
                    .showWarning();
            return;
        } else if (addResidence.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) addResidence.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's residence.")
                    .showWarning();
            return;
        } else if (addEmail.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) addEmail.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's email address.")
                    .showWarning();
            return;
        } else if (addTelephone.getText().equals("") && addMobile.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) addTelephone.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's regular telephone number and / or their mobile number.")
                    .showWarning();
            return;
        }

        CustomerModel customer = new CustomerModel();
        customer.setFirstname(addFirstname.getText());
        customer.setPrefix(addPrefix.getText());
        customer.setLastname(addLastname.getText());
        customer.setGender(addGender.getSelectionModel().getSelectedItem().toString());
        customer.setInsurerId(Integer.toString(addInsurerId.getSelectionModel().getSelectedItem().getId()));
        customer.setAddress(addAddress.getText());
        customer.setPostalCode(addPostalcode.getText());
        customer.setResidence(addResidence.getText());
        customer.setEmail(addEmail.getText());
        customer.setTelephone(addTelephone.getText());
        customer.setMobile(addMobile.getText());
        customer.save();

        CustomersController customersController = (CustomersController) StageHelper.callbackController;
        customersController.listOnSearch();

        newCancel();
    }

    public void editCancel() {
        Stage cancelStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }

    public void editReset() {
        editFirstname.setText("");
        editPrefix.setText("");
        editLastname.setText("");
        editAddress.setText("");
        editPostalcode.setText("");
        editResidence.setText("");
        editEmail.setText("");
        editTelephone.setText("");
        editMobile.setText("");
        editGender.setValue(null);
        editInsurerId.setValue(null);
    }

    public void editSave() {
        if (editFirstname.getText().equals("") || editLastname.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editLastname.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's first and last name.")
                    .showWarning();
            return;
        } else if (editGender.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editGender.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the gender.")
                    .showWarning();
            return;
        } else if (editInsurerId.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editInsurerId.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please enter the insurer.")
                    .showWarning();
            return;
        } else if (editAddress.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editAddress.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's address.")
                    .showWarning();
            return;
        } else if (editPostalcode.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editPostalcode.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's postal code.")
                    .showWarning();
            return;
        } else if (editResidence.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editResidence.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's residence.")
                    .showWarning();
            return;
        } else if (editEmail.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editEmail.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's email address.")
                    .showWarning();
            return;
        } else if (editTelephone.getText().equals("") && editMobile.getText().equals("")) {
            Dialogs.create()
                    .owner((Stage) editTelephone.getScene().getWindow())
                    .title("Warning")
                    .masthead("Entry error")
                    .message("Please enter the customer's regular telephone number and / or their mobile number.")
                    .showWarning();
            return;
        }

        CustomerModel customer = new CustomerModel(MainActivity.editId);
        customer.setFirstname(editFirstname.getText());
        customer.setPrefix(editPrefix.getText());
        customer.setLastname(editLastname.getText());
        customer.setGender(editGender.getSelectionModel().getSelectedItem().toString());
        customer.setInsurerId(Integer.toString(editInsurerId.getSelectionModel().getSelectedItem().getId()));
        customer.setAddress(editAddress.getText());
        customer.setPostalCode(editPostalcode.getText());
        customer.setResidence(editResidence.getText());
        customer.setEmail(editEmail.getText());
        customer.setTelephone(editTelephone.getText());
        customer.setMobile(editMobile.getText());
        customer.save();

        CustomersController customersController = (CustomersController) StageHelper.callbackController;
        customersController.listOnSearch();

        editCancel();
    }

    public void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }

}
