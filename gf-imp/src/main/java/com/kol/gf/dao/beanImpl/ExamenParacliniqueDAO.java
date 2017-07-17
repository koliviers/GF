/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.IExamenParacliniqueDAO;
import com.kol.gf.entities.ExamenParaclinique;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ExamenParacliniqueDAO  extends BaseDaoBeanImpl<ExamenParaclinique, Long> implements IExamenParacliniqueDAO{

    public ExamenParacliniqueDAO() {
        super(ExamenParaclinique.class);
    }
    
}
