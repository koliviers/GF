/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.ConsultationDaoBeanLocal;
import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.dao.bean.PathologieDaoBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.entities.Consultation;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.Patient_intervenantid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author kol
 */
@ManagedBean(name = "consBean")
public class ConsultationBean implements Serializable {

    private Consultation consultation;
    private Intervenant intervenant;
    private Patient patient;
    private Pathologie pathologie;
    private Patient_intervenantid id;

    private List<Intervenant> listintervenant;
    private List<Patient> listePatient;
    private List<Consultation> listeConsultation;
    private List<Pathologie> listePathologie;
    private Date madate;

    @EJB
    private PathologieDaoBeanLocal daoPathologie;

    @EJB
    private PatientDaoBeanLocal daoPatient;

    @EJB
    private IntervenantDaoBeanLocal daoIntervenant;

    @EJB
    private ConsultationDaoBeanLocal daoConsultation;

    public ConsultationBean() {

        intervenant = new Intervenant();
        patient = new Patient();
        pathologie = new Pathologie();
        consultation = new Consultation();
        listePatient = new ArrayList<Patient>();
        listintervenant = new ArrayList<Intervenant>();
        listeConsultation = new ArrayList<Consultation>();
        listePathologie = new ArrayList<Pathologie>();
        id=new Patient_intervenantid();
        madate=new Date();

    }

    /**
     * @return the consultation
     */
    public void addConsultation() {
        try {
            id.setId_intervenant(consultation.getIntervenant().getId());
            id.setId_patient(consultation.getPatient().getId());
            consultation.setId(id);
            this.daoConsultation.addOne(consultation);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setListePathologie(List<Pathologie> listePathologie) {
        this.listePathologie = listePathologie;
    }

    public void setMadate(Date madate) {
        this.madate = madate;
    }

    public void setDaoPathologie(PathologieDaoBeanLocal daoPathologie) {
        this.daoPathologie = daoPathologie;
    }

    public List<Pathologie> getListePathologie() {
        return listePathologie;
    }

    public Date getMadate() {
        return madate;
    }

    public PathologieDaoBeanLocal getDaoPathologie() {
        return daoPathologie;
    }

    public List<Pathologie> getAllPathologie() {

        listePathologie = this.daoPathologie.getAll();
        return listePathologie;
    }

    public void updateConsultation() {

        try {
            this.daoConsultation.updateOne(consultation);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public List<Patient> getAllPatient() {

        listePatient = this.daoPatient.getAll();
        return listePatient;
    }

    public List<Intervenant> getAllIntervenant() {
        listintervenant = this.daoIntervenant.getAll();
        return listintervenant;
    }

    public List<Consultation> getAllConsultation() {
        listeConsultation = this.daoConsultation.getAll();
        return listeConsultation;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    /**
     * @param consultation the consultation to set
     */
    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
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
     * @return the listintervenant
     */
    public List<Intervenant> getListintervenant() {
        return listintervenant;
    }

    /**
     * @param listintervenant the listintervenant to set
     */
    public void setListintervenant(List<Intervenant> listintervenant) {
        this.listintervenant = listintervenant;
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
     * @return the listeConsultation
     */
    public List<Consultation> getListeConsultation() {
        return listeConsultation;
    }

    /**
     * @param listeConsultation the listeConsultation to set
     */
    public void setListeConsultation(List<Consultation> listeConsultation) {
        this.listeConsultation = listeConsultation;
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
     * @return the daoConsultation
     */
    public ConsultationDaoBeanLocal getDaoConsultation() {
        return daoConsultation;
    }

    /**
     * @param daoConsultation the daoConsultation to set
     */
    public void setDaoConsultation(ConsultationDaoBeanLocal daoConsultation) {
        this.daoConsultation = daoConsultation;
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
     * @return the id
     */
    public Patient_intervenantid getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Patient_intervenantid id) {
        this.id = id;
    }

}
