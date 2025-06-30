package com.shared.dto;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String contactNo;

    public Customer() {
    }

    public Customer(int id, String name, String email, String contactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo + "]";
    }

    
}
