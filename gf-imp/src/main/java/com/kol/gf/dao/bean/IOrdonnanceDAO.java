/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Ordonnance;
import com.miki.webapp.core.Dao.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface IOrdonnanceDAO extends BaseDaoBean<Ordonnance, Long>{
    
}
