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
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

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
    @Column(name = "cID")
    private int cID;
    @Column(name="orderno")
    private int orderno;
    @Column(name = "orderstatus")
    private String orderstatus;
    @Column(name = "orderamount")
    private double orderamount;
    @Column(name = "orderdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date = new Date();
    @Column(name = "delivarydate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date delivarydate;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "pid")
    private int pid;
    @Column(name = "price")
    private double price;
    @Column(name = "image")
    private String image;

    public Orderinformation(int orderID, int cID, int orderno, String orderstatus, double orderamount, Date delivarydate, String name, String category, int quantity, int pid, double price, String image) {
        this.orderID = orderID;
        this.cID = cID;
        this.orderno = orderno;
        this.orderstatus = orderstatus;
        this.orderamount = orderamount;
         this.delivarydate = delivarydate;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.pid = pid;
        this.price = price;
        this.image = image;
    }

    public Orderinformation() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public double getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(double orderamount) {
        this.orderamount = orderamount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDelivarydate() {
        return delivarydate;
    }

    public void setDelivarydate(Date delivarydate) {
        this.delivarydate = delivarydate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return pid;
    }

    public void setProduct_id(int pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
