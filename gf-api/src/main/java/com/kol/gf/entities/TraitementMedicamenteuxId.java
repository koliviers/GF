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
public class TraitementMedicamenteuxId implements Serializable{
    
    private Long id_consultation;
    private Long id_classeThe;

    public TraitementMedicamenteuxId() {
    }

    public TraitementMedicamenteuxId(Long id_consultation, Long id_classeThe) {
        this.id_consultation = id_consultation;
        this.id_classeThe = id_classeThe;
    }

    public Long getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(Long id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Long getId_classeThe() {
        return id_classeThe;
    }

    public void setId_classeThe(Long id_classeThe) {
        this.id_classeThe = id_classeThe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id_consultation);
        hash = 79 * hash + Objects.hashCode(this.id_classeThe);
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
        final TraitementMedicamenteuxId other = (TraitementMedicamenteuxId) obj;
        if (!Objects.equals(this.id_consultation, other.id_consultation)) {
            return false;
        }
        if (!Objects.equals(this.id_classeThe, other.id_classeThe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TraitementMedicamenteuxId{" + "id_consultation=" + id_consultation + ", id_classeThe=" + id_classeThe + '}';
    }
    
    
}
