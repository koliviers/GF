/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.ServiceDaoBeanLocal;
import com.kol.gf.entities.Services;
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
 * @author kol
 */
@ManagedBean
@ViewScoped
public class ServiceBean implements Serializable {

    private Services service;

    private List<Services> listServices;

    @EJB
    private ServiceDaoBeanLocal daoService;

    public ServiceBean() {

        service = new Services();
        listServices = new ArrayList<>();

    }

   public void gestionService(){
       UserTransaction tx = TransactionManager.getUserTransaction();
       
       try {
           if(service.getCode().trim().isEmpty()){
               Mtm.mikiMessageWarnSaisir("le code du service");
           }else if(service.getNomService().trim().isEmpty()){
               Mtm.mikiMessageWarnSaisir("le nom du service");
           }else{
               if(service.getId() == null){
                   
                   tx.begin();
                   daoService.saveOne(service);
                   tx.commit();
                   
                   Mtm.mikiMessageInfo();
                   service = new Services();
                   
               }else{
                   
                   tx.begin();
                   daoService.updateOne(service);
                   tx.commit();
                   
                   Mtm.mikiMessageInfo();
                   service = new Services();
                   
               }
           }
       } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(ServiceBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(ServiceBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(ServiceBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
   }
   
   public void renvoieService(Services sr){
       service = sr;
   }
   
   public void annulerService(){
       service = new Services();
   }
   
   
   

    /**
     * @return the service
     */
    public Services getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Services service) {
        this.service = service;
    }

    /**
     * @return the listServices
     */
    public List<Services> getListServices() {
        return daoService.getAll("code", true);
    }

    /**
     * @param listServices the listServices to set
     */
    public void setListServices(List<Services> listServices) {
        this.listServices = listServices;
    }

    /**
     * @return the daoService
     */
    public ServiceDaoBeanLocal getDaoService() {
        return daoService;
    }

    /**
     * @param daoService the daoService to set
     */
    public void setDaoService(ServiceDaoBeanLocal daoService) {
        this.daoService = daoService;
    }

    

}
