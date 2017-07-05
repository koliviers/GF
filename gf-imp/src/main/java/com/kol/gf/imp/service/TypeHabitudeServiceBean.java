/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.TypeHabitudeDaoBeanLocal;
import com.kol.gf.entities.TypeHabitude;
import com.kol.gf.service.TypeHabitudeServiceBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeHabitudeServiceBean extends GenericServiceBean<TypeHabitude, Long> implements TypeHabitudeServiceBeanLocal {

    @EJB
    private TypeHabitudeDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<TypeHabitude, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeHabitude e) {
        return e.getId();
    }

}
