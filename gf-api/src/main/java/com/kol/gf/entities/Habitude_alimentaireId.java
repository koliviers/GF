/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author koliviers
 */
@Embeddable
public class Habitude_alimentaireId implements Serializable{
    private Long id_consultation;
    private Long id_Consommation;
    private Long id_type_habitude;

    public Habitude_alimentaireId() {
    }

    public Habitude_alimentaireId(Long id_consultation, Long id_Consommation, Long id_type_habitude) {
        this.id_consultation = id_consultation;
        this.id_Consommation = id_Consommation;
        this.id_type_habitude = id_type_habitude;
    }

    public Long getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(Long id_consultation) {
        this.id_consultation = id_consultation;
    }


    public Long getId_Consommation() {
        return id_Consommation;
    }

    public void setId_Consommation(Long id_Consommation) {
        this.id_Consommation = id_Consommation;
    }

    public Long getId_type_habitude() {
        return id_type_habitude;
    }

    public void setId_type_habitude(Long id_type_habitude) {
        this.id_type_habitude = id_type_habitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.id_consultation ^ (this.id_consultation >>> 32));
        hash = 89 * hash + (int) (this.id_Consommation ^ (this.id_Consommation >>> 32));
        hash = 89 * hash + (int) (this.id_type_habitude ^ (this.id_type_habitude >>> 32));
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
        final Habitude_alimentaireId other = (Habitude_alimentaireId) obj;
        if (this.id_consultation != other.id_consultation) {
            return false;
        }
        if (this.id_Consommation != other.id_Consommation) {
            return false;
        }
        if (this.id_type_habitude != other.id_type_habitude) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Habitude_alimentaireId{" + "id_consultation=" + id_consultation + ", id_Consommation=" + id_Consommation + ", id_type_habitude=" + id_type_habitude + '}';
    }

    

    
}
