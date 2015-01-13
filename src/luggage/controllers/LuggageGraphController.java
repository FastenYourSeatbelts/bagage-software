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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.paint.Color.BLUE;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import luggage.database.models.LogModel;
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;
import luggage.Debug;
import luggage.MainActivity;
import luggage.helpers.StageHelper;

/**
 * UsersController
 *
 * Controller for graphs/luggage.fxml
 *
 * Package: luggage.controllers
 *
 * @author ITopia IS102-5
 */
public class LuggageGraphController extends BaseController implements Initializable {

    /**
     *
     */
    @FXML
    private AnchorPane helpGeneral;

    /**
     *
     */
    @FXML
    private GridPane chartHolder;

    /**
     *
     */
    @FXML
    private PieChart piechart;

    /**
     *
     */
    @FXML
    private BarChart barchart;

    /**
     *
     */
    @FXML
    private Button listHelp;

    /**
     *
     */
    @FXML
    private Button saveAsPng;

    /**
     *
     */
    @FXML
    private Button viewClose;

    /**
     *
     */
    @FXML
    private DatePicker start;

    /**
     *
     */
    @FXML
    private DatePicker end;

    /**
     *
     */
    @FXML
    private CheckBox showResolved;

    /**
     *
     */
    @FXML
    private Label printNotif;

    /**
     *
     */
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
            start.setValue(LocalDate.parse("2014-01-01"));
            initialTitle();
            //updateChart();
            KeyActions();

