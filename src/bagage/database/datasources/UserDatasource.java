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

import bagage.database.models.Model;
import bagage.database.models.UserModel;

/**
 * UserDatasource
 *
 * This class contains all user specific
 * queries
 *
 * @package bagage.database.data sources
 * @author Tijme Gommers
 */
public class UserDatasource extends Datasource {
    
    private final String sTable = "users";
    
    private final String[] sColumns = {
        "id", 
        "firstname",
        "middlename",
        "lastname",
        "email", 
        "password"
    };

    /**
     * Return the columns of the current data source
     * 
     * @return String[] sColumns
     */
    @Override
    public String[] getColumns() {
        return sColumns;
    }

    /**
     * Return the table name of the current data source
     * 
     * @return String sTable
     */
    @Override
    public String getTable() {
        return sTable;
    }

    /**
     * Return the model of the current data source
     * 
     * @return new Model
     */
    @Override
    public Model getModel() {
        return new UserModel();
    }
    
}
