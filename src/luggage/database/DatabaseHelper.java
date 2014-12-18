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
package luggage.database;

import luggage.AppConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import luggage.Debug;

/**
 * DatabaseHelper
 *
 * This class manages the database connection
 *
 * @package luggage.database
 * @author Tijme Gommers
 */
public class DatabaseHelper {

    public static Connection oConnection = null;

    /**
     * Get the connection and generate it if it doesn't exist yet
     *
     * @return
     */
    public static Connection getConnection() {
        if (oConnection == null) {
            openConnection();
        }

        return oConnection;
    }

    /**
     * Open a new database connection
     */
    public static void openConnection() {
        try {
            long startTime = System.nanoTime();
            oConnection = DriverManager.getConnection(AppConfig.databaseName, AppConfig.databaseUser, AppConfig.databasePass);
            long endTime = System.nanoTime();
            long microseconds = (endTime - startTime) / 1000;
            Debug.print("Connecting to database (microseconds): " + microseconds);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Close the given database connection
     *
     * @param oConnection
     */
    public static void closeConnection(Connection oConnection) {
        if (oConnection == null) {
            return;
        }

        try {
            oConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
