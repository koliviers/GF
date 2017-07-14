/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;


import com.kol.gf.dao.bean.ISuiviDAO;
import com.kol.gf.entities.Suivi;
import com.kol.gf.service.SuiviSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class SuiviSessionBean extends BaseServiceBeanImpl<Suivi, Long> implements SuiviSessionBeanLocal {
    
    @EJB
    private ISuiviDAO dao;

    @Override
    protected BaseDaoBean<Suivi, Long> getDao() {
       return dao;
    }

   
}
