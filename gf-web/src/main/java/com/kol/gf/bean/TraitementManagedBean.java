/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Traitement;
import com.kol.gf.service.TraitementServiceBeanLocal;
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
 * @author anonymousghost
 */
@ManagedBean
@ViewScoped
public class TraitementManagedBean implements Serializable {

    private Traitement traitement;
    private List<Traitement> traitementListe;

    @EJB
    private TraitementServiceBeanLocal traitementServices;

    public TraitementManagedBean() {
        traitement = new Traitement();
        traitementListe = new ArrayList<>();
    }

    public void gestionTraitement() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (traitement.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du traitement");
            } else {
                if (traitement.getId() == null) {
                    tx.begin();
                    traitementServices.saveOne(traitement);
                    tx.commit();
                } else {
                    tx.begin();
                    traitementServices.updateOne(traitement);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                traitement = new Traitement();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }
    
    public void renvoieTraitement(Traitement trait){
        traitement = trait;
    }

    public void annulerTraitement(){
        traitement = new Traitement();
    }
    
    
    
    
    
    
    
    public Traitement getTraitement() {
        return traitement;
    }

    public void setTraitement(Traitement traitement) {
        this.traitement = traitement;
    }

    public List<Traitement> getTraitementListe() {
        return traitementServices.getAll("label", true);
    }

    public void setTraitementListe(List<Traitement> traitementListe) {
        this.traitementListe = traitementListe;
    }

    public TraitementServiceBeanLocal getTraitementServices() {
        return traitementServices;
    }

    public void setTraitementServices(TraitementServiceBeanLocal traitementServices) {
        this.traitementServices = traitementServices;
    }

}
