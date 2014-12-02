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

/**
 * Log Model
 *
 * A class that maps the log table
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
public class LogModel extends Model {
    
    public static final String TYPE_INFO = "info";
    
    public static final String TYPE_WARNING = "warning";
    
    public static final String TYPE_ERROR = "error";

    public LogModel() {
        
    }
    
    public LogModel(int id) {
        super(id);
    }
    
    public LogModel(String where, String... params) {
        super(where, params);
    }

    @Override
    protected String getTable() {
        return "log";
    }
    
    @Override
    protected String getOrderBy() {
        return "ORDER BY datetime DESC";
    }

    @Override
    protected Model getModel() {
        return new LogModel();
    }
    
   
    /**
     * Return the type of the current row
     * 
     * @return 
     */
    public String getType() {
        return row.get("type");
    }
   
    /**
     * Set the type of the current row
     * 
     * @param type 
     */
    public void setType(String type) {
        row.put("type", type);
    }
   
    /**
     * Return the datetime of the current row
     * 
     * @return 
     */
    public String getDatetime() {
        return row.get("datetime");
    }
   
    /**
     * Set the datetime of the current row
     * 
     * @param datetime 
     */
    public void setDatetime(String datetime) {
        row.put("datetime", datetime);
    }
   
    /**
     * Return the message of the current row
     * 
     * @return 
     */
    public String getMessage() {
        return row.get("message");
    }
   
    /**
     * Set the message of the current row
     * 
     * @param message 
     */
    public void setMessage(String message) {
        row.put("message", message);
    }
    
  
    
        /**
     * Return the location_id of the current row
     * 
     * @return 
     */
    public int getUserId() {
        return Integer.parseInt(row.get("user_id"));
    }
    
    /**
     * Return the LocationModel of the current row
     * 
     * @return UserModel
     */
    public UserModel getUsers() {
        return new UserModel(getUserId());
    }
    
    
    
        /**
     * Return the location name of the current row
     * 
     * @return username
     */
    public String getUsername() {
        return getUsers().getUsername();
    }
   
    /**
     * Set the location_id of the current row
     * 
     * @param user_id
     */
    public void setUserId(String user_id) {
        row.put("user_id", user_id);
    }
}
