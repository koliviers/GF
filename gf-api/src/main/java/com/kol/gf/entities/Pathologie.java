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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "Pathologie", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nomPathologie"})
})
public class Pathologie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "nomPathologie", nullable = false)
    private String nomPathologie;

    public Pathologie() {
    }

    public Pathologie(String nomPathologie) {
        this.nomPathologie = nomPathologie;
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
        return "Pathologie{" + "id=" + id + ", nomPathologie=" + nomPathologie + '}';
    }

}
