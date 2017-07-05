/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.dao.bean.RendezVousDaoBeanLocal;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.Patient;
import com.kol.gf.entities.Patient_intervenantid;
import com.kol.gf.entities.RendezVous;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kol
 */
@ManagedBean(name = "rdvbean")
@ViewScoped
public class RendezVousBean implements Serializable {

    private RendezVous rdvselect;
    private RendezVous rdv;
    private Intervenant intervenant;
    private Patient patient;
    private Patient_intervenantid id;
    private List<Intervenant> listintervenant;
    private List<Patient> listePatient;
    private List<RendezVous> listeRdv;

    @EJB
    private PatientDaoBeanLocal daoPatient;

    @EJB
    private IntervenantDaoBeanLocal daoIntervenant;

    @EJB
    private RendezVousDaoBeanLocal daoRdv;

    public RendezVousBean() {

        rdv = new RendezVous();
        intervenant = new Intervenant();
        patient = new Patient();
        listePatient = new ArrayList<Patient>();
        listeRdv = new ArrayList<RendezVous>();
        listintervenant = new ArrayList<Intervenant>();
        rdvselect = new RendezVous();
        id = new Patient_intervenantid();

    }

    /**
     * @return the rdv
     */
    public void addRdv() {

        try {
            getId().setId_intervenant(rdv.getIntervenant().getId());
            getId().setId_patient(rdv.getPatient().getId());
            rdv.setId(getId());
            System.out.println("" + rdv.getId().getId_intervenant());
            System.out.println("tste de lidee" + rdv.getId().getId_intervenant() + " " + rdv.getId().getId_patient());
            System.out.println("le patient " + rdv.getPatient().getNomPatient());
            System.out.println("l'intervenant " + rdv.getIntervenant().getNomIntervenant());

            this.daoRdv.addOne(rdv);
            rdv=new RendezVous();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteRdv(){
        
        try {
            this.daoRdv.deleteOne(rdvselect);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteOneRdv(){
        
        try {
            this.daoRdv.deleteOne(id);
            
        } catch (Exception e) {
        }
        
    }

    public List<RendezVous> getAllRdv() {

        listeRdv = this.daoRdv.getAll();
        return listeRdv;
    }

    public List<Patient> getAllPatient() {

        listePatient = this.daoPatient.getAll();
        return listePatient;
    }

    public List<Intervenant> getAllIntervenant() {
        listintervenant = this.daoIntervenant.getAll();
        return listintervenant;
    }

    public RendezVous getRdv() {
        return rdv;
    }

    /**
     * @param rdv the rdv to set
     */
    public void setRdv(RendezVous rdv) {
        this.rdv = rdv;
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
     * @return the listeRdv
     */
    public List<RendezVous> getListeRdv() {
        return listeRdv;
    }

    /**
     * @param listeRdv the listeRdv to set
     */
    public void setListeRdv(List<RendezVous> listeRdv) {
        this.listeRdv = listeRdv;
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
     * @return the daoRdv
     */
    public RendezVousDaoBeanLocal getDaoRdv() {
        return daoRdv;
    }

    /**
     * @param daoRdv the daoRdv to set
     */
    public void setDaoRdv(RendezVousDaoBeanLocal daoRdv) {
        this.daoRdv = daoRdv;
    }

    /**
     * @return the rdvselect
     */
    public RendezVous getRdvselect() {
        return rdvselect;
    }

    /**
     * @param rdvselect the rdvselect to set
     */
    public void setRdvselect(RendezVous rdvselect) {
        this.rdvselect = rdvselect;
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
