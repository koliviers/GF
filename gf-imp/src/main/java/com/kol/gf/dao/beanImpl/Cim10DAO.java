/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ICim10DAO;
import com.kol.gf.entities.Cim10;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class Cim10DAO extends BaseDaoBeanImpl<Cim10, Long> implements ICim10DAO{

    public Cim10DAO() {
        super(Cim10.class);
    }
    
    
    
}
