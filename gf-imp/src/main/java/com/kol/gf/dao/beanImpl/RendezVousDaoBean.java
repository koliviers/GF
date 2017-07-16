/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;


import com.kol.gf.dao.bean.RendezVousDaoBeanLocal;
import com.kol.gf.entities.RendezVous;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class RendezVousDaoBean extends BaseDaoBeanImpl<RendezVous, Long> implements RendezVousDaoBeanLocal{

    public RendezVousDaoBean() {
        
        super(RendezVous.class);
    }
  
    
}
