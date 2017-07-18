/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.ExamenParaclinique;
import com.kol.gf.service.ExamenParacliniqueSessionBeanLocal;
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
public class ExamenParaCliniqueManagedBean implements Serializable{

    private ExamenParaclinique examenParaclinique;
    private List<ExamenParaclinique> examenParacliniqueListe;
    
    @EJB
    private ExamenParacliniqueSessionBeanLocal examenParacliniqueServices;
    
    
    public ExamenParaCliniqueManagedBean() {
        examenParaclinique = new ExamenParaclinique();
        
        examenParacliniqueListe = new ArrayList<>();
    }
    
    public void gestionExamenParaclinique() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (examenParaclinique.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de l'examen paraclinique");
            } else {
                if (examenParaclinique.getId() == null) {
                    tx.begin();
                    examenParacliniqueServices.saveOne(examenParaclinique);
                    tx.commit();
                } else {
                    tx.begin();
                    examenParacliniqueServices.updateOne(examenParaclinique);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                examenParaclinique = new ExamenParaclinique();
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

    public void renvoieExamenParaclinique(ExamenParaclinique classT) {
        examenParaclinique = classT;
    }

    public void annulerExamenParaclinique() {
        examenParaclinique = new ExamenParaclinique();
    }
    

    public ExamenParaclinique getExamenParaclinique() {
        return examenParaclinique;
    }

    public void setExamenParaclinique(ExamenParaclinique examenParaclinique) {
        this.examenParaclinique = examenParaclinique;
    }

    public List<ExamenParaclinique> getExamenParacliniqueListe() {
        return examenParacliniqueServices.getAll("label", true);
    }

    public void setExamenParacliniqueListe(List<ExamenParaclinique> examenParacliniqueListe) {
        this.examenParacliniqueListe = examenParacliniqueListe;
    }

    public ExamenParacliniqueSessionBeanLocal getExamenParacliniqueServices() {
        return examenParacliniqueServices;
    }

    public void setExamenParacliniqueServices(ExamenParacliniqueSessionBeanLocal examenParacliniqueServices) {
        this.examenParacliniqueServices = examenParacliniqueServices;
    }
    
    
    
    
    
}
