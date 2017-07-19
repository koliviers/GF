/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Patient;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import net.moozisms.api.client.Moozisms;
import net.moozisms.api.client.MoozismsApiClient;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@ViewScoped

public class MessageManagedBean implements Serializable {

    private Patient patient;

    private String messageText;

    private List<Patient> patientListe;

    @ManagedProperty(value = "#{connexionManagedBean}")
    private ConnexionManagedBean connexionMngdB;

    @EJB
    private PatientServiceBeanLocal patientServices;

    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;

    public MessageManagedBean() {
        patient = new Patient();
        patientListe = new ArrayList<>();
    }

    public void message() {
        if (patient == null) {
            Mtm.mikiMessageWarnSelectionner("le patient");
        } else {
            if (!patient.getContact().trim().isEmpty()) {
                if (messageText.trim().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le message");
                } else {
                    MoozismsApiClient apisms = new Moozisms();
                    boolean test = false;
                    Utilisateur utilisateurTampon = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
                    test = apisms.sendSimple("vLaR6iXDoLrvSPog", "fbb2c6a0-5533-11e7-806e-cfdc8033ccaa", "228" + patient.getContact(), "HPTL NOTSE", messageText + "\n" + utilisateurTampon.getNomComplet());

                    if (test) {
                        Mtm.mikiMessageInfoPerso("Message envoyé !");
                    } else {
                        Mtm.mikiMessageErrorPerso("Message non envoyé !");
                    }
                }
            } else {
                Mtm.mikiMessageInfoPerso("Le patient selectionné n'a pas de contact !");
            }
        }

        messageText = null;
        patient = new Patient();
    }

    public void annulerMessage() {
        messageText = null;
        patient = new Patient();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Patient> getPatientListe() {
        return patientServices.getAll("nomPatient", true);
    }

    public void setPatientListe(List<Patient> patientListe) {
        this.patientListe = patientListe;
    }

    public PatientServiceBeanLocal getPatientServices() {
        return patientServices;
    }

    public void setPatientServices(PatientServiceBeanLocal patientServices) {
        this.patientServices = patientServices;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public ConnexionManagedBean getConnexionMngdB() {
        return connexionMngdB;
    }

    public void setConnexionMngdB(ConnexionManagedBean connexionMngdB) {
        this.connexionMngdB = connexionMngdB;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

}
