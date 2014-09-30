/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bagage.database.models;

/**
 *
 * @author Tijme Gommers
 */
	
public class UserModel  {
    
    private int id;
    private String email;
    private String password;
  
    public UserModel(int id, String name, String password) {
        this.id = id;
        this.email = name;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
}