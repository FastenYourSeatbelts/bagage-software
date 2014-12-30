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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.BLUE;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import luggage.database.models.LogModel;
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;
import luggage.Debug;
import luggage.helpers.StageHelper;

/**
 * UsersController
 *
 * Controller for graphs/luggage.fxml
 *
 * @package luggage.controllers
 * @author Nick + Lars
 */
public class LuggageGraphController extends BaseController implements Initializable {
    @FXML
    private PieChart piechart;

    @FXML
    private Button listHelp;

    @FXML
    private Button viewClose;

    @FXML
    private DatePicker start;

    @FXML
    private DatePicker end;

    @FXML
    private CheckBox showResolved;

    @FXML
    private Label printNotif;

    @FXML
    private Stage stage;

    /**
     * Called on controller start
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("GRAPH CONTROLLER-----------------------------------------------------------------");

        if (piechart != null) {
            piechart.visibleProperty().set(true);
            end.setValue(LocalDate.now());
            start.setValue(LocalDate.parse("2011-01-01"));
            initialTitle();
            updateChart();
            KeyActions();
        }
    }

    /**
     * Opens the Graph's help view.
     */
    @FXML
    public void listHelp() {
        StageHelper.addStage("graphs/help", this, false, true);
    }

    double foundPercent, missingPercent, resolvedPercent, total;

    /**
     * Makes sure the pie is well-flavored and populated, handling the queries.
     */
    @FXML
    public void updateChart() {

        String dateQuery = "";

        if (this.start.getValue() != null && this.end.getValue() != null) {
            String sStart = this.start.getValue().toString() + " 00:00:00";
            String sEnd = this.end.getValue().toString() + " 00:00:00";

            dateQuery = "AND datetime BETWEEN '" + sStart + "' AND '" + sEnd + "'";
        } else if (this.start.getValue() != null) {
            String sStart = this.start.getValue().toString() + " 00:00:00";
            dateQuery = "AND datetime > '" + sStart + "'";
        } else if (this.end.getValue() != null) {
            String sEnd = this.end.getValue().toString() + " 00:00:00";
            dateQuery = "AND datetime < '" + sEnd + "'";
        }

        // Catches null dates
//        try {
        if (start.getValue() == null || end.getValue() == null) {
            Debug.print("For some reason, either start or end is null. Method will continue.");
        } else if (start.getValue().compareTo(end.getValue()) > 0) {
            printNotif("The start date may not occur after the end date!");
            Debug.print("User is an idiot: searched from " + start.getValue() + " to " + end.getValue());
            return;
        } else {
            Debug.print("Date is not null. Start: " + start.getValue() + " & End: " + end.getValue());
        }
//        } catch (NullPointerException n) {
//            Logger.getLogger(LuggageGraphController.class.getName()).log(Level.SEVERE, null, n);
//        }

        LuggageModel luggage = new LuggageModel();

        // Query time
        String[] foundParams = new String[1];
        foundParams[0] = "Found";
        List<Model> found = luggage.findAll("status = ? " + dateQuery, foundParams);

        String[] missingParams = new String[1];
        missingParams[0] = "Missing";
        List<Model> missing = luggage.findAll("status = ? " + dateQuery, missingParams);

        String[] resolvedParams = new String[1];
        resolvedParams[0] = "Resolved";
        List<Model> resolved = luggage.findAll("status = ? " + dateQuery, resolvedParams);

        ObservableList<PieChart.Data> pieChartData;
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Missing: " + missing.size() + " (" + Math.round(missingPercent) + "%)", missing.size()),
                new PieChart.Data("Found: " + found.size() + " (" + Math.round(foundPercent) + "%)", found.size())
        );

        // If Resolved cases are hidden, but Resolved CheckBox is ticked, apply query,
        // redraw including Resolved, recalculate percentages for hoverNotif() method.
        if (showResolved.isSelected() && pieChartData.size() == 2) {
            total = found.size() + missing.size() + resolved.size();
            foundPercent = (found.size() / total * 100);
            missingPercent = (missing.size() / total * 100);
            resolvedPercent = (resolved.size() / total * 100);

            pieChartData.removeAll(pieChartData);
            pieChartData.add(new PieChart.Data("Missing: " + missing.size() + " (" + Math.round(missingPercent) + "%)", missing.size()));
            pieChartData.add(new PieChart.Data("Found: " + found.size() + " (" + Math.round(foundPercent) + "%)", found.size()));
            pieChartData.add(new PieChart.Data("Resolved: " + resolved.size() + " (" + Math.round(resolvedPercent) + "%)", resolved.size()));

            hoverNotif();
            Debug.print("Graph updated: keep showing 'Resolved' cases");
        } else if (!showResolved.isSelected() && pieChartData.size() == 2) { // Apply query and update chart
            total = found.size() + missing.size();
            foundPercent = (found.size() / total * 100);
            missingPercent = (missing.size() / total * 100);

            pieChartData.removeAll(pieChartData);
            pieChartData.add(new PieChart.Data("Missing: " + missing.size() + " (" + Math.round(missingPercent) + "%)", missing.size()));
            pieChartData.add(new PieChart.Data("Found: " + found.size() + " (" + Math.round(foundPercent) + "%)", found.size()));

            hoverNotif();
            Debug.print("Graph updated: keep hiding 'Resolved 'cases");
        } else if (pieChartData.size() != 2 && pieChartData.size() != 3) {
            Debug.print("You ate two pie slices! How on Earth did you manage to do that? Slices left on the plate: " + pieChartData.size());
        }

