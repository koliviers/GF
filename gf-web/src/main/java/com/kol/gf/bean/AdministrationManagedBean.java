/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.miki.webapp.core.Utils.Mtm;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.miki.securite.Service.DroitSessionBeanLocal;
import com.miki.webapp.miki.securite.Service.PossederSessionBeanLocal;
import com.miki.webapp.miki.securite.Service.PosteSessionBeanLocal;
import com.miki.webapp.miki.securite.Service.ProfilSessionBeanLocal;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Droit;
import com.miki.webapp.miki.securite.entities.Posseder;
import com.miki.webapp.miki.securite.entities.PossederId;
import com.miki.webapp.miki.securite.entities.Poste;
import com.miki.webapp.miki.securite.entities.Profil;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Mikel
 */
@ManagedBean
@ViewScoped
public class AdministrationManagedBean implements Serializable {

    private Utilisateur utilisateur;
    private Utilisateur utilisateurTampon;
    private Utilisateur utilisateur2;
    private Utilisateur utilisateurProfil;
    private Utilisateur utilisateurPswd;
    private Profil profil;
    private Profil profilTampon;
    private Posseder posseder;
    private PossederId possederId;
    private Poste poste;
    private boolean disAdmin;

    private List<Utilisateur> utilisateurListe;
    private List<Utilisateur> utilisateurListeSansAdmin;
    private List<Droit> droitListe;
    private List<Droit> droitListeSource;
    private List<Droit> droitListeSupp;
    private List<Profil> profilListe;
    private List<Poste> posteListe;
    private List<Posseder> possederSupp;

    public String tofProfil;

    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;

    @EJB
    private ProfilSessionBeanLocal profilServices;

    @EJB
    private PossederSessionBeanLocal possederServices;

    @EJB
    private PosteSessionBeanLocal posteServices;

    @EJB
    private DroitSessionBeanLocal droitServices;

    @ManagedProperty(value = "#{connexionManagedBean}")
    private ConnexionManagedBean connexionMngdB;

    public AdministrationManagedBean() {
        utilisateur = new Utilisateur();
        utilisateurTampon = new Utilisateur();
        utilisateur2 = new Utilisateur();
        utilisateurProfil = new Utilisateur();
        utilisateurPswd = new Utilisateur();
        profil = new Profil();
        profilTampon = new Profil();
        posseder = new Posseder();
        possederId = new PossederId();
        poste = new Poste();

        utilisateurListe = new ArrayList<>();
        utilisateurListeSansAdmin = new ArrayList<>();
        droitListeSupp = new ArrayList<>();
        droitListe = new ArrayList<>();
        droitListeSource = new ArrayList<>();
        profilListe = new ArrayList<>();
        posteListe = new ArrayList<>();
        possederSupp = new ArrayList<>();

        tofProfil = "images/tofProfilDefaut.png";
        disAdmin = false;
    }

