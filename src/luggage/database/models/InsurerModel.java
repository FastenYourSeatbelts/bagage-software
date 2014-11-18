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
 * Insurer Model
 *
 * A class that maps the insurers table
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
public class InsurerModel extends Model {

    public InsurerModel() {
        
    }
    
    public InsurerModel(int id) {
        super(id);
    }
    
    public InsurerModel(String where, String... params) {
        super(where, params);
    }

    @Override
    protected Model getModel(int id) {
        return new InsurerModel(id);
    }

    @Override
    protected String getTable() {
        return "insurers";
    }
   
    /**
     * Return the name of the current row
     * 
     * @return 
     */
    public String getName() {
        return row.get("name");
    }
   
    /**
     * Set the name of the current row
     * 
     * @param name 
     */
    public void setName(String name) {
        row.put("name", name);
    }
 
}