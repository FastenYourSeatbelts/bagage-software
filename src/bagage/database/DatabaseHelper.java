/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bagage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tijme Gommers
 */
public class DatabaseHelper {
    
    Connection oConnection = null;

    public Connection getConnection() {
        if(oConnection == null) {
            openConnection();
        }
        
        return oConnection;
    }
    
    public void openConnection() {
        try {
            oConnection = DriverManager.getConnection(DatabaseConfiguration.name, DatabaseConfiguration.user, DatabaseConfiguration.pass);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void closeConnection(Connection oConnection) {
        if(oConnection == null) {
            return;
        }

        try {
            oConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
