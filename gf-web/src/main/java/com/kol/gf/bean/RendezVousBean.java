/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.Patient_intervenantid;
import com.kol.gf.entities.RendezVous;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.UserTransaction;

/**
 *
 * @author kol
 */
@ManagedBean(name = "rdvbean")
@ViewScoped
public class RendezVousBean implements Serializable {

    private RendezVous rdv;
    private Patient patient;
    private Date dateRdv;
    private boolean ajout;
    private Utilisateur utilisateur;
    private Intervenant intervenant;
    private Patient_intervenantid id;
    private List<Patient> listePatient;
    private List<RendezVous> listeRdv;

    @ManagedProperty(value = "#{connexionManagedBean}")
    private ConnexionManagedBean connexionMngdB;

    @EJB
    private RendezVousServiceBeanLocal daoRdv;

    @EJB
    private PatientServiceBeanLocal patientServices;
    
    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;
    
    @EJB
    private IntervenantDaoBeanLocal intervenantServices;

    public RendezVousBean() {
        
        utilisateur = new Utilisateur();
        intervenant = new Intervenant();
        rdv = new RendezVous();
        patient = new Patient();
        listePatient = new ArrayList<Patient>();
        listeRdv = new ArrayList<RendezVous>();
        id = new Patient_intervenantid();
        dateRdv = null;
        ajout = true;

    }

    /**
     * @return the rdv
     */
    public void gestionRdv() {
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            if (patient == null) {
                Mtm.mikiMessageWarnSelectionner("le patient");
            } else if (dateRdv == null) {
                Mtm.mikiMessageWarnSaisir("la date du rendez-vous");
            } else {
                if (ajout) {
                    tx.begin();
                    id.setId_patient(patient.getId());

                } else {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOneRdv(Patient_intervenantid idRDV) {

        try {
            this.daoRdv.deleteOne(idRDV);

        } catch (Exception e) {
        }

    }

    public List<RendezVous> getAllRdv() {

        listeRdv = daoRdv.getAll("dateRdv", false);
        return listeRdv;
    }

    public List<RendezVous> getRdvFiltreIntervenant() {
        Utilisateur utilisateurTampon = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
        Intervenant intervenantTampon = intervenantServices.getOneBy("utilisateur", utilisateurTampon);
        
        if(intervenantTampon == null){
            listeRdv = daoRdv.getAll("dateRdv", false);
        }else{
            listeRdv = daoRdv.getBy("intervenant", intervenantTampon);
        }
        
        return listeRdv;
        
    }

    public RendezVous getRdv() {
        return rdv;
    }

    public void setRdv(RendezVous rdv) {
        this.rdv = rdv;
    }

    public Patient_intervenantid getId() {
        return id;
    }

    public void setId(Patient_intervenantid id) {
        this.id = id;
    }

    public List<Patient> getListePatient() {
        return patientServices.getAll("nomPatient", true);
    }

    public void setListePatient(List<Patient> listePatient) {
        this.listePatient = listePatient;
    }

    public List<RendezVous> getListeRdv() {
        return listeRdv;
    }

    public void setListeRdv(List<RendezVous> listeRdv) {
        this.listeRdv = listeRdv;
    }

    public RendezVousServiceBeanLocal getDaoRdv() {
        return daoRdv;
    }

    public void setDaoRdv(RendezVousServiceBeanLocal daoRdv) {
        this.daoRdv = daoRdv;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientServiceBeanLocal getPatientServices() {
        return patientServices;
    }

    public void setPatientServices(PatientServiceBeanLocal patientServices) {
        this.patientServices = patientServices;
    }

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }

    public boolean isAjout() {
        return ajout;
    }

    public void setAjout(boolean ajout) {
        this.ajout = ajout;
    }

    public ConnexionManagedBean getConnexionMngdB() {
        return connexionMngdB;
    }

    public void setConnexionMngdB(ConnexionManagedBean connexionMngdB) {
        this.connexionMngdB = connexionMngdB;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    public IntervenantDaoBeanLocal getIntervenantServices() {
        return intervenantServices;
    }

    public void setIntervenantServices(IntervenantDaoBeanLocal intervenantServices) {
        this.intervenantServices = intervenantServices;
    }
    
    

}
