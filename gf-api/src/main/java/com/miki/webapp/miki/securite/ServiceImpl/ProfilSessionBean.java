/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.ServiceImpl;


import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import com.miki.webapp.miki.securite.Dao.IProfilDAO;
import com.miki.webapp.miki.securite.Service.ProfilSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Profil;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class ProfilSessionBean extends BaseServiceBeanImpl<Profil, Long> implements ProfilSessionBeanLocal {

    @EJB
    private IProfilDAO dao;

    @Override
    protected BaseDaoBean<Profil, Long> getDao() {
        return dao;
    }
}
