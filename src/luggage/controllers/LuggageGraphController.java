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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    /**
     * Called on controller start
     *
     * @param url
     * @param rb
     */
    @FXML
    public PieChart piechart;

    @FXML
    private Button listHelp;

    @FXML
    private Button viewClose;

    @FXML
    public DatePicker start;

    @FXML
    public DatePicker end;

    @FXML
    public CheckBox showResolved;

    @FXML
    public Label printNotif;

    public void printNotif() {
        printNotif.setText("");
    }

    @FXML
    public void saveAsPng() {
        WritableImage image = piechart.snapshot(new SnapshotParameters(), null);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStart = start.getValue().toString();
        String dateEnd;

        if (end.getValue() == null) {
            System.out.print(dateFormat2.format(date));
            dateEnd = dateFormat2.format(date);
        } else {
            dateEnd = end.getValue().toString();
        }

        // TODO: A working FileChooser / location picker :/
        File file = new File("Piechart of " + dateStart + " - " + dateEnd + " exported at " + dateFormat.format(date) + ".png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            printNotif.setText("Piechart successfully exported!");
            Debug.logToDatabase(LogModel.TYPE_INFO, "Piechart exported as image.");
        } catch (IOException ex) {
            Logger.getLogger(LuggageGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("GRAPH CONTROLLER-----------------------------------------------------------------");

        if (piechart != null) {
            piechart.visibleProperty().set(false);
            start.setValue(LocalDate.parse("2011-01-01"));
            piechart.setTitle("Hover over the pie slices for more information.");
            updateChart();
            KeyActions();
        }
    }

    @FXML
    public void listHelp() {
        StageHelper.addStage("graphs/help", this, false, true);
    }

    double foundPercent, missingPercent, resolvedPercent, total;

    @FXML
    public void KeyActions() {
        start.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            printNotif();
            if (evt.getCode().equals(KeyCode.ENTER)) {
                updateChart();
            }
        });
        end.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            printNotif();
            if (evt.getCode().equals(KeyCode.ENTER)) {
                updateChart();
            }
        });
        showResolved.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            printNotif();
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

    @FXML
    public void updateChart() {
        piechart.visibleProperty().set(true);

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

        LuggageModel luggage = new LuggageModel();

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
        } else if (!showResolved.isSelected() && pieChartData.size() == 2) {
            total = found.size() + missing.size();
            foundPercent = (found.size() / total * 100);
            missingPercent = (missing.size() / total * 100);

            pieChartData.removeAll(pieChartData);
            pieChartData.add(new PieChart.Data("Missing: " + missing.size() + " (" + Math.round(missingPercent) + "%)", missing.size()));
            pieChartData.add(new PieChart.Data("Found: " + found.size() + " (" + Math.round(foundPercent) + "%)", found.size()));

            hoverNotif();
            Debug.print("Graph updated: keep hiding 'Resolved 'cases");
        }

        showResolved.setOnAction((showResolved) -> {
            if (this.showResolved.isSelected() && pieChartData.size() == 2) {
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
            } else if (!this.showResolved.isSelected() && pieChartData.size() == 3) {
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
        printNotif();
    }
    
    public void hoverNotif() {
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            String s = data.getName().substring(0, data.getName().length()-6);
                            Double d = Double.valueOf(data.getPieValue() / total * 10000);
                            piechart.setTitle("The share of '" + s + "' is approximately " + (Math.round(d) / 100) + "%");
                        }
                    });
        }
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            piechart.setTitle("");
                        }
                    });
        }
    }

    public void viewClose() {
        Stage cancelStage = (Stage) viewClose.getScene().getWindow();
        StageHelper.closeStage(cancelStage);
    }
}
