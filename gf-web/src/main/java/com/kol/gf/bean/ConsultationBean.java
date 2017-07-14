/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.ConsommationDaoBeanLocal;
import com.kol.gf.dao.bean.ConsultationDaoBeanLocal;
import com.kol.gf.dao.bean.Habitude_alimentaireDaoBeanLocal;
import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.dao.bean.PathologieDaoBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.dao.bean.TraitementDaoBeanLocal;
import com.kol.gf.dao.bean.TypeConsommationDaoBeanLocal;
import com.kol.gf.entities.Antecedent_familial;
import com.kol.gf.entities.Antecedent_familial_Id;
import com.kol.gf.entities.Consommation;
import com.kol.gf.entities.Consultation;
import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Ordonnance;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.Patient_intervenantid;
import com.kol.gf.entities.RendezVous;
import com.kol.gf.entities.Suivi;
import com.kol.gf.entities.Traitement;
import com.kol.gf.entities.TypeConsommation;
import com.kol.gf.entities.TypeHabitude;
import com.kol.gf.service.Antecedent_FamilialSessionBeanLocal;
import com.kol.gf.service.OrdonnanceSessionBeanLocal;
import com.kol.gf.service.PathologieServiceBeanLocal;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import com.kol.gf.service.SuiviSessionBeanLocal;
import com.kol.gf.service.TypeHabitudeServiceBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@ManagedBean(name = "consBean")
@ViewScoped
public class ConsultationBean implements Serializable {

    private Pathologie pathologie;

    private Suivi suivi;

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

    private List<Patient> listePatient;

    private List<Pathologie> listePathologie;

    private List<Pathologie> listePathologieTampon;

    private List<Consommation> listeConsommation;

    private List<TypeHabitude> listeTypeHabitude;

    private List<Ordonnance> ordonnanceListe;

    private List<Traitement> traitementListe;

    private List<Intervenant> intervenantListe;

    private List<Consultation> consultationListe;

    private List<Antecedent_familial> antecedentFamilialListe = new ArrayList<>();

    private Map<Consommation, TypeHabitude> tamponHabitudeAlimentaire;
    private Map<Consommation, TypeHabitude> tamponHabitudeAlimentaireTemporaire;

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
    private Habitude_alimentaireDaoBeanLocal habitudeAlimentaireServices;

    @EJB
    private SuiviSessionBeanLocal suiviServices;

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

