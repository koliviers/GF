/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Suivie;
import com.miki.webapp.core.Dao.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author koliviers
 */
@Local
public interface Isuivie extends BaseDaoBean<Suivie, Long>{
    
}
