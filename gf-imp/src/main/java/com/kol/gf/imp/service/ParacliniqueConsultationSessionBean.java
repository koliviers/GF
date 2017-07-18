/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.IParacliniqueConsultationDAO;
import com.kol.gf.entities.ParacliniqueConsultation;
import com.kol.gf.entities.ParacliniqueConsultationId;
import com.kol.gf.service.ParacliniqueConsultationSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ParacliniqueConsultationSessionBean extends BaseServiceBeanImpl<ParacliniqueConsultation, ParacliniqueConsultationId> implements ParacliniqueConsultationSessionBeanLocal {

    @EJB
    private IParacliniqueConsultationDAO dao;

    @Override
    protected BaseDaoBean<ParacliniqueConsultation, ParacliniqueConsultationId> getDao() {
        return dao;
    }
    
    
}
