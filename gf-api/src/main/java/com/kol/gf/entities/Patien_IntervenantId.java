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
public class Patien_IntervenantId implements Serializable{
    
    private long id_patient;
    private long id_intervenant;

    public Patien_IntervenantId() {
    }

    public Patien_IntervenantId(long id_patient, long id_intervenant) {
        this.id_patient = id_patient;
        this.id_intervenant = id_intervenant;
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

    /**
     * @return the id_intervenant
     */
    public long getId_intervenant() {
        return id_intervenant;
    }

    /**
     * @param id_intervenant the id_intervenant to set
     */
    public void setId_intervenant(long id_intervenant) {
        this.id_intervenant = id_intervenant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id_patient ^ (this.id_patient >>> 32));
        hash = 97 * hash + (int) (this.id_intervenant ^ (this.id_intervenant >>> 32));
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
        final Patien_IntervenantId other = (Patien_IntervenantId) obj;
        if (this.id_patient != other.id_patient) {
            return false;
        }
        if (this.id_intervenant != other.id_intervenant) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
