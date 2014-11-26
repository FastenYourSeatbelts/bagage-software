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
 */
package luggage.security;

import java.util.ArrayList;
import java.util.Arrays;
import luggage.database.models.UserModel;

/**
 *
 * @author Tijme Gommers
 */
public class Permissions {
    
    public static final Permissions.Tab PERMISSION_MANAGE_CUSTOMERS = new Permissions.Tab("manage_customers", "Customers", "customers/list");
    public static final Permissions.Tab PERMISSION_MANAGE_LUGGAGE = new Permissions.Tab("manage_luggage", "Luggage", "luggage/list");
    public static final Permissions.Tab PERMISSION_MANAGE_USERS = new Permissions.Tab("manage_users", "Users", "users/list");
    
    public static final Permissions.Tab PERMISSION_VIEW_TOTAL_LUGGAGE = new Permissions.Tab("view_total_luggage", "Total luggage", "luggage/total");
    public static final Permissions.Tab PERMISSION_VIEW_MISSING_LUGGAGE = new Permissions.Tab("view_missing_luggage", "Missing luggage", "luggage/missing");
    public static final Permissions.Tab PERMISSION_VIEW_FOUND_LUGGAGE = new Permissions.Tab("view_found_luggage", "Found luggage", "luggage/found");
    public static final Permissions.Tab PERMISSION_VIEW_RESOLVED_LUGGAGE = new Permissions.Tab("view_resolved_luggage", "Resolved luggage", "luggage/resolved");
    
    public static final Permissions.Tab PERMISSION_VIEW_GRAPH_LUGGAGE = new Permissions.Tab("view_graph_luggage", "Luggage graph", "graphs/luggage");
    
    public static class Tab {
        
        public Tab(String id, String text, String view) {
            this.id = id;
            this.text = text;
            this.view = view;
        }
        
        @Override
        public String toString() {
            return text;
        }
        
        public String getId() {
            return id;
        }
        
        public String getText() {
            return text;
        }
        
        public String getView() {
            return view;
        }
        
        public String id;
        
        public String text;
        
        public String view;
        
    }
    
    public static final ArrayList<Permissions.Tab> MANAGER_PERMISSIONS = new ArrayList<Permissions.Tab>(Arrays.asList(
        new Permissions.Tab[] {
            PERMISSION_VIEW_GRAPH_LUGGAGE,
            PERMISSION_VIEW_TOTAL_LUGGAGE,
            PERMISSION_VIEW_MISSING_LUGGAGE,
            PERMISSION_VIEW_FOUND_LUGGAGE,
            PERMISSION_VIEW_RESOLVED_LUGGAGE
        }
    ));
    
    public static final ArrayList<Permissions.Tab> EMPLOYEE_PERMISSIONS = new ArrayList<Permissions.Tab>(Arrays.asList(
        new Permissions.Tab[] {
            PERMISSION_MANAGE_CUSTOMERS,
            PERMISSION_MANAGE_LUGGAGE
        }
    ));
    
    public static final ArrayList<Permissions.Tab> MODERATOR_PERMISSIONS = new ArrayList<Permissions.Tab>(Arrays.asList(
        new Permissions.Tab[] {
            PERMISSION_MANAGE_CUSTOMERS,
            PERMISSION_MANAGE_LUGGAGE,
            PERMISSION_VIEW_MISSING_LUGGAGE,
            PERMISSION_VIEW_FOUND_LUGGAGE,
            PERMISSION_VIEW_RESOLVED_LUGGAGE,
            PERMISSION_MANAGE_USERS
        }
    ));
    
    public static final ArrayList<Permissions.Tab> SUPER_PERMISSIONS = new ArrayList<Permissions.Tab>(Arrays.asList(
        new Permissions.Tab[] {
            PERMISSION_MANAGE_CUSTOMERS,
            PERMISSION_MANAGE_LUGGAGE,
            PERMISSION_VIEW_GRAPH_LUGGAGE,
            PERMISSION_VIEW_TOTAL_LUGGAGE,
            PERMISSION_VIEW_MISSING_LUGGAGE,
            PERMISSION_VIEW_FOUND_LUGGAGE,
            PERMISSION_VIEW_RESOLVED_LUGGAGE,
            PERMISSION_MANAGE_USERS
        }
    ));
    
    public static ArrayList<Permissions.Tab> getPermissions(UserModel user) {
        switch(user.getRole()) {
            case "moderator":
                return MODERATOR_PERMISSIONS;
            case "super":
                return SUPER_PERMISSIONS;
            case "employee":
                return EMPLOYEE_PERMISSIONS;
            case "manager":
                return MANAGER_PERMISSIONS;
        }
        
        return new ArrayList<Permissions.Tab>();
    }
    
}