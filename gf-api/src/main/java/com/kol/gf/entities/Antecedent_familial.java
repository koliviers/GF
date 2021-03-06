/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author anonymousghost
 */
@Entity
@Table(name = "ANTECEDENT_FAMILIAL")
public class Antecedent_familial implements Serializable{
    @EmbeddedId
    private Antecedent_familial_Id id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false, insertable = false, updatable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false, insertable = false, updatable = false)
    private Consultation consultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cm10", nullable = false, insertable = false, updatable = false)
    private Cim10 cim10;

    public Antecedent_familial() {
    }

    public Antecedent_familial(Antecedent_familial_Id id, Patient patient, Consultation consultation, Cim10 cim10) {
        this.id = id;
        this.patient = patient;
        this.consultation = consultation;
        this.cim10 = cim10;
    }

    

    
    

    public Antecedent_familial_Id getId() {
        return id;
    }

    public void setId(Antecedent_familial_Id id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Cim10 getCim10() {
        return cim10;
    }

    public void setCim10(Cim10 cim10) {
        this.cim10 = cim10;
    }

    

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.cim10);
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
        final Antecedent_familial other = (Antecedent_familial) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cim10, other.cim10)) {
            return false;
        }
        return true;
    }
    
    

    

    @Override
    public String toString() {
        return "Antecedent_familial{" + "id=" + id + ", patient=" + patient + ", consultation=" + consultation + ", cim10=" + cim10 + '}';
    }

   
    
}
