/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import javax.persistence.Column;
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
 * @author koliviers
 */
@Entity
@Table(name = "traitement")
public class Traitement implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "label")
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classtherapeutique")
    private ClasseTherapeutique classeTherapeutique;
    
    public Traitement() {
    }

    public Traitement(long id, String label, ClasseTherapeutique classeTherapeutique) {
        this.id = id;
        this.label = label;
        this.classeTherapeutique = classeTherapeutique;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the classeTherapeutique
     */
    public ClasseTherapeutique getClasseTherapeutique() {
        return classeTherapeutique;
    }

    /**
     * @param classeTherapeutique the classeTherapeutique to set
     */
    public void setClasseTherapeutique(ClasseTherapeutique classeTherapeutique) {
        this.classeTherapeutique = classeTherapeutique;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Traitement other = (Traitement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Traitement{" + "id=" + id + ", label=" + label + ", classeTherapeutique=" + classeTherapeutique + '}';
    }

    
    
   
   
    
    
    
}
