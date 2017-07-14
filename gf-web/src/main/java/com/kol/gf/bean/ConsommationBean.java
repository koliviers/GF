/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Consommation;
import com.kol.gf.entities.TypeConsommation;
import com.kol.gf.service.ConsommationServiceBeanLocal;
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
 * @author koliviers
 */
@ManagedBean
@ViewScoped
public class ConsommationBean implements Serializable{

    private Consommation consommation;
    
    private List<Consommation> consommationListe;
    
    private List<TypeConsommation> typeConsommationListe;
    
    @EJB
    private TypeConsommationServiceBeanLocal typeConsommationServices;
    
    @EJB
    private ConsommationServiceBeanLocal consommationServices;
    
    public ConsommationBean() {
        
        consommation = new Consommation();
        
        consommationListe = new ArrayList<>();
        
        typeConsommationListe = new ArrayList<>();
    }
    
    public void gestionConsommation() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (consommation.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de la consommation");
            } else if (consommation.getType_consommation() == null) {
                Mtm.mikiMessageWarnChoisir("le type de consommation");
            } else {
                if (consommation.getId() == null) {
                    tx.begin();
                    consommationServices.saveOne(consommation);
                    tx.commit();
                } else {
                    tx.begin();
                    consommationServices.updateOne(consommation);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                consommation = new Consommation();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(ConsommationBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(ConsommationBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(ConsommationBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }

    public void renvoieConsommation(Consommation classT) {
        consommation = classT;
    }

    public void annulerConsommation() {
        consommation = new Consommation();
    }
    
    
    
    
    
    
    

    public Consommation getConsommation() {
        return consommation;
    }

    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

    public List<Consommation> getConsommationListe() {
        return consommationServices.getAll("label", true);
    }

    public void setConsommationListe(List<Consommation> consommationListe) {
        this.consommationListe = consommationListe;
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

    public ConsommationServiceBeanLocal getConsommationServices() {
        return consommationServices;
    }

    public void setConsommationServices(ConsommationServiceBeanLocal consommationServices) {
        this.consommationServices = consommationServices;
    }
    
    
    
    
}
