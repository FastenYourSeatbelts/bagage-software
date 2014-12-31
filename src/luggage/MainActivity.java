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
 */
package luggage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import luggage.database.DatabaseHelper;
import luggage.helpers.StageHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * MainActivity
 *
 * Main activity for the application
 *
 * @package luggage
 * @author ITopia IS102-5
 */
public class MainActivity extends Application {

    /**
     *
     */
    public static int editId;

    /**
     *
     */
    public static int viewId;

    /**
     *
     */
    public static TabPane tabs;

    /**
     *
     */
    public static Tab logTab;

    /**
     *
     */
    public static Tab luggageTab;

    /**
     *
     */
    public static int viewUserLogParam;

    /**
     *
     */
    public static int viewCustomerOverviewParam;

    /**
     *
     */
    public static Runnable viewUserLogParamCallback;

    /**
     *
     */
    public static Runnable viewCustomerOverviewParamCallback;

    /**
     *
     * @param user
     */
    public static void setViewUserLogParam(int user) {
        viewUserLogParam = user;
        if (viewUserLogParamCallback != null) {
            viewUserLogParamCallback.run();
        }
    }
    
    /**
     *
     * @param customer
     */
    public static void setViewCustomerOverviewParam(int customer) {
        viewCustomerOverviewParam = customer;
        if (viewCustomerOverviewParamCallback != null) {
            viewCustomerOverviewParamCallback.run();
        }
    }

    /**
     * Called on application run.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        startMainStage();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper.openConnection();
            }
        });
    }

    /**
     * Called on application run.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Opens the login view.
     */
    public void startMainStage() {
        try {
            FXMLLoader primaryLoader = new FXMLLoader(this.getClass().getResource("/luggage/views/login.fxml"));
            Parent root = null;
            root = (Parent) primaryLoader.load();

            Scene newScene = new Scene(root);
            newScene.getStylesheets().add("/resources/stylesheets/header.css");

            Stage oNewStage = new Stage();
            oNewStage.setScene(newScene);
            oNewStage.getIcons().add(new Image("/resources/images/logo_red.png"));
            oNewStage.setTitle(AppConfig.ApplicationName + " " + "login");
            oNewStage.setMinHeight(AppConfig.MinHeight);
            oNewStage.setMinWidth(AppConfig.MinWidth);

            oNewStage.setMaximized(true);

            oNewStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StageHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