    public void ajouterPoste() {
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if (poste.getLibPoste().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du poste");
            } else {
                if (poste.getIdLibPoste() == null) {
                    posteServices.saveOne(poste);
                    tx.commit();
                    poste = new Poste();
                    Mtm.mikiMessageInfo();
                } else {
                    posteServices.updateOne(poste);
                    tx.commit();
                    poste = new Poste();
                    Mtm.mikiMessageInfo();
                }

            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }

    public void renvoiePoste(Poste pe) {
        poste = pe;
    }

    public void annulerPoste() {
        poste = new Poste();
    }

    public void affectationUtilisateurDroit() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if (utilisateurProfil == null) {
                    Mtm.mikiMessageWarnChoisir("l'utilisateur");
                } else if (utilisateurProfil.getProfil() == null) {
                    Mtm.mikiMessageWarnSelectionner("le profil a affecter à l'utilisateur");
                } else {
                    utilisateurServices.updateOne(utilisateurProfil);
                    tx.commit();
                    new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Affectation du profil : " + utilisateurProfil.getProfil().getNomProf() + ", à l'utilsateur : " + utilisateurProfil.getLogin());
                    utilisateurProfil = new Utilisateur();
                    Mtm.mikiMessageInfo();
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Mtm.mikiMessageError();
            }
            
           
        } else {
            Mtm.mikiLog4jMessageError();
        }
        

    }

    public void renvoieUtilisateurDroit(Utilisateur ur) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            if (ur.getLogin().equals("Administrateur")) {
                Mtm.mikiMessageErrorPerso("Impossible de modifier le profil de l'administrateur !");
            } else {
                utilisateurProfil = ur;
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void annulerUtilisateurDroit() {
        utilisateurProfil = new Utilisateur();
    }

    public void ajouterUtilisateur() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if (utilisateur.getPoste() == null) {
                    Mtm.mikiMessageWarnChoisir("le poste");
                } else if (utilisateur.getNom().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le nom");
                } else if (utilisateur.getPrenom().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le prénom");
                } else if (utilisateur.getLogin().isEmpty()) {
                    Mtm.mikiMessageWarnSaisir("le nom d'utilisateur");
                } else {
                    if (utilisateur.getId() == null) {
                        if (utilisateurServices.getBy("login", utilisateur.getLogin()).isEmpty()) {
                            utilisateur.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                            //utilisateur.setPhoto(tofProfil);
                            utilisateur.setDateCreation(new Date());
                            utilisateur.setProfil(profilServices.getOneBy("nomProf", "Invite"));
                            utilisateur.setReinitialiserPswd(true);
                            utilisateurServices.saveOne(utilisateur);
                            tx.commit();
                            new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement d'un personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                            utilisateur = new Utilisateur();
                            tofProfil = "images/tofProfilDefaut.png";
                            Mtm.mikiMessageInfo();
                        } else {
                            Mtm.mikiMessageErrorPerso("Ce nom d'utilisateur est déja utilisé, réessayez svp !");
                        }
                    } else {
                        utilisateurServices.updateOne(utilisateur);
                        tx.commit();

                        if (utilisateur.getNom().equals(utilisateurTampon.getNom()) && utilisateur.getPrenom().equals(utilisateurTampon.getPrenom())) {
                            new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification des données du personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                        } else {
                            new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification de donnée d'un personnel :" + utilisateurTampon.getNom() + " " + utilisateurTampon.getPrenom() + ", par :"
                                    + utilisateur.getNom() + " " + utilisateur.getPrenom());
                        }
                        utilisateur = new Utilisateur();
                        utilisateurTampon = new Utilisateur();
                        tofProfil = "images/tofProfilDefaut.png";
                        Mtm.mikiMessageInfo();
                    }
                    
                 
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
               new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors d'une opération sur le personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                Mtm.mikiMessageError();
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void renvoieUtilisateur(Utilisateur ur) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            if (ur.getLogin().equals("Administrateur")) {
                if (connexionMngdB.getUserConnexionTest().getLogin().equals("Administrateur")) {
                    utilisateur = ur;
                    //tofProfil = utilisateur.getPhoto();
                    utilisateurTampon = ur;
                    disAdmin = true;
                } else {
                    Mtm.mikiMessageErrorPerso("Impossible de modifier les données de l'administrateur !");
                }
            } else {
                utilisateur = ur;
                tofProfil = utilisateur.getPhoto();
                utilisateurTampon = ur;
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void annulerUtilisateur() {
        utilisateur = new Utilisateur();
        tofProfil = "images/tofProfilDefaut.png";
        disAdmin = false;
    }

    public void ajouterProfil() {
    if (EntityRealm.getSubject ().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if (profil.getNomProf().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du profil");
            } else if (droitListeSource.isEmpty()) {
                Mtm.mikiMessageWarnSelectionner("le(s) droit(s)");
            } else {
                if (profil.getIdProf()== null) {
                    profil.setDateCreaProf(new Date());
                    profilServices.saveOne(profil);
                    tx.commit();

                    List<Posseder> possederProfil;
                    possederProfil = new ArrayList<>();
                    for (Droit drt : droitListeSource) {
                        tx.begin();
                        possederId.setProfilID(profil.getIdProf());
                        possederId.setDroitUtilID(drt.getCodeDroit());
                        posseder.setId(possederId);
                        posseder.setProfil(profil);
                        posseder.setDroitUtilisateur(drt);

                        this.possederServices.saveOne(posseder);
                        tx.commit();

                        possederProfil.add(posseder);
                        posseder = new Posseder();
                        possederId = new PossederId();
                    }

                    for (Posseder po : possederProfil) {
                        tx.begin();
                        profil.ajouterPosseder(po);
                        profilServices.updateOne(profil);
                        tx.commit();
                    }

                    new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Ajout d'un profil : " + profil.getNomProf());
                    profil = new Profil();
                    droitListeSource = new ArrayList<>();
                    Mtm.mikiMessageInfo();
                } else {
                    profilServices.updateOne(profil);
                    tx.commit();

                    for (Posseder drt1 : possederSupp) {
                        tx.begin();
                        possederServices.deleteOne(drt1.getId());
                        tx.commit();
                    }

                    List<Posseder> possederProfil3;
                    possederProfil3 = new ArrayList<>();
                    for (Posseder poss : profilTampon.getPosseders()) {
                        possederProfil3.add(poss);
                    }

                    for (Posseder poss2 : possederProfil3) {
                        tx.begin();
                        profilTampon.supprimerPosseder(poss2);
                        profilServices.updateOne(profilTampon);
                        tx.commit();
                    }

                    List<Posseder> possederProfil2;
                    possederProfil2 = new ArrayList<>();
                    for (Droit drt3 : droitListeSource) {
                        tx.begin();
                        possederId.setProfilID(profil.getIdProf());
                        possederId.setDroitUtilID(drt3.getCodeDroit());
                        posseder.setId(possederId);
                        posseder.setProfil(profil);
                        posseder.setDroitUtilisateur(drt3);

                        this.possederServices.saveOne(posseder);
                        tx.commit();

                        possederProfil2.add(posseder);
                        posseder = new Posseder();
                        possederId = new PossederId();
                    }

                    for (Posseder po : possederProfil2) {
                        tx.begin();
                        profil.ajouterPosseder(po);
                        profilServices.updateOne(profil);
                        tx.commit();
                    }
                    if (profil.getNomProf().equals(profilTampon.getNomProf())) {
                        new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification des droits du profil :" + profil.getNomProf());
                    } else {
                        new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification de donnée du profil :" + profilTampon.getNomProf() + ", par :" + profil.getNomProf());
                    }
                    profil = new Profil();
                    profilTampon = new Profil();
                    droitListeSource = new ArrayList<>();
                    possederSupp = new ArrayList<>();
                    Mtm.mikiMessageInfo();
                }

            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors d'une opération sur le profil:" + profil.getNomProf());
            Mtm.mikiMessageError();
        }
        
       
    }

    
        else {
            Mtm.mikiLog4jMessageError();
    }

}

public void renvoieProfil(Profil pl) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            profil = pl;
            profilTampon = pl;
            for (Posseder drt : possederServices.getBy("profil", pl)) {
                droitListeSource.add(drt.getDroitUtilisateur());
            }
            possederSupp = possederServices.getBy("profil", pl);
        } else {
            Mtm.mikiLog4jMessageError();
        }
    }

    public void annulerProfil() {
        droitListeSource = new ArrayList<>();
        possederSupp = new ArrayList<>();
        profil = new Profil();
        profilTampon = new Profil();
    }

    public void resetPasswordUser(Utilisateur ur) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
            try {
                utilisateurPswd = ur;
                if (utilisateurPswd != null) {
                    utilisateurPswd.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                    utilisateurPswd.setReinitialiserPswd(true);
                    RequestContext.getCurrentInstance().execute("PF('confDialgModal').show();");
                } else {
                    System.out.println("Erreur");
                }
            } catch (Exception e) {
                Mtm.mikiMessageError();
                utilisateurPswd = new Utilisateur();
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }

    }

    public void confirmResetPasswordUser() {
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            utilisateurServices.updateOne(utilisateurPswd);
            tx.commit();
            new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Réinitialisation du mot de passe de l'utilisateur : " + utilisateurPswd.getLogin());
            utilisateurPswd = new Utilisateur();
            
            RequestContext.getCurrentInstance().execute("PF('confDialgModal').hide();");
            
            FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée avec succès !", "");
            FacesContext.getCurrentInstance().addMessage(null, message3);
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            utilisateurPswd = new Utilisateur();
            RequestContext.getCurrentInstance().execute("PF('confDialgModal').hide();");
            new Mtm().logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors de la réinitialisation du mot de passe de l'utilisateur: " + utilisateurPswd.getLogin());
            Mtm.mikiMessageError();
        }

    }

    public String actifLabel(boolean ac) {
        String testActif = ac ? "Actif" : "Inactif";
        return testActif;
    }

    public List<Droit> droitsUtilList(Profil grp) {
        List<Droit> droitUser = new ArrayList<>();

        try {
            if (grp.getPosseders().isEmpty() || grp.getPosseders() == null) {
                return null;
            } else {
                for (Posseder po : grp.getPosseders()) {
                    droitUser.add(po.getDroitUtilisateur());
                }
                return droitUser;
            }
        } catch (NullPointerException e) {
            return null;
        }

    }

