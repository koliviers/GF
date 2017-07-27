/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.Isuivie;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.Suivie;
import com.kol.gf.service.SuiviSessionBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Convertiseur;
import com.miki.webapp.core.Utils.ManipulationDate;
import com.miki.webapp.core.Utils.Mtm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
 * @author koliviers
 */
@ManagedBean(name = "suivieBean")
@ViewScoped
public class SuivieBean implements Serializable {

    private Suivie suivie;
    private List<Patient> patientListe;
    private List<Suivie> suivieListe;

    @EJB
    private SuiviSessionBeanLocal mysuivie;

    @EJB
    private PatientDaoBeanLocal mypatient;

    public SuivieBean() {

        suivie = new Suivie();

        patientListe = new ArrayList<>();
        suivieListe = new ArrayList<>();
    }

    public void gestionSuivie() {

        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (suivie.getPatient() == null) {
                Mtm.mikiMessageWarnSelectionner("le patient");
            } else {
                if (suivie.getId() == null) {
                    tx.begin();
                    suivie.setDate_suivie(new Date());
                    mysuivie.saveOne(suivie);
                    tx.commit();
                } else {
                    tx.begin();
                    mysuivie.updateOne(suivie);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                suivie = new Suivie();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(SuivieBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(PatientBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(PatientBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }

    }
    
    public void renvoieSuivie(Suivie suivi){
        suivie = suivi;
    }
    
    public void annulerSuivie(){
        suivie = new Suivie();
    }
    
    public String dateFormatRdv(Date dat) {
        return Convertiseur.getDate(dat);
    }
    

    public Suivie getSuivie() {
        return suivie;
    }

    public List<Patient> getPatientListe() {
        return mypatient.getAll("nomPatient", true);
    }

    public List<Suivie> getSuivieListe() {
        return mysuivie.getAll("id", false);
    }

    public SuiviSessionBeanLocal getMysuivie() {
        return mysuivie;
    }

    public void setMysuivie(SuiviSessionBeanLocal mysuivie) {
        this.mysuivie = mysuivie;
    }

   

    public void setSuivie(Suivie suivie) {
        this.suivie = suivie;
    }

    public void setPatientListe(List<Patient> patientListe) {
        this.patientListe = patientListe;
    }

    public void setSuivieListe(List<Suivie> suivieListe) {
        this.suivieListe = suivieListe;
    }


    public PatientDaoBeanLocal getMypatient() {
        return mypatient;
    }

    public void setMypatient(PatientDaoBeanLocal mypatient) {
        this.mypatient = mypatient;
    }

    public void annuleSuivie() {
        suivie = new Suivie();

    }

}
