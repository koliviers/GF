/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Consultation;
import com.kol.gf.entities.Patient;
import com.kol.gf.service.ConsultationServiceBeanLocal;
import com.kol.gf.service.DecesServiceBeanLocal;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.miki.webapp.core.Utils.ManipulationDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@ViewScoped
public class SuiviManagedBean implements Serializable {

    private List<Patient> patientListe;

    private List<Consultation> consultationListe;

    private Patient patient;

    private String etat;

    @EJB
    private PatientServiceBeanLocal patientServices;

    @EJB
    private ConsultationServiceBeanLocal consultationServices;

    @EJB
    private DecesServiceBeanLocal decesServices;

    public SuiviManagedBean() {

        patient = new Patient();

        patientListe = new ArrayList<>();

        consultationListe = new ArrayList<>();

        etat = "";
    }

    public void listenerPatient() {

        if (patient == null) {
            etat = "";
        } else {

            if (decesServices.getOneBy("patient", patient) == null) {

                List<Consultation> consulListe2 = consultationServices.getBy("patient", patient);

                if (consulListe2.isEmpty()) {
                    
                    etat = "";
                    
                } else {
                    
                    Date dateLimitePerduDeVu = ManipulationDate.ajouterMois(new Date(), -3);
                    
                    Date dateDernierConsul = consulListe2.stream()
                                              .reduce((c1,c2) -> c1.getDateConsultation().after(c2.getDateConsultation()) ? c1 : c2).get().getDateConsultation();
                    
                    if(dateLimitePerduDeVu.after(dateDernierConsul)){
                        etat = "perdu";
                    }else{
                        etat = "";
                    }
                }

            } else {
                etat = "decede";
            }

        }

    }

    public List<Consultation> getConsultationListeFiltre() {

        if (patient == null) {
            consultationListe = new ArrayList<>();

            return consultationListe;
        } else {

            List<Consultation> consulListe = consultationServices.getBy("patient", patient);

            consultationListe = consulListe.stream()
                    .sorted(Comparator.comparing(Consultation::getDateConsultation).reversed())
                    .collect(Collectors.toList());

            return consultationListe;
        }

    }

    public void annulerSuivi() {
        patient = new Patient();

        consultationListe = new ArrayList<>();
    }

    public List<Patient> getPatientListe() {
        return patientServices.getAll("nomPatient", true);
    }

    public void setPatientListe(List<Patient> patientListe) {
        this.patientListe = patientListe;
    }

    public List<Consultation> getConsultationListe() {
        return consultationListe;
    }

    public void setConsultationListe(List<Consultation> consultationListe) {
        this.consultationListe = consultationListe;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientServiceBeanLocal getPatientServices() {
        return patientServices;
    }

    public void setPatientServices(PatientServiceBeanLocal patientServices) {
        this.patientServices = patientServices;
    }

    public ConsultationServiceBeanLocal getConsultationServices() {
        return consultationServices;
    }

    public void setConsultationServices(ConsultationServiceBeanLocal consultationServices) {
        this.consultationServices = consultationServices;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public DecesServiceBeanLocal getDecesServices() {
        return decesServices;
    }

    public void setDecesServices(DecesServiceBeanLocal decesServices) {
        this.decesServices = decesServices;
    }

}
