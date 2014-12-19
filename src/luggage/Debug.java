
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
package luggage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import luggage.database.models.LogModel;
import luggage.security.Authentication;

/**
 *
 * @author Tijme Gommers
 */
public class Debug {
    
    /**
     *
     * @param message
     */
    public static void print(String message) {
        if(!AppConfig.debug) {
            return;
        }
        
        System.out.println(message);
    }
    
    /**
     *
     * @param type
     * @param message
     */
    public static void logToDatabase(String type, String message) {
        LogModel log = new LogModel();
        log.setUserId(Integer.toString(Authentication.getCurrentUser().getId()));
        log.setType(type);
        log.setMessage(message);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        log.setDatetime(dateFormat.format(date));
        log.save();
    }
    public static void logLoginFailToDatabase(String message) {
        LogModel log = new LogModel();
        log.setUserId("14");
        log.setType(LogModel.TYPE_INFO);
        log.setMessage(message);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        log.setDatetime(dateFormat.format(date));
        log.save();
    }
}
