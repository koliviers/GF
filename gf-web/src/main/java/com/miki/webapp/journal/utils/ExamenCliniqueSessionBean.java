/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.journal.utils;

import com.kol.gf.dao.bean.IExamenCliniqueDAO;
import com.kol.gf.entities.ExamenClinique;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ExamenCliniqueSessionBean extends BaseServiceBeanImpl<ExamenClinique, Long> implements ExamenCliniqueSessionBeanLocal {

    @EJB
    private IExamenCliniqueDAO dao;

    @Override
    protected BaseDaoBean<ExamenClinique, Long> getDao() {
        return dao;
    }
    
    
}
