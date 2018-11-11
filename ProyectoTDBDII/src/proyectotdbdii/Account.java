/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotdbdii;

/**
 *
 * @author Usuario Dell
 */
class Account {

    String UsserName;
    String Password;
    String Email;

    public Account() {
    }

    public Account(String UsserName, String Password, String Email) {
        this.UsserName = UsserName;
        this.Password = Password;
        this.Email = Email;
    }

    public String getUsserName() {
        return UsserName;
    }

    public void setUsserName(String UsserName) {
        this.UsserName = UsserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
