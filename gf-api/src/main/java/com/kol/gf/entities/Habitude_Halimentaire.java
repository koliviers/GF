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
 * @author koliviers
 */
@Entity
@Table(name = "habitude_alimentaire")
public class Habitude_Halimentaire implements Serializable {

    @EmbeddedId
    private Habitude_AlimentaireId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient",nullable = false, insertable = false,updatable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consommation",nullable = false, insertable = false,updatable = false)
    private Consommation consommation;

    public Habitude_Halimentaire() {
    }

    /**
     * @return the id
     */
    public Habitude_AlimentaireId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Habitude_AlimentaireId id) {
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
     * @return the consommation
     */
    public Consommation getConsommation() {
        return consommation;
    }

    /**
     * @param consommation the consommation to set
     */
    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Habitude_Halimentaire other = (Habitude_Halimentaire) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Habitude_Halimentaire{" + "id=" + id + ", patient=" + patient + ", consommation=" + consommation + '}';
    }
    
    
    
    

}
