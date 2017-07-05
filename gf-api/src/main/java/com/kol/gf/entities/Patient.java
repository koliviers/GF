/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
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
import org.hibernate.annotations.Fetch;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nomPatient", nullable = false)
    private String nomPatient;

    @Column(name = "prenomPatient", nullable = false)
    private String prenomPatient;

    @Column(name = "age")
    private int age;

    @Column(name = "sexe")
    private char sexe;

    @Column(name = "contact")
    private long contact;

    @Column(name = "civilite")
    private String civilite;

    @Column(name = "profession")
    private String profession;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "taille")
    private double taille;

    @Column(name = "poid")
    private double poid;

    @Column(name = "tourtaille")
    private double tourtaille;

    @Column(name = "tension")
    private double tension;

    @Column(name = "sport")
    private String sport;

    @Column(name = "fruit")
    private String fruit;

    @Column(name = "antmed")
    private String antMedicaux;

    @Column(name = "antChir")
    private String antChirugicaux;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "pathologie",nullable = false)
    private Pathologie pathologie;

     
    @OneToMany(mappedBy = "patient")
    private List<Consultation> listConsultation;
    
    
    @OneToMany(mappedBy = "patient")
    private List<RendezVous> listRdv;

    

    public Patient() {
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
     * @return the nomPatient
     */
    public String getNomPatient() {
        return nomPatient;
    }

    /**
     * @param nomPatient the nomPatient to set
     */
    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    /**
     * @return the prenomPatient
     */
    public String getPrenomPatient() {
        return prenomPatient;
    }

    /**
     * @param prenomPatient the prenomPatient to set
     */
    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the sexe
     */
    public char getSexe() {
        return sexe;
    }

    /**
     * @param sexe the sexe to set
     */
    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    /**
     * @return the contact
     */
    public long getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(long contact) {
        this.contact = contact;
    }

    /**
     * @return the civilite
     */
    public String getCivilite() {
        return civilite;
    }

    /**
     * @param civilite the civilite to set
     */
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    /**
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the taille
     */
    public double getTaille() {
        return taille;
    }

    /**
     * @param taille the taille to set
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /**
     * @return the poid
     */
    public double getPoid() {
        return poid;
    }

    /**
     * @param poid the poid to set
     */
    public void setPoid(double poid) {
        this.poid = poid;
    }

    /**
     * @return the tourtaille
     */
    public double getTourtaille() {
        return tourtaille;
    }

    /**
     * @param tourtaille the tourtaille to set
     */
    public void setTourtaille(double tourtaille) {
        this.tourtaille = tourtaille;
    }

    /**
     * @return the tension
     */
    public double getTension() {
        return tension;
    }

    /**
     * @param tension the tension to set
     */
    public void setTension(double tension) {
        this.tension = tension;
    }

    /**
     * @return the sport
     */
    public String getSport() {
        return sport;
    }

    /**
     * @param sport the sport to set
     */
    public void setSport(String sport) {
        this.sport = sport;
    }

    /**
     * @return the fruit
     */
    public String getFruit() {
        return fruit;
    }

    /**
     * @param fruit the fruit to set
     */
    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    /**
     * @return the antMedicaux
     */
    public String getAntMedicaux() {
        return antMedicaux;
    }

    /**
     * @param antMedicaux the antMedicaux to set
     */
    public void setAntMedicaux(String antMedicaux) {
        this.antMedicaux = antMedicaux;
    }

    /**
     * @return the antChirugicaux
     */
    public String getAntChirugicaux() {
        return antChirugicaux;
    }

    /**
     * @param antChirugicaux the antChirugicaux to set
     */
    public void setAntChirugicaux(String antChirugicaux) {
        this.antChirugicaux = antChirugicaux;
    }

    /**
     * @return the pathologie
     */
    public Pathologie getPathologie() {
        return pathologie;
    }

    /**
     * @param pathologie the pathologie to set
     */
    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    /**
     * @return the consommation
     */
    
    /**
     * @return the listConsultation
     */
    public List<Consultation> getListConsultation() {
        return listConsultation;
    }

    /**
     * @param listConsultation the listConsultation to set
     */
    public void setListConsultation(List<Consultation> listConsultation) {
        this.listConsultation = listConsultation;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    private static final Logger LOG = Logger.getLogger(Patient.class.getName());

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", nomPatient=" + nomPatient + ", prenomPatient=" + prenomPatient + ", age=" + age + ", sexe=" + sexe + ", contact=" + contact + ", civilite=" + civilite + ", profession=" + profession + ", adresse=" + adresse + ", taille=" + taille + ", poid=" + poid + ", tourtaille=" + tourtaille + ", tension=" + tension + ", sport=" + sport + ", fruit=" + fruit + ", antMedicaux=" + antMedicaux + ", antChirugicaux=" + antChirugicaux + ", pathologie=" + pathologie + ", listConsultation=" + listConsultation + ", listRdv=" + listRdv + '}';
    }

   

}
