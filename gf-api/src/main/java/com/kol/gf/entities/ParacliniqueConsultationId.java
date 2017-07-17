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
public class ParacliniqueConsultationId implements Serializable{
    
    private Long id_consultation;
    private Long id_examenParaclinique;

    public ParacliniqueConsultationId() {
    }

    public ParacliniqueConsultationId(Long id_consultation, Long id_examenParaclinique) {
        this.id_consultation = id_consultation;
        this.id_examenParaclinique = id_examenParaclinique;
    }

    public Long getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(Long id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Long getId_examenParaclinique() {
        return id_examenParaclinique;
    }

    public void setId_examenParaclinique(Long id_examenParaclinique) {
        this.id_examenParaclinique = id_examenParaclinique;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id_consultation);
        hash = 53 * hash + Objects.hashCode(this.id_examenParaclinique);
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
        final ParacliniqueConsultationId other = (ParacliniqueConsultationId) obj;
        if (!Objects.equals(this.id_consultation, other.id_consultation)) {
            return false;
        }
        if (!Objects.equals(this.id_examenParaclinique, other.id_examenParaclinique)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParacliniqueConsultationId{" + "id_consultation=" + id_consultation + ", id_examenParaclinique=" + id_examenParaclinique + '}';
    }
    
    
}
