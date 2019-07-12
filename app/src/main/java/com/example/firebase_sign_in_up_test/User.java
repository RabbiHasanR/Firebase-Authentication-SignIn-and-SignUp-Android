package com.example.firebase_sign_in_up_test;

public class User {
    private String username;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String address;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

//    public User(String username, String email,String phone,String password,String gender,String address) {
//        this.username = username;
//        this.email = email;
//        this.phone=phone;
//        this.password=password;
//        this.gender=gender;
//        this.address=address;
//    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
