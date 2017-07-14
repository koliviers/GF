/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.dao.bean.ServiceDaoBeanLocal;
import com.kol.gf.dao.bean.TypeIntervenantDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Services;
import com.kol.gf.entities.TypeIntervenant;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.miki.securite.Service.ProfilSessionBeanLocal;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author kol
 */
@ManagedBean(name = "interbean")
@ViewScoped
public class IntervenantBean implements Serializable {

    private Intervenant intervenant;
    private Intervenant intervenanselect;
    private TypeIntervenant typeintervenantModal;
    private Services services;
    private Utilisateur utilisateurIntervenant;

    private List<Services> listeservice;
    private List<TypeIntervenant> listetypeIntervenant;
    private List<TypeIntervenant> listetypeIntervenantModal;
    private List<Intervenant> listeIntervenant;

    public String tofProfil;

    @EJB
    private IntervenantDaoBeanLocal daoIntervenant;

    @EJB
    private TypeIntervenantDaoBeanLocal daotypeIntervenant;

    @EJB
    private ServiceDaoBeanLocal daoservice;

    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;

    @EJB
    private ProfilSessionBeanLocal profilServices;

    public IntervenantBean() {
        intervenant = new Intervenant();
        services = new Services();
        typeintervenantModal = new TypeIntervenant();
        intervenanselect = new Intervenant();
        utilisateurIntervenant = new Utilisateur();

        listeIntervenant = new ArrayList<Intervenant>();
        listeservice = new ArrayList<Services>();
        listetypeIntervenant = new ArrayList<TypeIntervenant>();

    }

