/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.ClasseTherapeutique;
import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.service.ClasseTheurapetiqueServiceBeanLocal;
import com.kol.gf.service.TraitementServiceBeanLocal;
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
public class ClasseTherapeutiqueManagedBean implements Serializable {

    private ClasseTherapeutique classeTherapeutique;

    private List<ClasseTherapeutique> classeTherapeutiqueListe;

    public ClasseTherapeutiqueManagedBean() {
        classeTherapeutique = new ClasseTherapeutique();

        classeTherapeutiqueListe = new ArrayList<>();
    }

    @EJB
    private TraitementServiceBeanLocal traitementServices;

    @EJB
    private ClasseTheurapetiqueServiceBeanLocal classeTherapeutiqueServices;

    public void gestionClasseTherapeutique() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (classeTherapeutique.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de la classe");
            } else {
                if (classeTherapeutique.getId() == null) {
                    tx.begin();
                    classeTherapeutiqueServices.saveOne(classeTherapeutique);
                    tx.commit();
                } else {
                    tx.begin();
                    classeTherapeutiqueServices.updateOne(classeTherapeutique);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                classeTherapeutique = new ClasseTherapeutique();
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

    public void renvoieClasseTherapeutique(ClasseTherapeutique classT) {
        classeTherapeutique = classT;
    }

    public void annulerClasseTherapeutique() {
        classeTherapeutique = new ClasseTherapeutique();
    }
    
    
    
    
    
    
    
    
    
    

    public ClasseTherapeutique getClasseTherapeutique() {
        return classeTherapeutique;
    }

    public void setClasseTherapeutique(ClasseTherapeutique classeTherapeutique) {
        this.classeTherapeutique = classeTherapeutique;
    }


    public List<ClasseTherapeutique> getClasseTherapeutiqueListe() {
        return classeTherapeutiqueServices.getAll("label", true);
    }

    public void setClasseTherapeutiqueListe(List<ClasseTherapeutique> classeTherapeutiqueListe) {
        this.classeTherapeutiqueListe = classeTherapeutiqueListe;
    }

    public TraitementServiceBeanLocal getTraitementServices() {
        return traitementServices;
    }

    public void setTraitementServices(TraitementServiceBeanLocal traitementServices) {
        this.traitementServices = traitementServices;
    }

    public ClasseTheurapetiqueServiceBeanLocal getClasseTherapeutiqueServices() {
        return classeTherapeutiqueServices;
    }

    public void setClasseTherapeutiqueServices(ClasseTheurapetiqueServiceBeanLocal classeTherapeutiqueServices) {
        this.classeTherapeutiqueServices = classeTherapeutiqueServices;
    }
    
    

}
