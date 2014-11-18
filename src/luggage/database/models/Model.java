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
package luggage.database.models;

import luggage.database.DatabaseHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Model
 *
 * A super class for all the models containing
 * default model functions
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
abstract public class Model {
    
    protected abstract String getTable();
    
    protected abstract Model getModel(int id);
    
    protected HashMap<String, String> row = new HashMap<String, String>();
 
    public Model() {
        
    }
    
    public Model(int id) {
        try {
            Statement stmt = DatabaseHelper.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + getTable() + " WHERE id = " + id);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            rs.first();
            
            for(int i = 0; i < rsmd.getColumnCount(); i ++)
            {
                String column = rsmd.getColumnName((i+1));
                row.put(column, rs.getString(column));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Model(String where, String... params) {
        try {
            PreparedStatement statement = DatabaseHelper.getConnection().prepareStatement("SELECT * FROM " + getTable() + " WHERE " + where);

            for(int i = 0; i < params.length; i ++) {
                statement.setString((i + 1), params[i]);
            }

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            rs.first();

            for(int i = 0; i < rsmd.getColumnCount(); i ++)
            {
                String column = rsmd.getColumnName((i+1));
                row.put(column, rs.getString(column));
            } 

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Model> findAll(String where, String... params) {
        try {
            PreparedStatement statement = DatabaseHelper.getConnection().prepareStatement("SELECT * FROM " + getTable() + " WHERE " + where);

            for(int i = 0; i < params.length; i ++) {
                statement.setString((i + 1), params[i]);
            }

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            rs.first();
            
            
            ArrayList<Model> rowList = new ArrayList<>();
            
            while (rs.next())
            {
                Model model = getModel(1);
                rowList.add(model);
            }
            
            return rowList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
  
    /**
     * Return the id of the current row
     * 
     * @return 
     */
    public int getId() {
        if(row.get("id") == null) {
            return 0;
        }
        
        return Integer.parseInt(row.get("id"));
    }
    
    /**
     * Check if the current row exists in the database
     * 
     * @return boolean true if exists
     */
    public boolean exists() {
        if(getId() != 0)
        {
            return true;
        }
        
        return false;
    }
    
    public boolean save() {
        if(getId() == 0) {
            return create();
        }
        
        return update();
    }
    
    private boolean create() {
        try {
            String sQuery = "INSERT INTO " + getTable() + " (";
            
            boolean firstColumn = true;
            for (Entry<String, String> column  : row.entrySet()) {
                if(firstColumn) {
                    firstColumn = false;
                    sQuery = sQuery + column.getKey();
                } else {
                    sQuery = sQuery + ", " + column.getKey();
                }
            }
            
            sQuery = sQuery + ") VALUES (";
            
            firstColumn = true;
            for (Entry<String, String> column  : row.entrySet()) {
                if(firstColumn) {
                    firstColumn = false;
                    sQuery = sQuery + "?";
                } else {
                    sQuery = sQuery + ", ?";
                }
            }
            
            sQuery = sQuery + ")";
            
            PreparedStatement statement = DatabaseHelper.getConnection().prepareStatement(sQuery);
            
            int currentColumn = 1;
            for (Entry<String, String> column  : row.entrySet()) {
                statement.setString(currentColumn, column.getValue());
                currentColumn = currentColumn + 1;
            }
            
            return statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    private boolean update() {
        try {
            String sQuery = "UPDATE " + getTable() + " SET ";
            
            boolean firstColumn = true;
            for (Entry<String, String> column  : row.entrySet()) {
                if(firstColumn) {
                    firstColumn = false;
                    sQuery = sQuery + column.getKey() + " = ?";
                } else {
                    sQuery = sQuery + ", " + column.getKey() + " = ?";
                }
            }
            
            sQuery = sQuery + " WHERE id = " + getId();
            
            PreparedStatement statement = DatabaseHelper.getConnection().prepareStatement(sQuery);
            
            int currentColumn = 1;
            for (Entry<String, String> column  : row.entrySet()) {
                statement.setString(currentColumn, column.getValue());
                currentColumn = currentColumn + 1;
            }
            
            return statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean delete() {
        String sQuery = "DELETE FROM " + getTable() + " WHERE id = ?";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = DatabaseHelper.getConnection().prepareStatement(sQuery);
            preparedStmt.setInt(1, getId());
            return preparedStmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
