/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author DuongPTH
 */
public class UserDTO implements Serializable {

    private String user, pass, role;
    private boolean deletable, available;

    public UserDTO(String user, String pass, String role) {
        this.user = user;
        this.pass = pass;
        this.role = role;
    }

    public UserDTO(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public UserDTO(String user, String pass, String role, boolean deletable, boolean available) {
        this.user = user;
        this.pass = pass;
        this.role = role;
        this.deletable = deletable;
        this.available = available;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(user);
        v.add(role);
        v.add(isAvailable());
        return v;
    }
}
