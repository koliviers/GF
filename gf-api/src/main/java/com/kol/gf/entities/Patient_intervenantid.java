/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author kol
 */
@Embeddable
public class Patient_intervenantid implements Serializable{
    
    private Long id_patient;
    private Long id_intervenant;

    public Patient_intervenantid() {
    }

    
    
    public Patient_intervenantid(Long id_patient, Long id_intervenant) {
        this.id_patient = id_patient;
        this.id_intervenant = id_intervenant;
    }

    /**
     * @return the id_patient
     */
    public Long getId_patient() {
        return id_patient;
    }

    /**
     * @param id_patient the id_patient to set
     */
    public void setId_patient(Long id_patient) {
        this.id_patient = id_patient;
    }

    /**
     * @return the id_intervenant
     */
    public Long getId_intervenant() {
        return id_intervenant;
    }

    /**
     * @param id_intervenant the id_intervenant to set
     */
    public void setId_intervenant(Long id_intervenant) {
        this.id_intervenant = id_intervenant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id_patient);
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
        final Patient_intervenantid other = (Patient_intervenantid) obj;
        if (!Objects.equals(this.id_patient, other.id_patient)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
