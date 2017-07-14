/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author koliviers
 */
@Entity
@Table(name = "habitude_alimentaire")
public class Habitude_alimentaire implements Serializable{
    
    @EmbeddedId
    private Habitude_alimentaireId id;
    
    @Column(name = "quantite", nullable = true)
    private Integer quantite;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation", nullable = false,insertable = false,updatable = false)
    private Consultation consultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consommation", nullable = false, insertable = false, updatable = false)
    private Consommation consommation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_habitude", nullable = false, insertable = false, updatable = false)
    private TypeHabitude type_habitude;

    public Habitude_alimentaire() {
    }

    public Habitude_alimentaire(Habitude_alimentaireId id, Integer quantite, Consultation consultation, Consommation consommation, TypeHabitude type_habitude) {
        this.id = id;
        this.quantite = quantite;
        this.consultation = consultation;
        this.consommation = consommation;
        this.type_habitude = type_habitude;
    }

    

    public Habitude_alimentaireId getId() {
        return id;
    }

    public void setId(Habitude_alimentaireId id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    

    public Consommation getConsommation() {
        return consommation;
    }

    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

    public TypeHabitude getType_habitude() {
        return type_habitude;
    }

    public void setType_habitude(TypeHabitude type_habitude) {
        this.type_habitude = type_habitude;
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
        final Habitude_alimentaire other = (Habitude_alimentaire) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Habitude_alimentaire{" + "id=" + id + ", quantite=" + quantite + ", consultation=" + consultation + ", consommation=" + consommation + ", type_habitude=" + type_habitude + '}';
    }

   
    
}
