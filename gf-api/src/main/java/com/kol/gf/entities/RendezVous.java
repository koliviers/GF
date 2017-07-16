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
@Table(name = "Rendez_vous")
public class RendezVous implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateRdv", nullable = true)
    private Date dateRdv;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateRdvFiltre", nullable = true)
    private Date dateRdvFiltre;

    @Column(name = "motifrdv")
    private String motifRdv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_intervenant", nullable = false)
    private Intervenant intervenant;

    public RendezVous() {
    }

    public RendezVous(Date dateRdv, Date dateRdvFiltre, String motifRdv, Patient patient, Intervenant intervenant) {
        this.dateRdv = dateRdv;
        this.dateRdvFiltre = dateRdvFiltre;
        this.motifRdv = motifRdv;
        this.patient = patient;
        this.intervenant = intervenant;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getDateRdvFiltre() {
        return dateRdvFiltre;
    }

    public void setDateRdvFiltre(Date dateRdvFiltre) {
        this.dateRdvFiltre = dateRdvFiltre;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
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
        return "RendezVous{" + "id=" + id + ", dateRdv=" + dateRdv + ", dateRdvFiltre=" + dateRdvFiltre + ", motifRdv=" + motifRdv + ", patient=" + patient + ", intervenant=" + intervenant + '}';
    }

    

}
