/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.IDiagnostique;
import com.kol.gf.entities.Diagnostique;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class DiagnostiqueDAO extends BaseDaoBeanImpl<Diagnostique, Long> implements IDiagnostique{

    public DiagnostiqueDAO() {
        super(Diagnostique.class);
    }
    
}
