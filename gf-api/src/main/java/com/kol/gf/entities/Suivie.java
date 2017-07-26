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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

    @Column(name = "tensiondroit", nullable = true)
    private String tensiondroit;

    @Column(name = "tensiongauche", nullable = true)
    private String tensiongauche;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    public Suivie() {
    }

    public Suivie(Long id, double taille, double poids, double tourtaille, String tensiondroit, String tensiongauche, Patient patient) {
        this.id = id;
        this.taille = taille;
        this.poids = poids;
        this.tourtaille = tourtaille;
        this.tensiondroit = tensiondroit;
        this.tensiongauche = tensiongauche;
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

    @Override
    public String toString() {
        return "Suivie{" + "id=" + getId() + ", taille=" + getTaille() + ", poids=" + getPoids() + ", tourtaille=" + getTourtaille() + ", tensiondroit=" + getTensiondroit() + ", tensiongauche=" + getTensiongauche() + ", patient=" + getPatient() + '}';
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

    /**
     * @return the tensiondroit
     */
    public String getTensiondroit() {
        return tensiondroit;
    }

    /**
     * @param tensiondroit the tensiondroit to set
     */
    public void setTensiondroit(String tensiondroit) {
        this.tensiondroit = tensiondroit;
    }

    /**
     * @return the tensiongauche
     */
    public String getTensiongauche() {
        return tensiongauche;
    }

    /**
     * @param tensiongauche the tensiongauche to set
     */
    public void setTensiongauche(String tensiongauche) {
        this.tensiongauche = tensiongauche;
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
    
    
    
    

    

}
