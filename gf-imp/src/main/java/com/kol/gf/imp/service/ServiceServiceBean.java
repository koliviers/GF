/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.ServiceDaoBeanLocal;
import com.kol.gf.entities.Services;
import com.kol.gf.service.ServiceServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ServiceServiceBean extends BaseServiceBeanImpl<Services, Long> implements ServiceServiceBeanLocal {

    @EJB
    private ServiceDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<Services, Long> getDao() {
        return dao;
    }

    

}
