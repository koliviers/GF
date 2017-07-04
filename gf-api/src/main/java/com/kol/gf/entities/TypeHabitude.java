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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author koliviers
 */
@Entity
@Table(name = "typeHabitude")
public class TypeHabitude implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "label", nullable = false)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "id_Patient", referencedColumnName = "id_Patient")
        ,
        @JoinColumn(name = "id_Consommation", referencedColumnName = "id_Consommation")})
    private Habitude_alimentaire habitude;

    public TypeHabitude() {
    }

    public TypeHabitude(String label, Habitude_alimentaire habitude) {
        this.label = label;
        this.habitude = habitude;
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
     * @return the habitude
     */
    public Habitude_alimentaire getHabitude() {
        return habitude;
    }

    /**
     * @param habitude the habitude to set
     */
    public void setHabitude(Habitude_alimentaire habitude) {
        this.habitude = habitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final TypeHabitude other = (TypeHabitude) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeHabitude{" + "id=" + id + ", label=" + label + ", habitude=" + habitude + '}';
    }

}
