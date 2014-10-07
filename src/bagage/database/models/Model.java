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
package bagage.database.models;

import java.util.HashMap;

/**
 * Model
 *
 * A super class for all the models containing
 * default model functions
 *
 * @package bagage.database.models
 * @author Tijme Gommers
 */
abstract public class Model {
 
    abstract protected HashMap<String, String> getRowData();
    abstract protected void setRowData(HashMap<String, String> rowData);
    
    /**
     * Set data for the current model
     * 
     * @param key the key or index you want to set
     * @param value the value for the key or index
     */
    public void set(String key, String value) {
        HashMap<String, String> rowData = getRowData();
        rowData.put(key, value);
        setRowData(rowData);
    }
    
}
