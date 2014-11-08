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
package bagage;

/**
 * Application configuration
 *
 * @package bagage
 * @author Tijme Gommers
 */
public class AppConfig {
    
    /**
     * Application wide title
     */
    public static final String ApplicationName = "Corendon Bagage";
    
    /**
     * Minimum application height
     */
    public static final int MinHeight = 500;
    
    /**
     * Minimum application width
     */
    public static final int MinWidth = 800;
    
    /**
     * Database host & driver
     */
    public static final String databaseName = "jdbc:mysql://localhost/bagage-software";
    
    /**
     * Database user
     */
    public static final String databaseUser = "root";
    
    /**
     * Database password
     */
    public static final String databasePass = "";
    
}
