/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Pathologie;
import com.kol.gf.service.PathologieServiceBeanLocal;
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
public class PathologieManagedBean implements Serializable{

    private Pathologie pathologie;
    
    private List<Pathologie> pathologieListe;
    
    @EJB
    private PathologieServiceBeanLocal pathologieService;
    
    
    
    public PathologieManagedBean() {
        pathologie = new Pathologie();
        
        pathologieListe = new ArrayList<>();
    }
    
    
    public void gestionPathologie(){
        UserTransaction tx = TransactionManager.getUserTransaction();
        
        try {
            if (pathologie.getNomPathologie().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de la pathologie");
            } else {
                if (pathologie.getId() == null) {
                    tx.begin();
                    pathologieService.saveOne(pathologie);
                    tx.commit();
                } else {
                    tx.begin();
                    pathologieService.updateOne(pathologie);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                pathologie = new Pathologie();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(PathologieManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(PathologieManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(PathologieManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }
    
    public void renvoiePathologie(Pathologie patho){
        pathologie = patho;
    }

    public void annulerPathologie(){
        pathologie = new Pathologie();
    }
    
    
    
    

    public Pathologie getPathologie() {
        return pathologie;
    }

    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    public List<Pathologie> getPathologieListe() {
        return pathologieService.getAll("nomPathologie", true);
    }

    public void setPathologieListe(List<Pathologie> pathologieListe) {
        this.pathologieListe = pathologieListe;
    }

    public PathologieServiceBeanLocal getPathologieService() {
        return pathologieService;
    }

    public void setPathologieService(PathologieServiceBeanLocal pathologieService) {
        this.pathologieService = pathologieService;
    }
    
    
    
    
    
    
    
}
