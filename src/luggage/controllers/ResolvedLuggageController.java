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

/**
 * ResolvedLuggageController
 *
 * Controller for customers/list.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class ResolvedLuggageController extends BaseController implements Initializable {

    @FXML
    private TableView luggageTableView;

    @FXML
    private TableColumn tableViewId;

    @FXML
    private TableColumn tableViewStatus;

    @FXML
    private TableColumn tableViewTags;

    @FXML
    private TableColumn tableViewDate;

    @FXML
    private TableColumn tableViewNotes;

    @FXML
    private Button listView;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private Button viewCancel;

    @FXML
    private TextField viewTags;

    @FXML
    private ChoiceBox<LocationModel> viewLocationId;

    @FXML
    private ChoiceBox<CustomerModel> viewCustomerId;

    @FXML
    private TextField viewNotes;

    @FXML
    private DatePicker viewDate;

    private ObservableList<LuggageModel> data = FXCollections.observableArrayList();

    private final ObservableList<LocationModel> locationData = FXCollections.observableArrayList();

    private final ObservableList<CustomerModel> customerData = FXCollections.observableArrayList();

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

                Debug.print("RESOLVED LUGGAGE CONTROLLER-----------------------------------------------------------------");

                if (luggageTableView != null) {
                    String[] params = new String[1];
                    params[0] = "resolved";

                    resetTableView("status = ?", params);

                    listView.disableProperty().bind(luggageTableView.getSelectionModel().selectedItemProperty().isNull());
                    luggageTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                }

                if (viewLocationId != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                }
            }
        });
    }
    public LocationModel selectedLocation;

    public CustomerModel selectedCustomer;

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
    }

    @FXML
    public void listView() {
        LuggageModel luggage = (LuggageModel) luggageTableView.getSelectionModel().getSelectedItem();

        if (luggage == null) {
            return;
        }

        MainActivity.viewId = luggage.getId();

        StageHelper.addPopup("luggage/resolvedview", this, false, true);
    }

    public void setViewFields() {
        LuggageModel luggage = new LuggageModel(MainActivity.viewId);

        viewTags.setText(luggage.getTags());
        viewNotes.setText(luggage.getNotes());

        LocalDate date = LocalDate.parse(luggage.getDatetime());
        viewDate.setValue(date);

        viewLocationId.getSelectionModel().select(selectedLocation);
        viewCustomerId.getSelectionModel().select(selectedCustomer);
    }

    /**
     *
     * @param where
     * @param params
     */
    public void resetTableView(String where, String... params) {
        LuggageModel luggage = new LuggageModel();
        List<Model> allLuggage = luggage.findAll(where, params);

        data = FXCollections.observableArrayList();
        for (Model singleModel : allLuggage) {
            LuggageModel singleLuggage = (LuggageModel) singleModel;
            data.add(singleLuggage);
        }

        tableViewId.setCellValueFactory(new PropertyValueFactory("id"));
        tableViewStatus.setCellValueFactory(new PropertyValueFactory("status"));
        tableViewTags.setCellValueFactory(new PropertyValueFactory("tags"));
        tableViewDate.setCellValueFactory(new PropertyValueFactory("datetime"));
        tableViewNotes.setCellValueFactory(new PropertyValueFactory("notes"));

        luggageTableView.setItems(data);
    }

    public void viewCancel() {
        Stage addStage = (Stage) viewCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }
}
