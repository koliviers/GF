/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;


import com.kol.gf.dao.bean.IOrdonnanceDAO;
import com.kol.gf.entities.Ordonnance;
import com.kol.gf.service.OrdonnanceSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class OrdonnanceSessionBean extends BaseServiceBeanImpl<Ordonnance, Long> implements OrdonnanceSessionBeanLocal {

    @EJB
    private IOrdonnanceDAO dao;
    
    @Override
    protected BaseDaoBean<Ordonnance, Long> getDao() {
        return dao;
    }

    
}
