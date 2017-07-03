/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.ConsommationDaoBeanLocal;
import com.kol.gf.dao.bean.PathologieDaoBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.dao.bean.TypeConsommationDaoBeanLocal;
import com.kol.gf.entities.Consommation;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.TypeConsommation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kol
 */
@ManagedBean(name = "patientBean")
@ViewScoped
public class PatientBean implements Serializable {

    private Patient patient;

    private Pathologie pathologie;

    private Consommation consommation;
    private TypeConsommation typecons;

    private List<Patient> listePatient;
    private List<Pathologie> listePathologie;
    private List<Consommation> listeConsommation;
    
    private List<TypeConsommation> liteType;

    @EJB
    private PatientDaoBeanLocal daoPatient;

    @EJB
    private PathologieDaoBeanLocal daoPathologie;
    @EJB
    private ConsommationDaoBeanLocal daoConsommation;
    
    @EJB
    private TypeConsommationDaoBeanLocal daotype;

    public PatientBean() {

        patient = new Patient();
        pathologie = new Pathologie();
        consommation = new Consommation();
        listePatient = new ArrayList<Patient>();
        listePathologie = new ArrayList<Pathologie>();
        listeConsommation = new ArrayList<Consommation>();
        typecons=new TypeConsommation();
        liteType=new ArrayList<TypeConsommation>();
    }

    @PostConstruct
    public void init() {
        patient = new Patient();

    }

    public void addPatient() {

        try {
            this.daoPatient.addOne(patient);
            
            
            FacesMessage msg = new FacesMessage("Successful", "Welcome :" + patient.getNomPatient());
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatient() {

        listePatient = this.daoPatient.getAll();
        return listePatient;
    }

    public List<Pathologie> getAllPathologie() {
        listePathologie = this.daoPathologie.getAll();
        return listePathologie;
    }

    public List<Consommation> getAllConsommation() {
        listeConsommation = this.daoConsommation.getAll();
        return listeConsommation;
    }

    public List<TypeConsommation> getAllTypeConsommations(){
        liteType=this.getDaotype().getAll();
        return liteType;
    }
    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the pathologie
     */
    public Pathologie getPathologie() {
        return pathologie;
    }

    /**
     * @param pathologie the pathologie to set
     */
    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    /**
     * @return the consommation
     */
    public Consommation getConsommation() {
        return consommation;
    }

    /**
     * @param consommation the consommation to set
     */
    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

    /**
     * @return the listePatient
     */
    public List<Patient> getListePatient() {
        return listePatient;
    }

    /**
     * @param listePatient the listePatient to set
     */
    public void setListePatient(List<Patient> listePatient) {
        this.listePatient = listePatient;
    }

    /**
     * @return the listePathologie
     */
    public List<Pathologie> getListePathologie() {
        return listePathologie;
    }

    /**
     * @param listePathologie the listePathologie to set
     */
    public void setListePathologie(List<Pathologie> listePathologie) {
        this.listePathologie = listePathologie;
    }

    /**
     * @return the listeConsommation
     */
    public List<Consommation> getListeConsommation() {
        return listeConsommation;
    }

    /**
     * @param listeConsommation the listeConsommation to set
     */
    public void setListeConsommation(List<Consommation> listeConsommation) {
        this.listeConsommation = listeConsommation;
    }

    /**
     * @return the daoPatient
     */
    public PatientDaoBeanLocal getDaoPatient() {
        return daoPatient;
    }

    /**
     * @param daoPatient the daoPatient to set
     */
    public void setDaoPatient(PatientDaoBeanLocal daoPatient) {
        this.daoPatient = daoPatient;
    }

    /**
     * @return the daoPathologie
     */
    public PathologieDaoBeanLocal getDaoPathologie() {
        return daoPathologie;
    }

    /**
     * @param daoPathologie the daoPathologie to set
     */
    public void setDaoPathologie(PathologieDaoBeanLocal daoPathologie) {
        this.daoPathologie = daoPathologie;
    }

    /**
     * @return the daoConsommation
     */
    public ConsommationDaoBeanLocal getDaoConsommation() {
        return daoConsommation;
    }

    /**
     * @param daoConsommation the daoConsommation to set
     */
    public void setDaoConsommation(ConsommationDaoBeanLocal daoConsommation) {
        this.daoConsommation = daoConsommation;
    }

    /**
     * @return the typecons
     */
    public TypeConsommation getTypecons() {
        return typecons;
    }

    /**
     * @param typecons the typecons to set
     */
    public void setTypecons(TypeConsommation typecons) {
        this.typecons = typecons;
    }

    /**
     * @return the daotype
     */
    public TypeConsommationDaoBeanLocal getDaotype() {
        return daotype;
    }

    /**
     * @param daotype the daotype to set
     */
    public void setDaotype(TypeConsommationDaoBeanLocal daotype) {
        this.daotype = daotype;
    }

}
