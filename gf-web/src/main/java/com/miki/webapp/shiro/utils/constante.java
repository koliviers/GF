/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.shiro.utils;

import java.io.Serializable;

/**
 *
 * @author Mikel
 */
public abstract class constante implements Serializable{
    
    public static final String ROLE_ALL = "Tous";
    public static final String ROLE_ALL_CLE = "*";
    
    
    public static final String ROLE_CREER_PATIENT = "Ajouter un patient";
    public static final String ROLE_MODIFIER_PATIENT = "Modifier un patient";
    public static final String ROLE_CONSULTER_PATIENT = "Consulter un patient";
    public static final String ROLE_IMPRIMER_PATIENT = "Imprimer un patient";
    
    public static final String ROLE_CREER_PATIENT_CLE = "patient:ajout";
    public static final String ROLE_MODIFIER_PATIENT_CLE = "patient:modification";
    public static final String ROLE_CONSULTER_PATIENT_CLE = "patient:consultation";
    public static final String ROLE_IMPRIMER_PATIENT_CLE = "patient:impression";
    
    public static final String ROLE_CREER_RDV = "Ajouter un rdv";
    public static final String ROLE_MODIFIER_RDV = "Modifier un rdv";
    public static final String ROLE_CONSULTER_RDV = "Consulter un rdv";
    public static final String ROLE_IMPRIMER_RDV = "Imprimer un rdv";
    
    public static final String ROLE_CREER_RDV_CLE = "rdv:ajout";
    public static final String ROLE_MODIFIER_RDV_CLE = "rdv:modification";
    public static final String ROLE_CONSULTER_RDV_CLE = "rdv:consultation";
    public static final String ROLE_IMPRIMER_RDV_CLE = "rdv:impression";
    
     public static final String ROLE_CREER_INTERVENANT = "Ajouter un intervenant";
    public static final String ROLE_MODIFIER_INTERVENANT = "Modifier un intervenant";
    public static final String ROLE_CONSULTER_INTERVENANT = "Consulter un intervenant";
    public static final String ROLE_IMPRIMER_INTERVENANT = "Imprimer un intervenant";
    
    public static final String ROLE_CREER_INTERVENANT_CLE = "intervenant:ajout";
    public static final String ROLE_MODIFIER_INTERVENANT_CLE = "intervenant:modification";
    public static final String ROLE_CONSULTER_INTERVENANT_CLE = "intervenant:consultation";
    public static final String ROLE_IMPRIMER_INTERVENANT_CLE = "intervenant:impression";
    
     public static final String ROLE_CREER_CONSULTATION = "Ajouter une consultation";
    public static final String ROLE_MODIFIER_CONSULTATION = "Modifier une consultation";
    public static final String ROLE_CONSULTER_CONSULTATION = "Consulter une consultation";
    public static final String ROLE_IMPRIMER_CONSULTATION = "Imprimer une consultation";
    
    public static final String ROLE_CREER_CONSULTATION_CLE = "consultation:ajout";
    public static final String ROLE_MODIFIER_CONSULTATION_CLE = "consultation:modification";
    public static final String ROLE_CONSULTER_CONSULTATION_CLE = "consultation:consultation";
    public static final String ROLE_IMPRIMER_CONSULTATION_CLE = "consultation:impression";
      
    
    public static final String ROLE_GESTION_SECURITE = "Gestion de la sécurité";
    public static final String ROLE_GESTION_SECURITE_CLE = "securite:*";
    
   
    public static final String MOT_DE_PASSE_DEFAUT = "gfweb2017";
    
    public static final String UTILISATEUR_BD = "gf";
    
    public static final String MOT_DE_PASSE_BD = "gf";
    
    public static final String HOSTNAME = "localhost";
    
    public static final String MOOZISMS_API_KEY = "vLaR6iXDoLrvSPog";
    
    public static final String MOOZISMS_API_SECRET = "fbb2c6a0-5533-11e7-806e-cfdc8033ccaa";
    
    public static final String ENTETE_MESSAGE = "HPTL NOTSE";

    public constante() {
    }
   
}
