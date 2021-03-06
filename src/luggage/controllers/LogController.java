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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luggage.Debug;
import luggage.MainActivity;
import luggage.database.models.LogModel;
import luggage.database.models.Model;
import luggage.helpers.StageHelper;

/**
 * LogController
 *
 * Controller for log/list.fxml and log/help.fxml
 *
 * Package: luggage.controllers
 * @author ITopia IS102-5
 */
public class LogController extends BaseController implements Initializable {

    /**
     *
     */
    @FXML
    private TableView listTableView;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewEmployee;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewType;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewMessage;

    /**
     *
     */
    @FXML
    private TableColumn listTableViewDate;

    /**
     *
     */
    @FXML
    private AnchorPane helpGeneral;

    /**
     *
     */
    @FXML
    private Button listHelp;

    /**
     *
     */
    @FXML
    private Button viewClose;

    /**
     *
     */
    @FXML
    private Button viewUser;

    /**
     *
     */
    @FXML
    private Label printNotif;

    /**
     *
     */
    @FXML
    private TextField listSearchField;

    /**
     *
     */
    private ObservableList<LogModel> listData = FXCollections.observableArrayList();

    /**
     * Opens the Log list's help view.
     */
    @FXML
    public void listHelp() {
        StageHelper.addStage("log/help", this, false, true);
    }

    /**
     * Handles the search field functionality.
     */
    @FXML
    public void listOnSearch() {

        if (MainActivity.viewLogsParam != 0) {
            viewLogs();
        } else {
            String[] keywords = listSearchField.getText().split("\\s+");

            String[] params = new String[3 * keywords.length];
            boolean firstColumn = true;
            String query = "";

            for (int i = 0; i < keywords.length; i++) {
                if (firstColumn) {
                    params[0 + i] = "%" + keywords[i] + "%";
                    query += "user_id LIKE ?";
                } else {
                    params[0 + i] = "%" + keywords[i] + "%";
                    query += " OR user_id LIKE ?";
                }

                params[1 + i] = "%" + keywords[i] + "%";
                query += " OR type LIKE ?";

                params[2 + i] = "%" + keywords[i] + "%";
                query += " OR message LIKE ?";

                firstColumn = false;
            }

            listResetTableView(query, params);
        }
    }

    private ObservableList<LogModel> data = FXCollections.observableArrayList();

    /**
     * Called on controller start
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("LOG CONTROLLER-----------------------------------------------------------------");

        MainActivity.viewLogsParamCallback = new Runnable() {
            @Override
            public void run() {
                viewLogs();
            }
        };

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listOnSearch();
                listTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                keyActions();
            }
        });
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the list view.
     */
    private void keyActions() {
        listTableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.H) || b.getCode().equals(KeyCode.F1)) {
                listHelp();
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
     * Creates the event filter for the help view.
     */
    @FXML
    private void helpKeyAction() {
        helpGeneral.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent b) -> {
            if (b.getCode().equals(KeyCode.ESCAPE)) {
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
        LogModel logs = new LogModel();
        List<Model> allLogs = logs.findAll(where, params);

        data = FXCollections.observableArrayList();
        for (Model allLog : allLogs) {
            LogModel log = (LogModel) allLog;
            data.add(log);
        }

        listTableViewEmployee.setCellValueFactory(new PropertyValueFactory("username"));
        listTableViewType.setCellValueFactory(new PropertyValueFactory("type"));
        listTableViewMessage.setCellValueFactory(new PropertyValueFactory("message"));
        listTableViewDate.setCellValueFactory(new PropertyValueFactory("datetime"));

        listTableView.setItems(data);
    }

    /**
     * Shows the actions the user has performed. Receiver for viewUserActions(),
     * origin UsersController.
     */
    @FXML
    public void viewLogs() {
        Debug.print("LOG CONTROLLER-----------------------------------------------------------------"
                + "\nInteger.toString(MainActivity.viewLogsParam): " + Integer.toString(MainActivity.viewLogsParam));
        listResetTableView("", new String[0]);
        listSearchField.setText("");

        listResetTableView("user_id LIKE ?", Integer.toString(MainActivity.viewLogsParam));
        printNotif("Searched \"" + MainActivity.searchTerm + "\". Click here to reset or use the search. ");
        MainActivity.viewLogsParam = 0;
        MainActivity.searchTerm = "";
        Debug.print("Reached end of viewUserLog() method (LogController).");
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
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
