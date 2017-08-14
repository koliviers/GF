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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "sexe", nullable = false)
    private String sexe;
    
    @Column(name = "accessibilite", nullable = true)
    private String accessibilite;

    @Column(name = "contact", nullable = true)
    private String contact;

    @Column(name = "civilite", nullable = true)
    private String civilite;

    @Column(name = "profession", nullable = true)
    private String profession;

    @Column(name = "adresse", nullable = true)
    private String adresse;


    public Patient() {
    }

    public Patient(String nomPatient, String prenomPatient, String codePatient, Integer age, String sexe, String accessibilite, String contact, String civilite, String profession, String adresse) {
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.codePatient = codePatient;
        this.age = age;
        this.sexe = sexe;
        this.accessibilite = accessibilite;
        this.contact = contact;
        this.civilite = civilite;
        this.profession = profession;
        this.adresse = adresse;
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
    
    public String getNomComplet(){
        return this.nomPatient+" "+this.prenomPatient;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAccessibilite() {
        return accessibilite;
    }

    public void setAccessibilite(String accessibilite) {
        this.accessibilite = accessibilite;
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
        return "Patient{" + "id=" + id + ", nomPatient=" + nomPatient + ", prenomPatient=" + prenomPatient + ", codePatient=" + codePatient + ", age=" + age + ", sexe=" + sexe + ", accessibilite=" + accessibilite + ", contact=" + contact + ", civilite=" + civilite + ", profession=" + profession + ", adresse=" + adresse + '}';
    }

    
  
}
