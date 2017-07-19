/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.ConsommationDaoBeanLocal;
import com.kol.gf.dao.bean.ConsultationDaoBeanLocal;
import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.dao.bean.TraitementDaoBeanLocal;
import com.kol.gf.dao.bean.TypeConsommationDaoBeanLocal;
import com.kol.gf.entities.Antecedent_familial;
import com.kol.gf.entities.Antecedent_familial_Id;
import com.kol.gf.entities.ClasseTherapeutique;
import com.kol.gf.entities.Consommation;
import com.kol.gf.entities.Consultation;
import com.kol.gf.entities.Diagnostique;
import com.kol.gf.entities.ExamenClinique;
import com.kol.gf.entities.ExamenParaclinique;
import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Ordonnance;
import com.kol.gf.entities.ParacliniqueConsultation;
import com.kol.gf.entities.ParacliniqueConsultationId;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.RendezVous;
import com.kol.gf.entities.Traitement;
import com.kol.gf.entities.TypeConsommation;
import com.kol.gf.entities.TypeHabitude;
import com.kol.gf.service.Antecedent_FamilialSessionBeanLocal;
import com.kol.gf.service.ClasseTheurapetiqueServiceBeanLocal;
import com.kol.gf.service.DiagnostiqueSessionBeanLocal;
import com.kol.gf.service.ExamenCliniqueSessionBeanLocal;
import com.kol.gf.service.ExamenParacliniqueSessionBeanLocal;
import com.kol.gf.service.Habitude_alimentaireServiceBeanLocal;
import com.kol.gf.service.OrdonnanceSessionBeanLocal;
import com.kol.gf.service.ParacliniqueConsultationSessionBeanLocal;
import com.kol.gf.service.PathologieServiceBeanLocal;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import com.kol.gf.service.TypeHabitudeServiceBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.ManipulationDate;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import net.moozisms.api.client.Moozisms;
import net.moozisms.api.client.MoozismsApiClient;

/**
 *
 * @author kol
 */
@ManagedBean(name = "consBean")
@ViewScoped
public class ConsultationBean implements Serializable {

    private Pathologie pathologie;

    private Patient patient;

    private boolean disable;

    private RendezVous rdv;

    private RendezVous rdvTampon;

    private Consultation consultation;

    private Ordonnance ordonnance;

    private Consommation consommation;

    private TypeConsommation typecons;

    private TypeHabitude typeHabitude;

    private Utilisateur utilisateurTampon2;

    private Intervenant intervenantTampon2;

    private ExamenClinique examentClinique;

    private ExamenParaclinique examenParaclinique;

    private Diagnostique diagnostique;

    private ParacliniqueConsultation paracliniqueConsultation;

    private ParacliniqueConsultationId paraconsultationId;

    private Integer nombre;

    private String tempsHabitude;

    private List<Patient> listePatient;

    private List<Pathologie> listePathologie;

    private List<Pathologie> listePathologieTampon;

    private List<Consommation> listeConsommation;

    private List<TypeHabitude> listeTypeHabitude;

    private List<Ordonnance> ordonnanceListe;

    private List<Traitement> traitementListe;

    private List<Intervenant> intervenantListe;

    private List<Consultation> consultationListe;

    private List<Antecedent_familial> antecedentFamilialListe;

    private List<Diagnostique> diagnostiqueListe;

    private List<ExamenParaclinique> examenParacliniqueListe;

    private List<ExamenParaclinique> examenParacliniqueListe2;

    private List<ParacliniqueConsultation> paracliniqueConsultationTamponListe;

    private List<ClasseTherapeutique> classeTherapeutiqueListe;

    private List<Habitude_alimentaire> tamponHabitudeAlimentaire;
    private List<Habitude_alimentaire> tamponHabitudeAlimentaireTemporaire;

    private List<TypeConsommation> liteType;

    @ManagedProperty(value = "#{connexionManagedBean}")
    private ConnexionManagedBean connexionMngdB;

    @EJB
    private PatientServiceBeanLocal patientServices;

    @EJB
    private PathologieServiceBeanLocal pathologieServices;

    @EJB
    private ConsommationDaoBeanLocal consommationServices;

    @EJB
    private TypeConsommationDaoBeanLocal typeconsommationServices;

    @EJB
    private TypeHabitudeServiceBeanLocal typeHabitudeServices;

    @EJB
    private Habitude_alimentaireServiceBeanLocal habitudeAlimentaireServices;

    @EJB
    private OrdonnanceSessionBeanLocal ordonnanceServices;

