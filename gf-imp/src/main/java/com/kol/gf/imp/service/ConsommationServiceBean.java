/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ConsommationDaoBeanLocal;
import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.entities.Consommation;
import com.kol.gf.service.ConsommationServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ConsommationServiceBean extends BaseServiceBeanImpl<Consommation, Long> implements ConsommationServiceBeanLocal {

    @EJB
    private ConsommationDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<Consommation, Long> getDao() {
        return dao;
    }

   
}
