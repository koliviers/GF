/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.IExamenCliniqueDAO;
import com.kol.gf.entities.ExamenClinique;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class ExamenCliniqueDAO extends BaseDaoBeanImpl<ExamenClinique, Long> implements IExamenCliniqueDAO{

    public ExamenCliniqueDAO() {
        super(ExamenClinique.class);
    }
    
    
}
