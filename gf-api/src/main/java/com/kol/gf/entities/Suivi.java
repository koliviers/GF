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
@Table(name = "Suivi")
public class Suivi implements Serializable {

    @Id
    @SequenceGenerator(name = "suiviSeq", sequenceName = "SUIVI_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "suiviSeq")
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "taille", nullable = true)
    private double taille;

    @Column(name = "poids", nullable = true)
    private double poids;

    @Column(name = "tourtaille", nullable = true)
    private double tourtaille;
    
    @Column(name = "tensiondroit", nullable = true)
    private double tensiondroit;

    @Column(name = "tension", nullable = true)
    private double tension;

    @Column(name = "sport", nullable = true)
    private String sport;
    
    @Column(name = "fruit", nullable = true)
    private String fruit;
            
    @Column(name = "antmed", nullable = true)
    private String antMedicaux;

    @Column(name = "antChir", nullable = true)
    private String antChirugicaux;

    public Suivi() {
    }

    public Suivi(double taille, double poids, double tourtaille, double tensiondroit, double tension, String sport, String fruit, String antMedicaux, String antChirugicaux) {
        this.taille = taille;
        this.poids = poids;
        this.tourtaille = tourtaille;
        this.tensiondroit = tensiondroit;
        this.tension = tension;
        this.sport = sport;
        this.fruit = fruit;
        this.antMedicaux = antMedicaux;
        this.antChirugicaux = antChirugicaux;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTourtaille() {
        return tourtaille;
    }

    public void setTourtaille(double tourtaille) {
        this.tourtaille = tourtaille;
    }

    public double getTensiondroit() {
        return tensiondroit;
    }

    public void setTensiondroit(double tensiondroit) {
        this.tensiondroit = tensiondroit;
    }

    public double getTension() {
        return tension;
    }

    public void setTension(double tension) {
        this.tension = tension;
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
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Suivi other = (Suivi) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Suivi{" + "id=" + id + ", taille=" + taille + ", poids=" + poids + ", tourtaille=" + tourtaille + ", tensiondroit=" + tensiondroit + ", tension=" + tension + ", sport=" + sport + ", fruit=" + fruit + ", antMedicaux=" + antMedicaux + ", antChirugicaux=" + antChirugicaux + '}';
    }
    
    
    
    
    public double calculMasse(){
        
        
        double m=0;
        
        m=((this.poids)/(this.taille*this.taille));
        
        return m;
    }
    
}
