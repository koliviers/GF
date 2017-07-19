/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Patient;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@RequestScoped
public class PatientViewManagedBean implements Serializable{

    private Patient patient;
    
    public PatientViewManagedBean() {
        patient = new Patient();
    }
    
    public String informationPatient(Patient pat){
        String page = null;
        System.out.println(pat);
        if(pat != null){
           patient = pat; 
           page = "/gf/patient/InformationPatient.xhtml";
        }
        return page;
    }
    
    public String retour(){
        return "/gf/patient/gestionPat.xhtml?faces-redirect=true";
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
    
}
