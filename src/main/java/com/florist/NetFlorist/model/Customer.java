/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;


/**
 *
 * @author User
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findByCID", query = "SELECT c FROM Customer c WHERE c.cID = :cID")
    , @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email")
    , @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password")
    , @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")
    , @NamedQuery(name = "Customer.findBySurname", query = "SELECT c FROM Customer c WHERE c.surname = :surname")
    , @NamedQuery(name = "Customer.findByEmailAndPassword", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password")
    , @NamedQuery(name = "Customer.findByMobile", query = "SELECT c FROM Customer c WHERE c.mobile = :mobile")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cID")
    private Integer cID;
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    @Size(min = 1, max = 10)
    @Column(name = "role")
    private String role;
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Size(min = 1, max = 50)
    @Column(name = "surname")
    private String surname;
    @Size(min = 1, max = 10)
    @Column(name = "mobile")
    private String mobile;

    public Customer() {
    }

    public Customer(Integer cID) {
        this.cID = cID;
    }

    public Customer(Integer cID, String email, String role, String password, String name, String surname, String mobile) {
        this.cID = cID;
        this.email = email;
        this.role = role;
        this.password = password;
        this.name = name;
        this.surname = name;
        this.mobile = mobile;
    }

    public Integer getCID() {
        return cID;
    }

    public void setCID(Integer cID) {
        this.cID = cID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFName() {
        return name;
    }

    public void setFName(String name) {
        this.name = name;
    }

    public String getLName() {
        return surname;
    }

    public void setLName(String surname) {
        this.surname = surname;
    }

    public String getMobileNo() {
        return mobile;
    }

    public void setMobileNo(String mobile) {
        this.mobile = mobile;
    }
    
}
