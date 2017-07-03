/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.dao.bean.ServiceDaoBeanLocal;
import com.kol.gf.dao.bean.TypeIntervenantDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Services;
import com.kol.gf.entities.TypeIntervenant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kol
 */
@ManagedBean(name = "interbean")
@ViewScoped
public class IntervenantBean implements Serializable {

    private Intervenant intervenant;
    private Intervenant intervenanselect;
    private List<Intervenant> listeIntervenant;
    private TypeIntervenant typeintervenant;
    private Services services;
    private List<Services> listeservice;
    private List<TypeIntervenant> listetypeIntervenant;

    @EJB
    private IntervenantDaoBeanLocal daoIntervenant;

    @EJB
    private TypeIntervenantDaoBeanLocal daotypeIntervenant;

    @EJB
    private ServiceDaoBeanLocal daoservice;

    public IntervenantBean() {
        intervenant = new Intervenant();
        services = new Services();
        typeintervenant = new TypeIntervenant();
        listeIntervenant = new ArrayList<Intervenant>();
        listeservice = new ArrayList<Services>();
        listetypeIntervenant = new ArrayList<TypeIntervenant>();
        intervenanselect=new Intervenant();

    }

    public void addIntervenant() {
        try {
            this.getDaoIntervenant().addOne(getIntervenant());

            FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage("Enregistrement avec succes "));
            intervenant=new Intervenant();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Intervenant> getAllIntervenant() {
        setListeIntervenant(this.getDaoIntervenant().getAll());

        return getListeIntervenant();
    }

    public List<Services> getAllService() {

        setListeservice(this.getDaoservice().getAll());
        return getListeservice();
    }

    public List<TypeIntervenant> getAllTypeIntervenant() {
        setListetypeIntervenant(this.getDaotypeIntervenant().getAll());
        return getListetypeIntervenant();
    }

    public void updateIntervenant() {
        try {
            this.getDaoIntervenant().updateOne(intervenanselect);
             FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage("Enregistrement avec succes "));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return the intervenant
     */
    public Intervenant getIntervenant() {
        return intervenant;
    }

    /**
     * @param intervenant the intervenant to set
     */
    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * @return the listeIntervenant
     */
    public List<Intervenant> getListeIntervenant() {
        return listeIntervenant;
    }

    /**
     * @param listeIntervenant the listeIntervenant to set
     */
    public void setListeIntervenant(List<Intervenant> listeIntervenant) {
        this.listeIntervenant = listeIntervenant;
    }

    /**
     * @return the typeintervenant
     */
    public TypeIntervenant getTypeintervenant() {
        return typeintervenant;
    }

    /**
     * @param typeintervenant the typeintervenant to set
     */
    public void setTypeintervenant(TypeIntervenant typeintervenant) {
        this.typeintervenant = typeintervenant;
    }

    /**
     * @return the services
     */
    public Services getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(Services services) {
        this.services = services;
    }

    /**
     * @return the listeservice
     */
    public List<Services> getListeservice() {
        return listeservice;
    }

    /**
     * @param listeservice the listeservice to set
     */
    public void setListeservice(List<Services> listeservice) {
        this.listeservice = listeservice;
    }

    /**
     * @return the listetypeIntervenant
     */
    public List<TypeIntervenant> getListetypeIntervenant() {
        return listetypeIntervenant;
    }

    /**
     * @param listetypeIntervenant the listetypeIntervenant to set
     */
    public void setListetypeIntervenant(List<TypeIntervenant> listetypeIntervenant) {
        this.listetypeIntervenant = listetypeIntervenant;
    }

    /**
     * @return the daoIntervenant
     */
    public IntervenantDaoBeanLocal getDaoIntervenant() {
        return daoIntervenant;
    }

    /**
     * @param daoIntervenant the daoIntervenant to set
     */
    public void setDaoIntervenant(IntervenantDaoBeanLocal daoIntervenant) {
        this.daoIntervenant = daoIntervenant;
    }

    /**
     * @return the daotypeIntervenant
     */
    public TypeIntervenantDaoBeanLocal getDaotypeIntervenant() {
        return daotypeIntervenant;
    }

    /**
     * @param daotypeIntervenant the daotypeIntervenant to set
     */
    public void setDaotypeIntervenant(TypeIntervenantDaoBeanLocal daotypeIntervenant) {
        this.daotypeIntervenant = daotypeIntervenant;
    }

    /**
     * @return the daoservice
     */
    public ServiceDaoBeanLocal getDaoservice() {
        return daoservice;
    }

    /**
     * @param daoservice the daoservice to set
     */
    public void setDaoservice(ServiceDaoBeanLocal daoservice) {
        this.daoservice = daoservice;
    }

    /**
     * @return the intervenanselect
     */
    public Intervenant getIntervenanselect() {
        return intervenanselect;
    }

    /**
     * @param intervenanselect the intervenanselect to set
     */
    public void setIntervenanselect(Intervenant intervenanselect) {
        this.intervenanselect = intervenanselect;
    }

}
