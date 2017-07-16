/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.Habitude_alimentaireDaoBeanLocal;
import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class Habitude_alimentaireDaoBean extends BaseDaoBeanImpl<Habitude_alimentaire, Habitude_alimentaireId> implements Habitude_alimentaireDaoBeanLocal{
  
    public Habitude_alimentaireDaoBean(){
        
        super(Habitude_alimentaire.class);
    }
    
   
    
}
