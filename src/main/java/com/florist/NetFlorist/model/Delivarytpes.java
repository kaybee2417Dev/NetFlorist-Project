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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "delivarytpes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivarytpes.findAll", query = "SELECT d FROM Delivarytpes d")
    , @NamedQuery(name = "Delivarytpes.findByDelivaryTypeID", query = "SELECT d FROM Delivarytpes d WHERE d.delivarytypeID = :delivarytypeID")
    , @NamedQuery(name = "Delivarytpes.findByName", query = "SELECT d FROM Delivarytpes d WHERE d.name = :name")})
public class Delivarytpes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "delivarytypeID")
    private Integer delivarytypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "name")
    private String name;

    public Delivarytpes() {
    }

    public Delivarytpes(Integer delivarytypeID) {
        this.delivarytypeID = delivarytypeID;
    }

    public Delivarytpes(Integer delivarytypeID, String name) {
        this.delivarytypeID = delivarytypeID;
        this.name = name;
    }

    public Integer getDelivaryTypeID() {
        return delivarytypeID;
    }

    public void setDelivaryTypeID(Integer delivarytypeID) {
        this.delivarytypeID = delivarytypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
}
