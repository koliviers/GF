/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.IMedicamentDAO;
import com.kol.gf.entities.Medicament;
import com.kol.gf.service.MedicamentSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class MedicamentSessionBean extends BaseServiceBeanImpl<Medicament, Long> implements MedicamentSessionBeanLocal {

    @EJB
    private IMedicamentDAO dao;

    @Override
    protected BaseDaoBean<Medicament, Long> getDao() {
        return dao;
    }
    
    
}
