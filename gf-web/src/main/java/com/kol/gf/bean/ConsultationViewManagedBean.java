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
import com.kol.gf.entities.Comorbidite;
import com.kol.gf.entities.Consultation;
import com.kol.gf.entities.Diagnostique;
import com.kol.gf.entities.ExamenClinique;
import com.kol.gf.entities.ExamenParaclinique;
import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Ordonnance;
import com.kol.gf.entities.ParacliniqueConsultation;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.service.Antecedent_FamilialSessionBeanLocal;
import com.kol.gf.service.ClasseTheurapetiqueServiceBeanLocal;
import com.kol.gf.service.ComorbiditeSessionBeanLocal;
import com.kol.gf.service.DecesServiceBeanLocal;
import com.kol.gf.service.DiagnostiqueSessionBeanLocal;
import com.kol.gf.service.ExamenCliniqueSessionBeanLocal;
import com.kol.gf.service.ExamenParacliniqueSessionBeanLocal;
import com.kol.gf.service.Habitude_alimentaireServiceBeanLocal;
import com.kol.gf.service.OrdonnanceSessionBeanLocal;
import com.kol.gf.service.ParacliniqueConsultationSessionBeanLocal;
import com.kol.gf.service.PathologieServiceBeanLocal;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import com.kol.gf.service.TraitementMedicamenteuxSessionBeanLocal;
import com.kol.gf.service.TypeHabitudeServiceBeanLocal;
import com.miki.webapp.core.Utils.ManipulationDate;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import com.miki.webapp.report.EtatEnvois;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@SessionScoped
public class ConsultationViewManagedBean implements Serializable {

    private Consultation consultation;
    private ExamenClinique examentClinique;
    private Patient patient;
    private Utilisateur utilisateurTampon2;
    private Intervenant intervenantTampon2;
    private String etat;

    private List<Habitude_alimentaire> tamponHabitudeAlimentaire;
    private List<Pathologie> listePathologieTampon;
    private List<Antecedent_familial> antecedentFamilialListe;
    private List<Ordonnance> ordonnanceListe;
    private List<Diagnostique> diagnostiqueListe;
    private List<ParacliniqueConsultation> paracliniqueConsultationTamponListe;
    private List<ExamenParaclinique> examenParacliniqueListe;

    private List<Comorbidite> comorbiditeListe;

    private List<TraitementMedicamenteux> traitementMedicamenteuxClasseListe;

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

    @EJB
    private TraitementMedicamenteuxSessionBeanLocal traitementMedicamenteuxServices;

    @EJB
    private ComorbiditeSessionBeanLocal comorbiditeServices;

    @EJB
    private DecesServiceBeanLocal decesServices;