        //These only activate when toggling the Resolved CheckBox
        showResolved.setOnAction((unusedVariable) -> {
            if (this.showResolved.isSelected() && pieChartData.size() == 2) { // Toggled on: Apply any date changes and update chart
                total = found.size() + missing.size() + resolved.size();
                foundPercent = (found.size() / total * 100);
                missingPercent = (missing.size() / total * 100);
                resolvedPercent = (resolved.size() / total * 100);

                pieChartData.removeAll(pieChartData);
                pieChartData.add(new PieChart.Data("Missing: " + missing.size() + " (" + Math.round(missingPercent) + "%)", missing.size()));
                pieChartData.add(new PieChart.Data("Found: " + found.size() + " (" + Math.round(foundPercent) + "%)", found.size()));
                pieChartData.add(new PieChart.Data("Resolved: " + resolved.size() + " (" + Math.round(resolvedPercent) + "%)", resolved.size()));

                hoverNotif();
                Debug.print("CheckBox action: show 'Resolved 'cases");
            } else if (!this.showResolved.isSelected() && pieChartData.size() == 3) { // Toggled off: Apply any date changes and update chart
                total = found.size() + missing.size();
                foundPercent = (found.size() / total * 100);
                missingPercent = (missing.size() / total * 100);

                pieChartData.removeAll(pieChartData);
                pieChartData.add(new PieChart.Data("Missing: " + missing.size() + " (" + Math.round(missingPercent) + "%)", missing.size()));
                pieChartData.add(new PieChart.Data("Found: " + found.size() + " (" + Math.round(foundPercent) + "%)", found.size()));

                hoverNotif();
                Debug.print("CheckBox action: hide 'Resolved 'cases");
            }
        });
        piechart.setData(pieChartData);
        hoverNotif();
        clearNotif();
        Debug.print("Reached end of updateChart() method.");
    }

    /**
     * Saves snapshot of pie chart as image.
     */
    public void saveAsPng() {
        // Starts off with reading the given dates
        DateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        DateFormat dateFormatShort = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStart = start.getValue().toString();
        String dateEnd;

        if (end.getValue() == null) {
            dateEnd = dateFormatShort.format(date);
        } else {
            dateEnd = end.getValue().toString();
        }

        // Screenshot area
        if (piechart.getTitle().startsWith("Hover")) {
            piechart.setTitle("\n" + dateStart + " - " + dateEnd);
        }
        Debug.print("Locally loaded CSS files prior to snapshot: " + piechart.getStylesheets().toString());
        piechart.getStylesheets().add("/resources/stylesheets/chartonexport.css");
        Debug.print("Locally loaded CSS files after add: " + piechart.getStylesheets().toString());
        WritableImage image = piechart.snapshot(new SnapshotParameters(), null);
        piechart.getStylesheets().remove("/resources/stylesheets/chartonexport.css");
        Debug.print("Locally loaded CSS files after remove: " + piechart.getStylesheets().toString());
        initialTitle();

        // File chooser area
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Pie Chart As");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Debug.print("Initial Directory poll after set: " + chooser.getInitialDirectory());
        chooser.setInitialFileName("Pie chart exported on " + dateFormatFull.format(date) + " of period " + dateStart + " - " + dateEnd);
//        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG", "*.png")); // This doesn't even work?
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"));
//                new FileChooser.ExtensionFilter("bmp", "*.bmp"),
//                new FileChooser.ExtensionFilter("gif", "*.gif"),
//                new FileChooser.ExtensionFilter("jpg", "*.jpg"),
//                new FileChooser.ExtensionFilter("pdf", "*.pdf"),
//                new FileChooser.ExtensionFilter("tiff", "*.tiff"));
        File file = chooser.showSaveDialog(stage);
        String extension = "PNG";
//        extension = returnExtension(file.toString().toLowerCase());
//        Debug.print("Extension: " + extension);

        try {
            if (file == null) {
                printNotif("Pie chart export canceled.");
                Debug.print("Pie chart export canceled by user.");
            } else {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), extension, file);
                printNotif("Pie chart successfully exported!");
                Debug.logToDatabase(LogModel.TYPE_INFO, "Pie chart exported as " + extension + ".");
            }
        } catch (IOException io) {
            Logger.getLogger(LuggageGraphController.class.getName()).log(Level.SEVERE, null, io);
        }
        Debug.print("Reached end of saveAsPng() method.");
    }

    // Disabled until I fix other export for extensions other than PNG
