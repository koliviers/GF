/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.ServiceImpl;


import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import com.miki.webapp.miki.securite.Dao.IUtilisateurDAO;
import com.miki.webapp.miki.securite.Service.UtilisateurSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class UtilisateurSessionBean extends BaseServiceBeanImpl<Utilisateur, Long> implements UtilisateurSessionBeanLocal {

    @EJB
    private IUtilisateurDAO dao;

    @Override
    protected BaseDaoBean<Utilisateur, Long> getDao() {
        return dao;
    }
}