    public ConsultationViewManagedBean() {

        patient = new Patient();

        tamponHabitudeAlimentaire = new ArrayList<>();

        ordonnanceListe = new ArrayList<>();
        antecedentFamilialListe = new ArrayList<>();

        listePathologieTampon = new ArrayList<>();
        diagnostiqueListe = new ArrayList<>();
        examenParacliniqueListe = new ArrayList<>();

        paracliniqueConsultationTamponListe = new ArrayList<>();

        comorbiditeListe = new ArrayList<>();

        traitementMedicamenteuxClasseListe = new ArrayList<>();

        consultation = new Consultation();

        utilisateurTampon2 = new Utilisateur();
        intervenantTampon2 = new Intervenant();

        examentClinique = new ExamenClinique();

        etat = "";

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

    public String informationConsultation(Consultation consul) {
        String page = null;

        if (EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE) && intervenantTampon2 != null) {
            try {

                this.annulerConsultation();
                consultation = consul;
                examentClinique = consul.getExamen_Clinique();
                patient = consul.getPatient();

                //Recuperation Habitude alimentaire
                List<Habitude_alimentaire> habitude_Al = habitudeAlimentaireServices.getBy("consultation", consul);

                tamponHabitudeAlimentaire = habitude_Al;

                //Recuperation TraitementMedicamenteux
                List<TraitementMedicamenteux> traitMedListe = traitementMedicamenteuxServices.getBy("consultation", consul);

                traitementMedicamenteuxClasseListe = traitMedListe;

                //Recuperation Antecedent familial
                antecedentFamilialListe = antecedentFamilialServices.getBy("consultation", consul);
                List<Pathologie> pathologieRecup = new ArrayList<>();

                for (Antecedent_familial antC : antecedentFamilialListe) {
                    pathologieRecup.add(antC.getPathologie());
                }

                listePathologieTampon = pathologieRecup;

                //Recuperation de l'ordonnance
                ordonnanceListe = ordonnanceServices.getBy("consultation", consul);

                //Recuperation du Diagnostique
                diagnostiqueListe = diagnostiqueServices.getBy("consultation", consul);

                //Recuperation de la comorbidite
                comorbiditeListe = comorbiditeServices.getBy("consultation", consul);

                //Recuperation du paracliniqueConsultation
                paracliniqueConsultationTamponListe = paracliniqueConsultationServices.getBy("consultation", consul);

                for (ParacliniqueConsultation pr : paracliniqueConsultationTamponListe) {
                    examenParacliniqueListe.add(pr.getExamen());
                }
                
                //Recuperation de l'etat du patient;
                if (decesServices.getOneBy("patient", patient) == null) {

                    List<Consultation> consulListe2 = consultationServices.getBy("patient", patient);

                    if (consulListe2.isEmpty()) {

                        etat = "En vie";

                    } else {

                        Date dateLimitePerduDeVu = ManipulationDate.ajouterMois(new Date(), -3);

                        Date dateDernierConsul = consulListe2.stream()
                                .reduce((c1, c2) -> c1.getDateConsultation().after(c2.getDateConsultation()) ? c1 : c2).get().getDateConsultation();

                        if (dateLimitePerduDeVu.after(dateDernierConsul)) {
                            etat = "Perdu de vue";
                        } else {
                            etat = "En vie";
                        }
                    }

                } else {
                    etat = "Décédé";
                }

                page = "/gf/consultation/informationConsultation.xhtml";

            } catch (Exception e) {
                e.printStackTrace();
                Mtm.mikiMessageError();
                page = null;
            }

        } else {
            Mtm.mikiMessageErrorPerso("Opération interrompue :vous n'etes pas un intervenant donc vous ne pouvez pas effectuer cette opération !");
            page = null;
        }

        return page;
    }

    public String informationConsultation2(Consultation consul) {
        String page = null;

        if (EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PATIENT_CLE) && intervenantTampon2 != null) {
            try {

                this.annulerConsultation();
                consultation = consul;
                examentClinique = consul.getExamen_Clinique();
                patient = consul.getPatient();

                //Recuperation Habitude alimentaire
                List<Habitude_alimentaire> habitude_Al = habitudeAlimentaireServices.getBy("consultation", consul);

                tamponHabitudeAlimentaire = habitude_Al;

                //Recuperation TraitementMedicamenteux
                List<TraitementMedicamenteux> traitMedListe = traitementMedicamenteuxServices.getBy("consultation", consul);

                traitementMedicamenteuxClasseListe = traitMedListe;

                //Recuperation Antecedent familial
                antecedentFamilialListe = antecedentFamilialServices.getBy("consultation", consul);
                List<Pathologie> pathologieRecup = new ArrayList<>();

                for (Antecedent_familial antC : antecedentFamilialListe) {
                    pathologieRecup.add(antC.getPathologie());
                }

                listePathologieTampon = pathologieRecup;

                //Recuperation de l'ordonnance
                ordonnanceListe = ordonnanceServices.getBy("consultation", consul);

                //Recuperation du Diagnostique
                diagnostiqueListe = diagnostiqueServices.getBy("consultation", consul);

                //Recuperation de la comorbidite
                comorbiditeListe = comorbiditeServices.getBy("consultation", consul);

                //Recuperation du paracliniqueConsultation
                paracliniqueConsultationTamponListe = paracliniqueConsultationServices.getBy("consultation", consul);

                for (ParacliniqueConsultation pr : paracliniqueConsultationTamponListe) {
                    examenParacliniqueListe.add(pr.getExamen());
                }
                
                //Recuperation de l'etat du patient;
                if (decesServices.getOneBy("patient", patient) == null) {

                    List<Consultation> consulListe22 = consultationServices.getBy("patient", patient);

                    if (consulListe22.isEmpty()) {

                        etat = "En vie";

                    } else {

                        Date dateLimitePerduDeVu2 = ManipulationDate.ajouterMois(new Date(), -3);

                        Date dateDernierConsul2 = consulListe22.stream()
                                .reduce((c1, c2) -> c1.getDateConsultation().after(c2.getDateConsultation()) ? c1 : c2).get().getDateConsultation();

                        if (dateLimitePerduDeVu2.after(dateDernierConsul2)) {
                            etat = "Perdu de vue";
                        } else {
                            etat = "En vie";
                        }
                    }

                } else {
                    etat = "Décédé";
                }

                page = "/gf/suivi/informationConsultation.xhtml";

            } catch (Exception e) {
                e.printStackTrace();
                Mtm.mikiMessageError();
                page = null;
            }

        } else {
            Mtm.mikiMessageErrorPerso("Opération interrompue :vous n'etes pas un intervenant donc vous ne pouvez pas effectuer cette opération !");
            page = null;
        }

