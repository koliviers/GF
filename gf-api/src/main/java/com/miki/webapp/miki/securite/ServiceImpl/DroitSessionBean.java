/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.ServiceImpl;


import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import com.miki.webapp.miki.securite.Dao.IDroitDAO;
import com.miki.webapp.miki.securite.Service.DroitSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Droit;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class DroitSessionBean extends BaseServiceBeanImpl<Droit, Long> implements DroitSessionBeanLocal {

    @EJB
    private IDroitDAO dao;

    @Override
    protected BaseDaoBean<Droit, Long> getDao() {
        return dao;
    }
}
