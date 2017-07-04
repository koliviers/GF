/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.ServiceImpl;


import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import com.miki.webapp.miki.securite.Dao.IPossederDAO;
import com.miki.webapp.miki.securite.Service.PossederSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Posseder;
import com.miki.webapp.miki.securite.entities.PossederId;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class PossederSessionBean extends BaseServiceBeanImpl<Posseder, PossederId> implements PossederSessionBeanLocal {

    @EJB
    private IPossederDAO dao;

    @Override
    protected BaseDaoBean<Posseder, PossederId> getDao() {
        return dao;
    }
}