        return page;
    }

    public void annulerConsultation() {

        patient = new Patient();

        tamponHabitudeAlimentaire = new ArrayList<>();

        ordonnanceListe = new ArrayList<>();
        antecedentFamilialListe = new ArrayList<>();

        consultation = new Consultation();

        consultation.setIntervenant(intervenantTampon2);

        examentClinique = new ExamenClinique();

        diagnostiqueListe = new ArrayList<>();
        examenParacliniqueListe = new ArrayList<>();
        paracliniqueConsultationTamponListe = new ArrayList<>();

    }

    public String retour() {
        return "/gf/consultation/gestionConsu.xhtml?faces-redirect=true";
    }

    public String retour2() {
        return "/gf/suivi/gestionSuivi.xhtml?faces-redirect=true";
    }

    public String retour3() {
        return "/gf/consultation/informationConsultation.xhtml";
    }

    public String ordonnanceEtat() throws JRException, IOException {
        System.out.println("-------------debut-----------------");
        List<Map<String, ?>> fields = new ArrayList();
        for (Ordonnance sto : ordonnanceListe) {
            Map<String, Object> mape = new HashMap();

            mape.put("id", sto.getId());
            mape.put("medicament", sto.getMedicament());
            mape.put("posologie", sto.getPosologie());
            mape.put("quantite", sto.getQuantite());
            mape.put("nom", sto.getConsultation().getIntervenant().getNomIntervenant());
            mape.put("prenom", sto.getConsultation().getIntervenant().getPrenomIntervenant());
            mape.put("nomPatient", sto.getConsultation().getPatient().getNomPatient());
            mape.put("prenomPatient", sto.getConsultation().getPatient().getPrenomPatient());

            fields.add(mape);
        }

        EtatEnvois envois = new EtatEnvois(fields, true);
        envois.setCompileFileName("OrdonnanceReport");
        envois.changerFormat("PDF");
        envois.exporterMap();
        System.out.println("-------------fin-----------------");

        return "/gf/Etat/Etat.xhtml?faces-redirect=true";
    }

    public String examenParacliniqueEtat() throws JRException, IOException {
        System.out.println("-------------debut-----------------");

        List<Map<String, ?>> fields2 = new ArrayList();
        for (ExamenParaclinique sto2 : examenParacliniqueListe) {
            Map<String, Object> mape2 = new HashMap();

            mape2.put("examen", sto2.getLabel());
            mape2.put("nom", consultation.getIntervenant().getNomIntervenant());
            mape2.put("prenom", consultation.getIntervenant().getPrenomIntervenant());
            mape2.put("nomPatient", consultation.getPatient().getNomPatient());
            mape2.put("prenomPatient", consultation.getPatient().getPrenomPatient());

            fields2.add(mape2);
        }

        EtatEnvois envois = new EtatEnvois(fields2, true);
        envois.setCompileFileName("ExamenParaClinique");
        envois.changerFormat("PDF");
        envois.exporterMap();
        System.out.println("-------------fin-----------------");

        return "/gf/Etat/Etat.xhtml?faces-redirect=true";
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public ExamenClinique getExamentClinique() {
        return examentClinique;
    }

    public void setExamentClinique(ExamenClinique examentClinique) {
        this.examentClinique = examentClinique;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public List<Habitude_alimentaire> getTamponHabitudeAlimentaire() {
        return tamponHabitudeAlimentaire;
    }

    public void setTamponHabitudeAlimentaire(List<Habitude_alimentaire> tamponHabitudeAlimentaire) {
        this.tamponHabitudeAlimentaire = tamponHabitudeAlimentaire;
    }

    public List<Pathologie> getListePathologieTampon() {
        return listePathologieTampon;
    }

    public void setListePathologieTampon(List<Pathologie> listePathologieTampon) {
        this.listePathologieTampon = listePathologieTampon;
    }

    public List<Antecedent_familial> getAntecedentFamilialListe() {
        return antecedentFamilialListe;
    }

    public void setAntecedentFamilialListe(List<Antecedent_familial> antecedentFamilialListe) {
        this.antecedentFamilialListe = antecedentFamilialListe;
    }

    public List<Ordonnance> getOrdonnanceListe() {
        return ordonnanceListe;
    }

    public void setOrdonnanceListe(List<Ordonnance> ordonnanceListe) {
        this.ordonnanceListe = ordonnanceListe;
    }

    public List<Diagnostique> getDiagnostiqueListe() {
        return diagnostiqueListe;
    }

    public void setDiagnostiqueListe(List<Diagnostique> diagnostiqueListe) {
        this.diagnostiqueListe = diagnostiqueListe;
    }

    public List<ParacliniqueConsultation> getParacliniqueConsultationTamponListe() {
        return paracliniqueConsultationTamponListe;
    }

    public void setParacliniqueConsultationTamponListe(List<ParacliniqueConsultation> paracliniqueConsultationTamponListe) {
        this.paracliniqueConsultationTamponListe = paracliniqueConsultationTamponListe;
    }

    public List<ExamenParaclinique> getExamenParacliniqueListe() {
        return examenParacliniqueListe;
    }

    public void setExamenParacliniqueListe(List<ExamenParaclinique> examenParacliniqueListe) {
        this.examenParacliniqueListe = examenParacliniqueListe;
    }

    public ConnexionManagedBean getConnexionMngdB() {
        return connexionMngdB;
    }

    public void setConnexionMngdB(ConnexionManagedBean connexionMngdB) {
        this.connexionMngdB = connexionMngdB;
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

    public TypeHabitudeServiceBeanLocal getTypeHabitudeServices() {
        return typeHabitudeServices;
    }

    public void setTypeHabitudeServices(TypeHabitudeServiceBeanLocal typeHabitudeServices) {
        this.typeHabitudeServices = typeHabitudeServices;
    }

    public Habitude_alimentaireServiceBeanLocal getHabitudeAlimentaireServices() {
        return habitudeAlimentaireServices;
    }

    public void setHabitudeAlimentaireServices(Habitude_alimentaireServiceBeanLocal habitudeAlimentaireServices) {
        this.habitudeAlimentaireServices = habitudeAlimentaireServices;
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

    public Antecedent_FamilialSessionBeanLocal getAntecedentFamilialServices() {
        return antecedentFamilialServices;
    }

    public void setAntecedentFamilialServices(Antecedent_FamilialSessionBeanLocal antecedentFamilialServices) {
        this.antecedentFamilialServices = antecedentFamilialServices;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    public RendezVousServiceBeanLocal getRdvServices() {
        return rdvServices;
    }

    public void setRdvServices(RendezVousServiceBeanLocal rdvServices) {
        this.rdvServices = rdvServices;
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

    public ClasseTheurapetiqueServiceBeanLocal getClasseTherapeutiqueServices() {
        return classeTherapeutiqueServices;
    }

    public void setClasseTherapeutiqueServices(ClasseTheurapetiqueServiceBeanLocal classeTherapeutiqueServices) {
        this.classeTherapeutiqueServices = classeTherapeutiqueServices;
    }

    public TraitementMedicamenteuxSessionBeanLocal getTraitementMedicamenteuxServices() {
        return traitementMedicamenteuxServices;
    }

    public void setTraitementMedicamenteuxServices(TraitementMedicamenteuxSessionBeanLocal traitementMedicamenteuxServices) {
        this.traitementMedicamenteuxServices = traitementMedicamenteuxServices;
    }

    public ComorbiditeSessionBeanLocal getComorbiditeServices() {
        return comorbiditeServices;
    }

    public void setComorbiditeServices(ComorbiditeSessionBeanLocal comorbiditeServices) {
        this.comorbiditeServices = comorbiditeServices;
    }

    public List<Comorbidite> getComorbiditeListe() {
        return comorbiditeListe;
    }

    public void setComorbiditeListe(List<Comorbidite> comorbiditeListe) {
        this.comorbiditeListe = comorbiditeListe;
    }

    public List<TraitementMedicamenteux> getTraitementMedicamenteuxClasseListe() {
        return traitementMedicamenteuxClasseListe;
    }

    public void setTraitementMedicamenteuxClasseListe(List<TraitementMedicamenteux> traitementMedicamenteuxClasseListe) {
        this.traitementMedicamenteuxClasseListe = traitementMedicamenteuxClasseListe;
    }

    public DecesServiceBeanLocal getDecesServices() {
        return decesServices;
    }

    public void setDecesServices(DecesServiceBeanLocal decesServices) {
        this.decesServices = decesServices;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    

}
