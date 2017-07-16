/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ClasseTheurapetiqueDaoBeanLocal;
import com.kol.gf.entities.ClasseTherapeutique;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author koliviers
 */
@Stateless
public class ClasseTheurapetiqueDaoBean extends BaseDaoBeanImpl<ClasseTherapeutique, Long> implements ClasseTheurapetiqueDaoBeanLocal{
    
    
    public ClasseTheurapetiqueDaoBean(){
        super(ClasseTherapeutique.class);
    }
    
}
