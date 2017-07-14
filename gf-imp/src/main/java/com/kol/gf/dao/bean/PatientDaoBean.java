/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Patient;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class PatientDaoBean extends BaseDaoBeanImpl<Patient, Long> implements PatientDaoBeanLocal{

    public PatientDaoBean() {
        
        super(Patient.class);
    }
    
    
    
}
