/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.TypeConsommation;
import com.kol.gf.service.TypeConsommationServiceBeanLocal;
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
public class TypeConsommationManagedBean implements Serializable{

    private TypeConsommation typeConsommation;
    
    private List<TypeConsommation> typeConsommationListe;
    
    @EJB
    private TypeConsommationServiceBeanLocal typeConsommationServices;
    
    
    public TypeConsommationManagedBean() {
        typeConsommation = new TypeConsommation();
        
        typeConsommationListe = new ArrayList<>();
    }
    
    
    public void gestionTypeConsommation(){
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (typeConsommation.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du type de consommation");
            } else {
                if (typeConsommation.getId() == null) {
                    tx.begin();
                    typeConsommationServices.saveOne(typeConsommation);
                    tx.commit();
                } else {
                    tx.begin();
                    typeConsommationServices.updateOne(typeConsommation);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                typeConsommation = new TypeConsommation();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(TypeConsommationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(TraitementManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }
    
    public void renvoieTypeConsommation(TypeConsommation trait){
        typeConsommation = trait;
    }

    public void annulerTypeConsommation(){
        typeConsommation = new TypeConsommation();
    }
    
    
    
    
    

    public TypeConsommation getTypeConsommation() {
        return typeConsommation;
    }

    public void setTypeConsommation(TypeConsommation typeConsommation) {
        this.typeConsommation = typeConsommation;
    }

    public List<TypeConsommation> getTypeConsommationListe() {
        return typeConsommationServices.getAll("label", true);
    }

    public void setTypeConsommationListe(List<TypeConsommation> typeConsommationListe) {
        this.typeConsommationListe = typeConsommationListe;
    }

    public TypeConsommationServiceBeanLocal getTypeConsommationServices() {
        return typeConsommationServices;
    }

    public void setTypeConsommationServices(TypeConsommationServiceBeanLocal typeConsommationServices) {
        this.typeConsommationServices = typeConsommationServices;
    }
    
    
    
    
    
    
    
}
