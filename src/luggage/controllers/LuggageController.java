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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.CustomerModel;
import luggage.database.models.LocationModel;
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * LuggageController
 *
 * Controller for customers/list.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class LuggageController extends BaseController implements Initializable {

    /**
     * LIST ELEMENTS
     */
    @FXML
    private TableView listTableView;

    @FXML
    private TableColumn listTableViewId;

    @FXML
    private TableColumn listTableViewStatus;

    @FXML
    private TableColumn listTableViewTags;

    @FXML
    private TableColumn listTableViewDate;

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
    private Button newAdd;

    @FXML
    private Button newReset;

    @FXML
    private Button newCancel;

    @FXML
    private TextField addTags;

    @FXML
    private ChoiceBox<LocationModel> addLocationId;

    @FXML
    private ChoiceBox<CustomerModel> addCustomerId;
    
    @FXML
    private ChoiceBox<String> addStatus;

    @FXML
    private TextField addNotes;

    @FXML
    private DatePicker addDate;

    /**
     * EDIT ELEMENTS
     */
    @FXML
    private Button editAdd;

    @FXML
    private Button editReset;

    @FXML
    private Button editCancel;

    @FXML
    private TextField editTags;

    @FXML
    private ChoiceBox<LocationModel> editLocationId;

    @FXML
    private ChoiceBox<CustomerModel> editCustomerId;
    
    @FXML
    private ChoiceBox<String> editStatus;

    @FXML
    private TextField editNotes;

    @FXML
    private DatePicker editDate;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private Button viewCancel;

    @FXML
    private TextField viewTags;
    
    @FXML
    private ChoiceBox<String> viewStatus;

    @FXML
    private ChoiceBox<LocationModel> viewLocationId;

    @FXML
    private ChoiceBox<CustomerModel> viewCustomerId;

    @FXML
    private TextField viewNotes;

    @FXML
    private DatePicker viewDate;

    private ObservableList<LuggageModel> listData = FXCollections.observableArrayList();

    private final ObservableList<LocationModel> locationData = FXCollections.observableArrayList();

    private final ObservableList<CustomerModel> customerData = FXCollections.observableArrayList();

    /**
     *
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

                Debug.print("LUGGAGE CONTROLLER-----------------------------------------------------------------");

                // List
                if (listTableView != null) {
                    listResetTableView("", new String[0]);

                    listEdit.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listRemove.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listView.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                }

                // Add
                if (addLocationId != null) {
                    setAddChoiceBoxes();
                }

                // Edit
                if (editLocationId != null) {
                    setEditChoiceBoxes();
                    setEditFields();
                }

                // View
                if (viewLocationId != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                }
            }
        });
    }

    public LocationModel selectedLocation;

    public CustomerModel selectedCustomer;

    public void setAddChoiceBoxes() {
        // Locations
        LocationModel oLocationModel = new LocationModel();
        List<Model> allLocations = oLocationModel.findAll();

        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            locationData.add(location);
        }

        addLocationId.setItems(locationData);

        // Customers
        CustomerModel oCustomerModel = new CustomerModel();
        List<Model> allCustomers = oCustomerModel.findAll();

        for (Model allCustomer : allCustomers) {
            CustomerModel customer = (CustomerModel) allCustomer;
            customerData.add(customer);
        }

        addCustomerId.setItems(customerData);
      
        ObservableList<String> statuses = FXCollections.observableArrayList();
        statuses.add("Missing");
        statuses.add("Found");
        statuses.add("Resolved");
        addStatus.setItems(statuses);
    }

    public void setEditChoiceBoxes() {
        // Locations
        LocationModel oLocationModel = new LocationModel();
        List<Model> allLocations = oLocationModel.findAll();

        int selectedLocationId = new LuggageModel(MainActivity.editId).getLocationId();

        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            if (location.getId() == selectedLocationId) {
                selectedLocation = location;
            }

            locationData.add(location);
        }

        editLocationId.setItems(locationData);

        // Customers
        CustomerModel oCustomerModel = new CustomerModel();
        List<Model> allCustomers = oCustomerModel.findAll();

        int selectedCustomerId = new LuggageModel(MainActivity.editId).getCustomerId();

        for (Model allCustomer : allCustomers) {
            CustomerModel customer = (CustomerModel) allCustomer;
            if (customer.getId() == selectedCustomerId) {
                selectedCustomer = customer;
            }

            customerData.add(customer);
        }

        editCustomerId.setItems(customerData);
      
        ObservableList<String> statuses = FXCollections.observableArrayList();
        statuses.add("Missing");
        statuses.add("Found");
        statuses.add("Resolved");
        editStatus.setItems(statuses);
    }

    public void setViewChoiceBoxes() {

        // Locations
        LocationModel oLocationModel = new LocationModel();
        List<Model> allLocations = oLocationModel.findAll();

        int selectedLocationId = new LuggageModel(MainActivity.viewId).getLocationId();

        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            if (location.getId() == selectedLocationId) {
                selectedLocation = location;
            }

            locationData.add(location);
        }

        viewLocationId.setItems(locationData);

        long startTime = System.nanoTime();

        // Customers
        CustomerModel oCustomerModel = new CustomerModel();
        List<Model> allCustomers = oCustomerModel.findAll();

        int selectedCustomerId = new LuggageModel(MainActivity.viewId).getCustomerId();

        for (Model allCustomer : allCustomers) {
            CustomerModel customer = (CustomerModel) allCustomer;
            if (customer.getId() == selectedCustomerId) {
                selectedCustomer = customer;
            }

            customerData.add(customer);
        }

        viewCustomerId.setItems(customerData);
        
        ObservableList<String> statuses = FXCollections.observableArrayList();
        statuses.add("Missing");
        statuses.add("Found");
        statuses.add("Resolved");
        viewStatus.setItems(statuses);

        long endTime = System.nanoTime();
        long microseconds = ((endTime - startTime) / 1000);
        Debug.print("zoooio: " + " took " + microseconds + " microseconds.");
    }

    @FXML
    protected void listOnSearch() {

        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[4 * keywords.length];
        boolean firstColumn = true;
        String query = "";

        for (int i = 0; i < keywords.length; i++) {
            if (firstColumn) {
                params[0 + i] = "%" + keywords[i] + "%";
                query += "id LIKE ?";
            } else {
                params[0 + i] = "%" + keywords[i] + "%";
                query += " OR id LIKE ?";
            }

            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR tags LIKE ?";

            params[1 + i] = "%" + keywords[i] + "%";
            query += " OR status LIKE ?";

            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR datetime LIKE ?";

            firstColumn = false;
        }

        listResetTableView(query, params);
    }

    @FXML
    public void listNew() {
        StageHelper.addPopup("luggage/add", this, false, true);
    }

    @FXML
    public void listEdit() {
        LuggageModel luggage = (LuggageModel) listTableView.getSelectionModel().getSelectedItem();

        if (luggage == null) {
            return;
        }

        MainActivity.editId = luggage.getId();

        StageHelper.addPopup("luggage/edit", this, false, true);
    }
    
    @FXML
    public void listHelp() {
        StageHelper.addStage("luggage/listHelp", this, false, true);
        }

    @FXML
    public void listRemove() {
        Stage removeStage = (Stage) listTableView.getScene().getWindow();

        Action response = Dialogs.create().owner(removeStage)
                .title("Are you sure you want to delete this item?")
                //.masthead("Are you sure you want to delete this item? 2")
                .message("Are you sure you want to delete this item?")
                .actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL)
                .showWarning();

        if (response == Dialog.ACTION_OK) {
            LuggageModel luggage = (LuggageModel) listTableView.getSelectionModel().getSelectedItem();

            if (luggage == null) {
                return;
            }

            luggage.delete();
            listOnSearch();
        }
    }

    @FXML
    public void listView() {
        LuggageModel luggage = (LuggageModel) listTableView.getSelectionModel().getSelectedItem();

        if (luggage == null) {
            return;
        }

        MainActivity.viewId = luggage.getId();

        StageHelper.addPopup("luggage/view", this, false, true);
    }

    public void listResetTableView(String where, String... params) {
        LuggageModel luggage = new LuggageModel();
        List<Model> allLuggage = luggage.findAll(where, params);

        listData = FXCollections.observableArrayList();
        for (Model allLuggage1 : allLuggage) {
            LuggageModel luggage2 = (LuggageModel) allLuggage1;
            listData.add(luggage2);
        }

        listTableViewId.setCellValueFactory(new PropertyValueFactory("id"));
        listTableViewStatus.setCellValueFactory(new PropertyValueFactory("status"));
        listTableViewTags.setCellValueFactory(new PropertyValueFactory("tags"));
        listTableViewDate.setCellValueFactory(new PropertyValueFactory("datetime"));

        listTableView.setItems(listData);
    }

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        addTags.setText("");
        addNotes.setText("");
        addLocationId.setValue(null);
        addCustomerId.setValue(null);
        addStatus.setValue(null);
        addDate.setValue(null);
    }

    public void newSave() {
        if (addLocationId.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        LuggageModel luggage = new LuggageModel();
        luggage.setLocationId(Integer.toString(addLocationId.getSelectionModel().getSelectedItem().getId()));

        luggage.setDatetime(addDate.getValue() + " 00:00:00");

        luggage.setTags(addTags.getText());
        luggage.setNotes(addNotes.getText());
        luggage.setStatus(addStatus.getValue());
        luggage.save();

        LuggageController luggageController = (LuggageController) StageHelper.callbackController;
        luggageController.listOnSearch();

        newCancel();
    }

    public void setEditFields() {
        LuggageModel luggage = new LuggageModel(MainActivity.editId);

        editTags.setText(luggage.getTags());
        editNotes.setText(luggage.getNotes());
        editStatus.setValue(luggage.getStatus());

        LocalDate date = LocalDate.parse(luggage.getDatetime());
        editDate.setValue(date);

        editLocationId.getSelectionModel().select(selectedLocation);
        editCustomerId.getSelectionModel().select(selectedCustomer);
    }

    public void setViewFields() {
        LuggageModel luggage = new LuggageModel(MainActivity.viewId);

        viewTags.setText(luggage.getTags());
        viewNotes.setText(luggage.getNotes());
        viewStatus.setValue(luggage.getStatus());

        LocalDate date = LocalDate.parse(luggage.getDatetime());
        viewDate.setValue(date);

        viewLocationId.getSelectionModel().select(selectedLocation);
        viewCustomerId.getSelectionModel().select(selectedCustomer);
    }

    public void editCancel() {
        Stage addStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void editReset() {
        editTags.setText("");
        editNotes.setText("");
        editLocationId.setValue(null);
        editCustomerId.setValue(null);
        editStatus.setValue(null);
        editDate.setValue(null);
    }

    public void editSave() {
        if (editLocationId.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        LuggageModel luggage = new LuggageModel(MainActivity.editId);
        luggage.setLocationId(Integer.toString(editLocationId.getSelectionModel().getSelectedItem().getId()));

        luggage.setDatetime(editDate.getValue() + " 00:00:00");

        luggage.setTags(editTags.getText());
        luggage.setNotes(editNotes.getText());
        luggage.setStatus(editStatus.getValue());
        luggage.save();

        LuggageController luggageController = (LuggageController) StageHelper.callbackController;
        luggageController.listOnSearch();

        editCancel();
    }

    public void viewCancel() {
        Stage addStage = (Stage) viewCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

}
