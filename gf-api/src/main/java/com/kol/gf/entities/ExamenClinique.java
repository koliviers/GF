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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author anonymousghost
 */
@Entity
@Table(name = "EXAMEN_CLINIQUE")
public class ExamenClinique implements Serializable{
    
    @Id
    @SequenceGenerator(name = "examenCliSeq", sequenceName = "EXAMEN_CLI_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "examenCliSeq")
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "Signe_clinique", nullable = true)
    private String signe_clinique;
    
    @Column(name = "Etat_general", nullable = true)
    private String etat_general;
    
    @Column(name = "Etat_consce", nullable = true)
    private String etat_consce;
    
    @Column(name = "Palure", nullable = true)
    private String palure;
    
    @Column(name = "Etat_hydratation", nullable = true)
    private String etat_hydratation;

    public ExamenClinique() {
    }

    public ExamenClinique(String signe_clinique, String etat_general, String etat_consce, String palure, String etat_hydratation) {
        this.signe_clinique = signe_clinique;
        this.etat_general = etat_general;
        this.etat_consce = etat_consce;
        this.palure = palure;
        this.etat_hydratation = etat_hydratation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigne_clinique() {
        return signe_clinique;
    }

    public void setSigne_clinique(String signe_clinique) {
        this.signe_clinique = signe_clinique;
    }

    public String getEtat_general() {
        return etat_general;
    }

    public void setEtat_general(String etat_general) {
        this.etat_general = etat_general;
    }

    public String getEtat_consce() {
        return etat_consce;
    }

    public void setEtat_consce(String etat_consce) {
        this.etat_consce = etat_consce;
    }

    public String getPalure() {
        return palure;
    }

    public void setPalure(String palure) {
        this.palure = palure;
    }

    public String getEtat_hydratation() {
        return etat_hydratation;
    }

    public void setEtat_hydratation(String etat_hydratation) {
        this.etat_hydratation = etat_hydratation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final ExamenClinique other = (ExamenClinique) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExamenClinique{" + "id=" + id + ", signe_clinique=" + signe_clinique + ", etat_general=" + etat_general + ", etat_consce=" + etat_consce + ", palure=" + palure + ", etat_hydratation=" + etat_hydratation + '}';
    }
    
    
    
}
