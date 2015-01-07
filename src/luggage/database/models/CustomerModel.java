/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 ITopia IS102-5
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
 * @author ITopia IS102-5
 */
public class CustomerModel extends Model {

    public CustomerModel() {

    }

    /**
     *
     * @param id
     */
    public CustomerModel(int id) {
        super(id);
    }

    /**
     *
     * @param where
     * @param params
     */
    public CustomerModel(String where, String... params) {
        super(where, params);
    }

    /**
     *
     * @return customers
     */
    @Override
    protected String getTable() {
        return "customers";
    }

    /**
     *
     * @return customermodel
     */
    @Override
    protected Model getModel() {
        return new CustomerModel();
    }

    /**
     *
     * @return standard order by
     */
    @Override
    protected String getOrderBy() {
        return "ORDER BY firstname ASC";
    }

    /**
     *
     * @return full name
     */
    @Override
    public String toString() {
        return getFullname();
    }

    /**
     * Return the insurer_id of the current row
     *
     * @return
     */
    public int getInsurerId() {
        try {
            return Integer.parseInt(row.get("insurer_id"));
        } catch (Exception e) {
            return 0;
        }
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
     * Return the customer of a user
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
     * Return the insurer of a customer
     *
     * @return InsurerModel
     */
    public InsurerModel getInsurer() {
        return new InsurerModel(getInsurerId());
    }

    /**
     * Return the insurer name of a customer
     *
     * @return
     */
    public String getInsurerName() {
        return new InsurerModel(getInsurerId()).getName();
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
     * Return the holiday address of the current row
     *
     * @return
     */
    public String getAddress2() {
        return row.get("address2");
    }

    /**
     * Set the holiday address of the current row
     *
     * @param address
     */
    public void setAddress2(String address) {
        row.put("address2", address);
    }

    /**
     * Return the postal code of the current row
     *
     * @return
     */
    public String getPostalCode() {
        return row.get("postalcode");
    }

    /**
     * Set the postal code of the current row
     *
     * @param postalCode2
     */
    public void setPostalCode(String postalCode2) {
        row.put("postalcode", postalCode2);
    }

    /**
     * Return the holiday postal code of the current row
     *
     * @return
     */
    public String getPostalCode2() {
        return row.get("postalcode2");
    }

    /**
     * Set the holiday postal code of the current row
     *
     * @param postalCode2
     */
    public void setPostalCode2(String postalCode2) {
        row.put("postalcode2", postalCode2);
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
     * Return the holiday residence of the current row
     *
     * @return
     */
    public String getResidence2() {
        return row.get("residence2");
    }

    /**
     * Set the holiday residence of the current row
     *
     * @param residence2
     */
    public void setResidence2(String residence2) {
        row.put("residence2", residence2);
    }

    /**
     * Return the email address of the current row
     *
     * @return
     */
    public String getEmail() {
        return row.get("email");
    }

    /**
     * Set the email address of the current row
     *
     * @param email
     */
    public void setEmail(String email) {
        row.put("email", email);
    }

    /**
     * Return the telephone number of the current row
     *
     * @return
     */
    public String getTelephone() {
        return row.get("telephone");
    }

    /**
     * Set the telephone number of the current row
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        row.put("telephone", telephone);
    }

    /**
     * Return the mobile number of the current row
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

}
