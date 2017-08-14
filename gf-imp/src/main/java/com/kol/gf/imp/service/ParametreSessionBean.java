/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.IParametreDAO;
import com.kol.gf.entities.Parametre;
import com.kol.gf.service.ParametreSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ParametreSessionBean extends BaseServiceBeanImpl<Parametre, Long> implements ParametreSessionBeanLocal {

   @EJB
   private IParametreDAO dao;

    @Override
    protected BaseDaoBean<Parametre, Long> getDao() {
        return dao;
    }
    
}
