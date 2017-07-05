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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "intervenant")
public class Intervenant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nomIntervenant", nullable = false)
    private String nomIntervenant;
    @Column(name = "prenomIntervenant", nullable = false)
    private String prenomIntervenant;
    @Column(name = "contact")
    private Long contact;
    
    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services",nullable = false)
    private Services services;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "type_intervenant",nullable = false)
    private TypeIntervenant type_intervenant;

    @OneToMany(mappedBy = "intervenant")
    private List<Consultation> listeConsultation;

    @OneToMany(mappedBy = "intervenant")
    private List<RendezVous> listRdv;

    public Intervenant() {
        this.active=true;
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
     * @return the nomIntervenant
     */
    public String getNomIntervenant() {
        return nomIntervenant;
    }

    /**
     * @param nomIntervenant the nomIntervenant to set
     */
    public void setNomIntervenant(String nomIntervenant) {
        this.nomIntervenant = nomIntervenant;
    }

    /**
     * @return the prenomIntervenant
     */
    public String getPrenomIntervenant() {
        return prenomIntervenant;
    }

    /**
     * @param prenomIntervenant the prenomIntervenant to set
     */
    public void setPrenomIntervenant(String prenomIntervenant) {
        this.prenomIntervenant = prenomIntervenant;
    }

    /**
     * @return the contact
     */
    public Long getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Long contact) {
        this.contact = contact;
    }

    /**
     * @return the services
     */
    public Services getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(Services services) {
        this.services = services;
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
        final Intervenant other = (Intervenant) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Intervenant{" + "id=" + id + ", nomIntervenant=" + nomIntervenant + ", prenomIntervenant=" + prenomIntervenant + ", contact=" + contact + ", services=" + services + '}';
    }

    /**
     * @return the type_intervenant
     */
    public TypeIntervenant getType_intervenant() {
        return type_intervenant;
    }

    /**
     * @param type_intervenant the type_intervenant to set
     */
    public void setType_intervenant(TypeIntervenant type_intervenant) {
        this.type_intervenant = type_intervenant;
    }

    /**
     * @return the listeConsultation
     */
    public List<Consultation> getListeConsultation() {
        return listeConsultation;
    }

    /**
     * @param listeConsultation the listeConsultation to set
     */
    public void setListeConsultation(List<Consultation> listeConsultation) {
        this.listeConsultation = listeConsultation;
    }

    /**
     * @return the listRdv
     */
    public List<RendezVous> getListRdv() {
        return listRdv;
    }

    /**
     * @param listRdv the listRdv to set
     */
    public void setListRdv(List<RendezVous> listRdv) {
        this.listRdv = listRdv;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
