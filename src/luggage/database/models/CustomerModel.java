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

import com.mysql.jdbc.StringUtils;

/**
 * Customer Model
 *
 * A class that maps the customers table
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
public class CustomerModel extends Model {
 
    public CustomerModel() {
        
    }
    
    public CustomerModel(int id) {
        super(id);
    }
    
    public CustomerModel(String where, String... params) {
        super(where, params);
    }

    @Override
    protected String getTable() {
        return "customers";
    }

    @Override
    protected Model getModel(int id) {
        return new CustomerModel(id);
    }
   
    /**
     * Return the insurer_id of the current row
     * 
     * @return 
     */
    public int getInsurerId() {
        return Integer.parseInt(row.get("insurer_id"));
    }
   
    /**
     * Set the insurer_id of the current row
     * 
     * @param insurer_id 
     */
    public void setInsurerId(String insurer_id) {
        row.put("insurer_id", insurer_id);
    }
   
    /**
     * Return the first name of the current row
     * 
     * @return 
     */
    public String getFirstname() {
        return row.get("firstname");
    }
   
    /**
     * Set the first name of the current row
     * 
     * @param firstname 
     */
    public void setFirstname(String firstname) {
        row.put("firstname", firstname);
    }
   
    /**
     * Return the middle name of the current row
     * 
     * @return 
     */
    public String getMiddlename() {
        return row.get("middlename");
    }
   
    /**
     * Set the middle name of the current row
     * 
     * @param middlename 
     */
    public void setMiddlename(String middlename) {
        row.put("middlename", middlename);
    }
   
    /**
     * Return the last name of the current row
     * 
     * @return 
     */
    public String getLastname() {
        return row.get("lastname");
    }
   
    /**
     * Set the last name of the current row
     * 
     * @param lastname 
     */
    public void setLastname(String lastname) {
        row.put("lastname", lastname);
    }
   
    /**
     * Return the customer of a user
     * 
     * @return;
     */
    public String getFullname() {
        String firstname = getFirstname();
        String middlename = getMiddlename();
        String lastname = getLastname();
        
        String fullname = firstname;
        
        if(!StringUtils.isNullOrEmpty(middlename)) {
            fullname += " " + middlename;
        }
        
        fullname += " " + lastname;
        
        return fullname;
    }
    
    /**
     * Return the insurer of a customer
     * 
     * @return InsurerModel
     */
    public InsurerModel getInsurer()
    {
        return new InsurerModel(getInsurerId());
    }
    
    /**
     * Return the insurer name of a customer
     * 
     * @return
     */
    public String getInsurerName()
    {
        return new InsurerModel(getInsurerId()).getName();
    }
    
}