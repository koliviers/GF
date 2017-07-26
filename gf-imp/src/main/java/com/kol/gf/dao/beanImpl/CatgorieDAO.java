/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ICategorieDAO;
import com.kol.gf.entities.Categorie;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class CatgorieDAO extends BaseDaoBeanImpl<Categorie, Long> implements ICategorieDAO{

    public CatgorieDAO() {
        super(Categorie.class);
    }
    
    
    
}
