/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author koliviers
 */
@Entity
@Table(name = "deces")
public class Deces implements  Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;
    
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateDeces")
    private Date datedeces;

    public Deces() {
    }

    public Deces(long id, Patient patient, Date datedeces) {
        this.id = id;
        this.patient = patient;
        this.datedeces = datedeces;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the datedeces
     */
    public Date getDatedeces() {
        return datedeces;
    }

    /**
     * @param datedeces the datedeces to set
     */
    public void setDatedeces(Date datedeces) {
        this.datedeces = datedeces;
    }

    @Override
    public String toString() {
        return "Deces{" + "id=" + id + ", patient=" + patient + ", datedeces=" + datedeces + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deces other = (Deces) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
