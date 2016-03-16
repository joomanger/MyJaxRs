/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isd.myjaxrs.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "item_sq", initialValue = 6, allocationSize = 1)
    @GeneratedValue(generator = "item_sq")
    private long id;
    private String name;
    private String description;
    private Boolean booler = true;
    
    private double doublez=1234.12;
    private byte bytez=123;
    private short shorter=434;
    private int inter=555;
    private float floater=111.11f;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt=Calendar.getInstance(Locale.ENGLISH).getTime();

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
    
    public float getFloater() {
        return floater;
    }

    public void setFloater(float floater) {
        this.floater = floater;
    }
    
    
    public byte getBytez() {
        return bytez;
    }

    public void setBytez(byte bytez) {
        this.bytez = bytez;
    }

    public short getShorter() {
        return shorter;
    }

    public void setShorter(short shorter) {
        this.shorter = shorter;
    }

    public int getInter() {
        return inter;
    }

//    private Float floatez=111.1f;
    public void setInter(int inter) {
        this.inter = inter;
    }

    public double getDoublez() {
        return doublez;
    }

    public void setDoublez(double doublez) {
        this.doublez = doublez;
    }

  

//    public Float getFloatez() {
//        return floatez;
//    }
//
//    public void setFloatez(Float floatez) {
//        this.floatez = floatez;
//    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getBooler() {
        return booler;
    }

    public void setBooler(Boolean booler) {
        this.booler = booler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; name: " + name + "; descr: " + description;
    }

}
