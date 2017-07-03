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
 * @author kol
 */
@Entity
@Table(name = "consultation")
public class Consultation implements Serializable{
    
    @EmbeddedId
    private Patient_ConsultationId id;
    
    @Column (name = "traitement")
    private String traitement;
    
    @Column(name = "detailConsultation")
    private String detailConsultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient",nullable = false, insertable = false, updatable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervenant",nullable = false, insertable = false, updatable = false)
    private Intervenant intervenant;
    
    @ManyToOne
    @JoinColumn(name = "pathologie")
    private Pathologie pathologie;

    public Consultation() {
    }

    public Consultation(Patient_ConsultationId id, String traitement, String detailConsultation, Patient patient, Intervenant intervenant, Pathologie pathologie) {
        this.id = id;
        this.traitement = traitement;
        this.detailConsultation = detailConsultation;
        this.patient = patient;
        this.intervenant = intervenant;
        this.pathologie = pathologie;
    }

    /**
     * @return the id
     */
    public Patient_ConsultationId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Patient_ConsultationId id) {
        this.id = id;
    }

    /**
     * @return the traitement
     */
    public String getTraitement() {
        return traitement;
    }

    /**
     * @param traitement the traitement to set
     */
    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    /**
     * @return the detailConsultation
     */
    public String getDetailConsultation() {
        return detailConsultation;
    }

    /**
     * @param detailConsultation the detailConsultation to set
     */
    public void setDetailConsultation(String detailConsultation) {
        this.detailConsultation = detailConsultation;
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

    /**
     * @return the pathologie
     */
    public Pathologie getPathologie() {
        return pathologie;
    }

    /**
     * @param pathologie the pathologie to set
     */
    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
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
        final Consultation other = (Consultation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", traitement=" + traitement + ", detailConsultation=" + detailConsultation + ", patient=" + patient + ", intervenant=" + intervenant + ", pathologie=" + pathologie + '}';
    }

    /**
     * @return the id
     */
    

    
    
}
