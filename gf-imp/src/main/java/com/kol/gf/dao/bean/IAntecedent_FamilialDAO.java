/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Antecedent_familial;
import com.kol.gf.entities.Antecedent_familial_Id;
import com.miki.webapp.core.Dao.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface IAntecedent_FamilialDAO extends BaseDaoBean<Antecedent_familial, Antecedent_familial_Id>{
    
}
