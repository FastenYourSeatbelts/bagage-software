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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import luggage.Debug;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import luggage.database.models.LuggageModel;
import luggage.database.models.Model;
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
    public DatePicker start;

    @FXML
    public DatePicker end;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("GRAPH CONTROLLER-----------------------------------------------------------------");

        if (piechart != null) {
            piechart.visibleProperty().set(false);
            start.setValue(LocalDate.parse("1970-01-01"));
            updateChart();
        }
    }

    @FXML
    public void listHelp() {
        StageHelper.addStage("graphs/help", this, false, true);
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

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Missing", missing.size()),
                new PieChart.Data("Found", found.size()),
                new PieChart.Data("Resolved", resolved.size())
        );

        piechart.setData(pieChartData);
    }
}
