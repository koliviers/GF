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
@Table(name = "Comorbidite")
public class Comorbidite implements Serializable {

    @Id
    @SequenceGenerator(name = "comorbiditeSeq", sequenceName = "COMORBIDITE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "comorbiditeSeq")
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "label", nullable = true)
    private String label;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false)
    private Consultation consultation;

    public Comorbidite() {
    }

    public Comorbidite(String label, Consultation consultation) {
        this.label = label;
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.label);
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
        final Comorbidite other = (Comorbidite) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comorbidite{" + "id=" + id + ", label=" + label + ", consultation=" + consultation + '}';
    }
    
    
}
