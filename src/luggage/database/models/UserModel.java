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
 * A class that maps the users table.
 *
 * @package luggage.database.models
 * @author Tijme Gommers
 */
public class UserModel extends Model {

    /**
     * Creates role title string for the employee.
     */
    public static final String ROLE_EMPLOYEE = "employee";

    /**
     * Creates role title string for the super admin (application vendor).
     */
    public static final String ROLE_SUPER = "super";

    /**
     * Creates role title string for the moderator.
     */
    public static final String ROLE_MODERATOR = "moderator";

    /**
     * Creates role title string for the manager.
     */
    public static final String ROLE_MANAGER = "manager";

    /**
     *
     */
    public UserModel() {

    }

    /**
     *
     * @param id
     */
    public UserModel(int id) {
        super(id);
    }

    /**
     *
     * @param where
     * @param params
     */
    public UserModel(String where, String... params) {
        super(where, params);
    }

    /**
     *
     * @return usermodel
     */
    @Override
    protected Model getModel() {
        return new UserModel();
    }

    /**
     *
     * @return users
     */
    @Override
    protected String getTable() {
        return "users";
    }

    /**
     *
     * @return order by standard
     */
    @Override
    protected String getOrderBy() {
        return "ORDER BY firstname ASC";
    }

    /**
     * Returns the first name of the current row.
     *
     * @return
     */
    public String getFirstname() {
        return row.get("firstname");
    }

    /**
     * Sets the first name of the current row.
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        row.put("firstname", firstname);
    }

    /**
     * Returns the prefix of the current row.
     *
     * @return
     */
    public String getPrefix() {
        return row.get("prefix");
    }

    /**
     * Sets the prefix of the current row.
     *
     * @param prefix
     */
    public void setPrefix(String prefix) {
        row.put("prefix", prefix);
    }

    /**
     * Returns the last name of the current row.
     *
     * @return
     */
    public String getLastname() {
        return row.get("lastname");
    }

    /**
     * Sets the last name of the current row.
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        row.put("lastname", lastname);
    }

    /**
     * Returns the username of the current row.
     *
     * @return
     */
    public String getUsername() {
        return row.get("username");
    }

    /**
     * Sets the username of the current row.
     *
     * @param username
     */
    public void setUsername(String username) {
        row.put("username", username);
    }

    /**
     * Returns the (hashed) password of the current row.
     *
     * @return
     */
    public String getPassword() {
        return row.get("password");
    }

    /**
     * Sets the password of the current row.
     *
     * @param password
     */
    public void setPassword(String password) {
        row.put("password", password);
    }

    /**
     * Returns the role of the current row.
     *
     * @return
     */
    public String getRole() {
        return row.get("role");
    }

    /**
     * Sets the role of the current row
     *
     * @param role
     */
    public void setRole(String role) {
        row.put("role", role);
    }

    /**
     * Returns the full name of a user
     *
     * @return;
     */
    public String getFullname() {
        String firstname = getFirstname();
        String prefix = getPrefix();
        String lastname = getLastname();

        String fullname = firstname;

        if (!StringUtils.isNullOrEmpty(prefix)) {
            fullname += " " + prefix;
        }

        fullname += " " + lastname;

        return fullname;
    }

    /**
     * Checks if a user has permissions on the given operation
     *
     * @param operation
     *
     * @return true if the user has permissions
     */
    @SuppressWarnings("element-type-mismatch")
    public boolean hasPermissionsOn(String operation) {
        switch (getRole()) {
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
     * Returns the gender of the current row
     *
     * @return
     */
    public String getGender() {
        return row.get("gender");
    }

    /**
     * Sets the gender of the current row
     *
     * @param gender
     */
    public void setGender(String gender) {
        row.put("gender", gender);
    }

    /**
     * Returns the address of the current row.
     *
     * @return
     */
    public String getAddress() {
        return row.get("address");
    }

    /**
     * Sets the address of the current row.
     *
     * @param address
     */
    public void setAddress(String address) {
        row.put("address", address);
    }

    /**
     * Returns the postal code of the current row.
     *
     * @return
     */
    public String getPostalcode() {
        return row.get("postalcode");
    }

    /**
     * Sets the postal code of the current row.
     *
     * @param postalcode
     */
    public void setPostalcode(String postalcode) {
        row.put("postalcode", postalcode);
    }

    /**
     * Returns the residence of the current row.
     *
     * @return
     */
    public String getResidence() {
        return row.get("residence");
    }

    /**
     * Sets the residence of the current row.
     *
     * @param residence
     */
    public void setResidence(String residence) {
        row.put("residence", residence);
    }

    /**
     * Returns the Location of the current row.
     *
     * @return
     */
    public LocationModel getLocation() {
        return new LocationModel(Integer.parseInt(row.get("location_id")));
    }

    /**
     * Returns the Location of the current row.
     *
     * @return
     */
    public int getLocationId() {
        if (row.get("location_id") == null || row.get("location_id").isEmpty()) {
            return 0;
        }

        return Integer.parseInt(row.get("location_id"));
    }

    /**
     * Sets the Location of the current row.
     *
     * @param location_id
     */
    public void setLocationId(String location_id) {
        row.put("location_id", location_id);
    }

    /**
     * Returns the location name of the current row.
     *
     * @return location
     */
    public String getLocationName() {
        return getLocation().getName();
    }

    /**
     * Returns the telephone of the current row.
     *
     * @return
     */
    public String getTelephone() {
        return row.get("telephone");
    }

    /**
     * Sets the telephone of the current row.
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        row.put("telephone", telephone);
    }

    /**
     * Returns the mobile of the current row.
     *
     * @return
     */
    public String getMobile() {
        return row.get("mobile");
    }

    /**
     * Sets the mobile of the current row.
     *
     * @param mobile
     */
    public void setMobile(String mobile) {
        row.put("mobile", mobile);
    }

}
