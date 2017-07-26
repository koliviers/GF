/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.IComorbiditeDAO;
import com.kol.gf.entities.Comorbidite;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ComorbiditeDao extends BaseDaoBeanImpl<Comorbidite, Long> implements IComorbiditeDAO{

    public ComorbiditeDao() {
        super(Comorbidite.class);
    }
 
    
}
