/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Intervenant;
import com.miki.webapp.core.Dao.BaseDaoBean;
import javax.ejb.Local;

/**
 *
 * @author kol
 */
@Local
public interface IntervenantDaoBeanLocal extends BaseDaoBean<Intervenant, Long>{
    
}
