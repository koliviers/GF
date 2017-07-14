/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;


import com.kol.gf.dao.bean.IAntecedent_FamilialDAO;
import com.kol.gf.entities.Antecedent_familial;
import com.kol.gf.entities.Antecedent_familial_Id;
import com.kol.gf.service.Antecedent_FamilialSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class Antecedent_FamilialSessionBean extends BaseServiceBeanImpl<Antecedent_familial, Antecedent_familial_Id> implements Antecedent_FamilialSessionBeanLocal {

    @EJB
    private IAntecedent_FamilialDAO dao;
    
    @Override
    protected BaseDaoBean<Antecedent_familial, Antecedent_familial_Id> getDao() {
        return dao;
    }

    
}
