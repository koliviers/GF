/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.service.ComorbiditeSessionBeanLocal;
import com.kol.gf.dao.bean.IComorbiditeDAO;
import com.kol.gf.entities.Comorbidite;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ComorbiditeSessionBean extends BaseServiceBeanImpl<Comorbidite, Long> implements ComorbiditeSessionBeanLocal {

    @EJB
    private IComorbiditeDAO dao;

    @Override
    protected BaseDaoBean<Comorbidite, Long> getDao() {
       return dao;
    }
    
    
}
