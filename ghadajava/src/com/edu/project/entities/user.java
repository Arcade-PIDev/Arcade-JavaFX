/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

/**
 *
 * @author pc
 */
public class user {
    private Integer id;
    
    private String username;
    private String email;
    private String mdp;
    private String avatar;
    private String role ; 

    
    public user() {
    }

    public user(Integer id, String username, String email, String mdp, String avatar, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mdp = mdp;
        this.avatar = avatar;
        this.role = role;
    }

    public user(String username, String email, String mdp, String avatar, String role) {
        this.username = username;
        this.email = email;
        this.mdp = mdp;
        this.avatar = avatar;
        this.role = role;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", username=" + username + ", email=" + email + ", mdp=" + mdp + ", avatar=" + avatar + ", role=" + role + '}';
    }

 
}
