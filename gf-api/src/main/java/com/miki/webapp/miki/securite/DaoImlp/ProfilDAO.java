/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.DaoImlp;


import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import com.miki.webapp.miki.securite.Dao.IProfilDAO;
import com.miki.webapp.miki.securite.entities.Profil;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class ProfilDAO extends BaseDaoBeanImpl<Profil, Long> implements IProfilDAO {

    public ProfilDAO() {
        super(Profil.class);
    }
    
}