    @EJB
    private TraitementDaoBeanLocal traitementServices;

    @EJB
    private ConsultationDaoBeanLocal consultationServices;

    @EJB
    private IntervenantDaoBeanLocal intervenantServices;

    @EJB
    private Antecedent_FamilialSessionBeanLocal antecedentFamilialServices;

    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;

    @EJB
    private RendezVousServiceBeanLocal rdvServices;

    @EJB
    private DiagnostiqueSessionBeanLocal diagnostiqueServices;

    @EJB
    private ExamenCliniqueSessionBeanLocal examenCliniqueServices;

    @EJB
    private ExamenParacliniqueSessionBeanLocal examentParacliniqueServices;

    @EJB
    private ParacliniqueConsultationSessionBeanLocal paracliniqueConsultationServices;

    @EJB
    private ClasseTheurapetiqueServiceBeanLocal classeTherapeutiqueServices;

    public ConsultationBean() {

        pathologie = new Pathologie();
        consommation = new Consommation();
        rdv = new RendezVous();
        rdvTampon = new RendezVous();
        patient = new Patient();

        listePatient = new ArrayList<>();
        listePathologie = new ArrayList<>();
        listeConsommation = new ArrayList<>();
        typecons = new TypeConsommation();
        liteType = new ArrayList<>();
        listeTypeHabitude = new ArrayList<>();
        tamponHabitudeAlimentaire = new ArrayList<>();
        tamponHabitudeAlimentaireTemporaire = new ArrayList<>();
        traitementListe = new ArrayList<>();
        ordonnanceListe = new ArrayList<>();
        antecedentFamilialListe = new ArrayList<>();
        intervenantListe = new ArrayList<>();
        consultationListe = new ArrayList<>();
        listePathologieTampon = new ArrayList<>();
        diagnostiqueListe = new ArrayList<>();
        examenParacliniqueListe = new ArrayList<>();
        examenParacliniqueListe2 = new ArrayList<>();
        paracliniqueConsultationTamponListe = new ArrayList<>();
        classeTherapeutiqueListe = new ArrayList<>();

        consultation = new Consultation();
        ordonnance = new Ordonnance();
        utilisateurTampon2 = new Utilisateur();
        intervenantTampon2 = new Intervenant();

        examentClinique = new ExamenClinique();
        examenParaclinique = new ExamenParaclinique();
        diagnostique = new Diagnostique();
        paracliniqueConsultation = new ParacliniqueConsultation();
        paraconsultationId = new ParacliniqueConsultationId();

        disable = false;

    }

    @PostConstruct
    public void init() {
        utilisateurTampon2 = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
        intervenantTampon2 = intervenantServices.getOneBy("utilisateur", utilisateurTampon2);

        if (intervenantTampon2 == null) {
            Mtm.mikiMessageWarn("Vous ne pouvez pas effectuer d'enregistrement ni de modification !");
        } else {
            consultation.setIntervenant(intervenantTampon2);
        }
    }

