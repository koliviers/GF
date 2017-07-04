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
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ConsommationServiceBean extends GenericServiceBean<Consommation, Long> implements ConsommationServiceBeanLocal {

    @EJB
    private ConsommationDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Consommation, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Consommation e) {
        return e.getId();
    }

}
