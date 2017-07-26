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
public class Consultation implements Serializable {

    @Id
    @SequenceGenerator(name = "consulationSeq", sequenceName = "CONSULTATION_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "consulationSeq")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "detailConsultation", length = 1000, nullable = true)
    private String detailConsultation;

    @Column(name = "TraitementNomMedicamenteux", length = 1000, nullable = true)
    private String traitementNomMedicamenteux;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateConsultation", nullable = false)
    private Date dateConsultation;

    @Column(name = "sport", nullable = true)
    private String sport;

    @Column(name = "fruit", nullable = true)
    private String fruit;

    @Column(name = "antmed", nullable = true)
    private String antMedicaux;

    @Column(name = "antChir", nullable = true)
    private String antChirugicaux;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_intervenant", nullable = true)
    private Intervenant intervenant;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_examenClinique", nullable = true)
    private ExamenClinique examen_Clinique;

    public Consultation() {
    }

    public Consultation(String detailConsultation, String traitementNomMedicamenteux, Date dateConsultation, String sport, String fruit, String antMedicaux, String antChirugicaux, Patient patient, Intervenant intervenant, ExamenClinique examen_Clinique) {
        this.detailConsultation = detailConsultation;
        this.traitementNomMedicamenteux = traitementNomMedicamenteux;
        this.dateConsultation = dateConsultation;
        this.sport = sport;
        this.fruit = fruit;
        this.antMedicaux = antMedicaux;
        this.antChirugicaux = antChirugicaux;
        this.patient = patient;
        this.intervenant = intervenant;
        this.examen_Clinique = examen_Clinique;
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

    public ExamenClinique getExamen_Clinique() {
        return examen_Clinique;
    }

    public void setExamen_Clinique(ExamenClinique examen_Clinique) {
        this.examen_Clinique = examen_Clinique;
    }

    public String getTraitementNomMedicamenteux() {
        return traitementNomMedicamenteux;
    }

    public void setTraitementNomMedicamenteux(String traitementNomMedicamenteux) {
        this.traitementNomMedicamenteux = traitementNomMedicamenteux;
    }


    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getAntMedicaux() {
        return antMedicaux;
    }

    public void setAntMedicaux(String antMedicaux) {
        this.antMedicaux = antMedicaux;
    }

    public String getAntChirugicaux() {
        return antChirugicaux;
    }

    public void setAntChirugicaux(String antChirugicaux) {
        this.antChirugicaux = antChirugicaux;
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
        return "Consultation{" + "id=" + id + ", detailConsultation=" + detailConsultation + ", traitementNomMedicamenteux=" + traitementNomMedicamenteux + ", dateConsultation=" + dateConsultation + ", sport=" + sport + ", fruit=" + fruit + ", antMedicaux=" + antMedicaux + ", antChirugicaux=" + antChirugicaux + ", patient=" + patient + ", intervenant=" + intervenant + ", examen_Clinique=" + examen_Clinique + '}';
    }

 
   

}
