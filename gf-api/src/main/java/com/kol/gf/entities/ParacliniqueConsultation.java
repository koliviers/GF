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
@Table(name = "PARACLINIQUE_CONSULTATION")
public class ParacliniqueConsultation implements Serializable{
    
    @EmbeddedId
    private ParacliniqueConsultationId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false, insertable = false, updatable = false)
    private Consultation consultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_examenParaclinique", nullable = false, insertable = false, updatable = false)
    private ExamenParaclinique examen;

    public ParacliniqueConsultationId getId() {
        return id;
    }

    public void setId(ParacliniqueConsultationId id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public ExamenParaclinique getExamen() {
        return examen;
    }

    public void setExamen(ExamenParaclinique examen) {
        this.examen = examen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final ParacliniqueConsultation other = (ParacliniqueConsultation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParacliniqueConsultation{" + "id=" + id + ", consultation=" + consultation + ", examen=" + examen + '}';
    }
    
    
}
