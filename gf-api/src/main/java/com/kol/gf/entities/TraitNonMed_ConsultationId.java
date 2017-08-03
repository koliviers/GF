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
public class TraitNonMed_ConsultationId implements Serializable{
    
    private Long id_consultation;
    private Long id_traitNonMedc;

    public TraitNonMed_ConsultationId() {
    }

    public TraitNonMed_ConsultationId(Long id_consultation, Long id_traitNonMedc) {
        this.id_consultation = id_consultation;
        this.id_traitNonMedc = id_traitNonMedc;
    }

    public Long getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(Long id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Long getId_traitNonMedc() {
        return id_traitNonMedc;
    }

    public void setId_traitNonMedc(Long id_traitNonMedc) {
        this.id_traitNonMedc = id_traitNonMedc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id_consultation);
        hash = 29 * hash + Objects.hashCode(this.id_traitNonMedc);
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
        final TraitNonMed_ConsultationId other = (TraitNonMed_ConsultationId) obj;
        if (!Objects.equals(this.id_consultation, other.id_consultation)) {
            return false;
        }
        if (!Objects.equals(this.id_traitNonMedc, other.id_traitNonMedc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TraitNonMed_ConsultationId{" + "id_consultation=" + id_consultation + ", id_traitNonMedc=" + id_traitNonMedc + '}';
    }
    
    
}
