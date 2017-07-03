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
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ServiceServiceBean extends GenericServiceBean<Services, Long> implements ServiceServiceBeanLocal {

    @EJB
    private ServiceDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Services, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Services e) {

        return e.getId();
    }

}
