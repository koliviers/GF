/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "rendez_vous")
public class RendezVous implements Serializable{
    
    @EmbeddedId
    private Patien_IntervenantId id;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateRdv")
    private Date dateRdv;

    @Column(name = "motifrdv",nullable = false)
    private String motifRdv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient",nullable = false,insertable = false,updatable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervenant",nullable = false,insertable = false,updatable = false)
    private Intervenant intervenant;

    public RendezVous() {
    }

    public RendezVous(Patien_IntervenantId id, Date dateRdv, String motifRdv, Patient patient, Intervenant intervenant) {
        this.id = id;
        this.dateRdv = dateRdv;
        this.motifRdv = motifRdv;
        this.patient = patient;
        this.intervenant = intervenant;
    }

    /**
     * @return the id
     */
    public Patien_IntervenantId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Patien_IntervenantId id) {
        this.id = id;
    }

    /**
     * @return the dateRdv
     */
    public Date getDateRdv() {
        return dateRdv;
    }

    /**
     * @param dateRdv the dateRdv to set
     */
    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }

    /**
     * @return the motifRdv
     */
    public String getMotifRdv() {
        return motifRdv;
    }

    /**
     * @param motifRdv the motifRdv to set
     */
    public void setMotifRdv(String motifRdv) {
        this.motifRdv = motifRdv;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the intervenant
     */
    public Intervenant getIntervenant() {
        return intervenant;
    }

    /**
     * @param intervenant the intervenant to set
     */
    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final RendezVous other = (RendezVous) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", dateRdv=" + dateRdv + ", motifRdv=" + motifRdv + ", patient=" + patient + ", intervenant=" + intervenant + '}';
    }

    /**
     * @return the id
     */
    
    
}
