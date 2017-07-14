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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "consultation")
public class Consultation implements Serializable{
    
    @Id
    @SequenceGenerator(name = "consulationSeq", sequenceName = "CONSULTATION_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "consulationSeq")
    @Column(name = "ID",nullable = false)
    private Long id;
    
    @Column(name = "detailConsultation")
    private String detailConsultation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateConsultation")
    private Date dateConsultation;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient",nullable = true)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_intervenant",nullable = true)
    private Intervenant intervenant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_suivi",nullable = true)
    private Suivi suivi;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_traitement",nullable = true)
    private Traitement traitement;
    
    

    public Consultation() {
    }

    public Consultation(String detailConsultation, Date dateConsultation, Patient patient, Intervenant intervenant, Suivi suivi, Traitement traitement) {
        this.detailConsultation = detailConsultation;
        this.dateConsultation = dateConsultation;
        this.patient = patient;
        this.intervenant = intervenant;
        this.suivi = suivi;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Suivi getSuivi() {
        return suivi;
    }

    public void setSuivi(Suivi suivi) {
        this.suivi = suivi;
    }

   

    public Traitement getTraitement() {
        return traitement;
    }

    public void setTraitement(Traitement traitement) {
        this.traitement = traitement;
    }
    
    

    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Consultation other = (Consultation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", detailConsultation=" + detailConsultation + ", dateConsultation=" + dateConsultation + ", patient=" + patient + ", intervenant=" + intervenant + ", suivi=" + suivi + ", traitement=" + traitement + '}';
    }

   

    
}
