/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



/**
 *
 * @author User
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Delivary.findAll", query = "SELECT d FROM Delivary d")
    , @NamedQuery(name = "Delivary.findByDelivaryID", query = "SELECT d FROM Delivary d WHERE d.delivaryID = :delivaryID")
    , @NamedQuery(name = "Delivary.findByDelivarytype", query = "SELECT d FROM Delivary d WHERE d.delivarytype = :delivarytype")
    , @NamedQuery(name = "Delivary.findByName", query = "SELECT d FROM Delivary d WHERE d.name = :name")
    , @NamedQuery(name = "Delivary.findBySurname", query = "SELECT d FROM Delivary d WHERE d.surname = :surname")
    , @NamedQuery(name = "Delivary.findByEmail", query = "SELECT d FROM Delivary d WHERE d.email = :email")
    , @NamedQuery(name = "Delivary.findByContacts", query = "SELECT d FROM Delivary d WHERE d.contacts = :contacts")
    , @NamedQuery(name = "Delivary.findByStreet", query = "SELECT d FROM Delivary d WHERE d.street = :street")
    , @NamedQuery(name = "Delivary.findByCity", query = "SELECT d FROM Delivary d WHERE d.city = :city")
    , @NamedQuery(name = "Delivary.findByProvince", query = "SELECT d FROM Delivary d WHERE d.province = :province")})
public class Delivary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivaryID")
    private Integer delivaryID;
   @Column(name = "orderno")
    private Integer orderno;    
     @Column(name = "delivarytype")
    private String delivarytype;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    
    @Column(name = "contacts")
    private String contacts;

    @Column(name = "street")
    private String street;
   
    @Column(name = "city")
    private String city;
   
    @Column(name = "province")
    private String province;

    public Delivary() {
    }

    public Delivary(Integer delivaryID) {
        this.delivaryID = delivaryID;
    }

    public Delivary(Integer delivaryID, int orderno, String delivarytype, String name, String surname, String email, String contacts, String street, String city, String province) {
        this.delivaryID = delivaryID;
        this.orderno = orderno;
        this.delivarytype = delivarytype;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contacts = contacts;
        this.street = street;
        this.city = city;
        this.province = province;
        
    }

    public Integer getDelivaryID() {
        return delivaryID;
    }

    public void setDelivaryID(Integer delivaryID) {
        this.delivaryID = delivaryID;
    }

    public String getDelivarytype() {
        return delivarytype;
    }

    public void setDelivarytype(String delivarytype) {
        this.delivarytype = delivarytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

}