            // Help
            if (helpGeneral != null) {
                helpKeyAction();
            }
        }
    }

    /**
     * Opens the Graph's help view.
     */
    @FXML
    private void listHelp() {
        StageHelper.addStage("graphs/help", this, false, true);
    }

    double foundPercent, missingPercent, resolvedPercent, total;

    /**
     * Makes sure the pie is well-flavored and populated, handling the queries.
     */
    @FXML
    public void updateChart() {
        showResolved.setDisable(true);
        saveAsPng.setDisable(true);
        String dateQuery = "";

        if (start.getValue() != null && end.getValue() != null) {
            String sStart = start.getValue().toString() + " 00:00:00";
            String sEnd = end.getValue().toString() + " 00:00:00";
            dateQuery = "AND datetime BETWEEN '" + sStart + "' AND '" + sEnd + "'";
        } else if (start.getValue() != null) {
            String sStart = start.getValue().toString() + " 00:00:00";
            dateQuery = "AND datetime > '" + sStart + "'";
        } else if (end.getValue() != null) {
            String sEnd = end.getValue().toString() + " 00:00:00";
            dateQuery = "AND datetime < '" + sEnd + "'";
        }

        // Catches null dates
        if (start.getValue() == null || end.getValue() == null) {
            Debug.print("For some reason, either start or end is null. Method will continue.");
        } else if (start.getValue().compareTo(end.getValue()) > 0) {
            printNotif("Start date may not occur after end date!");
            showResolved.setDisable(true);
            saveAsPng.setDisable(true);
            Debug.print("User is an idiot: searched from " + start.getValue() + " to " + end.getValue());
            return;
        } else {
            Debug.print("Date is not null. Start: " + start.getValue() + " & End: " + end.getValue());
        }
        showResolved.setDisable(false);
        saveAsPng.setDisable(false);

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

        updateBarChart();
    }

    /**
     * Saves snapshot of pie chart as image.
     */
    public void saveAsPng() {
        // Disable the button until method ends
        saveAsPng.setDisable(true);
        // Starts off with reading the given dates
        Date date = new Date();
        String dateStart = start.getValue().toString();
        String dateEnd;

        if (end.getValue() == null) {
            dateEnd = MainActivity.dateFormatShort.format(date);
        } else {
            dateEnd = end.getValue().toString();
        }

        // Screenshot area
        if (piechart.getTitle().startsWith("Hover")) {
            piechart.setTitle("\n" + dateStart + " - " + dateEnd);
        }
        // Debug.print("Locally loaded CSS files prior to snapshot: " + piechart.getStylesheets().toString());
        chartHolder.getStylesheets().add("/resources/stylesheets/chartonexport.css");
        // Debug.print("Locally loaded CSS files after add: " + piechart.getStylesheets().toString());
        WritableImage image = chartHolder.snapshot(new SnapshotParameters(), null);
        chartHolder.getStylesheets().remove("/resources/stylesheets/chartonexport.css");
        // Debug.print("Locally loaded CSS files after remove: " + piechart.getStylesheets().toString());
        initialTitle();

        // File chooser area
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Pie Chart As");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Debug.print("Initial Directory poll after set: " + chooser.getInitialDirectory());
        chooser.setInitialFileName("Pie chart exported on " + MainActivity.dateFormatFull.format(date) + " of period " + dateStart + " - " + dateEnd);
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = chooser.showSaveDialog(stage);
        String extension = "PNG";

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
        saveAsPng.setDisable(false);
        Debug.print("Reached end of saveAsPng() method.");
    }

    /**
     * Sets the initial title.
     */
    private void initialTitle() {
        piechart.setTitle("Hover over the pie slices for more information.");
    }

    /**
     * Clears the notification label.
     */
    @FXML
    private void clearNotif() {
        printNotif.setText("");
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
                //Debug.print("Locally loaded CSS files prior to mouse enter: " + piechart.getStylesheets().toString());

                piechart.getStylesheets().clear();
                piechart.getStylesheets().add("/resources/stylesheets/title" + returnCssFilePath(lastSlice) + ".css");
                piechart.setTitle("The share of " + returnLabel(lastSlice) + " is approximately " + (Math.round(dValue) / 100) + "%.");
                data.getNode().setEffect(shadow);

                effectName = data.getNode().getEffect().toString().substring(20, data.getNode().getEffect().toString().length() - 9);
                // Debug.print("Locally loaded CSS files after mouse enter: " + piechart.getStylesheets().toString() + "\nMouse entered '" + lastSlice + "'. Effect '" + effectName + "' enabled.");
            });
        });

        // Clear and recolor the title when mouse moves away, clear shadow effect
        piechart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent me) -> {
                piechart.getStylesheets().remove("/resources/stylesheets/title" + returnCssFilePath(lastSlice) + ".css");
                initialTitle();
                data.getNode().setEffect(null);
                //Debug.print("Mouse exited  '" + lastSlice + "'. Effect '" + effectName + "' disabled.");
                // Debug.print("Locally loaded CSS files after mouse exit: " + piechart.getStylesheets().toString());
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
            if (evt.getCode().equals(KeyCode.F1) || evt.getCode().equals(KeyCode.ENTER)) {
                listHelp();
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
     * Closes current view.
     */
    @FXML
    private void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }

    private void updateBarChart() {
        String dateQuery = "";

        LocalDate startDate = LocalDate.parse("2014-01-01");
        LocalDate endDate = LocalDate.now();

        if (this.start.getValue() != null && this.end.getValue() != null) {
            startDate = start.getValue();
            endDate = end.getValue();
        } else if (this.start.getValue() != null) {
            startDate = start.getValue();
        } else if (this.end.getValue() != null) {
            endDate = end.getValue();
        }

		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		if(diffMonth < 1)
			diffMonth = 1;
		
		Date startDateForEach = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Calendar myDate = Calendar.getInstance();
		myDate.setTime(startDateForEach);
			
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Found");

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Missing");

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Resolved");

		barchart.getData().clear();
		for (int i = 0; i < diffMonth; i++) 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sStart = format.format(myDate.getTime());
			
			myDate.add(Calendar.MONTH, 1);
			
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sEnd = format2.format(myDate.getTime());
					
				//String sStart = this.start.getValue().toString() + " 00:00:00";
				//String sEnd = this.end.getValue().toString() + " 00:00:00";
			dateQuery = "AND datetime BETWEEN '" + sStart + "' AND '" + sEnd + "'";
			
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
			
			String dateZooi = myDate.get(Calendar.YEAR) + "/" + (myDate.get(Calendar.MONTH) + 1);
			
			series1.getData().add(new XYChart.Data(dateZooi, found.size()));
			
			series2.getData().add(new XYChart.Data(dateZooi, missing.size()));

			series3.getData().add(new XYChart.Data(dateZooi, resolved.size()));

		}
		
		barchart.getData().addAll(series1, series2, series3);
		
	}

}