//    public void onRowSelect(SelectEvent event) {
//        utilisateurProfil = utilisateur2;
//        RequestContext.getCurrentInstance().execute("jQuery('#SearchUser').modal('hide');");
//    }

    public void onRowUnselect(UnselectEvent event) {

    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateurProfil() {
        return utilisateurProfil;
    }

    public void setUtilisateurProfil(Utilisateur utilisateurProfil) {
        this.utilisateurProfil = utilisateurProfil;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Posseder getPosseder() {
        return posseder;
    }

    public void setPosseder(Posseder posseder) {
        this.posseder = posseder;
    }

    public PossederId getPossederId() {
        return possederId;
    }

    public void setPossederId(PossederId possederId) {
        this.possederId = possederId;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public List<Utilisateur> getUtilisateurListe() {
        return utilisateurServices.getAll("nom", true);
    }

    public void setUtilisateurListe(List<Utilisateur> utilisateurListe) {
        this.utilisateurListe = utilisateurListe;
    }

    public List<Droit> getDroitListe() {
        return droitServices.getNonBy("libDroit", "Tous");
    }

    public void setDroitListe(List<Droit> droitListe) {
        this.droitListe = droitListe;
    }

    public List<Droit> getDroitListeSource() {
        return droitListeSource;
    }

    public void setDroitListeSource(List<Droit> droitListeSource) {
        this.droitListeSource = droitListeSource;
    }

    public List<Profil> getProfilListe() {
        return profilServices.getNonBy("nomProf", "All_privilege");
    }

    public void setProfilListe(List<Profil> profilListe) {
        this.profilListe = profilListe;
    }

    public List<Poste> getPosteListe() {
        return posteServices.getAll("libPoste", true);
    }

    public void setPosteListe(List<Poste> posteListe) {
        this.posteListe = posteListe;
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

    public PossederSessionBeanLocal getPossederServices() {
        return possederServices;
    }

    public void setPossederServices(PossederSessionBeanLocal possederServices) {
        this.possederServices = possederServices;
    }

    public PosteSessionBeanLocal getPosteServices() {
        return posteServices;
    }

    public void setPosteServices(PosteSessionBeanLocal posteServices) {
        this.posteServices = posteServices;
    }

    public DroitSessionBeanLocal getDroitServices() {
        return droitServices;
    }

    public void setDroitServices(DroitSessionBeanLocal droitServices) {
        this.droitServices = droitServices;
    }

    public List<Droit> getDroitListeSupp() {
        return droitListeSupp;
    }

    public void setDroitListeSupp(List<Droit> droitListeSupp) {
        this.droitListeSupp = droitListeSupp;
    }

    public String getTofProfil() {
        return tofProfil;
    }

    public void setTofProfil(String tofProfil) {
        this.tofProfil = tofProfil;
    }

    public Utilisateur getUtilisateurPswd() {
        return utilisateurPswd;
    }

    public void setUtilisateurPswd(Utilisateur utilisateurPswd) {
        this.utilisateurPswd = utilisateurPswd;
    }

    public Utilisateur getUtilisateur2() {
        return utilisateur2;
    }

    public void setUtilisateur2(Utilisateur utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }

    public Utilisateur getUtilisateurTampon() {
        return utilisateurTampon;
    }

    public void setUtilisateurTampon(Utilisateur utilisateurTampon) {
        this.utilisateurTampon = utilisateurTampon;
    }

    public Profil getProfilTampon() {
        return profilTampon;
    }

    public void setProfilTampon(Profil profilTampon) {
        this.profilTampon = profilTampon;
    }

    public List<Utilisateur> getUtilisateurListeSansAdmin() {
        return utilisateurServices.getNonBy("login", "Administrateur", "login", true);
    }

    public void setUtilisateurListeSansAdmin(List<Utilisateur> utilisateurListeSansAdmin) {
        this.utilisateurListeSansAdmin = utilisateurListeSansAdmin;
    }

    public ConnexionManagedBean getConnexionMngdB() {
        return connexionMngdB;
    }

    public void setConnexionMngdB(ConnexionManagedBean connexionMngdB) {
        this.connexionMngdB = connexionMngdB;
    }

    public boolean isDisAdmin() {
        return disAdmin;
    }

    public void setDisAdmin(boolean disAdmin) {
        this.disAdmin = disAdmin;
    }

}
