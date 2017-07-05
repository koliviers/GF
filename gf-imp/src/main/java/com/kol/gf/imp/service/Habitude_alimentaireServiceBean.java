/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.Habitude_alimentaireDaoBeanLocal;
import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.kol.gf.service.Habitude_alimentaireServiceBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class Habitude_alimentaireServiceBean extends GenericServiceBean<Habitude_alimentaire, Habitude_alimentaireId> implements Habitude_alimentaireServiceBeanLocal {

    @EJB
    private Habitude_alimentaireDaoBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Habitude_alimentaire, Habitude_alimentaireId> getDAO() {
        return this.dao;
    }

    @Override
    public Habitude_alimentaireId getId(Habitude_alimentaire e) {
        return e.getId();
    }

}
