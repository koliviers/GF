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
    private long id_Patient;
    private long id_Consommation;
    private long id_type_habitude;

    public Habitude_alimentaireId() {
    }

    public Habitude_alimentaireId(long id_Patient, long id_Consommation, long id_type_habitude) {
        this.id_Patient = id_Patient;
        this.id_Consommation = id_Consommation;
        this.id_type_habitude = id_type_habitude;
    }

    

    public long getId_Patient() {
        return id_Patient;
    }

    public void setId_Patient(long id_Patient) {
        this.id_Patient = id_Patient;
    }

    public long getId_Consommation() {
        return id_Consommation;
    }

    public void setId_Consommation(long id_Consommation) {
        this.id_Consommation = id_Consommation;
    }

    public long getId_type_habitude() {
        return id_type_habitude;
    }

    public void setId_type_habitude(long id_type_habitude) {
        this.id_type_habitude = id_type_habitude;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id_Patient ^ (this.id_Patient >>> 32));
        hash = 37 * hash + (int) (this.id_Consommation ^ (this.id_Consommation >>> 32));
        hash = 37 * hash + (int) (this.id_type_habitude ^ (this.id_type_habitude >>> 32));
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
        if (this.id_Patient != other.id_Patient) {
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
        return "Habitude_alimentaireId{" + "id_Patient=" + id_Patient + ", id_Consommation=" + id_Consommation + ", id_type_habitude=" + id_type_habitude + '}';
    }
    
    

    
    
    
}
