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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tijme Gommers
 */
public class Permissions {
    
    public static final String PERMISSION_MANAGE_CUSTOMERS = "manage_customers";
    public static final String PERMISSION_MANAGE_LUGGAGE = "manage_luggage";
    public static final String PERMISSION_MANAGE_USERS = "manage_users";
    
    public static final String PERMISSION_VIEW_TOTAL_LUGGAGE = "view_total_luggage";
    public static final String PERMISSION_VIEW_MISSING_LUGGAGE = "view_missing_luggage";
    public static final String PERMISSION_VIEW_FOUND_LUGGAGE = "view_found_luggage";
    public static final String PERMISSION_VIEW_RESOLVED_LUGGAGE = "view_resolved_luggage";
    
    public static final String PERMISSION_VIEW_GRAPH_LUGGAGE = "view_graph_luggage";
    
    public static final Set<String> MANAGER_PERMISSIONS = new HashSet<String>(Arrays.asList(new String[] {
            PERMISSION_VIEW_GRAPH_LUGGAGE,
            PERMISSION_VIEW_TOTAL_LUGGAGE,
            PERMISSION_VIEW_MISSING_LUGGAGE,
            PERMISSION_VIEW_FOUND_LUGGAGE,
            PERMISSION_VIEW_RESOLVED_LUGGAGE
        }
    ));
    
    public static final Set<String> EMPLOYEE_PERMISSIONS = new HashSet<String>(Arrays.asList(
        new String[] {
            PERMISSION_MANAGE_CUSTOMERS,
            PERMISSION_MANAGE_LUGGAGE
        }
    ));
    
    public static final Set<String> MODERATOR_PERMISSIONS = new HashSet<String>(Arrays.asList(
        new String[] {
            PERMISSION_MANAGE_CUSTOMERS,
            PERMISSION_MANAGE_LUGGAGE,
            PERMISSION_VIEW_MISSING_LUGGAGE,
            PERMISSION_VIEW_FOUND_LUGGAGE,
            PERMISSION_VIEW_RESOLVED_LUGGAGE,
            PERMISSION_MANAGE_USERS
        }
    ));
    
}