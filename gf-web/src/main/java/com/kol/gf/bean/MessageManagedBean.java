/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Parametre;
import com.kol.gf.entities.Patient;
import com.kol.gf.service.ParametreSessionBeanLocal;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import net.moozisms.api.Exception.InsufficientSmsException;
import net.moozisms.api.Exception.InvalidAccessKeyException;
import net.moozisms.api.Exception.InvalidPhoneNumberException;
import net.moozisms.api.Exception.InvalidSenderIdException;
import net.moozisms.api.Exception.MessageNotSentException;
import net.moozisms.api.Exception.NetworkNotSupportedException;
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

    private Parametre parametreSauvegarde;

    @ManagedProperty(value = "#{connexionManagedBean}")
    private ConnexionManagedBean connexionMngdB;

    @EJB
    private PatientServiceBeanLocal patientServices;

    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;

    @EJB
    private ParametreSessionBeanLocal parametreServices;

    public MessageManagedBean() {
        patient = new Patient();
        parametreSauvegarde = new Parametre();
        patientListe = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        List<Parametre> parametreListeSauvegarde = parametreServices.getAll();

        if (!parametreListeSauvegarde.isEmpty()) {
            parametreSauvegarde = parametreListeSauvegarde.get(0);
        }

    }

    public void message() {

        try {

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
                        
                        if(parametreSauvegarde.getMoozisms_ApiSecret().isEmpty() || parametreSauvegarde.getMoozisms_Apikey().isEmpty() || parametreSauvegarde.getEntete_message().isEmpty()){
                            
                            Mtm.mikiMessageErrorPerso("Veuillez configurer les paramètres de messagerie svp !");
                            
                        }else{
                            
                            test = apisms.sendSimple(parametreSauvegarde.getMoozisms_Apikey(), parametreSauvegarde.getMoozisms_ApiSecret(), "228" + patient.getContact(), parametreSauvegarde.getEntete_message(), messageText + "\n\n" + utilisateurTampon.getNomComplet());
                        }
                        
                        

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

        } catch (MessageNotSentException e) {
            System.out.println("Erreur :"+e.getMessage());
             Mtm.mikiMessageErrorPerso("Message non envoyé !");
        } catch (NetworkNotSupportedException e) {
            System.out.println("Erreur :"+e.getMessage());
             Mtm.mikiMessageErrorPerso("Le réseau de téléphonie mobile vers lequel le message est envoyé n'est pas supporté !");
        } catch (InsufficientSmsException e) {
            System.out.println("Erreur :"+e.getMessage());
            Mtm.mikiMessageErrorPerso("Le nombre de message restant est insuffisant pour envoyer le message !");
        } catch (InvalidAccessKeyException e) {
            System.out.println("Erreur :"+e.getMessage());
            Mtm.mikiMessageErrorPerso("Mauvaise configuration des paramètres de messagerie !");
        } catch (InvalidPhoneNumberException e) {
            System.out.println("Erreur :"+e.getMessage());
            Mtm.mikiMessageErrorPerso("Numero de téléphone incorrect !");
        } catch (InvalidSenderIdException e) {
            System.out.println("Erreur :"+e.getMessage());
            Mtm.mikiMessageErrorPerso("Erreur au niveau de l'entête du message, revoir les paramètres de messagerie !");
        } catch (Exception e) {
            System.out.println("Erreur :"+e.getMessage());
            e.printStackTrace();
            Mtm.mikiMessageErrorPerso("Message non envoyé !");
            
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

    public ParametreSessionBeanLocal getParametreServices() {
        return parametreServices;
    }

    public void setParametreServices(ParametreSessionBeanLocal parametreServices) {
        this.parametreServices = parametreServices;
    }

    public Parametre getParametreSauvegarde() {
        return parametreSauvegarde;
    }

    public void setParametreSauvegarde(Parametre parametreSauvegarde) {
        this.parametreSauvegarde = parametreSauvegarde;
    }
    
    

}
