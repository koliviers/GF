/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ICim10DAO;
import com.kol.gf.entities.Cim10;
import com.kol.gf.service.Cim10SessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class Cim10SessionBean extends BaseServiceBeanImpl<Cim10, Long> implements Cim10SessionBeanLocal {

    @EJB
    private ICim10DAO dao;

    @Override
    protected BaseDaoBean<Cim10, Long> getDao() {
        return dao;
    }
    
}
