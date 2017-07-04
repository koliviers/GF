/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Patient;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class PatientDaoBean extends GenericDaoBean<Patient, Long> implements PatientDaoBeanLocal{

    public PatientDaoBean() {
        
        super(Patient.class);
    }
    
    public PatientDaoBean(Class<Patient> entity){
        this.entityClass=entity;
    
    }
    
    
    
}
