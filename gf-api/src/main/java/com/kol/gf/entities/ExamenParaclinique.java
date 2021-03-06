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
@Table(name = "EXAMEN_PARACLINIQUE")
public class ExamenParaclinique implements Serializable{
    
    @Id
    @SequenceGenerator(name = "examenParaCliSeq", sequenceName = "EXAMEN_PARACLI_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "examenParaCliSeq")
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "label", nullable = true)
    private String label;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie",  nullable = true)
    private Categorie categorie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = true)
    private Services service;

    public ExamenParaclinique() {
    }

    public ExamenParaclinique(String label, Categorie categorie, Services service) {
        this.label = label;
        this.categorie = categorie;
        this.service = service;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final ExamenParaclinique other = (ExamenParaclinique) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExamenParaclinique{" + "id=" + id + ", label=" + label + ", categorie=" + categorie + ", service=" + service + '}';
    }

    
    
}
