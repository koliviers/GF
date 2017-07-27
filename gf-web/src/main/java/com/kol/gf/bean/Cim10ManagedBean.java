/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Cim10;
import com.kol.gf.service.Cim10SessionBeanLocal;
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
public class Cim10ManagedBean implements Serializable{

    private Cim10 cim10;
    
    private List<Cim10> cim10Liste;
    
    @EJB
    private Cim10SessionBeanLocal cimServices;
    
    public Cim10ManagedBean() {
        cim10 = new Cim10();
        
        cim10Liste = new ArrayList<>();
    }
    
    public void gesitonCim10(){
        UserTransaction tx = TransactionManager.getUserTransaction();
        
        try {
            if(cim10.getLabel().trim().isEmpty()){
                Mtm.mikiMessageWarnSaisir("le nom de la pathologie");
            }else{
                if(cim10.getId() == null){
                    tx.begin();
                    cimServices.saveOne(cim10);
                    tx.commit();
                }else{
                    tx.begin();
                    cimServices.updateOne(cim10);
                    tx.commit();
                }
                
                Mtm.mikiMessageInfo();
                cim10 = new Cim10();
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
    
    public void renvoieCim(Cim10 cm){
        cim10 = cm;
    }
    
    public void annulerCim(){
        cim10 = new Cim10();
    }

    public Cim10 getCim10() {
        return cim10;
    }

    public void setCim10(Cim10 cim10) {
        this.cim10 = cim10;
    }

    public List<Cim10> getCim10Liste() {
        return cimServices.getAll("label", true);
    }

    public void setCim10Liste(List<Cim10> cim10Liste) {
        this.cim10Liste = cim10Liste;
    }

    public Cim10SessionBeanLocal getCimServices() {
        return cimServices;
    }

    public void setCimServices(Cim10SessionBeanLocal cimServices) {
        this.cimServices = cimServices;
    }
    
    
    
}
