/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.journal.ServiceImpl;


import com.miki.journal.Dao.IJournalDAO;
import com.miki.journal.Service.JournalSessionBeanLocal;
import com.miki.journal.entities.Journal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class JournalSessionBean extends BaseServiceBeanImpl<Journal, Long> implements JournalSessionBeanLocal {

    @EJB
    private IJournalDAO dao;

    @Override
    protected BaseDaoBean<Journal, Long> getDao() {
        return dao;
    }
}
