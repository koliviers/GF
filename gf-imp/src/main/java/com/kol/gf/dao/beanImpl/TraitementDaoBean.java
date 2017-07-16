/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.TraitementDaoBeanLocal;
import com.kol.gf.entities.Traitement;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author koliviers
 */
@Stateless
public class TraitementDaoBean extends BaseDaoBeanImpl<Traitement, Long> implements TraitementDaoBeanLocal{
    
    
    public TraitementDaoBean(){
        super(Traitement.class);
    }
    
}
