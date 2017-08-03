/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.text.NumberFormat;
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
 * @author koliviers
 */
@Entity
@Table(name = "suivie")
public class Suivie implements Serializable {

    @Id
    @SequenceGenerator(name = "suivSeq", sequenceName = "Suiv_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "suivSeq")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "taille", nullable = true)
    private double taille;

    @Column(name = "poids", nullable = true)
    private double poids;

    @Column(name = "tourtaille", nullable = true)
    private double tourtaille;

    @Column(name = "diastoliqueBD", nullable = true)
    private double diastoliqueBD;

    @Column(name = "diastoliqueBG", nullable = true)
    private double diastoliqueBG;
    
    @Column(name = "systoliqueBD", nullable = true)
    private double systoliqueBD;
    
    @Column(name = "systoliqueBG", nullable = true)
    private double systoliqueBG;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_suivie", nullable = true)
    private Date date_suivie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    public Suivie() {
    }

    public Suivie(double taille, double poids, double tourtaille, double diastoliqueBD, double diastoliqueBG, double systoliqueBD, double systoliqueBG, Date date_suivie, Patient patient) {
        this.taille = taille;
        this.poids = poids;
        this.tourtaille = tourtaille;
        this.diastoliqueBD = diastoliqueBD;
        this.diastoliqueBG = diastoliqueBG;
        this.systoliqueBD = systoliqueBD;
        this.systoliqueBG = systoliqueBG;
        this.date_suivie = date_suivie;
        this.patient = patient;
    }

    public Suivie(Patient patient) {
        this.patient = patient;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.getId());
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
        final Suivie other = (Suivie) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Date getDate_suivie() {
        return date_suivie;
    }

    public void setDate_suivie(Date date_suivie) {
        this.date_suivie = date_suivie;
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
     * @return the taille
     */
    public double getTaille() {
        return taille;
    }

    /**
     * @param taille the taille to set
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /**
     * @return the poids
     */
    public double getPoids() {
        return poids;
    }

    /**
     * @param poids the poids to set
     */
    public void setPoids(double poids) {
        this.poids = poids;
    }

    /**
     * @return the tourtaille
     */
    public double getTourtaille() {
        return tourtaille;
    }

    /**
     * @param tourtaille the tourtaille to set
     */
    public void setTourtaille(double tourtaille) {
        this.tourtaille = tourtaille;
    }

    public double getDiastoliqueBD() {
        return diastoliqueBD;
    }

    public void setDiastoliqueBD(double diastoliqueBD) {
        this.diastoliqueBD = diastoliqueBD;
    }

    public double getDiastoliqueBG() {
        return diastoliqueBG;
    }

    public void setDiastoliqueBG(double diastoliqueBG) {
        this.diastoliqueBG = diastoliqueBG;
    }

    public double getSystoliqueBD() {
        return systoliqueBD;
    }

    public void setSystoliqueBD(double systoliqueBD) {
        this.systoliqueBD = systoliqueBD;
    }

    public double getSystoliqueBG() {
        return systoliqueBG;
    }

    public void setSystoliqueBG(double systoliqueBG) {
        this.systoliqueBG = systoliqueBG;
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

    @Override
    public String toString() {
        return "Suivie{" + "id=" + id + ", taille=" + taille + ", poids=" + poids + ", tourtaille=" + tourtaille + ", diastoliqueBD=" + diastoliqueBD + ", diastoliqueBG=" + diastoliqueBG + ", systoliqueBD=" + systoliqueBD + ", systoliqueBG=" + systoliqueBG + ", date_suivie=" + date_suivie + ", patient=" + patient + '}';
    }

      

    public Double calculMasse() {

        double m = 0;

        m = ((this.poids) / (this.taille * this.taille));

        Double s = Double.parseDouble(String.valueOf(((int)(m*100))/100));

        return s;
    }

}