    public ConsultationBean() {

        pathologie = new Pathologie();
        consommation = new Consommation();
        rdv = new RendezVous();
        rdvTampon = new RendezVous();

        listePatient = new ArrayList<>();
        listePathologie = new ArrayList<>();
        listeConsommation = new ArrayList<>();
        typecons = new TypeConsommation();
        liteType = new ArrayList<>();
        listeTypeHabitude = new ArrayList<>();
        tamponHabitudeAlimentaire = new LinkedHashMap<>();
        tamponHabitudeAlimentaireTemporaire = new LinkedHashMap<>();
        traitementListe = new ArrayList<>();
        ordonnanceListe = new ArrayList<>();
        antecedentFamilialListe = new ArrayList<>();
        intervenantListe = new ArrayList<>();
        consultationListe = new ArrayList<>();
        listePathologieTampon = new ArrayList<>();

        suivi = new Suivi();
        consultation = new Consultation();
        ordonnance = new Ordonnance();
        utilisateurTampon2 = new Utilisateur();
        intervenantTampon2 = new Intervenant();

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
                } else if (consultation.getTraitement() == null) {
                    Mtm.mikiMessageWarnSelectionner("le traitement du patient");
                } else if (consultation.getIntervenant() == null) {
                    Mtm.mikiMessageWarnSelectionner("l'intervenant du patient");
                } else {
                    if (consultation.getId() == null) {
                        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PATIENT_CLE)) {

                            tx.begin();
                            suiviServices.saveOne(suivi);
                            tx.commit();

                            tx.begin();
                            consultation.setDateConsultation(new Date());
                            consultation.setSuivi(suivi);
                            consultationServices.saveOne(consultation);
                            tx.commit();

                            tx.begin();
                            Patient_intervenantid idRdv = new Patient_intervenantid();
                            idRdv.setId_intervenant(consultation.getIntervenant().getId());
                            idRdv.setId_patient(consultation.getPatient().getId());
                            rdv.setId(idRdv);
                            rdv.setIntervenant(consultation.getIntervenant());
                            rdv.setPatient(consultation.getPatient());
                            tx.commit();

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

                            for (Map.Entry<Consommation, TypeHabitude> bt : tamponHabitudeAlimentaire.entrySet()) {

                                tx.begin();
                                Habitude_alimentaireId idHb = new Habitude_alimentaireId();
                                Habitude_alimentaire Hb = new Habitude_alimentaire();

                                idHb.setId_Consommation(bt.getKey().getId());
                                idHb.setId_type_habitude(bt.getValue().getId());
                                idHb.setId_consultation(consultation.getId());

                                Hb.setId(idHb);
                                Hb.setConsommation(bt.getKey());
                                Hb.setType_habitude(bt.getValue());
                                Hb.setConsultation(consultation);

                                habitudeAlimentaireServices.saveOne(Hb);
                                tx.commit();

                            }

                            new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement d'une consultation du patient :" + consultation.getPatient().getNomPatient() + " " + consultation.getPatient().getPrenomPatient());

                        } else {
                            Mtm.mikiLog4jMessageError();
                        }
                    } else {

                        tx.begin();
                        suiviServices.updateOne(suivi);
                        tx.commit();

                        tx.begin();
                        consultation.setSuivi(suivi);
                        consultationServices.updateOne(consultation);
                        tx.commit();

                        tx.begin();
                        rdvServices.deleteOne(rdvTampon.getId());
                        tx.commit();

                        tx.begin();
                        Patient_intervenantid idRdv = new Patient_intervenantid();
                        idRdv.setId_intervenant(consultation.getIntervenant().getId());
                        idRdv.setId_patient(consultation.getPatient().getId());
                        rdv.setId(idRdv);
                        rdv.setIntervenant(consultation.getIntervenant());
                        rdv.setPatient(consultation.getPatient());
                        tx.commit();

                        for (Antecedent_familial antC : antecedentFamilialListe) {

                            tx.begin();

                            antecedentFamilialServices.deleteOne(antC.getId());

                            tx.commit();

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

                        for (Ordonnance ord2 : ordonnanceListe) {
                            if (ord2.getId() == null) {
                                tx.begin();

                                ord2.setConsultation(consultation);
                                ordonnanceServices.saveOne(ord2);

                                tx.commit();
                            }

                        }

                        for (Map.Entry<Consommation, TypeHabitude> bt2 : tamponHabitudeAlimentaireTemporaire.entrySet()) {
                            tx.begin();
                            Habitude_alimentaireId idHb2 = new Habitude_alimentaireId();

                            idHb2.setId_Consommation(bt2.getKey().getId());
                            idHb2.setId_type_habitude(bt2.getValue().getId());
                            idHb2.setId_consultation(consultation.getId());

                            habitudeAlimentaireServices.deleteOne(idHb2);
                            tx.commit();

                        }

                        for (Map.Entry<Consommation, TypeHabitude> bt : tamponHabitudeAlimentaire.entrySet()) {
                            tx.begin();
                            Habitude_alimentaireId idHb = new Habitude_alimentaireId();
                            Habitude_alimentaire Hb = new Habitude_alimentaire();

                            idHb.setId_Consommation(bt.getKey().getId());
                            idHb.setId_type_habitude(bt.getValue().getId());
                            idHb.setId_consultation(consultation.getId());

                            Hb.setId(idHb);
                            Hb.setConsommation(bt.getKey());
                            Hb.setType_habitude(bt.getValue());
                            Hb.setConsultation(consultation);

                            habitudeAlimentaireServices.saveOne(Hb);
                            tx.commit();

                        }

                    }

                    Mtm.mikiMessageInfo();
                    new Mtm().logMikiLog4j(PatientBean.class.getName(), org.apache.log4j.Level.INFO, "Modification effectuée sur la consultation du patient :" + consultation.getPatient().getNomPatient() + " " + consultation.getPatient().getPrenomPatient());
                    pathologie = new Pathologie();
                    consommation = new Consommation();

                    typecons = new TypeConsommation();
                    listeTypeHabitude = new ArrayList<>();
                    tamponHabitudeAlimentaire = new LinkedHashMap<>();
                    tamponHabitudeAlimentaireTemporaire = new LinkedHashMap<>();
                    ordonnanceListe = new ArrayList<>();
                    antecedentFamilialListe = new ArrayList<>();

                    suivi = new Suivi();
                    consultation = new Consultation();
                    ordonnance = new Ordonnance();
                    rdv = new RendezVous();
                    rdvTampon = new RendezVous();

                    consultation.setIntervenant(intervenantTampon2);

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
                suivi = consultation.getSuivi();

                //Recuperation Habitude alimentaire
                List<Habitude_alimentaire> habitude_Al = habitudeAlimentaireServices.getBy("consultation", consultation);
                Map<Consommation, TypeHabitude> HabitudeAlimentaireRecup = new LinkedHashMap<>();

                for (Habitude_alimentaire hbt : habitude_Al) {
                    HabitudeAlimentaireRecup.put(hbt.getConsommation(), hbt.getType_habitude());
                }

                tamponHabitudeAlimentaire = HabitudeAlimentaireRecup;
                tamponHabitudeAlimentaireTemporaire = HabitudeAlimentaireRecup;

                //Recuperation Antecedent familial
                antecedentFamilialListe = antecedentFamilialServices.getBy("patient", consultation.getPatient());
                List<Pathologie> pathologieRecup = new ArrayList<>();

                for (Antecedent_familial antC : antecedentFamilialListe) {
                    pathologieRecup.add(antC.getPathologie());
                }

                listePathologieTampon = pathologieRecup;

                //Recuperation de l'ordonnance
                ordonnanceListe = ordonnanceServices.getBy("consultation", consultation);

                //Disable du rdv
                disable = true;

            } catch (Exception e) {
                e.printStackTrace();
                Mtm.mikiMessageError();
            }

        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void annulerConsultation() {
        pathologie = new Pathologie();
        consommation = new Consommation();

        typecons = new TypeConsommation();
        listeTypeHabitude = new ArrayList<>();
        tamponHabitudeAlimentaire = new LinkedHashMap<>();
        tamponHabitudeAlimentaireTemporaire = new LinkedHashMap<>();
        ordonnanceListe = new ArrayList<>();
        antecedentFamilialListe = new ArrayList<>();

        suivi = new Suivi();
        consultation = new Consultation();
        ordonnance = new Ordonnance();
        rdv = new RendezVous();
        rdvTampon = new RendezVous();

        consultation.setIntervenant(intervenantTampon2);
        
        disable = false;
    }

    public void ajouterHabitudeAlimentaire() {
        if (typecons == null) {
            Mtm.mikiMessageWarnSelectionner("le type consommation");
        } else if (consommation == null) {
            Mtm.mikiMessageWarnSelectionner("la consommation");
        } else if (typeHabitude == null) {
            Mtm.mikiMessageWarnSelectionner("le type habitude");
        } else {
            if (!tamponHabitudeAlimentaire.containsKey(consommation)) {
                tamponHabitudeAlimentaire.put(consommation, typeHabitude);
                typecons = new TypeConsommation();
                consommation = new Consommation();
                typeHabitude = new TypeHabitude();
            }
        }

    }

    public List<Consultation> getConsulationFiltrerIntervenant() {
        Utilisateur utilisateurTampon = utilisateurServices.getOneBy("login", connexionMngdB.getUserLogin());
        Intervenant intervenantTampon = intervenantServices.getOneBy("utilisateur", utilisateurTampon);
        List<Consultation> consultationListeTampon = new ArrayList<>();

        if (intervenantTampon == null) {
            consultationListeTampon = consultationServices.getAll("dateConsultation", false);
        } else {
            consultationListeTampon = consultationServices.getBy("intervenant", intervenantTampon);
        }

        return consultationListeTampon;

    }

    public void supprimerHabitudeAlimentaire(Consommation tampon) {
        tamponHabitudeAlimentaire.remove(tampon);
    }

    public void ajouterAntecedentFamilial() {
        System.out.println(pathologie);
        try {
            if (pathologie != null && !listePathologieTampon.contains(pathologie)) {
                listePathologieTampon.add(pathologie);
                System.out.println(listePathologieTampon);
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

    public Map<Consommation, TypeHabitude> getTamponHabitudeAlimentaire() {
        return tamponHabitudeAlimentaire;
    }

    public void setTamponHabitudeAlimentaire(Map<Consommation, TypeHabitude> tamponHabitudeAlimentaire) {
        this.tamponHabitudeAlimentaire = tamponHabitudeAlimentaire;
    }

    public TypeHabitude getTypeHabitude() {
        return typeHabitude;
    }

    public void setTypeHabitude(TypeHabitude typeHabitude) {
        this.typeHabitude = typeHabitude;
    }

    public Habitude_alimentaireDaoBeanLocal getHabitudeAlimentaireServices() {
        return habitudeAlimentaireServices;
    }

    public void setHabitudeAlimentaireServices(Habitude_alimentaireDaoBeanLocal habitudeAlimentaireServices) {
        this.habitudeAlimentaireServices = habitudeAlimentaireServices;
    }

    public Suivi getSuivi() {
        return suivi;
    }

    public void setSuivi(Suivi suivi) {
        this.suivi = suivi;
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

    public SuiviSessionBeanLocal getSuiviServices() {
        return suiviServices;
    }

    public void setSuiviServices(SuiviSessionBeanLocal suiviServices) {
        this.suiviServices = suiviServices;
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

    public Map<Consommation, TypeHabitude> getTamponHabitudeAlimentaireTemporaire() {
        return tamponHabitudeAlimentaireTemporaire;
    }

    public void setTamponHabitudeAlimentaireTemporaire(Map<Consommation, TypeHabitude> tamponHabitudeAlimentaireTemporaire) {
        this.tamponHabitudeAlimentaireTemporaire = tamponHabitudeAlimentaireTemporaire;
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
    
    

}
