/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.ServiceImpl;


import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import com.miki.webapp.miki.securite.Dao.IPosteDAO;
import com.miki.webapp.miki.securite.Service.PosteSessionBeanLocal;
import com.miki.webapp.miki.securite.entities.Poste;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class PosteSessionBean extends BaseServiceBeanImpl<Poste, Long> implements PosteSessionBeanLocal {

    @EJB
    private IPosteDAO dao;

    @Override
    protected BaseDaoBean<Poste, Long> getDao() {
        return dao;
    }
}
