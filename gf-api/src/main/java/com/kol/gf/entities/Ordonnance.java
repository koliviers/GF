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
 * @author anonymousghost
 */
@Entity
@Table(name = "ORDONNANCE")
public class Ordonnance implements Serializable{
    @Id
    @SequenceGenerator(name = "ordonnanceSeq", sequenceName = "ORDONNANCE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ordonnanceSeq")
    @Column(name = "ID",nullable = false)
    private Long id;
    
    @Column(name = "Medicament", nullable = true)
    private String medicament;
    
    @Column(name = "Posologie", nullable = true)
    private String posologie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false)
    private Consultation consultation;

    public Ordonnance() {
    }

    public Ordonnance(String medicament, String posologie, Consultation consultation) {
        this.medicament = medicament;
        this.posologie = posologie;
        this.consultation = consultation;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.medicament);
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
        final Ordonnance other = (Ordonnance) obj;
        if (!Objects.equals(this.medicament, other.medicament)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

    

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", medicament=" + medicament + ", posologie=" + posologie + ", consultation=" + consultation + '}';
    }

   
    
}