    public void gestionConsultation() {
        if ((EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE)) && intervenantTampon2 != null) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                if (consultation.getPatient() == null) {
                    Mtm.mikiMessageWarnSelectionner("le patient");
                } else if (consultation.getIntervenant() == null) {
                    Mtm.mikiMessageWarnSelectionner("l'intervenant du patient");
                } else {
                    if (consultation.getId() == null) {
                        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE)) {

                            tx.begin();
                            examenCliniqueServices.saveOne(examentClinique);
                            tx.commit();

                            tx.begin();
                            consultation.setDateConsultation(new Date());
                            consultation.setExamen_Clinique(examentClinique);
                            consultationServices.saveOne(consultation);
                            tx.commit();

                            if (!diagnostiqueListe.isEmpty()) {
                                for (Diagnostique diag : diagnostiqueListe) {
                                    tx.begin();
                                    diag.setConsultation(consultation);
                                    diagnostiqueServices.saveOne(diag);
                                    tx.commit();
                                }
                            }

                            if (!examenParacliniqueListe.isEmpty()) {
                                for (ExamenParaclinique examP : examenParacliniqueListe) {
                                    tx.begin();
                                    ParacliniqueConsultationId idParaCl = new ParacliniqueConsultationId();
                                    ParacliniqueConsultation paraCl = new ParacliniqueConsultation();

                                    idParaCl.setId_consultation(consultation.getId());
                                    idParaCl.setId_examenParaclinique(examP.getId());

                                    paraCl.setId(idParaCl);
                                    paraCl.setConsultation(consultation);
                                    paraCl.setExamen(examP);

                                    paracliniqueConsultationServices.saveOne(paraCl);
                                    tx.commit();
                                }
                            }

                            if (rdv.getDateRdv() != null) {
                                tx.begin();
                                rdv.setIntervenant(consultation.getIntervenant());
                                rdv.setDateRdvFiltre(rdv.getDateRdv());
                                rdv.setPatient(consultation.getPatient());
                                rdvServices.saveOne(rdv);
                                tx.commit();

                                if (!rdv.getPatient().getContact().trim().isEmpty()) {
                                    MoozismsApiClient apisms = new Moozisms();
                                    boolean test = false;
                                    test = apisms.sendSimple("vLaR6iXDoLrvSPog", "fbb2c6a0-5533-11e7-806e-cfdc8033ccaa", "228" + rdv.getPatient().getContact(), "HPTL NOTSE", "Rendez-vous pris pour"
                                            + " le " + ManipulationDate.mediumDateFormatFR(rdv.getDateRdv()) + "\n" + rdv.getIntervenant().getNomIntervenant() + " " + rdv.getIntervenant().getPrenomIntervenant());
                                    if (test) {
                                        Mtm.mikiMessageInfoPerso("Message envoyé sur le mobile du patient !");
                                    }
                                }

                            }

                            for (Pathologie pth : listePathologieTampon) {
                                tx.begin();

                                Antecedent_familial_Id idAF = new Antecedent_familial_Id();
                                Antecedent_familial Af = new Antecedent_familial();

                                idAF.setId_pathologie(pth.getId());
                                idAF.setId_patient(consultation.getPatient().getId());

                                Af.setId(idAF);
                                Af.setPathologie(pth);
                                Af.setPatient(consultation.getPatient());

                                antecedentFamilialServices.saveOne(Af);

                                tx.commit();
                            }

                            for (Ordonnance ord : ordonnanceListe) {
                                if (ord.getId() == null) {
                                    tx.begin();

                                    ord.setConsultation(consultation);
                                    ordonnanceServices.saveOne(ord);

                                    tx.commit();
                                }
                            }

                            if (!tamponHabitudeAlimentaire.isEmpty()) {
                                for (Habitude_alimentaire bt : tamponHabitudeAlimentaire) {

                                    tx.begin();
                                    Habitude_alimentaireId idHb = new Habitude_alimentaireId();

                                    idHb.setId_Consommation(bt.getConsommation().getId());
                                    idHb.setId_type_habitude(bt.getType_habitude().getId());
                                    idHb.setId_consultation(consultation.getId());

                                    bt.setId(idHb);
                                    bt.setConsultation(consultation);

                                    habitudeAlimentaireServices.saveOne(bt);
                                    tx.commit();

                                }
                            }

                            new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement d'une consultation du patient :" + consultation.getPatient().getNomPatient() + " " + consultation.getPatient().getPrenomPatient());

                        } else {
                            Mtm.mikiLog4jMessageError();
                        }
                    } else {

                        tx.begin();
                        examenCliniqueServices.updateOne(examentClinique);
                        tx.commit();

                        tx.begin();
                        consultation.setExamen_Clinique(examentClinique);
                        consultationServices.updateOne(consultation);
                        tx.commit();

                        if (!diagnostiqueListe.isEmpty()) {
                            for (Diagnostique diag : diagnostiqueListe) {

                                if (diag.getId() == null) {
                                    tx.begin();
                                    diag.setConsultation(consultation);
                                    diagnostiqueServices.saveOne(diag);
                                    tx.commit();
                                }

                            }
                        }

                        if (!paracliniqueConsultationTamponListe.isEmpty()) {
                            for (ParacliniqueConsultation paraCl2 : paracliniqueConsultationTamponListe) {
                                tx.begin();
                                paracliniqueConsultationServices.deleteOne(paraCl2.getId());
                                tx.commit();
                            }
                        }

                        if (!examenParacliniqueListe.isEmpty()) {
                            for (ExamenParaclinique examP3 : examenParacliniqueListe) {
                                tx.begin();
                                ParacliniqueConsultationId idParaCl3 = new ParacliniqueConsultationId();
                                ParacliniqueConsultation paraCl3 = new ParacliniqueConsultation();

                                idParaCl3.setId_consultation(consultation.getId());
                                idParaCl3.setId_examenParaclinique(examP3.getId());

                                paraCl3.setId(idParaCl3);
                                paraCl3.setConsultation(consultation);
                                paraCl3.setExamen(examP3);

                                paracliniqueConsultationServices.saveOne(paraCl3);
                                tx.commit();
                            }
                        }

                        for (Antecedent_familial antC3 : antecedentFamilialListe) {

                            tx.begin();

                            antecedentFamilialServices.deleteOne(antC3.getId());

                            tx.commit();

                        }

                        for (Pathologie pth3 : listePathologieTampon) {
                            tx.begin();

                            Antecedent_familial_Id idAF3 = new Antecedent_familial_Id();
                            Antecedent_familial Af3 = new Antecedent_familial();

                            idAF3.setId_pathologie(pth3.getId());
                            idAF3.setId_patient(consultation.getPatient().getId());

                            Af3.setId(idAF3);
                            Af3.setPathologie(pth3);
                            Af3.setPatient(consultation.getPatient());

                            antecedentFamilialServices.saveOne(Af3);

                            tx.commit();
                        }

                        for (Ordonnance ord2 : ordonnanceListe) {
                            if (ord2.getId() == null) {
                                tx.begin();

                                ord2.setConsultation(consultation);
                                ordonnanceServices.saveOne(ord2);

                                tx.commit();
                            }

                        }

                        if (!tamponHabitudeAlimentaireTemporaire.isEmpty()) {
                            for (Habitude_alimentaire bt22 : tamponHabitudeAlimentaireTemporaire) {
                                System.out.println(bt22);
                                if (bt22.getId() != null) {
                                    tx.begin();
                                    habitudeAlimentaireServices.supprimerHabitudeAlimentaire(bt22.getId());
                                    tx.commit();
                                }

                            }
                        }

                        if (!tamponHabitudeAlimentaire.isEmpty()) {
                            for (Habitude_alimentaire bt3 : tamponHabitudeAlimentaire) {

                                tx.begin();
                                Habitude_alimentaireId idHb33 = new Habitude_alimentaireId();

                                idHb33.setId_Consommation(bt3.getConsommation().getId());
                                idHb33.setId_type_habitude(bt3.getType_habitude().getId());
                                idHb33.setId_consultation(consultation.getId());

                                bt3.setId(idHb33);
                                bt3.setConsultation(consultation);

                                habitudeAlimentaireServices.saveOne(bt3);
                                tx.commit();
                            }
                        }

                    }

                    Mtm.mikiMessageInfo();
                    new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.INFO, "Modification effectuée sur la consultation du patient :" + consultation.getPatient().getNomPatient() + " " + consultation.getPatient().getPrenomPatient());

                    this.annulerConsultation();
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(PatientBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors d'une opération sur le patient :" + consultation.getPatient().getNomPatient() + " " + consultation.getPatient().getPrenomPatient());
                Mtm.mikiMessageError();
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void renvoieConsultation(Consultation consul) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE) && intervenantTampon2 != null) {
            try {

                consultation = consul;
                examentClinique = consul.getExamen_Clinique();
                patient = consul.getPatient();

                //Recuperation Habitude alimentaire
                List<Habitude_alimentaire> habitude_Al = habitudeAlimentaireServices.getBy("consultation", consul);

                System.out.println("Habitude_Al :" + habitude_Al);
                tamponHabitudeAlimentaire = habitude_Al;
                tamponHabitudeAlimentaireTemporaire = habitude_Al;

                //Recuperation Antecedent familial
                antecedentFamilialListe = antecedentFamilialServices.getBy("patient", consul.getPatient());
                List<Pathologie> pathologieRecup = new ArrayList<>();

                for (Antecedent_familial antC : antecedentFamilialListe) {
                    pathologieRecup.add(antC.getPathologie());
                }

                listePathologieTampon = pathologieRecup;

                //Recuperation de l'ordonnance
                ordonnanceListe = ordonnanceServices.getBy("consultation", consul);

                //Disable du rdv
                disable = true;

                //Recuperation du Diagnostique
                diagnostiqueListe = diagnostiqueServices.getBy("consultation", consul);

                //Recuperation du paracliniqueConsultation
                paracliniqueConsultationTamponListe = paracliniqueConsultationServices.getBy("consultation", consul);

                for (ParacliniqueConsultation pr : paracliniqueConsultationTamponListe) {
                    examenParacliniqueListe.add(pr.getExamen());
                }

            } catch (Exception e) {
                e.printStackTrace();
                Mtm.mikiMessageError();
            }

        } else {
            Mtm.mikiMessageErrorPerso("Opération interrompue :vous n'etes pas un intervenant donc vous ne pouvez pas effectuer cette opération !");
        }

    }

    public void annulerConsultation() {
        pathologie = new Pathologie();
        consommation = new Consommation();
        patient = new Patient();

        typecons = new TypeConsommation();
        listeTypeHabitude = new ArrayList<>();
        tamponHabitudeAlimentaire = new ArrayList<>();
        tamponHabitudeAlimentaireTemporaire = new ArrayList<>();
        ordonnanceListe = new ArrayList<>();
        antecedentFamilialListe = new ArrayList<>();

        consultation = new Consultation();
        ordonnance = new Ordonnance();
        rdv = new RendezVous();
        rdvTampon = new RendezVous();

        consultation.setIntervenant(intervenantTampon2);

        examentClinique = new ExamenClinique();
        examenParaclinique = new ExamenParaclinique();
        diagnostique = new Diagnostique();
        paracliniqueConsultation = new ParacliniqueConsultation();
        paraconsultationId = new ParacliniqueConsultationId();

        diagnostiqueListe = new ArrayList<>();
        examenParacliniqueListe = new ArrayList<>();
        paracliniqueConsultationTamponListe = new ArrayList<>();

        disable = false;
    }

    public void listenerPatient() {
        if (consultation.getPatient() == null) {
            patient = new Patient();
        } else {
            patient = consultation.getPatient();
        }
    }

    public void ajouterHabitudeAlimentaire() {
        if (typecons == null) {
            Mtm.mikiMessageWarnSelectionner("le type consommation");
        } else if (consommation == null) {
            Mtm.mikiMessageWarnSelectionner("la consommation");
        } else if (typeHabitude == null) {
            Mtm.mikiMessageWarnSelectionner("le type habitude");
        } else {
            Habitude_alimentaire newHabT = new Habitude_alimentaire();

            newHabT.setConsommation(consommation);
            newHabT.setType_habitude(typeHabitude);
            newHabT.setTemps(tempsHabitude);
            newHabT.setQuantite(nombre);

            if (!tamponHabitudeAlimentaire.contains(newHabT)) {
                tamponHabitudeAlimentaire.add(newHabT);
                typecons = new TypeConsommation();
                consommation = new Consommation();
                typeHabitude = new TypeHabitude();
                nombre = 0;
                tempsHabitude = "";
            }
        }

    }

    public void ajouterDiagnostique() {
        if (diagnostique != null && !diagnostiqueListe.contains(diagnostique)) {
            diagnostiqueListe.add(diagnostique);
            diagnostique = new Diagnostique();
        }
    }

    public void supprimerDiagnostique(Diagnostique diagn) {
        if (diagn.getId() == null) {
            diagnostiqueListe.remove(diagn);
        } else {
            diagnostiqueServices.deleteOne(diagn.getId());
        }
    }

    public List<Consultation> getConsulationFiltrerIntervenant() {
        Utilisateur utilisateurTampon = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
        Intervenant intervenantTampon = intervenantServices.getOneBy("utilisateur", utilisateurTampon);
        List<Consultation> consultationListeTampon = new ArrayList<>();

        if (intervenantTampon == null) {
            consultationListeTampon = consultationServices.getAll("dateConsultation", false);
        } else {
            consultationListeTampon = consultationServices.getBy("intervenant", intervenantTampon).stream()
                    .sorted(Comparator.comparing(Consultation::getDateConsultation).reversed())
                    .collect(Collectors.toList());
        }

        return consultationListeTampon;

    }

    public void supprimerHabitudeAlimentaire(Habitude_alimentaire tampon) {
        try {
            if (tampon.getId() == null) {
                tamponHabitudeAlimentaire.remove(tampon);
            } else {
                habitudeAlimentaireServices.supprimerHabitudeAlimentaire(tampon.getId());
                tamponHabitudeAlimentaireTemporaire.remove(tampon);
            }
        } catch (Exception e) {
            Mtm.mikiMessageError();
        }

    }

    public void ajouterAntecedentFamilial() {
        try {
            if (pathologie != null && !listePathologieTampon.contains(pathologie)) {
                listePathologieTampon.add(pathologie);
                pathologie = new Pathologie();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Mtm.mikiMessageError();
        }

    }

    public void supprimerAntecedentFamilial(Pathologie pathlg) {
        listePathologieTampon.remove(pathlg);
    }

    public void ajouterOrdonnance() {
        ordonnanceListe.add(ordonnance);
        ordonnance = new Ordonnance();
    }

    public void supprimerOrdonnance(Ordonnance ordo) {
        if (ordo.getId() == null) {
            ordonnanceListe.remove(ordo);
        } else {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {

                tx.begin();

                ordonnanceServices.deleteOne(ordo.getId());

                tx.commit();

                ordonnanceListe.remove(ordo);

            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(PatientBean.class.getName()).log(Level.SEVERE, null, ex1);
                }

                Mtm.mikiMessageError();
            }
        }
    }

    /**
     * @return the pathologie
     */
    public Pathologie getPathologie() {
        return pathologie;
    }

    /**
     * @param pathologie the pathologie to set
     */
    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    /**
     * @return the consommation
     */
    public Consommation getConsommation() {
        return consommation;
    }

    /**
     * @param consommation the consommation to set
     */
    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

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

    /**
     * @return the listePathologie
     */
    public List<Pathologie> getListePathologie() {
        return pathologieServices.getAll("nomPathologie", true);
    }

    /**
     * @param listePathologie the listePathologie to set
     */
    public void setListePathologie(List<Pathologie> listePathologie) {
        this.listePathologie = listePathologie;
    }

    /**
     * @return the listeConsommation
     */
    public List<Consommation> getListeConsommation() {
        return consommationServices.getBy("type_consommation", typecons);
    }

    /**
     * @param listeConsommation the listeConsommation to set
     */
    public void setListeConsommation(List<Consommation> listeConsommation) {
        this.listeConsommation = listeConsommation;
    }

    public PatientServiceBeanLocal getPatientServices() {
        return patientServices;
    }

    public void setPatientServices(PatientServiceBeanLocal patientServices) {
        this.patientServices = patientServices;
    }

    public PathologieServiceBeanLocal getPathologieServices() {
        return pathologieServices;
    }

    public void setPathologieServices(PathologieServiceBeanLocal pathologieServices) {
        this.pathologieServices = pathologieServices;
    }

    public ConsommationDaoBeanLocal getConsommationServices() {
        return consommationServices;
    }

    public void setConsommationServices(ConsommationDaoBeanLocal consommationServices) {
        this.consommationServices = consommationServices;
    }

    public TypeConsommationDaoBeanLocal getTypeconsommationServices() {
        return typeconsommationServices;
    }

    public void setTypeconsommationServices(TypeConsommationDaoBeanLocal typeconsommationServices) {
        this.typeconsommationServices = typeconsommationServices;
    }

    /**
     * @return the typecons
     */
    public TypeConsommation getTypecons() {
        return typecons;
    }

    /**
     * @param typecons the typecons to set
     */
    public void setTypecons(TypeConsommation typecons) {
        this.typecons = typecons;
    }

    /**
     * @return the daotype
     */
    public List<TypeConsommation> getLiteType() {
        return typeconsommationServices.getAll("label", true);
    }

    public void setLiteType(List<TypeConsommation> liteType) {
        this.liteType = liteType;
    }

    public TypeHabitudeServiceBeanLocal getTypeHabitudeServices() {
        return typeHabitudeServices;
    }

    public void setTypeHabitudeServices(TypeHabitudeServiceBeanLocal typeHabitudeServices) {
        this.typeHabitudeServices = typeHabitudeServices;
    }

    public List<TypeHabitude> getListeTypeHabitude() {
        return typeHabitudeServices.getBy("type_consommation", typecons);
    }

    public void setListeTypeHabitude(List<TypeHabitude> listeTypeHabitude) {
        this.listeTypeHabitude = listeTypeHabitude;
    }

    public TypeHabitude getTypeHabitude() {
        return typeHabitude;
    }

    public void setTypeHabitude(TypeHabitude typeHabitude) {
        this.typeHabitude = typeHabitude;
    }

    public Habitude_alimentaireServiceBeanLocal getHabitudeAlimentaireServices() {
        return habitudeAlimentaireServices;
    }

    public void setHabitudeAlimentaireServices(Habitude_alimentaireServiceBeanLocal habitudeAlimentaireServices) {
        this.habitudeAlimentaireServices = habitudeAlimentaireServices;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }

    public OrdonnanceSessionBeanLocal getOrdonnanceServices() {
        return ordonnanceServices;
    }

    public void setOrdonnanceServices(OrdonnanceSessionBeanLocal ordonnanceServices) {
        this.ordonnanceServices = ordonnanceServices;
    }

    public TraitementDaoBeanLocal getTraitementServices() {
        return traitementServices;
    }

    public void setTraitementServices(TraitementDaoBeanLocal traitementServices) {
        this.traitementServices = traitementServices;
    }

    public ConsultationDaoBeanLocal getConsultationServices() {
        return consultationServices;
    }

    public void setConsultationServices(ConsultationDaoBeanLocal consultationServices) {
        this.consultationServices = consultationServices;
    }

    public IntervenantDaoBeanLocal getIntervenantServices() {
        return intervenantServices;
    }

    public void setIntervenantServices(IntervenantDaoBeanLocal intervenantServices) {
        this.intervenantServices = intervenantServices;
    }

    public List<Ordonnance> getOrdonnanceListe() {
        return ordonnanceListe;
    }

    public void setOrdonnanceListe(List<Ordonnance> ordonnanceListe) {
        this.ordonnanceListe = ordonnanceListe;
    }

    public List<Traitement> getTraitementListe() {
        return traitementServices.getAll("label", true);
    }

    public void setTraitementListe(List<Traitement> traitementListe) {
        this.traitementListe = traitementListe;
    }

    public List<Pathologie> getListePathologieTampon() {
        return listePathologieTampon;
    }

    public void setListePathologieTampon(List<Pathologie> listePathologieTampon) {
        this.listePathologieTampon = listePathologieTampon;
    }

    public Antecedent_FamilialSessionBeanLocal getAntecedentFamilialServices() {
        return antecedentFamilialServices;
    }

    public void setAntecedentFamilialServices(Antecedent_FamilialSessionBeanLocal antecedentFamilialServices) {
        this.antecedentFamilialServices = antecedentFamilialServices;
    }

    public List<Antecedent_familial> getAntecedentFamilialListe() {
        return antecedentFamilialListe;
    }

    public void setAntecedentFamilialListe(List<Antecedent_familial> antecedentFamilialListe) {
        this.antecedentFamilialListe = antecedentFamilialListe;
    }

    public List<Intervenant> getIntervenantListe() {
        return intervenantServices.getAll("nomIntervenant", true);
    }

    public void setIntervenantListe(List<Intervenant> intervenantListe) {
        this.intervenantListe = intervenantListe;
    }

    public ConnexionManagedBean getConnexionMngdB() {
        return connexionMngdB;
    }

    public void setConnexionMngdB(ConnexionManagedBean connexionMngdB) {
        this.connexionMngdB = connexionMngdB;
    }

    public List<Consultation> getConsultationListe() {
        return consultationListe;
    }

    public void setConsultationListe(List<Consultation> consultationListe) {
        this.consultationListe = consultationListe;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    public Utilisateur getUtilisateurTampon2() {
        return utilisateurTampon2;
    }

    public void setUtilisateurTampon2(Utilisateur utilisateurTampon2) {
        this.utilisateurTampon2 = utilisateurTampon2;
    }

    public Intervenant getIntervenantTampon2() {
        return intervenantTampon2;
    }

    public void setIntervenantTampon2(Intervenant intervenantTampon2) {
        this.intervenantTampon2 = intervenantTampon2;
    }

    public RendezVous getRdv() {
        return rdv;
    }

    public void setRdv(RendezVous rdv) {
        this.rdv = rdv;
    }

    public RendezVous getRdvTampon() {
        return rdvTampon;
    }

    public void setRdvTampon(RendezVous rdvTampon) {
        this.rdvTampon = rdvTampon;
    }

    public RendezVousServiceBeanLocal getRdvServices() {
        return rdvServices;
    }

    public void setRdvServices(RendezVousServiceBeanLocal rdvServices) {
        this.rdvServices = rdvServices;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public ExamenClinique getExamentClinique() {
        return examentClinique;
    }

    public void setExamentClinique(ExamenClinique examentClinique) {
        this.examentClinique = examentClinique;
    }

    public ExamenParaclinique getExamenParaclinique() {
        return examenParaclinique;
    }

    public void setExamenParaclinique(ExamenParaclinique examenParaclinique) {
        this.examenParaclinique = examenParaclinique;
    }

    public Diagnostique getDiagnostique() {
        return diagnostique;
    }

    public void setDiagnostique(Diagnostique diagnostique) {
        this.diagnostique = diagnostique;
    }

    public ParacliniqueConsultation getParacliniqueConsultation() {
        return paracliniqueConsultation;
    }

    public void setParacliniqueConsultation(ParacliniqueConsultation paracliniqueConsultation) {
        this.paracliniqueConsultation = paracliniqueConsultation;
    }

    public ParacliniqueConsultationId getParaconsultationId() {
        return paraconsultationId;
    }

    public void setParaconsultationId(ParacliniqueConsultationId paraconsultationId) {
        this.paraconsultationId = paraconsultationId;
    }

    public List<Diagnostique> getDiagnostiqueListe() {
        return diagnostiqueListe;
    }

    public void setDiagnostiqueListe(List<Diagnostique> diagnostiqueListe) {
        this.diagnostiqueListe = diagnostiqueListe;
    }

    public List<ExamenParaclinique> getExamenParacliniqueListe() {
        return examenParacliniqueListe;
    }

    public void setExamenParacliniqueListe(List<ExamenParaclinique> examenParacliniqueListe) {
        this.examenParacliniqueListe = examenParacliniqueListe;
    }

    public DiagnostiqueSessionBeanLocal getDiagnostiqueServices() {
        return diagnostiqueServices;
    }

    public void setDiagnostiqueServices(DiagnostiqueSessionBeanLocal diagnostiqueServices) {
        this.diagnostiqueServices = diagnostiqueServices;
    }

    public ExamenCliniqueSessionBeanLocal getExamenCliniqueServices() {
        return examenCliniqueServices;
    }

    public void setExamenCliniqueServices(ExamenCliniqueSessionBeanLocal examenCliniqueServices) {
        this.examenCliniqueServices = examenCliniqueServices;
    }

    public ExamenParacliniqueSessionBeanLocal getExamentParacliniqueServices() {
        return examentParacliniqueServices;
    }

    public void setExamentParacliniqueServices(ExamenParacliniqueSessionBeanLocal examentParacliniqueServices) {
        this.examentParacliniqueServices = examentParacliniqueServices;
    }

    public ParacliniqueConsultationSessionBeanLocal getParacliniqueConsultationServices() {
        return paracliniqueConsultationServices;
    }

    public void setParacliniqueConsultationServices(ParacliniqueConsultationSessionBeanLocal paracliniqueConsultationServices) {
        this.paracliniqueConsultationServices = paracliniqueConsultationServices;
    }

    public List<ParacliniqueConsultation> getParacliniqueConsultationTamponListe() {
        return paracliniqueConsultationTamponListe;
    }

    public void setParacliniqueConsultationTamponListe(List<ParacliniqueConsultation> paracliniqueConsultationTamponListe) {
        this.paracliniqueConsultationTamponListe = paracliniqueConsultationTamponListe;
    }

    public List<ExamenParaclinique> getExamenParacliniqueListe2() {
        return examentParacliniqueServices.getAll("label", true);
    }

    public void setExamenParacliniqueListe2(List<ExamenParaclinique> examenParacliniqueListe2) {
        this.examenParacliniqueListe2 = examenParacliniqueListe2;
    }

    public List<ClasseTherapeutique> getClasseTherapeutiqueListe() {
        return classeTherapeutiqueServices.getAll("label", true);
    }

    public void setClasseTherapeutiqueListe(List<ClasseTherapeutique> classeTherapeutiqueListe) {
        this.classeTherapeutiqueListe = classeTherapeutiqueListe;
    }

    public ClasseTheurapetiqueServiceBeanLocal getClasseTherapeutiqueServices() {
        return classeTherapeutiqueServices;
    }

    public void setClasseTherapeutiqueServices(ClasseTheurapetiqueServiceBeanLocal classeTherapeutiqueServices) {
        this.classeTherapeutiqueServices = classeTherapeutiqueServices;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public String getTempsHabitude() {
        return tempsHabitude;
    }

    public void setTempsHabitude(String tempsHabitude) {
        this.tempsHabitude = tempsHabitude;
    }

    public List<Habitude_alimentaire> getTamponHabitudeAlimentaire() {
        return tamponHabitudeAlimentaire;
    }

    public void setTamponHabitudeAlimentaire(List<Habitude_alimentaire> tamponHabitudeAlimentaire) {
        this.tamponHabitudeAlimentaire = tamponHabitudeAlimentaire;
    }

    public List<Habitude_alimentaire> getTamponHabitudeAlimentaireTemporaire() {
        return tamponHabitudeAlimentaireTemporaire;
    }

    public void setTamponHabitudeAlimentaireTemporaire(List<Habitude_alimentaire> tamponHabitudeAlimentaireTemporaire) {
        this.tamponHabitudeAlimentaireTemporaire = tamponHabitudeAlimentaireTemporaire;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
