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
@Table(name = "TraitNonMed_Consultation")
public class TraitNonMed_Consultation implements Serializable{
    
    
    @EmbeddedId
    private TraitNonMed_ConsultationId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false, insertable = false, updatable = false)
    private Consultation consultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_traitNonMedc", nullable = false, insertable = false, updatable = false)
    private TraitementNonMedicamenteux traitementNonMedicamenteux;

    public TraitNonMed_Consultation() {
    }

    public TraitNonMed_Consultation(TraitNonMed_ConsultationId id, Consultation consultation, TraitementNonMedicamenteux traitementNonMedicamenteux) {
        this.id = id;
        this.consultation = consultation;
        this.traitementNonMedicamenteux = traitementNonMedicamenteux;
    }

    public TraitNonMed_ConsultationId getId() {
        return id;
    }

    public void setId(TraitNonMed_ConsultationId id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public TraitementNonMedicamenteux getTraitementNonMedicamenteux() {
        return traitementNonMedicamenteux;
    }

    public void setTraitementNonMedicamenteux(TraitementNonMedicamenteux traitementNonMedicamenteux) {
        this.traitementNonMedicamenteux = traitementNonMedicamenteux;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final TraitNonMed_Consultation other = (TraitNonMed_Consultation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TraitNonMed_Consultation{" + "id=" + id + ", consultation=" + consultation + ", traitementNonMedicamenteux=" + traitementNonMedicamenteux + '}';
    }
    
    
}
