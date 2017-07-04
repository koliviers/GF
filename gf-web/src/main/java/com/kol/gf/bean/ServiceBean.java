/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.ServiceDaoBeanLocal;
import com.kol.gf.entities.Services;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.eclipse.persistence.exceptions.EntityManagerSetupException;

/**
 *
 * @author kol
 */
@ManagedBean(name = "serbean")
@ViewScoped
public class ServiceBean implements Serializable {

    private Services service;

    private List<Services> listServices;
    private Services serviceSelect;

    @EJB
    private ServiceDaoBeanLocal daoService;

    public ServiceBean() {

        service = new Services();
        listServices = new ArrayList<Services>();
        this.serviceSelect = new Services();

    }

    @PostConstruct
    public void init() {
        this.service = new Services();
        this.serviceSelect=new Services();

    }

    public void addService() {

        try {

            this.getDaoService().addOne(getService());
            System.out.println("operation reussi");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Enregistrement avec succes "));
            service = new Services();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateService() {
        try {
            this.daoService.updateOne(serviceSelect);
            FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage("Mise a jour Reussi "));
            serviceSelect = new Services();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Services> getAllService() {
        setListServices(this.getDaoService().getAll());
        return getListServices();
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
        return listServices;
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

    /**
     * @return the serviceSelect
     */
    public Services getServiceSelect() {
        return serviceSelect;
    }

    /**
     * @param serviceSelect the serviceSelect to set
     */
    public void setServiceSelect(Services serviceSelect) {
        this.serviceSelect = serviceSelect;
    }

}
