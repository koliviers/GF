/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.TypeIntervenant;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeIntervenantDaoBean extends BaseDaoBeanImpl<TypeIntervenant, Long> implements TypeIntervenantDaoBeanLocal{

    public TypeIntervenantDaoBean() {
        super(TypeIntervenant.class);
    }

    
    
}
