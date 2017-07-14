/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Antecedent_familial;
import com.kol.gf.entities.Antecedent_familial_Id;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class Antecedent_FamilialDAO extends BaseDaoBeanImpl<Antecedent_familial, Antecedent_familial_Id> implements IAntecedent_FamilialDAO{

    public Antecedent_FamilialDAO(){
        super(Antecedent_familial.class);
    }
    
}
