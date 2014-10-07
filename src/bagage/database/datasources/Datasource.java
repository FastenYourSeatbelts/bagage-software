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
package bagage.database.datasources;

import bagage.database.DatabaseHelper;
import bagage.database.models.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Datasource
 *
 * Super class for all data sources
 * This class contains all default functions
 * for the child data sources 
 *
 * @package bagage.database.datasources
 * @author Tijme Gommers
 */
abstract public class Datasource {
    
    abstract protected String getTable();
    abstract protected Model getModel();
    abstract protected String[] getColumns();
    
    /**
     * Find a row by id
     * 
     * @param id
     * @return 
     */
    public Model findById(int id) {
        try {
            Statement stmt = DatabaseHelper.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + getTable() + " WHERE id = " + id);
            
            rs.first();
            
            Model model = getModel();
            
            for(String column : getColumns()) {
                model.set(column, rs.getString(column));
            }
        
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(UserDatasource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Delete a row by id
     * 
     * @param id 
     */
    public void deleteById(int id) {
        try {
            Statement stmt = DatabaseHelper.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeQuery("DELETE FROM " + getTable() + " WHERE id = " + id);
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
