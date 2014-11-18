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
 */
package luggage.helpers;

import luggage.AppConfig;
import luggage.controllers.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * StageHelper
 *
 * Makes creating and updating stages & scenes easier
 *
 * @package luggage.helpers
 * @author Tijme Gommers
 */
public class StageHelper {
    
    public static void addStage(String sNewStage, Class oCurrentClass) {
        StageHelper.addStage(sNewStage, oCurrentClass, true, false);
    }
    
    public static void addStage(String sNewStage, Class oCurrentClass, boolean bMaximized, boolean bLocked) {
        Parent root = null;
        try {
            root = FXMLLoader.load(oCurrentClass.getResource("/luggage/views/" + sNewStage + ".fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add("/resources/stylesheets/header.css");
        
        Stage oNewStage = new Stage();
        oNewStage.setScene(newScene);
        oNewStage.getIcons().add(new Image("/resources/images/logo_red.png"));
        oNewStage.setTitle(AppConfig.ApplicationName + " " + sNewStage);
        oNewStage.setMinHeight(AppConfig.MinHeight);
        oNewStage.setMinWidth(AppConfig.MinWidth);
        
        if(bLocked)
        {
            oNewStage.setMaxHeight(AppConfig.MinHeight);
            oNewStage.setMaxWidth(AppConfig.MinWidth);
        }
        
        oNewStage.setMaximized(bMaximized);
        
        oNewStage.show();
    }

    public static void replaceStage(Stage oCurrentStage, String sNewStage, Class oCurrentClass) {
        StageHelper.addStage(sNewStage, oCurrentClass);
        oCurrentStage.close();
    }

    public static void closeStage(Stage oCurrentStage) {
        oCurrentStage.close();
    }

}
