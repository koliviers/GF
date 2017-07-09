/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.DecesDaoBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.entities.Deces;
import com.kol.gf.entities.Patient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author koliviers
 */
@ManagedBean(name = "decesbean")
public class DecesBean implements Serializable {

    private Deces deces;
    private Patient patient;
    private List<Deces> listDeces;
    private List<Patient> listpatient;

    @EJB
    private DecesDaoBeanLocal daoDeces;

    @EJB
    private PatientDaoBeanLocal daopatient;

    public DecesBean() {

        deces = new Deces();

        patient = new Patient();
        listDeces = new ArrayList<>();
        listpatient = new ArrayList<>();

    }

    public void addDeces() {

        try {

            this.daoDeces.saveOne(deces);
        } catch (Exception e) {
        }
    }
    
    public List<Deces> getAllDeces(){
        
     listDeces=this.daoDeces.getAll();
     return listDeces;
    }
    
    public List<Patient> getAllPatient(){
        listpatient=this.daopatient.getAll();
        
        return listpatient;
    }

    /**
     * @return the deces
     */
    public Deces getDeces() {
        return deces;
    }

    /**
     * @param deces the deces to set
     */
    public void setDeces(Deces deces) {
        this.deces = deces;
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
     * @return the listDeces
     */
    public List<Deces> getListDeces() {
      listDeces=this.daoDeces.getAll();
        return listDeces;
    }

    /**
     * @param listDeces the listDeces to set
     */
    public void setListDeces(List<Deces> listDeces) {
        this.listDeces = listDeces;
    }

    /**
     * @return the listpatient
     */
    public List<Patient> getListpatient() {
        return listpatient;
    }

    /**
     * @param listpatient the listpatient to set
     */
    public void setListpatient(List<Patient> listpatient) {
        this.listpatient = listpatient;
    }

    /**
     * @return the daoDeces
     */
    public DecesDaoBeanLocal getDaoDeces() {
        return daoDeces;
    }

    /**
     * @param daoDeces the daoDeces to set
     */
    public void setDaoDeces(DecesDaoBeanLocal daoDeces) {
        this.daoDeces = daoDeces;
    }

    /**
     * @return the daopatient
     */
    public PatientDaoBeanLocal getDaopatient() {
        return daopatient;
    }

    /**
     * @param daopatient the daopatient to set
     */
    public void setDaopatient(PatientDaoBeanLocal daopatient) {
        this.daopatient = daopatient;
    }

}