//    private String returnExtension(String file) {
//        if (file.endsWith(".png")) {
//            return "PNG";
//        } else if (file.endsWith(".bmp")) {
//            return "BMP";
//        } else if (file.endsWith(".gif")) {
//            return "GIF";
//        } else if (file.endsWith(".jpg")) {
//            return "JPEG";
//        } else if (file.endsWith(".pdf")) {
//            return "PDF";
//        } else if (file.endsWith(".tiff")) {
//            return "TIFF";
//        } else {
//            return "png";
//        }
//    }
    /**
     * Sets the initial title.
     */
    private void initialTitle() {
        piechart.setTitle("Hover over the pie slices for more information.");
    }

    /**
     * Clears the notification label.
     */
    public void clearNotif() {
        printNotif.setText("");
    }

    /**
     * Prints given parameter as notification label.
     *
     * @param notif
     */
    public void printNotif(String notif) {
        printNotif.setText(notif);
    }

//    private void watchTheDate(SimpleDateFormat start, SimpleDateFormat end) {}
    /**
     * Does magic when the mouse hovers over a pie slice
     */
    String lastSlice, effectName = "";

    private void hoverNotif() {
        DropShadow shadow = new DropShadow();
        shadow.setColor(BLUE);
        // Mouse on slice -> get name and value, set useful pie chart title, make slice stand out
        piechart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                lastSlice = returnLabel(data.getName());
                Double dValue = data.getPieValue() / total * 10000;
                Debug.print("Locally loaded CSS files prior to mouse enter: " + piechart.getStylesheets().toString());

                piechart.getStylesheets().clear();
                piechart.getStylesheets().add("/resources/stylesheets/title" + returnCssFilePath(lastSlice) + ".css");
                piechart.setTitle("The share of " + returnLabel(lastSlice) + " is approximately " + (Math.round(dValue) / 100) + "%.");
                data.getNode().setEffect(shadow);
                
                effectName = data.getNode().getEffect().toString().substring(20, data.getNode().getEffect().toString().length() - 9);
                Debug.print("Locally loaded CSS files after mouse enter: " + piechart.getStylesheets().toString() + "\nMouse entered '" + lastSlice + "'. Effect '" + effectName + "' enabled.");
            });
        });

        // Clear and recolor the title when mouse moves away, clear shadow effect
        piechart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent me) -> {
                piechart.getStylesheets().remove("/resources/stylesheets/title" + returnCssFilePath(lastSlice) + ".css");
                initialTitle();
                data.getNode().setEffect(null);
                Debug.print("Mouse exited  '" + lastSlice + "'. Effect '" + effectName + "' disabled.");
                Debug.print("Locally loaded CSS files after mouse exit: " + piechart.getStylesheets().toString());
            });
        });
    }

    private String returnLabel(String pieSlice) {
        if (pieSlice.startsWith("Found")) {
            return "Found";
        } else if (pieSlice.startsWith("Missing")) {
            return "Missing";
        } else if (pieSlice.startsWith("Resolved")) {
            return "Resolved";
        } else {
            return "the selected slice";
        }
    }

    private String returnCssFilePath(String pieSlice) {
        if (pieSlice.startsWith("Found")) {
            return "found";
        } else if (pieSlice.startsWith("Missing")) {
            return "missing";
        } else if (pieSlice.startsWith("Resolved")) {
            return "resolved";
        } else {
            return "default";
        }
    }

    /**
     * Creates the (mouse, keyboard, etc.) event filters for the graph view.
     */
    @FXML
    private void KeyActions() {
        start.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            clearNotif();
            if (evt.getCode().equals(KeyCode.ENTER)) {
                updateChart();
            }
        });
        end.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            clearNotif();
            if (evt.getCode().equals(KeyCode.ENTER)) {
                updateChart();
            }
        });
        showResolved.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            clearNotif();
            if (evt.getCode().equals(KeyCode.ENTER)) {
                if (showResolved.isSelected()) {
                    showResolved.setSelected(false);
                    Debug.print("CheckBox action: hide 'Resolved 'cases");
                } else if (!showResolved.isSelected()) {
                    showResolved.setSelected(true);
                    Debug.print("CheckBox action: show 'Resolved 'cases");
                }
                updateChart();
            }
        });
        listHelp.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ENTER)) {
                listHelp();
            }
        });
    }

    /**
     * Closes current view.
     */
    private void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
