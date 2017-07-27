/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Patient;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author kol
 */
@ManagedBean(name = "patientBean")
@ViewScoped
public class PatientBean implements Serializable {

    private Patient patient;   

    private List<Patient> listePatient;

    

    @EJB
    private PatientServiceBeanLocal patientServices;

    

    public PatientBean() {

        patient = new Patient();
       
        listePatient = new ArrayList<>();
        
    }

    public void gestionPatient() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                if (patient.getNomPatient().trim().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le nom du patient");
                } else if (patient.getPrenomPatient().trim().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le prenom du patient");
                } else if (patient.getSexe().isEmpty()) {
                    Mtm.mikiMessageWarnSelectionner("le sexe du patient");
                } else if (patient.getAge() == 0) {
                    Mtm.mikiMessageWarnSaisir("l'age du patient");
                } else {
                    if (patient.getId() == null) {
                        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE)) {

                            tx.begin();
                            patientServices.saveOne(patient);
                            tx.commit();
                            
                            new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement du patient :" + patient.getNomPatient() + " " + patient.getPrenomPatient());
                            

                        } else {
                            Mtm.mikiLog4jMessageError();
                        }
                    } else {

                        tx.begin();
                        patientServices.updateOne(patient);
                        tx.commit();

                        new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.INFO, "Modification effectuée sur le patient :" + patient.getNomPatient() + " " + patient.getPrenomPatient());

                    }

                    Mtm.mikiMessageInfo();
                    patient = new Patient();
                    

                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(PatientBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors d'une opération sur le patient :" + patient.getNomPatient() + " " + patient.getPrenomPatient());
                Mtm.mikiMessageError();
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void renvoiePatient(Patient pat) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE)) {
           
            patient = pat;
            
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void annulerPatient() {
        patient = new Patient();
       
    }

    
    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the pathologie
     */
    

    /**
     * @return the listePatient
     */
    public List<Patient> getListePatient() {
        return patientServices.getAll("nomPatient", true);
    }

    /**
     * @param listePatient the listePatient to set
     */
    public void setListePatient(List<Patient> listePatient) {
        this.listePatient = listePatient;
    }

    public PatientServiceBeanLocal getPatientServices() {
        return patientServices;
    }

    public void setPatientServices(PatientServiceBeanLocal patientServices) {
        this.patientServices = patientServices;
    }

    
}
