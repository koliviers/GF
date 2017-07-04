/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.TypeIntervenantDaoBeanLocal;
import com.kol.gf.entities.TypeIntervenant;
import com.kol.gf.service.TypeIntervenantServiceBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeIntervenantServiceBean extends GenericServiceBean<TypeIntervenant, Long> implements TypeIntervenantServiceBeanLocal {

    @EJB
    private TypeIntervenantDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<TypeIntervenant, Long> getDAO() {

        return this.dao;
    }

    @Override
    public Long getId(TypeIntervenant e) {

        return e.getId();
    }

}
