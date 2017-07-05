/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ConsultationDaoBeanLocal;
import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.entities.Consultation;
import com.kol.gf.entities.Patient_intervenantid;
import com.kol.gf.service.ConsultationServiceBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ConsultationServiceBean extends GenericServiceBean<Consultation, Patient_intervenantid> implements ConsultationServiceBeanLocal {

    @EJB
    private ConsultationDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Consultation, Patient_intervenantid> getDAO() {

        return this.dao;
    }

    @Override
    public Patient_intervenantid getId(Consultation e) {
        return e.getId();
    }

}
