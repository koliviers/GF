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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author anonymousghost
 */
@Entity
@Table(name = "TraitementNonMedicamenteux", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"label"})
})
public class TraitementNonMedicamenteux implements Serializable{
    
    @Id
    @SequenceGenerator(name = "traitNonMedcSeq", sequenceName = "TRAITEMENTNONMEDICAMENTEUX_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "traitNonMedcSeq")
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "label", nullable = true)
    private String label;

    public TraitementNonMedicamenteux() {
    }

    public TraitementNonMedicamenteux(String label) {
        this.label = label;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final TraitementNonMedicamenteux other = (TraitementNonMedicamenteux) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TraitementNonMedicamenteux{" + "id=" + id + ", label=" + label + '}';
    }
    
    
    
}
