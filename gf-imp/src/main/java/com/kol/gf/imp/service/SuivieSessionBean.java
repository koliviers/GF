/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.Isuivie;
import com.kol.gf.entities.Suivie;
import com.kol.gf.service.SuiviSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author koliviers
 */
@Stateless
public class SuivieSessionBean extends BaseServiceBeanImpl<Suivie, Long> implements SuiviSessionBeanLocal {

    @EJB
    private Isuivie dao;

    @Override
    protected BaseDaoBean<Suivie, Long> getDao() {
      return  this.dao;
    }

}
