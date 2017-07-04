/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.DaoImlp;


import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import com.miki.webapp.miki.securite.Dao.IPosteDAO;
import com.miki.webapp.miki.securite.entities.Poste;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class PosteDAO extends BaseDaoBeanImpl<Poste, Long> implements IPosteDAO {

    public PosteDAO() {
        super(Poste.class);
    }
    
}
