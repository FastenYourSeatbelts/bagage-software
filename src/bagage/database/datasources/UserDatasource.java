/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bagage.database.datasources;

import bagage.MainActivity;
import bagage.database.models.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tijme Gommers
 */
public class UserDatasource {
    
    public static final String TABLE_NAME = "users";
    
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_EMAIL = "email";
    public static final String TABLE_COLUMN_PASSWORD = "password";
    
    public UserModel findById(int id) {
        try {
            Statement stmt = MainActivity.oDatabase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_COLUMN_ID + " = " + id);
            
            rs.first();
            
            int row_id = rs.getInt(TABLE_COLUMN_ID);
            String row_email = rs.getString(TABLE_COLUMN_EMAIL);
            String row_password = rs.getString(TABLE_COLUMN_PASSWORD);
            
            UserModel user = new UserModel(row_id, row_email, row_password);
            
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDatasource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
