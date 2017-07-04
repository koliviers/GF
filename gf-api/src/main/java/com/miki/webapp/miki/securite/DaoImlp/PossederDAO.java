/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.DaoImlp;

import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import com.miki.webapp.miki.securite.Dao.IPossederDAO;
import com.miki.webapp.miki.securite.entities.Posseder;
import com.miki.webapp.miki.securite.entities.PossederId;
import javax.ejb.Stateless;



/**
 *
 * @author Mikel
 */
@Stateless
public class PossederDAO extends BaseDaoBeanImpl<Posseder, PossederId> implements IPossederDAO {

    public PossederDAO() {
        super(Posseder.class);
    }
    
}
