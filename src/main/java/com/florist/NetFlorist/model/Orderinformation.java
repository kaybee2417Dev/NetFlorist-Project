/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "orderinformation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderinformation.findAll", query = "SELECT o FROM Orderinformation o")
    , @NamedQuery(name = "Orderinformation.findByOrderID", query = "SELECT o FROM Orderinformation o WHERE o.orderID = :orderID")
    , @NamedQuery(name = "Orderinformation.findByOrderstatus", query = "SELECT o FROM Orderinformation o WHERE o.orderstatus = :orderstatus")})
public class Orderinformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;
    @Column(name="orderno")
    private int orderno;
    @Column(name = "orderstatus")
    private String orderstatus;
    @Column(name = "orderdetails")
    private String orderdetails;
    @Column(name = "orderdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date = new Date();
       
    public Orderinformation() {
    }

    public Orderinformation(int orderID) {
        this.orderID = orderID;
    }

    public Orderinformation(int orderID, int orderno, String orderstatus, String orderdetails, Date date) {
        this.orderID = orderID;
        this.orderno = orderno;
        this.orderstatus = orderstatus;
        this.orderdetails = orderdetails;
        this.date = date;  
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(String orderdetails) {
        this.orderdetails = orderdetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }
    
    
}
