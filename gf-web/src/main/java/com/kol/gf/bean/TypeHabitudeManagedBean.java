/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.TypeConsommation;
import com.kol.gf.entities.TypeHabitude;
import com.kol.gf.service.TypeConsommationServiceBeanLocal;
import com.kol.gf.service.TypeHabitudeServiceBeanLocal;
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
public class TypeHabitudeManagedBean implements Serializable{

    private TypeHabitude typeHabitude;
    
    private List<TypeHabitude> typeHabitudeListe;
    
    private List<TypeConsommation> typeConsommationListe;
    
    @EJB
    private TypeConsommationServiceBeanLocal typeConsommationServices;
    
    @EJB
    private TypeHabitudeServiceBeanLocal typeHabitudeServices;
    
    
    public TypeHabitudeManagedBean() {
        typeHabitude = new TypeHabitude();
        
        typeHabitudeListe = new ArrayList<>();
        
        typeConsommationListe = new ArrayList<>();
    }
    
    
    public void gestionTypeHabitude() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (typeHabitude.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom ");
            } else if (typeHabitude.getType_consommation() == null) {
                Mtm.mikiMessageWarnChoisir("le type de consommation");
            } else {
                if (typeHabitude.getId() == null) {
                    tx.begin();
                    typeHabitudeServices.saveOne(typeHabitude);
                    tx.commit();
                } else {
                    tx.begin();
                    typeHabitudeServices.updateOne(typeHabitude);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                typeHabitude = new TypeHabitude();
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

    public void renvoieTypeHabitude(TypeHabitude classT) {
        typeHabitude = classT;
    }

    public void annulerTypeHabitude() {
        typeHabitude = new TypeHabitude();
    }
    
    
    
    
    
    

    public TypeHabitude getTypeHabitude() {
        return typeHabitude;
    }

    public void setTypeHabitude(TypeHabitude typeHabitude) {
        this.typeHabitude = typeHabitude;
    }

    public List<TypeHabitude> getTypeHabitudeListe() {
        return typeHabitudeServices.getAll("label", true);
    }

    public void setTypeHabitudeListe(List<TypeHabitude> typeHabitudeListe) {
        this.typeHabitudeListe = typeHabitudeListe;
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

    public TypeHabitudeServiceBeanLocal getTypeHabitudeServices() {
        return typeHabitudeServices;
    }

    public void setTypeHabitudeServices(TypeHabitudeServiceBeanLocal typeHabitudeServices) {
        this.typeHabitudeServices = typeHabitudeServices;
    }
    
    
    
}
