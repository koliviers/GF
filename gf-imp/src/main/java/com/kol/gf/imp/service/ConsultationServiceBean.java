/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ConsultationDaoBeanLocal;
import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.entities.Consultation;
import com.kol.gf.service.ConsultationServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ConsultationServiceBean extends BaseServiceBeanImpl<Consultation, Long> implements ConsultationServiceBeanLocal {

    @EJB
    private ConsultationDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<Consultation, Long> getDao() {
        return dao;
    }

    

}
