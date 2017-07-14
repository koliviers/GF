/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.entities.Patient;
import com.kol.gf.service.PatientServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class PatientServiceBean extends BaseServiceBeanImpl<Patient, Long> implements PatientServiceBeanLocal {

    @EJB
    PatientDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<Patient, Long> getDao() {
        return dao;
    }

    
    

}
