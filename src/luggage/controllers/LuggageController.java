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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
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
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;

/**
 * LuggageController
 *
 * Controller for customers/list.fxml
 *
 * @package luggage.controllers
 * @author Tijme Gommers
 */
public class LuggageController extends BaseController implements Initializable {

    @FXML
    private TableView listTableView;

    @FXML
    private TableColumn listTableViewId;

    @FXML
    private TableColumn listTableViewStatus;

    @FXML
    private TableColumn listTableViewLocation;

    @FXML
    private TableColumn listTableViewTags;

    @FXML
    private TableColumn listTableViewDate;

    @FXML
    private TextField listSearchField;

    @FXML
    private Button newAdd;

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
    private TextField addTags;

    @FXML
    private TextField addLocation;

    @FXML
    private TextField addNotes;

    @FXML
    private DatePicker addDate;

    @FXML
    private ChoiceBox addStatus;

    private ObservableList<LuggageModel> listData = FXCollections.observableArrayList();

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
                if (listTableView != null) {
                    listResetTableView("", new String[0]);
                }

                // Add
                if (addStatus != null) {
                    setAddChoiceBoxes();
                }
            
            }
         }).start();
    }

    public void setAddChoiceBoxes() {
        addStatus.setItems(FXCollections.observableArrayList(
                "",
                "Missing",
                "Found",
                "Resolved"
        ));
    }

    @FXML
    protected void listOnSearch() {

        String[] keywords = listSearchField.getText().split("\\s+");

        String[] params = new String[3 * keywords.length];
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
            query += " OR datetime LIKE ?";

            firstColumn = false;
        }

        listResetTableView(query, params);
    }

    @FXML
    public void listNew() {
        StageHelper.addStage("luggage/add", this, false, true);
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
        listTableViewLocation.setCellValueFactory(new PropertyValueFactory("locationName"));
        listTableViewTags.setCellValueFactory(new PropertyValueFactory("tags"));
        listTableViewDate.setCellValueFactory(new PropertyValueFactory("datetime"));

        listTableView.setItems(listData);
    }

    public void newCancel() {
        Stage addStage = (Stage) newCancel.getScene().getWindow();
        StageHelper.closeStage(addStage);
    }

    public void newReset() {
        addFirstname.setText("");
        addPrefix.setText("");
        addLastname.setText("");
        addTags.setText("");
        addLocation.setText("");
        addNotes.setText("");
        addStatus.setValue("");
    }

    public void newSave() {
        if (addStatus.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        LuggageModel luggage = new LuggageModel();
        luggage.setStatus(addStatus.getSelectionModel().getSelectedItem().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = sdf.format(addDate.getValue());
        luggage.setDatetime(dateString);
        luggage.setTags(addTags.getText());
        luggage.setNotes(addNotes.getText());
        luggage.save();
        LuggageController luggageController = (LuggageController) StageHelper.callbackController;
        luggageController.listOnSearch();

        newCancel();
    }
}
