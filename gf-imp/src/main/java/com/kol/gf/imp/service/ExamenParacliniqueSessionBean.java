/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.IExamenParacliniqueDAO;
import com.kol.gf.entities.ExamenParaclinique;
import com.kol.gf.service.ExamenParacliniqueSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ExamenParacliniqueSessionBean extends BaseServiceBeanImpl<ExamenParaclinique, Long> implements ExamenParacliniqueSessionBeanLocal {

    @EJB
    private IExamenParacliniqueDAO dao;

    @Override
    protected BaseDaoBean<ExamenParaclinique, Long> getDao() {
        return dao;
    }
    
    
}