    public void gestionIntervenant() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_INTERVENANT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_INTERVENANT_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {

                if (intervenant.getNomIntervenant().trim().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le nom de l'intervenant");
                } else if (intervenant.getPrenomIntervenant().trim().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le prenom de l'intervenant");
                } else if (intervenant.getServices() == null) {
                    Mtm.mikiMessageWarnChoisir("le service de l'intervenant");
                } else if (intervenant.getType_intervenant() == null) {
                    Mtm.mikiMessageWarnChoisir("le type d'intervenant");
                } else if (utilisateurIntervenant.getLogin().trim().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le nom d'utilisateur de l'intervenant");
                } else {
                    if (intervenant.getId() == null) {
                        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_INTERVENANT_CLE)) {
                            if (utilisateurServices.getBy("login", utilisateurIntervenant.getLogin()).isEmpty()) {
                                tx.begin();
                                utilisateurIntervenant.setContact(intervenant.getContact());
                                utilisateurIntervenant.setDateCreation(new Date());
                                utilisateurIntervenant.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                                utilisateurIntervenant.setNom(intervenant.getNomIntervenant());
                                utilisateurIntervenant.setPrenom(intervenant.getPrenomIntervenant());
                                utilisateurIntervenant.setProfil(profilServices.getOneBy("nomProf", "Invite"));
                                utilisateurIntervenant.setReinitialiserPswd(true);
                                utilisateurIntervenant.setSexe(intervenant.getSexeIntervenant());
                                utilisateurIntervenant.setActif(intervenant.isActive());

                                utilisateurServices.saveOne(utilisateurIntervenant);
                                tx.commit();

                                tx.begin();
                                intervenant.setUtilisateur(utilisateurIntervenant);
                                daoIntervenant.saveOne(intervenant);
                                tx.commit();

                                Mtm.mikiMessageInfo();
                                new Mtm().logMikiLog4j(IntervenantBean.class.getName(), org.apache.log4j.Level.INFO, "Création de l'intervenant : " + intervenant.getNomIntervenant() + " " + intervenant.getPrenomIntervenant() + ""
                                        + " et de son compte utilisateur");
                            } else {
                                Mtm.mikiMessageWarn("Ce nom d'utilisateur existe déjà !, veuillez saisir un autre nom d'utilisateur svp !");
                            }
                        } else {
                            Mtm.mikiLog4jMessageError();
                        }

                    } else {
                        if (EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_INTERVENANT_CLE)) {
                            List<Utilisateur> utilisateurTestExistence = utilisateurServices.getBy("login", utilisateurIntervenant.getLogin());
                            if (utilisateurTestExistence.isEmpty()) {
                                tx.begin();
                                utilisateurIntervenant.setContact(intervenant.getContact());
                                utilisateurIntervenant.setNom(intervenant.getNomIntervenant());
                                utilisateurIntervenant.setPrenom(intervenant.getPrenomIntervenant());
                                utilisateurIntervenant.setSexe(intervenant.getSexeIntervenant());
                                utilisateurIntervenant.setActif(intervenant.isActive());

                                utilisateurServices.updateOne(utilisateurIntervenant);
                                tx.commit();

                                tx.begin();
                                intervenant.setUtilisateur(utilisateurIntervenant);
                                daoIntervenant.updateOne(intervenant);
                                tx.commit();

                                Mtm.mikiMessageInfo();
                                new Mtm().logMikiLog4j(IntervenantBean.class.getName(), org.apache.log4j.Level.INFO, "Modification de l'intervenant : " + intervenant.getNomIntervenant() + " " + intervenant.getPrenomIntervenant() + ""
                                        + " et de son compte utilisateur");
                            } else {
                                if (utilisateurTestExistence.size() == 1) {
                                    if (Objects.equals(utilisateurTestExistence.get(0).getId(), utilisateurIntervenant.getId())) {
                                        tx.begin();
                                        utilisateurIntervenant.setContact(intervenant.getContact());
                                        utilisateurIntervenant.setNom(intervenant.getNomIntervenant());
                                        utilisateurIntervenant.setPrenom(intervenant.getPrenomIntervenant());
                                        utilisateurIntervenant.setSexe(intervenant.getSexeIntervenant());
                                        utilisateurIntervenant.setActif(intervenant.isActive());

                                        utilisateurServices.updateOne(utilisateurIntervenant);
                                        tx.commit();

                                        tx.begin();
                                        intervenant.setUtilisateur(utilisateurIntervenant);
                                        daoIntervenant.updateOne(intervenant);
                                        tx.commit();

                                        Mtm.mikiMessageInfo();
                                        new Mtm().logMikiLog4j(IntervenantBean.class.getName(), org.apache.log4j.Level.INFO, "Modification de l'intervenant : " + intervenant.getNomIntervenant() + " " + intervenant.getPrenomIntervenant() + ""
                                                + " et de son compte utilisateur");
                                    } else {
                                        Mtm.mikiMessageWarn("Ce nom d'utilisateur existe déjà !, veuillez saisir un autre nom d'utilisateur svp !");
                                    }
                                } else {
                                    Mtm.mikiMessageWarn("Ce nom d'utilisateur existe déjà !, veuillez saisir un autre nom d'utilisateur svp !");
                                }
                            }
                        } else {
                            Mtm.mikiLog4jMessageError();
                        }

                    }

                    utilisateurIntervenant = new Utilisateur();
                    intervenant = new Intervenant();
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                ex.printStackTrace();
                new Mtm().logMikiLog4j(IntervenantBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors de la création de l'intendant: " + intervenant.getNomIntervenant() + " " + intervenant.getPrenomIntervenant() + ""
                        + " et de son compte utilisateur");
                Mtm.mikiMessageError();
            }
        } else {
            Mtm.mikiLog4jMessageError();

        }

    }

    public void renvoieIntendant(Intervenant interv) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_INTERVENANT_CLE)) {
            intervenant = interv;
            utilisateurIntervenant = interv.getUtilisateur();
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void annuler() {
        utilisateurIntervenant = new Utilisateur();
        intervenant = new Intervenant();
    }

    public void gestionTypeIntervenant() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (typeintervenantModal.getLibelletype().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du type d'intervenant");
            } else {
                if (typeintervenantModal.getId() == null) {
                    tx.begin();
                    daotypeIntervenant.saveOne(typeintervenantModal);
                    tx.commit();

                    Mtm.mikiMessageInfo();
                } else {
                    tx.begin();
                    daotypeIntervenant.updateOne(typeintervenantModal);
                    tx.commit();

                    Mtm.mikiMessageInfo();
                }

                typeintervenantModal = new TypeIntervenant();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ex.printStackTrace();
            Mtm.mikiMessageError();
        }
    }

    public void renvoieTypeIntervenant(TypeIntervenant typI) {
        typeintervenantModal = typI;
    }

    public void annulerTypeIntervenant() {
        typeintervenantModal = new TypeIntervenant();
    }

    public String actifString(boolean bol) {
        return bol ? "Actif" : "Inactif";
    }

    /**
     * @return the intervenant
     */
    public Intervenant getIntervenant() {
        return intervenant;
    }

    /**
     * @param intervenant the intervenant to set
     */
    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * @return the listeIntervenant
     */
    public List<Intervenant> getListeIntervenant() {
        return daoIntervenant.getAll();
    }

    /**
     * @param listeIntervenant the listeIntervenant to set
     */
    public void setListeIntervenant(List<Intervenant> listeIntervenant) {
        this.listeIntervenant = listeIntervenant;
    }

    /**
     * @return the services
     */
    public Services getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(Services services) {
        this.services = services;
    }

    /**
     * @return the listeservice
     */
    public List<Services> getListeservice() {
        return daoservice.getAll();
    }

    /**
     * @param listeservice the listeservice to set
     */
    public void setListeservice(List<Services> listeservice) {
        this.listeservice = listeservice;
    }

    /**
     * @return the listetypeIntervenant
     */
    public List<TypeIntervenant> getListetypeIntervenant() {
        return daotypeIntervenant.getAll();
    }

    /**
     * @param listetypeIntervenant the listetypeIntervenant to set
     */
    public void setListetypeIntervenant(List<TypeIntervenant> listetypeIntervenant) {
        this.listetypeIntervenant = listetypeIntervenant;
    }

    /**
     * @return the daoIntervenant
     */
    public IntervenantDaoBeanLocal getDaoIntervenant() {
        return daoIntervenant;
    }

    /**
     * @param daoIntervenant the daoIntervenant to set
     */
    public void setDaoIntervenant(IntervenantDaoBeanLocal daoIntervenant) {
        this.daoIntervenant = daoIntervenant;
    }

    /**
     * @return the daotypeIntervenant
     */
    public TypeIntervenantDaoBeanLocal getDaotypeIntervenant() {
        return daotypeIntervenant;
    }

    /**
     * @param daotypeIntervenant the daotypeIntervenant to set
     */
    public void setDaotypeIntervenant(TypeIntervenantDaoBeanLocal daotypeIntervenant) {
        this.daotypeIntervenant = daotypeIntervenant;
    }

    /**
     * @return the daoservice
     */
    public ServiceDaoBeanLocal getDaoservice() {
        return daoservice;
    }

    /**
     * @param daoservice the daoservice to set
     */
    public void setDaoservice(ServiceDaoBeanLocal daoservice) {
        this.daoservice = daoservice;
    }

    /**
     * @return the intervenanselect
     */
    public Intervenant getIntervenanselect() {
        return intervenanselect;
    }

    /**
     * @param intervenanselect the intervenanselect to set
     */
    public void setIntervenanselect(Intervenant intervenanselect) {
        this.intervenanselect = intervenanselect;
    }

    public Utilisateur getUtilisateurIntervenant() {
        return utilisateurIntervenant;
    }

    public void setUtilisateurIntervenant(Utilisateur utilisateurIntervenant) {
        this.utilisateurIntervenant = utilisateurIntervenant;
    }

    public String getTofProfil() {
        return tofProfil;
    }

    public void setTofProfil(String tofProfil) {
        this.tofProfil = tofProfil;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    public ProfilSessionBeanLocal getProfilServices() {
        return profilServices;
    }

    public void setProfilServices(ProfilSessionBeanLocal profilServices) {
        this.profilServices = profilServices;
    }

    public TypeIntervenant getTypeintervenantModal() {
        return typeintervenantModal;
    }

    public void setTypeintervenantModal(TypeIntervenant typeintervenantModal) {
        this.typeintervenantModal = typeintervenantModal;
    }

    public List<TypeIntervenant> getListetypeIntervenantModal() {
        return daotypeIntervenant.getAll();
    }

    public void setListetypeIntervenantModal(List<TypeIntervenant> listetypeIntervenantModal) {
        this.listetypeIntervenantModal = listetypeIntervenantModal;
    }

}
