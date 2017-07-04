/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column (name = "traitement")
    private String traitement;
    
    @Column(name = "detailConsultation")
    private String detailConsultation;
    
    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "intervenant")
    private Intervenant intervenant;
    
    @ManyToOne
    @JoinColumn(name = "pathologie")
    private Pathologie pathologie;

    public Consultation() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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
        hash = 89 * hash + Objects.hashCode(this.id);
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
    
    
    
    

}
