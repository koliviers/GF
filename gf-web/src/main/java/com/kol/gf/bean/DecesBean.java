/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.DecesDaoBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.entities.Deces;
import com.kol.gf.entities.Patient;
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
 * @author koliviers
 */
@ManagedBean(name = "decesbean")
@ViewScoped
public class DecesBean implements Serializable {

    private Deces deces;
    private Patient patient;
    private List<Deces> listDeces;
    private List<Patient> listpatient;

    @EJB
    private DecesDaoBeanLocal daoDeces;

    @EJB
    private PatientDaoBeanLocal daopatient;

    public DecesBean() {

        deces = new Deces();

        patient = new Patient();
        listDeces = new ArrayList<>();
        listpatient = new ArrayList<>();

    }

    public void addDeces() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE)) {

            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                if (deces.getPatient() == null) {
                    Mtm.mikiMessageWarnSelectionner("le patient décédé");
                } else {
                    if (deces.getId() == null) {

                        if (daoDeces.getBy("patient", deces.getPatient()).isEmpty()) {

                            tx.begin();
                            this.daoDeces.saveOne(deces);
                            tx.commit();
                            
                            Mtm.mikiMessageInfo();
                            deces = new Deces();

                        } else {
                            Mtm.mikiMessageWarn("Ce patient a déjà été enregistré comme décédé, veuillez revoir vos informations svp !");
                        }

                    } else {
                        tx.begin();
                        this.daoDeces.updateOne(deces);
                        tx.commit();
                        Mtm.mikiMessageInfo();
                        deces = new Deces();
                    }

                    
                    
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Mtm.mikiMessageError();
            }

        } else {

            Mtm.mikiLog4jMessageError();
        }

    }

    public void renvoieDeces(Deces dc) {
        deces = dc;
    }

    public void annulerDeces() {
        deces = new Deces();
    }

    public void supprimerDeces(Deces dc) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                daoDeces.deleteOne(dc.getId());
                tx.commit();
                Mtm.mikiMessageInfoPerso("Suppression de l'information de décès éffectuée avec succès !");
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                e.printStackTrace();
                Mtm.mikiMessageError();
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    /**
     * @return the deces
     */
    public Deces getDeces() {
        return deces;
    }

    /**
     * @param deces the deces to set
     */
    public void setDeces(Deces deces) {
        this.deces = deces;
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
     * @return the listDeces
     */
    public List<Deces> getListDeces() {
        return daoDeces.getAll("datedeces", false);
    }

    /**
     * @param listDeces the listDeces to set
     */
    public void setListDeces(List<Deces> listDeces) {
        this.listDeces = listDeces;
    }

    /**
     * @return the listpatient
     */
    public List<Patient> getListpatient() {
        return daopatient.getAll("nomPatient", true);
    }

    /**
     * @param listpatient the listpatient to set
     */
    public void setListpatient(List<Patient> listpatient) {
        this.listpatient = listpatient;
    }

    /**
     * @return the daoDeces
     */
    public DecesDaoBeanLocal getDaoDeces() {
        return daoDeces;
    }

    /**
     * @param daoDeces the daoDeces to set
     */
    public void setDaoDeces(DecesDaoBeanLocal daoDeces) {
        this.daoDeces = daoDeces;
    }

    /**
     * @return the daopatient
     */
    public PatientDaoBeanLocal getDaopatient() {
        return daopatient;
    }

    /**
     * @param daopatient the daopatient to set
     */
    public void setDaopatient(PatientDaoBeanLocal daopatient) {
        this.daopatient = daopatient;
    }

}
