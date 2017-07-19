    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kol
 */
@Entity
@Table(name = "Patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nomPatient", nullable = false)
    private String nomPatient;

    @Column(name = "prenomPatient", nullable = false)
    private String prenomPatient;
    
    @Column(name = "codePatient", nullable = true)
    private String codePatient;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_naissance", nullable = true)
    private Date date_naissance;

    @Column(name = "sexe", nullable = false)
    private String sexe;

    @Column(name = "contact", nullable = true)
    private String contact;

    @Column(name = "civilite", nullable = true)
    private String civilite;

    @Column(name = "profession", nullable = true)
    private String profession;

    @Column(name = "adresse", nullable = true)
    private String adresse;
    
     @Column(name = "taille", nullable = true)
    private double taille;

    @Column(name = "poids", nullable = true)
    private double poids;

    @Column(name = "tourtaille", nullable = true)
    private double tourtaille;
    
    @Column(name = "tensiondroit", nullable = true)
    private double tensiondroit;

    @Column(name = "tension", nullable = true)
    private double tension;



    public Patient() {
    }

    public Patient(String nomPatient, String prenomPatient, String codePatient, Date date_naissance, String sexe, String contact, String civilite, String profession, String adresse, double taille, double poids, double tourtaille, double tensiondroit, double tension) {
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.codePatient = codePatient;
        this.date_naissance = date_naissance;
        this.sexe = sexe;
        this.contact = contact;
        this.civilite = civilite;
        this.profession = profession;
        this.adresse = adresse;
        this.taille = taille;
        this.poids = poids;
        this.tourtaille = tourtaille;
        this.tensiondroit = tensiondroit;
        this.tension = tension;
    }

   



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }  

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTourtaille() {
        return tourtaille;
    }

    public void setTourtaille(double tourtaille) {
        this.tourtaille = tourtaille;
    }

    public double getTensiondroit() {
        return tensiondroit;
    }

    public void setTensiondroit(double tensiondroit) {
        this.tensiondroit = tensiondroit;
    }

    public double getTension() {
        return tension;
    }

    public void setTension(double tension) {
        this.tension = tension;
    }
    
    public Double calculMasseCorporelle(){
        return this.poids/(this.taille*this.taille);
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", nomPatient=" + nomPatient + ", prenomPatient=" + prenomPatient + ", codePatient=" + codePatient + ", date_naissance=" + date_naissance + ", sexe=" + sexe + ", contact=" + contact + ", civilite=" + civilite + ", profession=" + profession + ", adresse=" + adresse + ", taille=" + taille + ", poids=" + poids + ", tourtaille=" + tourtaille + ", tensiondroit=" + tensiondroit + ", tension=" + tension + '}';
    }

    
  
}
