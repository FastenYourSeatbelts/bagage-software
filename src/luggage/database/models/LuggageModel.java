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
 * City Model
 *
 * A class that maps the luggage table
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
public class LuggageModel extends Model {
    
    public static final String STATUS_LOST = "lost";
    
    public static final String STATUS_FOUND = "found";
    
    public static final String STATUS_RESOLVED = "resolved";

    public LuggageModel() {
        
    }
    
    public LuggageModel(int id) {
        super(id);
    }
    
    public LuggageModel(String where, String... params) {
        super(where, params);
    }

    @Override
    protected String getTable() {
        return "luggage";
    }

    @Override
    protected Model getModel(int id) {
        return new LuggageModel(id);
    }
   
    /**
     * Return the location_id of the current row
     * 
     * @return 
     */
    public int getLocationId() {
        return Integer.parseInt(row.get("location_id"));
    }
    
    /**
     * Return the LocationModel of the current row
     * 
     * @return LocationModel
     */
    public LocationModel getLocation() {
        return new LocationModel(getLocationId());
    }
    
    /**
     * Return the location name of the current row
     * 
     * @return locaiton
     */
    public String getLocationName() {
        return getLocation().getName();
    }
   
    /**
     * Set the location_id of the current row
     * 
     * @param location_id
     */
    public void setLocationId(String location_id) {
        row.put("location_id", location_id);
    }
   
    /**
     * Return the status of the current row
     * 
     * @return 
     */
    public String getStatus() {
        return row.get("status");
    }
   
    /**
     * Set the status of the current row
     * 
     * @param status
     */
    public void setStatus(String status) {
        row.put("status", status);
    }
   
    /**
     * Return the tags of the current row
     * 
     * @return 
     */
    public String getTags() {
        return row.get("tags");
    }
   
    /**
     * Set the tags of the current row
     * 
     * @param tags
     */
    public void setTags(String tags) {
        row.put("tags", tags);
    }
   
    /**
     * Return the notes of the current row
     * 
     * @return 
     */
    public String getNotes() {
        return row.get("notes");
    }
   
    /**
     * Set the notes of the current row
     * 
     * @param notes
     */
    public void setNotes(String notes) {
        row.put("notes", notes);
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
 
}