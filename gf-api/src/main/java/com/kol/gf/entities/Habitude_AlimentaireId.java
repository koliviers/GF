/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author koliviers
 */

@Embeddable
public class Habitude_AlimentaireId implements Serializable{
    
    private long id_consomation;
    private long id_patient;

    public Habitude_AlimentaireId() {
    }

    public Habitude_AlimentaireId(long id_consomation, long id_patient) {
        this.id_consomation = id_consomation;
        this.id_patient = id_patient;
    }

    /**
     * @return the id_consomation
     */
    public long getId_consomation() {
        return id_consomation;
    }

    /**
     * @param id_consomation the id_consomation to set
     */
    public void setId_consomation(long id_consomation) {
        this.id_consomation = id_consomation;
    }

    /**
     * @return the id_patient
     */
    public long getId_patient() {
        return id_patient;
    }

    /**
     * @param id_patient the id_patient to set
     */
    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id_consomation ^ (this.id_consomation >>> 32));
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
        final Habitude_AlimentaireId other = (Habitude_AlimentaireId) obj;
        if (this.id_consomation != other.id_consomation) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
