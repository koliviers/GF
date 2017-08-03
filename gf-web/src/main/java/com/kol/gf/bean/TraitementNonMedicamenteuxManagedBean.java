/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.TraitementNonMedicamenteux;
import com.kol.gf.service.TraitementNonMedicamenteuxSessionBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
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
public class TraitementNonMedicamenteuxManagedBean implements Serializable{

    private TraitementNonMedicamenteux traitementNonMedicamenteux;
    
    private List<TraitementNonMedicamenteux> traitementNonMedicamenteuxListe;
    
    @EJB
    private TraitementNonMedicamenteuxSessionBeanLocal traitementNonMedicamenteuxServices;
    
    public TraitementNonMedicamenteuxManagedBean() {
        traitementNonMedicamenteux = new TraitementNonMedicamenteux();
        
        traitementNonMedicamenteuxListe = new ArrayList<>();
    }
    
    public void gestionTraitementNonMedicamenteux() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (traitementNonMedicamenteux.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du traitement");
            } else {
                if (traitementNonMedicamenteux.getId() == null) {
                    tx.begin();
                    traitementNonMedicamenteuxServices.saveOne(traitementNonMedicamenteux);
                    tx.commit();
                } else {
                    tx.begin();
                    traitementNonMedicamenteuxServices.updateOne(traitementNonMedicamenteux);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
               traitementNonMedicamenteux = new TraitementNonMedicamenteux();
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
    }

    public void renvoieTraitementNonMedicamenteux(TraitementNonMedicamenteux classT) {
        traitementNonMedicamenteux = classT;
    }

    public void annulerTraitementNonMedicamenteux() {
        traitementNonMedicamenteux = new TraitementNonMedicamenteux();
    }

    public TraitementNonMedicamenteux getTraitementNonMedicamenteux() {
        return traitementNonMedicamenteux;
    }

    public void setTraitementNonMedicamenteux(TraitementNonMedicamenteux traitementNonMedicamenteux) {
        this.traitementNonMedicamenteux = traitementNonMedicamenteux;
    }

    public List<TraitementNonMedicamenteux> getTraitementNonMedicamenteuxListe() {
        return traitementNonMedicamenteuxServices.getAll("label", true);
    }

    public void setTraitementNonMedicamenteuxListe(List<TraitementNonMedicamenteux> traitementNonMedicamenteuxListe) {
        this.traitementNonMedicamenteuxListe = traitementNonMedicamenteuxListe;
    }

    public TraitementNonMedicamenteuxSessionBeanLocal getTraitementNonMedicamenteuxServices() {
        return traitementNonMedicamenteuxServices;
    }

    public void setTraitementNonMedicamenteuxServices(TraitementNonMedicamenteuxSessionBeanLocal traitementNonMedicamenteuxServices) {
        this.traitementNonMedicamenteuxServices = traitementNonMedicamenteuxServices;
    }
    
    
    
    
}
