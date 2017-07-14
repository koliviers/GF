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
 * @author anonymousghost
 */
@Embeddable
public class Antecedent_familial_Id implements Serializable{
    
    private Long id_patient;
    private Long id_pathologie;

    public Antecedent_familial_Id() {
    }

    public Antecedent_familial_Id(Long id_patient, Long id_pathologie) {
        this.id_patient = id_patient;
        this.id_pathologie = id_pathologie;
    }

    public Long getId_patient() {
        return id_patient;
    }

    public void setId_patient(Long id_patient) {
        this.id_patient = id_patient;
    }

    public Long getId_pathologie() {
        return id_pathologie;
    }

    public void setId_pathologie(Long id_pathologie) {
        this.id_pathologie = id_pathologie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id_patient);
        hash = 97 * hash + Objects.hashCode(this.id_pathologie);
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
        final Antecedent_familial_Id other = (Antecedent_familial_Id) obj;
        if (!Objects.equals(this.id_patient, other.id_patient)) {
            return false;
        }
        if (!Objects.equals(this.id_pathologie, other.id_pathologie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Antecedent_familial_Id{" + "id_patient=" + id_patient + ", id_pathologie=" + id_pathologie + '}';
    }
    
    
}
