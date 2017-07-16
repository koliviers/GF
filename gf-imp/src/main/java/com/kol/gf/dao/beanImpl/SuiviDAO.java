/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ISuiviDAO;
import com.kol.gf.entities.Suivi;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class SuiviDAO extends BaseDaoBeanImpl<Suivi, Long> implements ISuiviDAO{
    
    public SuiviDAO(){
        super(Suivi.class);
    }
}
