package com.thestart.projectbig1;

public class myBase {
    String name, email, username, phone, passwd;
    public myBase(){

    }

    public myBase(String name, String email, String username, String phone, String passwd) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
