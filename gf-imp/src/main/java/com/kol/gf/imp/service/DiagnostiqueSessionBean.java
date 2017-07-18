/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.IDiagnostique;
import com.kol.gf.entities.Diagnostique;
import com.kol.gf.service.DiagnostiqueSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class DiagnostiqueSessionBean extends BaseServiceBeanImpl<Diagnostique, Long> implements DiagnostiqueSessionBeanLocal {

    @EJB
    private IDiagnostique dao;

    @Override
    protected BaseDaoBean<Diagnostique, Long> getDao() {
        return dao;
    }
    
    
}
