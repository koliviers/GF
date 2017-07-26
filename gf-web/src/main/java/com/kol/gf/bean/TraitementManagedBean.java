/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.service.TraitementServiceBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@ViewScoped
public class TraitementManagedBean implements Serializable {

    private TraitementMedicamenteux traitement;
    private List<TraitementMedicamenteux> traitementListe;

    @EJB
    private TraitementServiceBeanLocal traitementServices;

    public TraitementManagedBean() {
        traitement = new TraitementMedicamenteux();
        traitementListe = new ArrayList<>();
    }

//    public void gestionTraitement() {
//        UserTransaction tx = TransactionManager.getUserTransaction();
//
//        try {
//            if (traitement.getLabel().trim().isEmpty()) {
//                Mtm.mikiMessageWarnSaisir("le nom du traitement");
//            } else {
//                if (traitement.getId() == null) {
//                    tx.begin();
//                    traitementServices.saveOne(traitement);
//                    tx.commit();
//                } else {
//                    tx.begin();
//                    traitementServices.updateOne(traitement);
//                    tx.commit();
//                }
//
//                Mtm.mikiMessageInfo();
//                traitement = new TraitementMedicamenteux();
//            }
//        } catch (Exception ex) {
//            try {
//                tx.rollback();
//            } catch (IllegalStateException ex1) {
//                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
//            } catch (SecurityException ex1) {
//                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
//            } catch (SystemException ex1) {
//                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//            Mtm.mikiMessageError();
//        }
//    }
    
    public void renvoieTraitement(TraitementMedicamenteux trait){
        traitement = trait;
    }

    public void annulerTraitement(){
        traitement = new TraitementMedicamenteux();
    }
    
    
    
    
    
    
    
    public TraitementMedicamenteux getTraitement() {
        return traitement;
    }

    public void setTraitement(TraitementMedicamenteux traitement) {
        this.traitement = traitement;
    }

    public List<TraitementMedicamenteux> getTraitementListe() {
        return traitementServices.getAll("label", true);
    }

    public void setTraitementListe(List<TraitementMedicamenteux> traitementListe) {
        this.traitementListe = traitementListe;
    }

    public TraitementServiceBeanLocal getTraitementServices() {
        return traitementServices;
    }

    public void setTraitementServices(TraitementServiceBeanLocal traitementServices) {
        this.traitementServices = traitementServices;
    }

}
