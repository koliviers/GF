/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "pathologie")
public class Pathologie implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nomPathologie")
    private String nomPathologie;
    
    @OneToMany(mappedBy = "pathologie")
    private List<Patient> listpatient;
    
    @OneToMany(mappedBy = "pathologie")
    private List<Consultation> listconsultation;

    public Pathologie() {
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
     * @return the nomPathologie
     */
    public String getNomPathologie() {
        return nomPathologie;
    }

    /**
     * @param nomPathologie the nomPathologie to set
     */
    public void setNomPathologie(String nomPathologie) {
        this.nomPathologie = nomPathologie;
    }

    /**
     * @return the listpatient
     */
    public List<Patient> getListpatient() {
        return listpatient;
    }

    /**
     * @param listpatient the listpatient to set
     */
    public void setListpatient(List<Patient> listpatient) {
        this.listpatient = listpatient;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Pathologie other = (Pathologie) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pathologie{" + "id=" + id + ", nomPathologie=" + nomPathologie + ", listpatient=" + listpatient + '}';
    }

    /**
     * @return the listconsultation
     */
    public List<Consultation> getListconsultation() {
        return listconsultation;
    }

    /**
     * @param listconsultation the listconsultation to set
     */
    public void setListconsultation(List<Consultation> listconsultation) {
        this.listconsultation = listconsultation;
    }

    
    
}
