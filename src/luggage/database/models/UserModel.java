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
import luggage.security.Permissions;

/**
 * User Model
 *
 * A class that maps the users table
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
public class UserModel extends Model {
    
    public static final String ROLE_EMPLOYEE = "employee";
    public static final String ROLE_SUPER = "super";
    public static final String ROLE_MODERATOR = "moderator";
    public static final String ROLE_MANAGER = "manager";

    public UserModel() {
        
    }
    
    public UserModel(int id) {
        super(id);
    }
    
    public UserModel(String where, String... params) {
        super(where, params);
    }

    @Override
    protected Model getModel(int id) {
        return new UserModel(id);
    }

    @Override
    protected String getTable() {
        return "users";
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
     * Return the prefix of the current row
     * 
     * @return 
     */
    public String getPrefix() {
        return row.get("prefix");
    }
   
    /**
     * Set the prefix of the current row
     * 
     * @param prefix 
     */
    public void setPrefix(String prefix) {
        row.put("prefix", prefix);
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
     * Return the username of the current row
     * 
     * @return 
     */
    public String getUsername() {
        return row.get("username");
    }
   
    /**
     * Set the username of the current row
     * 
     * @param username 
     */
    public void setUsername(String username) {
        row.put("username", username);
    }
   
    /**
     * Return the (hash) password of the current row
     * 
     * @return 
     */
    public String getPassword() {
        return row.get("password");
    }
   
    /**
     * Set the password of the current row
     * 
     * @param password 
     */
    public void setPassword(String password) {
        row.put("password", password);
    }
   
    /**
     * Return the role of the current row
     * 
     * @return 
     */
    public String getRole() {
        return row.get("role");
    }
   
    /**
     * Set the role of the current row
     * 
     * @param role 
     */
    public void setRole(String role) {
        row.put("role", role);
    }
    
    /**
     * Return the fullname of a user
     * 
     * @return;
     */
    public String getFullname() {
        String firstname = getFirstname();
        String prefix = getPrefix();
        String lastname = getLastname();
        
        String fullname = firstname;
        
        if(!StringUtils.isNullOrEmpty(prefix)) {
            fullname += " " + prefix;
        }
        
        fullname += " " + lastname;
        
        return fullname;
    }
    
    /**
     * Check if a user has permissions on the given operation
     * 
     * @param operation
     * 
     * @return true if the user has permissions
     */
    public boolean hasPermissionsOn(String operation) {
        switch(getRole()) {
            case ROLE_EMPLOYEE:
                return Permissions.EMPLOYEE_PERMISSIONS.contains(operation);
            case ROLE_SUPER:
                return Permissions.SUPER_PERMISSIONS.contains(operation);
            case ROLE_MODERATOR:
                return Permissions.MODERATOR_PERMISSIONS.contains(operation);
            case ROLE_MANAGER:
                return Permissions.MANAGER_PERMISSIONS.contains(operation);
        }
        
        return false;
    }
   
    /**
     * Return the gender of the current row
     * 
     * @return 
     */
    public String getGender() {
        return row.get("gender");
    }
   
    /**
     * Set the gender of the current row
     * 
     * @param gender 
     */
    public void setGender(String gender) {
        row.put("gender", gender);
    }
   
    /**
     * Return the address of the current row
     * 
     * @return 
     */
    public String getAddress() {
        return row.get("address");
    }
   
    /**
     * Set the address of the current row
     * 
     * @param address 
     */
    public void setAddress(String address) {
        row.put("address", address);
    }
   
    /**
     * Return the postalcode of the current row
     * 
     * @return 
     */
    public String getPostalcode() {
        return row.get("postalcode");
    }
   
    /**
     * Set the postalcode of the current row
     * 
     * @param postalcode 
     */
    public void setPostalcode(String postalcode) {
        row.put("postalcode", postalcode);
    }
   
    /**
     * Return the residence of the current row
     * 
     * @return 
     */
    public String getResidence() {
        return row.get("residence");
    }
   
    /**
     * Set the residence of the current row
     * 
     * @param residence 
     */
    public void setResidence(String residence) {
        row.put("residence", residence);
    }
   
    /**
     * Return the telephone of the current row
     * 
     * @return 
     */
    public String getTelephone() {
        return row.get("telephone");
    }
   
    /**
     * Set the telephone of the current row
     * 
     * @param telephone 
     */
    public void setTelephone(String telephone) {
        row.put("telephone", telephone);
    }
   
    /**
     * Return the mobile of the current row
     * 
     * @return 
     */
    public String getMobile() {
        return row.get("mobile");
    }
   
    /**
     * Set the mobile of the current row
     * 
     * @param mobile 
     */
    public void setMobile(String mobile) {
        row.put("mobile", mobile);
    }
    
    
    /**
     * Return the workplace of the current row
     * 
     * @return 
     */
    public String getWorkplace() {
       return row.get("residence");
    }
    /**
     * Set the workplace of the current row
     * 
     * @param workplace 
     */
    public void setWorkplace(String workplace) {
        row.put("residence", workplace);
    }
}