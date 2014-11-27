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
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.Initializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import luggage.Debug;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Debug.print("GRAPH CONTROLLER-----------------------------------------------------------------");
    }
    /*
    //@Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Luggage status");
        stage.setWidth(500);
        stage.setHeight(500);

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Missing", 13),
                new PieChart.Data("Found", 25),
                new PieChart.Data("Resolved", 110));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Luggage status");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

      public static void main(String[] args) {
        launch(args);
    }
      
      
      
    public class PieChartDemo extends BaseController {

        //PIE CHART DATA
        private ObservableList data;

        //MAIN EXECUTOR
        public static void main(String[] args) {
            buildData()
        }

        //CONNECTION DATABASE SAVING DATA
        public void buildData() {
            data = FXCollections.observableArrayList();
            try {
                //SQL FOR SELECTING NATIONALITY OF CUSTOMER
                String SQL = "SELECT COUNT(status), "
                        + "type FROM luggage l"
                        + " WHERE na.id=c.nationality_id GROUP BY type";

                ResultSet rs = l.createStatement().executeQuery(SQL);
                while (rs.next()) {
                    //adding data on piechart data
                    data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                }
            }

        }

        @Override
        public void start(Stage stage) throws Exception {
            //PIE CHART
            PieChart pieChart = new PieChart();
            buildData();
            pieChart.getData().addAll(data);

            //Main Scene
            Scene scene = new Scene(pieChart);

            stage.setScene(scene);
            stage.setVisible(true);
        }
    }
 */   
}