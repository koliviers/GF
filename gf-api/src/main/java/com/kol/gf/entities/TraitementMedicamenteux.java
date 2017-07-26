/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author koliviers
 */
@Entity
@Table(name = "TraitementMedicamenteux")
public class TraitementMedicamenteux implements Serializable{
    
    @EmbeddedId
    private TraitementMedicamenteuxId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false, insertable = false, updatable = false)
    private Consultation consultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classeThe", nullable = false, insertable = false, updatable = false)
    private ClasseTherapeutique classe;

    public TraitementMedicamenteux() {
    }

    public TraitementMedicamenteux(TraitementMedicamenteuxId id, Consultation consultation, ClasseTherapeutique classe) {
        this.id = id;
        this.consultation = consultation;
        this.classe = classe;
    }

    public TraitementMedicamenteuxId getId() {
        return id;
    }

    public void setId(TraitementMedicamenteuxId id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public ClasseTherapeutique getClasse() {
        return classe;
    }

    public void setClasse(ClasseTherapeutique classe) {
        this.classe = classe;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.classe);
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
        final TraitementMedicamenteux other = (TraitementMedicamenteux) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.classe, other.classe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TraitementMedicamenteux{" + "id=" + id + ", consultation=" + consultation + ", classe=" + classe + '}';
    }

    
    
    
}
