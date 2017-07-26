/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.Isuivie;
import com.kol.gf.entities.Suivie;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author koliviers
 */
@Stateless
public class SuivieDaoBean extends BaseDaoBeanImpl<Suivie, Long> implements Isuivie{
    
    
    public SuivieDaoBean(){
        super(Suivie.class);
    }
    
}
