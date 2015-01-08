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

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Context.printStackTrace;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.CustomerModel;
import luggage.database.models.LocationModel;
import luggage.database.models.LogModel;
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * LuggageController
 *
 * Controller for luggage/list.fxml, luggage/new, luggage/edit, luggage/view,
 * and luggage/help.
 *
 * @package luggage.controllers
 * @author ITopia IS102-5
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
    private Button listEdit;

    @FXML
    private Button listExportToPdf;

    @FXML
    private Button listHelp;

    @FXML
    private Button listNew;

    @FXML
    private Button listRemove;

    @FXML
    private Button listView;

    @FXML
    private TextField listSearchField;

    /**
     * NEW ELEMENTS
     */
    @FXML
    private Button newCancel;

    @FXML
    private Button newReset;

    @FXML
    private Button newSave;

    @FXML
    private DatePicker newDate;

    @FXML
    private ChoiceBox<CustomerModel> newCustomerId;

    @FXML
    private ChoiceBox<LocationModel> newLocationId;

    @FXML
    private ChoiceBox<String> newStatus;

    @FXML
    private TextField newNotes;

    @FXML
    private TextField newTags;

    /**
     * EDIT ELEMENTS
     */
    @FXML
    private Button editCancel;

    @FXML
    private Button editReset;

    @FXML
    private Button editSave;

    @FXML
    private DatePicker editDate;

    @FXML
    private ChoiceBox<CustomerModel> editCustomerId;

    @FXML
    private ChoiceBox<LocationModel> editLocationId;

    @FXML
    private ChoiceBox<String> editStatus;

    @FXML
    private TextField editNotes;

    @FXML
    private TextField editTags;

    /**
     * VIEW ELEMENTS
     */
    @FXML
    private Button viewClose;

    @FXML
    private ChoiceBox<String> viewStatus;

    @FXML
    private ChoiceBox<LocationModel> viewLocationId;

    @FXML
    private ChoiceBox<CustomerModel> viewCustomerId;

    @FXML
    private DatePicker viewDate;

    @FXML
    private Label printNotif;

    @FXML
    private TextField viewStatusAsText;

    @FXML
    private TextField viewLocationAsText;

    @FXML
    private TextField viewCustomerAsText;

    @FXML
    private TextField viewNotes;

    @FXML
    private TextField viewTags;
    public String mlg = "holy shit";

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
                    listExportToPdf.disableProperty().bind(listTableView.getSelectionModel().selectedItemProperty().isNull());
                    listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    listKeyActions();
                }

                // New
                if (newLocationId != null) {
                    setNewChoiceBoxes();
                    newKeyActions();
                }

                // Edit
                if (editLocationId != null) {
                    setEditChoiceBoxes();
                    setEditFields();
                    editKeyActions();
                }

                // View
                if (viewLocationId != null) {
                    setViewChoiceBoxes();
                    setViewFields();
                    viewKeyActions();
                }
            }
        });
    }

    public LocationModel selectedLocation;

    public CustomerModel selectedCustomer;

    public void setNewChoiceBoxes() {
        // Locations
        LocationModel oLocationModel = new LocationModel();
        List<Model> allLocations = oLocationModel.findAll();

        for (Model allLocation : allLocations) {
            LocationModel location = (LocationModel) allLocation;
            locationData.add(location);
        }

        newLocationId.setItems(locationData);

        // Customers
        CustomerModel oCustomerModel = new CustomerModel();
        List<Model> allCustomers = oCustomerModel.findAll();

        for (Model allCustomer : allCustomers) {
            CustomerModel customer = (CustomerModel) allCustomer;
            customerData.add(customer);
        }

        newCustomerId.setItems(customerData);

        ObservableList<String> statuses = FXCollections.observableArrayList();
        statuses.add("Missing");
        statuses.add("Found");
        statuses.add("Resolved");
        newStatus.setItems(statuses);
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

    /**
     * Populates the view Location, Customer &amp; Status ChoiceBoxes.
     */
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
        Debug.print("Luggage setViewChoiceBoxes()" + " took " + microseconds + " microseconds.");
    }

    /**
     * Handles the search field functionality.
     */
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

            params[2 + i] = "%" + keywords[i] + "%";
            query += " OR status LIKE ?";

            params[3 + i] = "%" + keywords[i] + "%";
            query += " OR datetime LIKE ?";

            firstColumn = false;
        }

        listResetTableView(query, params);
    }

    /**
     * Opens the 'New Luggage' view.
     */
    @FXML
    public void listNew() {
        StageHelper.addPopup("luggage/new", this, false, true);
    }

    /**
     * Opens the Luggage list's help view.
     */
    @FXML
    public void listHelp() {
        StageHelper.addStage("luggage/listHelp", this, false, true);
    }

    /**
     * Opens the Luggage edit view for the selected customer.
     */
    @FXML
    public void listEdit() {
        LuggageModel luggage = (LuggageModel) listTableView.getSelectionModel().getSelectedItem();

        if (luggage == null) {
            return;
        }

        MainActivity.editId = luggage.getId();

        StageHelper.addPopup("luggage/edit", this, false, true);
    }

    /**
     * Triggers a confirmation dialog for removing the selected luggage item.
     */
    @FXML
    public void listRemove() {
        Stage removeStage = (Stage) listTableView.getScene().getWindow();

        Action response = Dialogs.create().owner(removeStage)
                .title("Remove luggage")
                //.masthead("Are you sure you want to delete this item? 2")
                .message("Are you sure you want to delete this luggage item?")
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

    /**
     * Opens the Luggage list view.
     */
    @FXML
    public void listView() {
        LuggageModel luggage = (LuggageModel) listTableView.getSelectionModel().getSelectedItem();

        if (luggage == null) {
            return;
        }

        MainActivity.viewId = luggage.getId();

        StageHelper.addPopup("luggage/view", this, false, true);
    }

    @FXML
    public void listExportToPdf() throws IOException, COSVisitorException {
        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Define a text content stream using the selected font
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 100);
        contentStream.drawString("Corendon");
        contentStream.endText();
        
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(0, 48);
        contentStream.drawString("Corendon");
        contentStream.endText();
       
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(0, 48);
        contentStream.drawString("Corendon");
        contentStream.endText();
        
        // Make sure that the content stream is closed:
        contentStream.close();

        // Save the results and ensure that the document is properly closed:
        document.save("Bon.pdf");
        document.close();

        Stage exportPdf = (Stage) listTableView.getScene().getWindow();
        
        //Show a warning that the data has been exported to a PDF
        Action response = Dialogs.create().owner(exportPdf)
                .title("Export to PDF")
                //.masthead("Are you sure you want to delete this item? 2")
                .message("The data has been exported to a PDF file")
                .actions(Dialog.ACTION_OK)
                .showWarning();
        
        //Log the action so that it is viewable in the log
        Debug.logToDatabase(LogModel.TYPE_INFO, "/*eenIdentifier + */" + "exported as PDF file.");
    }

    /**
     *
     * @param where
     * @param params
     */
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

    /**
     * Handles canceling and closing the new view.
     */
    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    /**
     * Resets all fields in the new view.
     */
    public void newReset() {
        newTags.setText("");
        newNotes.setText("");
        newLocationId.setValue(null);
        newCustomerId.setValue(null);
        newStatus.setValue(null);
        newDate.setValue(null);
    }

    /**
     * Handles saving a new Luggage item. Checks if all necessary fields are
     * given and if so, writes to database.
     */
    public void newSave() {
        if (newLocationId.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newLocationId.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the current location of the luggage or where to ship it to.")
                    .showWarning();
            return;
        } else if (newStatus.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) newStatus.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the status for the luggage item.")
                    .showWarning();
            return;
        } else if (newDate.getValue() == null) {
            Dialogs.create()
                    .owner((Stage) newDate.getScene().getWindow())
                    .title("Warning")
                    .masthead("Date format error")
                    .message("Please enter or select the correct date for the luggage item.")
                    .showWarning();
            return;
        }

        LuggageModel luggage = new LuggageModel();
        try {
            luggage.setCustomerId(Integer.toString(newCustomerId.getSelectionModel().getSelectedItem().getId()));
        } catch (NullPointerException n) {
            printStackTrace(n);
        }
        luggage.setLocationId(Integer.toString(newLocationId.getSelectionModel().getSelectedItem().getId()));

        luggage.setDatetime(newDate.getValue() + " 00:00:00");

        luggage.setTags(newTags.getText());
        luggage.setNotes(newNotes.getText());
        luggage.setStatus(newStatus.getValue());
        luggage.save();

        LuggageController luggageController = (LuggageController) StageHelper.callbackController;
        luggageController.listOnSearch();

        newCancel();
    }

    /**
     * Populates the edit fields with the selected Luggage item's data.
     */
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

    /**
     * Populates the view fields with the selected Luggage item's data.
     */
    public void setViewFields() {
        LuggageModel luggage = new LuggageModel(MainActivity.viewId);

        viewLocationId.getSelectionModel().select(selectedLocation);
        viewStatus.setValue(luggage.getStatus());
        viewLocationAsText.setText(selectedLocation.toString());
        try {
            viewCustomerId.getSelectionModel().select(selectedCustomer);
            viewCustomerAsText.setText(selectedCustomer.toString());
                        
            MainActivity.searchTerm = selectedCustomer.getFullname();
            Debug.print("LuggageController setting: " + MainActivity.searchTerm);
            
        } catch (NullPointerException n) {
            printStackTrace(n);
        }
        viewStatusAsText.setText(luggage.getStatus());
        viewTags.setText(luggage.getTags());
        viewNotes.setText(luggage.getNotes());

        LocalDate date = LocalDate.parse(luggage.getDatetime());
        viewDate.setValue(date);
    }

    /**
     * Cancels editing a Luggage item, does not change saved data.
     */
    public void editCancel() {
        Stage addStage = (Stage) editCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    /**
     * Resets all fields in the edit view.
     */
    public void editReset() {
        editTags.setText("");
        editNotes.setText("");
        editLocationId.setValue(null);
        editCustomerId.setValue(null);
        editStatus.setValue(null);
        editDate.setValue(null);
    }

    /**
     * Handles saving changes to an existing Luggage item. Checks if all
     * necessary fields are filled and if so, writes to database, overwriting
     * existing data for selected Customer.
     */
    public void editSave() {
        if (editLocationId.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editLocationId.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the location of the luggage or where to ship it to.")
                    .showWarning();
            return;
        } else if (editStatus.getSelectionModel().getSelectedItem() == null) {
            Dialogs.create()
                    .owner((Stage) editStatus.getScene().getWindow())
                    .title("Warning")
                    .masthead("Selection error")
                    .message("Please select the status for the luggage item.")
                    .showWarning();
            return;
        } else if (editDate.getValue() == null) {
            Dialogs.create()
                    .owner((Stage) editDate.getScene().getWindow())
                    .title("Warning")
                    .masthead("Date format error")
                    .message("Please enter or select the correct date for the luggage item.")
                    .showWarning();
            return;
        }

        LuggageModel luggage = new LuggageModel(MainActivity.editId);
        try {
            luggage.setCustomerId(Integer.toString(editCustomerId.getSelectionModel().getSelectedItem().getId()));
        } catch (NullPointerException n) {
            printStackTrace(n);
        }
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

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the list view.
     */
    public void listKeyActions() {
        listTableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.E)) {
                listEdit();
            } else if (b.getCode().equals(KeyCode.H) || b.getCode().equals(KeyCode.F1)) {
                listHelp();
            } else if (b.getCode().equals(KeyCode.N)) {
                listNew();
            } else if (b.getCode().equals(KeyCode.R)) {
                listRemove();
            } else if (b.getCode().equals(KeyCode.V) || b.getCode().equals(KeyCode.ENTER)) {
                listView();
            } else if (b.getCode().equals(KeyCode.ESCAPE)) {
                listOnSearch();
            }
        });
        listSearchField.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                listResetTableView("", new String[0]);
                listSearchField.setText("");
                clearNotif();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the new view.
     */
    public void newKeyActions() {
        newLocationId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            }
            if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newCustomerId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newStatus.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newDate.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newTags.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                newSave();
            }
        });
        newNotes.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
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

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the edit view.
     */
    public void editKeyActions() {
        editLocationId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editCustomerId.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editStatus.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editDate.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editTags.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editNotes.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                editCancel();
            } else if (evt.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editSave.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                editSave();
            }
        });
        editReset.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
                newCancel();
            } else if (b.getCode().equals(KeyCode.ENTER)) {
                editReset();
            }
        });
        editCancel.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE) || b.getCode().equals(KeyCode.ENTER)) {
                editCancel();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the view page.
     */
    public void viewKeyActions() {
        viewLocationAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewCustomerAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewStatusAsText.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewDate.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewTags.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewNotes.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
        viewClose.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE) || evt.getCode().equals(KeyCode.ENTER)) {
                viewClose();
            }
        });
    }

    /**
     * Prints given parameter as notification label.
     *
     * @param notif
     */
    @FXML
    private void printNotif(String notif) {
        printNotif.setText(notif);
    }

    /**
     * Clears the notification label.
     */
    @FXML
    private void clearNotif() {
        printNotif.setText("");
    }

    /**
     * Clears the notification label.
     */
    @FXML
    private void clearSearch() {
        listOnSearch();
        clearNotif();
    }

    /**
     * Closes current view.
     */
    public void viewClose() {
        Stage addStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

}
