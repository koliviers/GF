/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.RendezVous;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.ManipulationDate;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author kol
 */
@ManagedBean(name = "rdvbean")
@ViewScoped
public class RendezVousBean implements Serializable {

    private RendezVous rdv;
    private RendezVous rdvTampon;
    private Date dateRdv;
    private List<Patient> listePatient;
    private List<Intervenant> intervenantListe;
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

        rdv = new RendezVous();
        rdvTampon = new RendezVous();
        listePatient = new ArrayList<Patient>();
        listeRdv = new ArrayList<RendezVous>();
        dateRdv = null;

    }

    public void gestionRdv() {
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            if (rdv.getPatient() == null) {
                Mtm.mikiMessageWarnSelectionner("le patient");
            } else if (rdv.getIntervenant() == null) {
                Mtm.mikiMessageWarnSaisir("l'intervenant");
            } else if (rdv.getDateRdv() == null) {
                Mtm.mikiMessageWarnSaisir("la date du rendez-vous");
            } else if (rdv.getDateRdv().before(new Date())) {
                Mtm.mikiMessageWarn("La date ou l'heure saisie est incorrecte, veuillez entrer une date ou heure supérieure à la date ou heure actuelle svp !");
            } else {
                if (rdv.getId() == null) {
                    if (!daoRdv.getBy("dateRdv", rdv.getDateRdv()).isEmpty()) {
                        Mtm.mikiMessageWarn("Attention : vous avez déjà un autre rendez-vous à cette date et heure !, veuillez revoir la date ou l'heure saisie svp !");
                    } else {
                        tx.begin();
                        rdv.setDateRdvFiltre(rdv.getDateRdv());
                        daoRdv.saveOne(rdv);
                        tx.commit();

                        Mtm.mikiMessageInfo();
                        rdv = new RendezVous();
                    }

                } else {
                    if (!daoRdv.getBy("dateRdv", rdv.getDateRdv()).isEmpty()) {
                        if (!Objects.equals(daoRdv.getBy("dateRdv", rdv.getDateRdv()).get(0).getId(), rdv.getId())) {
                            Mtm.mikiMessageWarn("Attention : vous avez déjà un autre rendez-vous à cette date et heure !, veuillez revoir la date ou l'heure saisie svp !");
                        } else {
                            tx.begin();
                            rdv.setDateRdvFiltre(rdv.getDateRdv());
                            daoRdv.updateOne(rdv);
                            tx.commit();

                            Mtm.mikiMessageInfo();
                            rdv = new RendezVous();
                        }
                    } else {
                        tx.begin();
                        rdv.setDateRdvFiltre(rdv.getDateRdv());
                        daoRdv.updateOne(rdv);
                        tx.commit();

                        Mtm.mikiMessageInfo();
                        rdv = new RendezVous();
                    }

                }

            }

        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(RendezVous.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Mtm.mikiMessageError();
        }
    }

    public void renvoieRdv(RendezVous rdv2) {
        rdv = rdv2;
    }

    public void annulerRdv() {
        rdv = new RendezVous();
    }

    public void deleteOneRdv(Long idRDV) {

        try {
            this.daoRdv.deleteOne(idRDV);

        } catch (Exception e) {
            e.printStackTrace();
            Mtm.mikiMessageError();
        }

    }

    public void reporterSelectRdv(RendezVous rdv3) {
        rdvTampon = rdv3;
        dateRdv = rdvTampon.getDateRdv();
    }

    public void reporterRdv() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (dateRdv.before(new Date())) {
                Mtm.mikiMessageWarn("La date ou l'heure saisie est incorrecte, veuillez entrer une date ou heure supérieure à la date ou heure actuelle svp !");
            } else {
                tx.begin();
                rdvTampon.setDateRdvFiltre(dateRdv);
                rdvTampon.setDateRdv(dateRdv);
                tx.commit();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(RendezVous.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Mtm.mikiMessageError();
        }
    }

    public List<RendezVous> getAllRdv() {

        listeRdv = daoRdv.getAll("dateRdv", false);
        return listeRdv;
    }

    public List<RendezVous> getRdvFiltreIntervenant() {
        Utilisateur utilisateurTampon = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
        Intervenant intervenantTampon = intervenantServices.getOneBy("utilisateur", utilisateurTampon);

        if (intervenantTampon == null) {
            listeRdv = daoRdv.getAll("dateRdv", false);
        } else {
            List<RendezVous> rdvListSortDesc = daoRdv.getBy("intervenant", intervenantTampon);
            listeRdv = rdvListSortDesc.stream()
                    .sorted(Comparator.comparing(RendezVous::getDateRdv).reversed())
                    .collect(Collectors.toList());

        }

        return listeRdv;

    }

    public String dateFormatRdv(Date dat) {
        return ManipulationDate.mediumDateFormatFR(dat);
    }

    public List<RendezVous> getRdvFiltreIntervenantDuJour() {
        Utilisateur utilisateurTampon = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
        Intervenant intervenantTampon = intervenantServices.getOneBy("utilisateur", utilisateurTampon);

        if (intervenantTampon == null) {
            listeRdv = daoRdv.getAll().stream()
                    .filter(rd2 -> rd2.getDateRdvFiltre().equals(new Date()))
                    .sorted(Comparator.comparing(RendezVous::getDateRdv))
                    .collect(Collectors.toList());
        } else {
            List<RendezVous> rdvListSortDesc = daoRdv.getBy("intervenant", intervenantTampon);
            listeRdv = rdvListSortDesc.stream()
                    .filter(rd -> rd.getDateRdvFiltre().equals(new Date()))
                    .sorted(Comparator.comparing(RendezVous::getDateRdv))
                    .collect(Collectors.toList());

        }

        return listeRdv;

    }

    public RendezVous getRdv() {
        return rdv;
    }

    public void setRdv(RendezVous rdv) {
        this.rdv = rdv;
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

    public IntervenantDaoBeanLocal getIntervenantServices() {
        return intervenantServices;
    }

    public void setIntervenantServices(IntervenantDaoBeanLocal intervenantServices) {
        this.intervenantServices = intervenantServices;
    }

    public RendezVous getRdvTampon() {
        return rdvTampon;
    }

    public void setRdvTampon(RendezVous rdvTampon) {
        this.rdvTampon = rdvTampon;
    }

    public List<Intervenant> getIntervenantListe() {
        return intervenantServices.getAll("nomIntervenant", true);
    }

    public void setIntervenantListe(List<Intervenant> intervenantListe) {
        this.intervenantListe = intervenantListe;
    }

}
