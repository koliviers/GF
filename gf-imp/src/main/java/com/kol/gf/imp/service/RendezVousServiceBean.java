/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.RendezVousDaoBeanLocal;
import com.kol.gf.entities.RendezVous;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class RendezVousServiceBean extends GenericServiceBean<RendezVous, Long> implements RendezVousServiceBeanLocal {

    @EJB
    private RendezVousDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<RendezVous, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(RendezVous e) {
        return null;
    }

}
