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
 * @param id l'id du service
 * @param code code du service
 * @param nomservice nom du service
 */
@Entity
@Table(name = "Services")
public class Services implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "nomService")
    private String nomService;
    @OneToMany(mappedBy = "services")
    private List<Intervenant> listIntervenant;

    public Services() {
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the nomService
     */
    public String getNomService() {
        return nomService;
    }

    /**
     * @param nomService the nomService to set
     */
    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    /**
     * @return the listIntervenant
     */
    public List<Intervenant> getListIntervenant() {
        return listIntervenant;
    }

    /**
     * @param listIntervenant the listIntervenant to set
     */
    public void setListIntervenant(List<Intervenant> listIntervenant) {
        this.listIntervenant = listIntervenant;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Services other = (Services) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Services{" + "id=" + id + ", code=" + code + ", nomService=" + nomService + ", listIntervenant=" + listIntervenant + '}';
    }
    
    

}
