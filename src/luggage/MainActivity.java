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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public static boolean firstStart;

    /**
     *
     */
    public static DateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    /**
     *
     */
    public static DateFormat dateFormatShort = new SimpleDateFormat("yyyy-MM-dd");

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
    public static int customerIdHolder;

    /**
     *
     */
    public static String searchTerm;

    public static String userSessionStart;
    /**
     *
     */
    public static TabPane tabs;

    /**
     *
     */
    public static Tab customersTab;

    /**
     *
     */
    public static Tab luggageTab;

    /**
     *
     */
    public static Tab usersTab;

    /**
     *
     */
    public static Tab logTab;

    /**
     *
     */
    public static int viewLuggageParam;

    /**
     *
     */
    public static Runnable viewLuggageParamCallback;

    /**
     *
     * @param luggage
     */
    public static void setViewLuggageParam(int luggage) {
        viewLuggageParam = luggage;
        Debug.print("Setting viewLuggageParam: " + viewLuggageParam);
        if (viewLuggageParamCallback != null) {
            viewLuggageParamCallback.run();
        }
    }

    /**
     *
     */
    public static int viewCustomerParam;

    /**
     *
     */
    public static Runnable viewCustomerParamCallback;

    /**
     *
     * @param customer
     */
    public static void setViewCustomerParam(int customer) {
        viewCustomerParam = customer;
        Debug.print("Setting viewCustomerParam: " + viewCustomerParam);
        if (viewCustomerParamCallback != null) {
            viewCustomerParamCallback.run();
        }
    }

    /**
     *
     */
    public static int viewLogsParam;

    /**
     *
     */
    public static Runnable viewLogsParamCallback;

    /**
     *
     * @param user
     */
    public static void setViewLogsParam(int user) {
        viewLogsParam = user;
        if (viewLogsParamCallback != null) {
            viewLogsParamCallback.run();
        }
    }

    /**
     *
     */
    public static int viewUserParam;

    /**
     *
     */
    public static Runnable viewUserParamCallback;

    /**
     *
     * @param user
     */
    public static void setViewUserParam(int user) {
        viewLogsParam = user;
        if (viewLogsParamCallback != null) {
            viewLogsParamCallback.run();
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
            firstStart = true;

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
