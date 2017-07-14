/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.service.IntervenantServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class IntervenantServiceBean extends BaseServiceBeanImpl<Intervenant, Long> implements IntervenantServiceBeanLocal {

    @EJB
    private IntervenantDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<Intervenant, Long> getDao() {
        return dao;
    }

   

}
