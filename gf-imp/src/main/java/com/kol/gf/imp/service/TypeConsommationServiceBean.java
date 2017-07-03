/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.TypeConsommationDaoBeanLocal;
import com.kol.gf.entities.TypeConsommation;
import com.kol.gf.service.TypeConsommationServiceBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeConsommationServiceBean extends GenericServiceBean<TypeConsommation, Long> implements TypeConsommationServiceBeanLocal {

    @EJB
    private TypeConsommationDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<TypeConsommation, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeConsommation e) {
        return e.getId();
    }

}
