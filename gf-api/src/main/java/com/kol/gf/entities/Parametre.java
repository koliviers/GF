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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author anonymousghost
 */

@Entity
@Table(name = "Parametre")
public class Parametre implements Serializable{
    
    @Id
    @SequenceGenerator(name = "parametreSeq", sequenceName = "PARAMETRE_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "parametreSeq")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "Moozisms_apiKey", nullable = true)
    private String moozisms_Apikey;
    
    @Column(name = "Moozisms_apiSecret", nullable = true)
    private String moozisms_ApiSecret;
    
    @Column(name = "Entete_message", nullable = true)
    private String entete_message;
    
    @Column(name = "lien_sauvegarde", nullable = true)
    private String lien_sauvegarde;
    
    @Column(name = "hostname", nullable = true)
    private String hostname;
    
    @Column(name = "baseDeDonnee", nullable = true)
    private String baseDeDonnee;
    
    @Column(name = "utilisateurBD", nullable = true)
    private String utilisateurBD;
    
    @Column(name = "motDePasse", nullable = true)
    private String motDePasse;
    
    @Column(name = "lienRepertoireMysqlDump", nullable = true)
    private String lienRepertoireMysqlDump;

    public Parametre() {
    }

    public Parametre(String moozisms_Apikey, String moozisms_ApiSecret, String entete_message, String lien_sauvegarde, String hostname, String baseDeDonnee, String utilisateurBD, String motDePasse, String lienRepertoireMysqlDump) {
        this.moozisms_Apikey = moozisms_Apikey;
        this.moozisms_ApiSecret = moozisms_ApiSecret;
        this.entete_message = entete_message;
        this.lien_sauvegarde = lien_sauvegarde;
        this.hostname = hostname;
        this.baseDeDonnee = baseDeDonnee;
        this.utilisateurBD = utilisateurBD;
        this.motDePasse = motDePasse;
        this.lienRepertoireMysqlDump = lienRepertoireMysqlDump;
    }

    public String getEntete_message() {
        return entete_message;
    }

    public void setEntete_message(String entete_message) {
        this.entete_message = entete_message;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoozisms_Apikey() {
        return moozisms_Apikey;
    }

    public void setMoozisms_Apikey(String moozisms_Apikey) {
        this.moozisms_Apikey = moozisms_Apikey;
    }

    public String getMoozisms_ApiSecret() {
        return moozisms_ApiSecret;
    }

    public void setMoozisms_ApiSecret(String moozisms_ApiSecret) {
        this.moozisms_ApiSecret = moozisms_ApiSecret;
    }

    public String getLien_sauvegarde() {
        return lien_sauvegarde;
    }

    public void setLien_sauvegarde(String lien_sauvegarde) {
        this.lien_sauvegarde = lien_sauvegarde;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getBaseDeDonnee() {
        return baseDeDonnee;
    }

    public void setBaseDeDonnee(String baseDeDonnee) {
        this.baseDeDonnee = baseDeDonnee;
    }

    public String getUtilisateurBD() {
        return utilisateurBD;
    }

    public void setUtilisateurBD(String utilisateurBD) {
        this.utilisateurBD = utilisateurBD;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getLienRepertoireMysqlDump() {
        return lienRepertoireMysqlDump;
    }

    public void setLienRepertoireMysqlDump(String lienRepertoireMysqlDump) {
        this.lienRepertoireMysqlDump = lienRepertoireMysqlDump;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Parametre other = (Parametre) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parametre{" + "id=" + id + ", moozisms_Apikey=" + moozisms_Apikey + ", moozisms_ApiSecret=" + moozisms_ApiSecret + ", entete_message=" + entete_message + ", lien_sauvegarde=" + lien_sauvegarde + ", hostname=" + hostname + ", baseDeDonnee=" + baseDeDonnee + ", utilisateurBD=" + utilisateurBD + ", motDePasse=" + motDePasse + ", lienRepertoireMysqlDump=" + lienRepertoireMysqlDump + '}';
    }

    
    
}
